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

import javax.swing.JPanel;

import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.FlightFocusLostListener;
import br.com.moonjava.flight.util.FocusTextField;
import br.com.moonjava.flight.util.VerifierString;
import br.com.moonjava.flight.view.passagem.CancelarPassagemUI;

/**
 * @version 1.0 Aug 31, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class CancelarPassagemController extends CancelarPassagemUI {

  public CancelarPassagemController(JPanel conteudo, ResourceBundle bundle) {
    super(conteudo, bundle);

    addFocusListener(new FocusTextField());
    addFocusBancoListener(new FocusBancoHandler());
    addFocusAgenciaListener(new FocusAgenciaHandler());
    addFocusContaListener(new FocusContaHandler());
    addFocusCpfListener(new FocusCpfHandler());
    addSolicitarCancelamentoListener(new SolicitarCancelamentoHandler());
    addOKListener(new OKHandler());
    addEfetuarCancelamentoListener(new EfetuarCancelamentoHandler());
  }

  private class FocusBancoHandler extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      String banco = getParameters().stringParam("banco");
      String defaultText = getDefaultTexts().stringParam("banco");

      if (!banco.isEmpty() && !banco.equals(defaultText)) {

        try {
          int num = Integer.parseInt(banco);
          if (num <= 0) {
            throw new NumberFormatException();
          }
          removeImageBancoParseException();
        } catch (NumberFormatException e2) {
          addImageBancoParseException();
        }

      }
    }
  }

  private class FocusAgenciaHandler extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      String agencia = getParameters().stringParam("agencia");
      String defaultText = getDefaultTexts().stringParam("agencia");

      if (!agencia.isEmpty() && !agencia.equals(defaultText)) {

        try {
          int num = Integer.parseInt(agencia);
          if (num <= 0) {
            throw new NumberFormatException();
          }
          removeImageAgenciaParseException();
        } catch (NumberFormatException e2) {
          addImageAgenciaParseException();
        }

      }
    }
  }

  private class FocusContaHandler extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      String conta = getParameters().stringParam("conta");
      String defaultText = getDefaultTexts().stringParam("conta");

      if (!conta.isEmpty() && !conta.equals(defaultText)) {

        try {
          int num = Integer.parseInt(conta);
          if (num <= 0) {
            throw new NumberFormatException();
          }
          removeImageContaParseException();
        } catch (NumberFormatException e2) {
          addImageContaParseException();
        }

      }
    }
  }

  private class FocusCpfHandler extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      String cpf = getParameters().stringParam("cpf");
      if (!VerifierString.containsSpace(cpf)) {
        try {
          CPF.parse(cpf);
          addImageCpfValido();
        } catch (Exception e1) {
          addImageCpfInvalido();
        }
      }
    }
  }

  private class SolicitarCancelamentoHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      addCalcularPassagemButton();
    }
  }

  private class OKHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      addEfetuarCancelamentoButton();
    }
  }

  private class EfetuarCancelamentoHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      messageOK();
    }
  }

}