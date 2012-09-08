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
import java.text.AttributedString;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import br.com.moonjava.flight.controller.financeiro.CartaoController;
import br.com.moonjava.flight.controller.financeiro.ChequeController;
import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.FlightFocusLostListener;
import br.com.moonjava.flight.util.FocusTextField;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.util.VerifierString;
import br.com.moonjava.flight.view.passagem.PrintFileToPrinter;
import br.com.moonjava.flight.view.passagem.VenderPassagemUI;

/**
 * @version 1.0 Aug 31, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class VenderPassagemController extends VenderPassagemUI {

  public VenderPassagemController(JPanel conteudo, ResourceBundle bundle) {
    super(conteudo, bundle);

    // add listeners
    addFocusListener(new FocusTextField());
    addFocusTelResidencialListener(new FocusTelResidencialHandler());
    addFocusTelCelularListener(new FocusTelCelularHandler());
    addFocusCpfListener(new FocusCpfHandler());
    addFocusDataDeNascimentoListener(new FocusDataDeNascimentoHandler());
    addPagamentoChangeListener(new PagamentoChangeHandler());
    addQuantidadeOKListener(new QuantidadeOKHandler());
    addSolicitarCompraListener(new SolicitarCompraHandler());
    addConcluirListener(new ConcluirHandler());
  }

  private class FocusTelResidencialHandler extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      String tel = getParameters().stringParam("telResidencial");
      String defaultText = getDefaultTexts().stringParam("telResidencial");

      if (!tel.isEmpty() && !tel.equals(defaultText)) {

        try {
          int num = Integer.parseInt(tel);
          if (num <= 0) {
            throw new NumberFormatException();
          }
          removeImageTelResidencialParseException();
        } catch (NumberFormatException e2) {
          addImageTelResidencialParseException();
        }

      }
    }
  }

  private class FocusTelCelularHandler extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      String tel = getParameters().stringParam("telCelular");
      String defaultText = getDefaultTexts().stringParam("telCelular");

      if (!tel.isEmpty() && !tel.equals(defaultText)) {

        try {
          int num = Integer.parseInt(tel);
          if (num <= 0) {
            throw new NumberFormatException();
          }
          removeImageTelCelularParseException();
        } catch (NumberFormatException e2) {
          addImageTelCelularParseException();
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

  private class FocusDataDeNascimentoHandler extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      String dataDeNascimento = getParameters().stringParam("dataDeNascimento");
      if (!VerifierString.containsSpace(dataDeNascimento)) {
        if (VerifierString.isBirthDay(dataDeNascimento, bundle)) {
          addImageBirthDayValid();
        } else {
          addImageBirthDayInvalid();
        }
      }
    }
  }

  private class PagamentoChangeHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      RequestParamWrapper request = getParameters();
      int pagamento = request.intParam("pagamentoIndex");

      if (pagamento == 1) {
        ChequeController chequeController = new ChequeController(bundle);
        if (chequeController.isParemeterValid()) {
          addConcluirButton();
        }
      } else {
        CartaoController cartaoController = new CartaoController(bundle);
        if (cartaoController.isParameterValid()) {
          addConcluirButton();
        }
      }

    }
  }

  private class QuantidadeOKHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      addSolicitarCompraButton();
    }
  }

  private class SolicitarCompraHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      addForm();
    }
  }

  private class ConcluirHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      /** Location of a file to print **/
      String fileName = "abc.txt";

      /** Read the text content from this location **/
      String mText = PrintFileToPrinter.readContentFromFileToPrint(fileName);

      /** Create an AttributedString object from the text read */
      PrintFileToPrinter.myStyledText = new AttributedString(mText);

      PrintFileToPrinter.printToPrinter();
    }
  }

}