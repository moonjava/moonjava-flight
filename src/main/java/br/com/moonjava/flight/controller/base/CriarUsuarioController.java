/*
 * Copyright 2012 MoonJava LTDA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.moonjava.flight.controller.base;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.joda.time.LocalDate;

import br.com.moonjava.flight.dao.base.PessoaFisicaDAO;
import br.com.moonjava.flight.dao.base.UsuarioDAO;
import br.com.moonjava.flight.model.base.PessoaFisica;
import br.com.moonjava.flight.model.base.Usuario;
import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.CPFInvalidException;
import br.com.moonjava.flight.util.EncryptPassword;
import br.com.moonjava.flight.util.FocusTextField;
import br.com.moonjava.flight.util.FormatDateTime;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.view.usuario.CriarUsuarioUI;

/**
 * @version 1.0 Aug 30, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class CriarUsuarioController extends CriarUsuarioUI {

  public CriarUsuarioController(JPanel conteudo,
                                ResourceBundle bundle,
                                JButton atualizar,
                                JButton deletar) {
    super(conteudo, bundle, atualizar, deletar);

    addFocusListener(new FocusTextField());
    addFocusCpfListener(new FocusCpfHandler());
    addFocusTelResListener(new FocusTelResHander());
    addFocusTelCelListener(new FocusTelCelHander());
    addCadastrarListener(new CadastrarHandler());
  }

  private class FocusCpfHandler implements FocusListener {

    @Override
    public void focusLost(FocusEvent e) {
      try {
        CPF.parse(getCpf().getText());
        addImageCpfValido();

      } catch (CPFInvalidException e2) {
        addImageCpfInvalido();
      }
    }

    @Override
    public void focusGained(FocusEvent e) {
    }
  }

  private class FocusTelResHander implements FocusListener {

    @Override
    public void focusLost(FocusEvent e) {
      String tel = getTelResidencial().getText();
      if (!tel.isEmpty() && !tel.equals(getTextTelResidencial())) {
        try {
          Integer.parseInt(tel);
        } catch (Exception e2) {
          messageTelParseExecption();
        }
      }
    }
    @Override
    public void focusGained(FocusEvent e) {
    }
  }

  private class FocusTelCelHander implements FocusListener {

    @Override
    public void focusLost(FocusEvent e) {
      String tel = getTelCelular().getText();
      if (!tel.isEmpty() && !tel.equals(getTextTelCelular())) {
        try {
          Integer.parseInt(tel);
        } catch (Exception e2) {
          messageTelParseExecption();
        }
      }
    }
    @Override
    public void focusGained(FocusEvent e) {
    }
  }

  private class CadastrarHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
      String maskNascimento = "  /  /    ";
      String maskCpf = "   .   .   -  ";
      EncryptPassword pass = new EncryptPassword();

      RequestParamWrapper param = getParameters();
      String nome = param.stringParam("nome");
      String sobrenome = param.stringParam("sobrenome");
      String nascimento = param.stringParam("nascimento");
      String cpf = param.stringParam("cpf");
      String rg = param.stringParam("rg");
      String endereco = param.stringParam("endereco");
      String telResidencial = param.stringParam("telResidencial");
      String telCelular = param.stringParam("telCelular");
      String login = param.stringParam("login");
      String senha = param.stringParam("senha");
      param.set("senha", pass.toEncryptMD5(senha));

      RequestParamWrapper text = getTexts();
      String textNome = text.stringParam("nome");
      String textSobrenome = text.stringParam("sobrenome");
      String textRg = text.stringParam("rg");
      String textEndereco = text.stringParam("endereco");
      String textTelResidencial = text.stringParam("telResidencial");
      String textTelCelular = text.stringParam("telCelular");
      String textLogin = text.stringParam("login");

      // Algo foi digitado
      if (!nome.equals(textNome) && !sobrenome.equals(textSobrenome) &&
          !nascimento.equals(maskNascimento) && !cpf.equals(maskCpf) &&
          !rg.equals(textRg) && !endereco.equals(textEndereco) &&
          !telResidencial.equals(textTelResidencial) && !telCelular.equals(textTelCelular) &&
          !login.equals(textLogin) &&

          !nome.isEmpty() && !sobrenome.isEmpty() && !rg.isEmpty() && !endereco.isEmpty() &&
          !telResidencial.isEmpty() && !telCelular.isEmpty() && !login.isEmpty() &&
          !senha.isEmpty()) {

        // CPF continua invalido
        CPF _cpf = null;
        try {
          _cpf = CPF.parse(cpf);
        } catch (Exception e) {
          messageCpfInvalidExecption();
          return;
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.consultarPorCpf(_cpf);

        if (usuario != null) {
          messageUsuarioExistente();
        } else {
          PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
          PessoaFisica pf = pessoaFisicaDAO.consultarPorCpf(_cpf);

          // Cria uma PF
          if (pf == null) {
            int _telResidencial = 0;
            int _telCelular = 0;
            LocalDate date = FormatDateTime.parseToLocalDate(nascimento, getCountry());

            try {
              _telResidencial = Integer.parseInt(telResidencial);
              _telCelular = Integer.parseInt(telCelular);
            } catch (Exception e) {
              messageTelParseExecption();
              return;
            }

            param.set("nascimento", date);
            param.set("cpf", _cpf.getDigito());
            param.set("telResidencial", _telResidencial);
            param.set("telCelular", _telCelular);

            PessoaFisica pojo = new PessoaFisicaControlCreate(param).createInstance();
            boolean executed = pessoaFisicaDAO.criar(pojo);

            if (executed) {
              PessoaFisica pessoa = pessoaFisicaDAO.consultarPorCpf(pojo.getCpf());
              param.set("pessoaFisica", pessoa.getId());
            } else {
              messageFailed();
              return;
            }

            // Utiliza a PF existente (uma vez cliente)
          } else {
            param.set("pessoaFisica", pf.getId());
          }
          Usuario pojo = new UsuarioControlCreate(param).createInstance();
          usuarioDAO.criar(pojo);

          messageOK();
          refresh();
        }
      }
    }
  }

}