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
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import br.com.moonjava.flight.dao.base.PessoaFisicaDAO;
import br.com.moonjava.flight.dao.base.UsuarioDAO;
import br.com.moonjava.flight.model.base.PessoaFisica;
import br.com.moonjava.flight.model.base.Usuario;
import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.CPFInvalidException;
import br.com.moonjava.flight.util.FocusTextField;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.view.usuario.AtualizarUsuarioUI;

/**
 * @version 1.0 Sep 3, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class AtualizarUsuarioController extends AtualizarUsuarioUI {

  private static final AtualizarUsuarioController ui = new AtualizarUsuarioController();
  private boolean result;
  private List<Usuario> list;
  private JTable tabela;

  private Usuario pojo;
  private PessoaFisica pojoPF;

  public AtualizarUsuarioController() {
  }

  public static AtualizarUsuarioController getInstance() {
    return ui;
  }

  public void setAttributes(JTable tabela, JPanel subConteudo,
      ResourceBundle bundle, JButton atualizar, JButton deletar) {

    this.tabela = tabela;

    setAttributes(subConteudo, bundle, atualizar, deletar);
    addFocusListener(new FocusTextField());
    addFocusCpfListener(new FocusCpfHandler());
    addFocusTelResListener(new FocusTelResHander());
    addFocusTelCelListener(new FocusTelCelHander());
    addAtualizarListener(new AtualizarHandler());
    addEnviarListener(new EnviarHandler());
  }

  public void setResult(boolean result) {
    this.result = result;
  }

  public void setList(List<Usuario> list) {
    this.list = list;
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

  private class AtualizarHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      disableButtons();
      refresh();
      showAll();
      if (!result) {
        result = true;
        int[] rows = tabela.getSelectedRows();

        if (rows.length == 1) {
          pojo = list.get(rows[0]);
          pojoPF = pojo.getPessoaFisica();

          setParameters(pojoPF, pojo);
          showAll();
        } else {
          messageFailed();
          refresh();
        }
      }
    }
  }

  private class EnviarHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      RequestParamWrapper requestPf = getParametersPessoaFisica();
      RequestParamWrapper requestUsu = getParametersUsuario();

      PessoaFisica pFisica = new PessoaFisicaControlUpdate(requestPf)
          .createInstance();
      new PessoaFisicaDAO().atualizar(pFisica);

      Usuario usuario = new UsuarioControlUpdate(requestUsu)
          .createInstance();
      new UsuarioDAO().atualizar(usuario);

      messageOK();
      refresh();
    }
  }
}