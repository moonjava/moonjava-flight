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
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.joda.time.LocalDate;

import br.com.moonjava.flight.model.base.PessoaFisica;
import br.com.moonjava.flight.model.base.PessoaFisicaModel;
import br.com.moonjava.flight.model.base.Usuario;
import br.com.moonjava.flight.model.base.UsuarioModel;
import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.CPFInvalidException;
import br.com.moonjava.flight.util.EncryptPassword;
import br.com.moonjava.flight.util.FlightFocusLostListener;
import br.com.moonjava.flight.util.FocusTextField;
import br.com.moonjava.flight.util.FormatDateTime;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.util.VerifierString;
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
    addFocusDataListener(new FocusDataHandler());
    addFocusCpfListener(new FocusCpfHandler());
    addFocusTelResListener(new FocusTelResHander());
    addFocusTelCelListener(new FocusTelCelHander());
    addCadastrarListener(new CadastrarHandler());
  }

  private class FocusDataHandler extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      try {
        RequestParamWrapper request = getParameters();
        String nascimento = request.stringParam("nascimento");

        if (VerifierString.isBirthDay(nascimento, bundle)) {
          addImageNascimentoValid();
        } else {
          addImageNascimentoInvalid();
        }
      } catch (Exception e2) {
        addImageNascimentoInvalid();
      }
    }
  }

  private class FocusCpfHandler extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      try {
        CPF.parse(getCpf().getText());
        addImageCpfValido();

      } catch (CPFInvalidException e2) {
        addImageCpfInvalido();
      }
    }
  }

  private class FocusTelResHander extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      String tel = getTelResidencial().getText();
      if (!tel.isEmpty() && !tel.equals(getTextTelResidencial())) {
        try {
          Integer.parseInt(tel);
          messageTelResidencialOk();
        } catch (Exception e2) {
          messageTelResidencialParseExecption();
        }
      }
    }
  }

  private class FocusTelCelHander extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      String tel = getTelCelular().getText();
      if (!tel.isEmpty() && !tel.equals(getTextTelCelular())) {
        try {
          Integer.parseInt(tel);
          messageTelCelularOk();
        } catch (Exception e2) {
          messageTelCelularParseExecption();
        }
      }
    }
  }

  private class CadastrarHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
      String maskNascimento = "  /  /    ";
      String maskCpf = "   .   .   -  ";
      EncryptPassword pass = new EncryptPassword();

      RequestParamWrapper request = getParameters();
      String nome = request.stringParam("nome");
      String sobrenome = request.stringParam("sobrenome");
      String nascimento = request.stringParam("nascimento");
      String cpf = request.stringParam("cpf");
      String rg = request.stringParam("rg");
      String endereco = request.stringParam("endereco");
      String telResidencial = request.stringParam("telResidencial");
      String telCelular = request.stringParam("telCelular");
      String login = request.stringParam("login");
      String senha = request.stringParam("senha");
      request.set("senha", pass.toEncryptMD5(senha));

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

        UsuarioModel usuarioModel = new UsuarioModel();
        Usuario usuario = usuarioModel.consultarPorCpf(_cpf);

        if (usuario != null) {
          messageUsuarioExistente();
        } else {
          PessoaFisicaModel pessoaFisicaModel = new PessoaFisicaModel();
          PessoaFisica pf = pessoaFisicaModel.consultarPorCPF(_cpf);

          // Cria uma PF
          if (pf == null) {
            int _telResidencial = 0;
            int _telCelular = 0;
            LocalDate date = FormatDateTime.parseToLocalDate(nascimento, getCountry());

            try {
              _telResidencial = Integer.parseInt(telResidencial);
              _telCelular = Integer.parseInt(telCelular);
            } catch (Exception e) {
              messageTelResidencialParseExecption();
              return;
            }

            request.set("nascimento", date);
            request.set("cpf", _cpf.getDigito());
            request.set("telResidencial", _telResidencial);
            request.set("telCelular", _telCelular);

            PessoaFisica pojo = new PessoaFisicaCreate(request).createInstance();
            boolean executed = pessoaFisicaModel.criar(pojo);

            if (executed) {
              PessoaFisica pessoa = pessoaFisicaModel.consultarPorCPF(pojo.getCpf());
              request.set("pessoaFisica", pessoa.getId());
            } else {
              messageFailed();
              return;
            }

            // Utiliza a PF existente (uma vez cliente)
          } else {
            request.set("pessoaFisica", pf.getId());
          }
          Usuario pojo = new UsuarioCreate(request).createInstance();
          new UsuarioModel().criar(pojo);

          messageOK();
          refresh();
        }
      }
    }
  }

}