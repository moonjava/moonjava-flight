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

import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.FocusTextField;
import br.com.moonjava.flight.view.passagem.ChequeUI;

/**
 * @version 1.0 Sep 7, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class ChequeController extends ChequeUI {

  public ChequeController(ResourceBundle bundle) {
    super(bundle);

    addFocusListener(new FocusTextField());
    addOkListener(new OkHandler());
    addFocusCpfListener(new FocusCpfHandler());
  }

  private class OkHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      dispose();
      setParameterValid(true);
    }
  }

  private class FocusCpfHandler implements FocusListener {
    @Override
    public void focusGained(FocusEvent e) {
      String cpf = getParameters().stringParam("cpf");
      try {
        CPF.parse(cpf);
        addImageCpfValido();
      } catch (Exception e1) {
        addImageCpfInvalido();
      }
    }
    @Override
    public void focusLost(FocusEvent e) {
    }
  }

}