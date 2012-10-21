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
package br.com.moonjava.flight.controller.financeiro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.util.ResourceBundle;

import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.FlightFocusLostListener;
import br.com.moonjava.flight.util.FocusTextField;
import br.com.moonjava.flight.util.VerifierString;
import br.com.moonjava.flight.view.passagem.ChequeUI;

/**
 * @version 1.0 Sep 7, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class ChequeController extends ChequeUI {

  public ChequeController(ResourceBundle bundle, double valorTotal) {
    super(bundle, valorTotal);

    // add listeners
    addFocusListener(new FocusTextField());
    addFocusNumeroListener(new FocusNumeroHandler());
    addFocusBancoListener(new FocusBancoHandler());
    addFocusAgenciaListener(new FocusAgenciaHandler());
    addFocusContaListener(new FocusContaHandler());
    addOkListener(new OkHandler());
    addFocusCpfListener(new FocusCpfHandler());

    showAll();
  }

  private class FocusNumeroHandler extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      String numero = getParameters().stringParam("numero");
      String defaultText = getDefaultTexts().stringParam("numero");

      if (!numero.isEmpty() && !numero.equals(defaultText)) {

        try {
          int num = Integer.parseInt(numero);
          if (num <= 0) {
            throw new NumberFormatException();
          }
          removeImageNumeroParseException();
        } catch (NumberFormatException e2) {
          addImageNumeroParseException();
        }

      }
    }
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

  private class OkHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      dispose();
      setParameterValid(true);
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

}