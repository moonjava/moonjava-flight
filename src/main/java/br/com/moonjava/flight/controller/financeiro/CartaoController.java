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

import org.joda.time.LocalDate;

import br.com.moonjava.flight.model.financeiro.Bandeira;
import br.com.moonjava.flight.model.financeiro.Cartao;
import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.FlightFocusLostListener;
import br.com.moonjava.flight.util.FocusTextField;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.util.VerifierString;
import br.com.moonjava.flight.view.passagem.CartaoUI;

/**
 * @version 1.0 Sep 7, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class CartaoController extends CartaoUI {

  public CartaoController(ResourceBundle bundle, double valorTotal) {
    super(bundle, valorTotal);

    // add listeners
    addFocusListener(new FocusTextField());
    addFocusDataListener(new FocusDataHandler());
    addFocusNumeroListener(new FocusNumeroHandler());
    addFocusCodigoListener(new FocusCodigoHandler());
    addOkListener(new OkHandler());
    addFocusCpfListener(new FocusCpfHandler());

    showAll();
  }

  private class FocusDataHandler extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      String validade = getParameters().stringParam("validade");
      if (!VerifierString.containsSpace(validade)) {
        if (VerifierString.isValidate(validade)) {
          addImageCardValid();
        } else {
          addImageCardInvalid();
        }
      }
    }
  }

  private class FocusNumeroHandler extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      String numero = getParameters().stringParam("numero");
      String defaultText = getDefaultTexts().stringParam("numero");

      if (!numero.isEmpty() && !numero.equals(defaultText)) {

        try {
          long num = Long.parseLong(numero);
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

  private class FocusCodigoHandler extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      String codigo = getParameters().stringParam("codigo");
      String defaultText = getDefaultTexts().stringParam("codigo");

      if (!codigo.isEmpty() && !codigo.equals(defaultText)) {

        try {
          int num = Integer.parseInt(codigo);
          if (num <= 0) {
            throw new NumberFormatException();
          }
          removeImageCodigoParseException();
        } catch (NumberFormatException e2) {
          addImageCodigoParseException();
        }

      }
    }
  }

  private class OkHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      dispose();
      try {
        RequestParamWrapper request = getParameters();
        request.set("numero", Long.parseLong(request.stringParam("numero")));

        String year = request.stringParam("validade").substring(3, 7);
        String month = request.stringParam("validade").substring(0, 2);

        LocalDate date = new LocalDate()
            .withYear(Integer.parseInt(year))
            .withMonthOfYear(Integer.parseInt(month));

        request.set("validade", date);
        request.set("bandeira", request.enumParam(Bandeira.class, "bandeira"));
        request.set("cpf", CPF.parse(request.stringParam("cpf")));
        request.set("codigo", Integer.parseInt(request.stringParam("codigo")));

        Cartao cartao = null;
        cartao = new CartaoCreate(request).createInstance();
        boolean created = new CartaoControl().creditar(cartao);
        if (created) {
          setParameterValid(created);
        } else {
          addMessageFailed();
        }
      } catch (NumberFormatException e2) {
        addMessageFailed();
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

}