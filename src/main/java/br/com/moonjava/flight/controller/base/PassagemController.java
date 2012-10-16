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
import java.util.ResourceBundle;

import javax.swing.JPanel;

import br.com.moonjava.flight.view.passagem.PassagemUI;

/**
 * @version 1.0 Oct 15, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class PassagemController extends PassagemUI {

  public PassagemController(JPanel conteudo, ResourceBundle bundle) {
    super(conteudo, bundle);

    addVenderListener(new VenderHandler());
    addCancelarListener(new CancelarHandler());
    addTransferirListener(new TransferirHandler());
  }

  /*
   * Ação para vender 
   */
  private class VenderHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      new ConsultarVooController(getConteudo(), bundle);
    }
  }

  /*
   * Ação para cancelar 
   */
  private class CancelarHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      new CancelarPassagemController(getConteudo(), bundle);
    }
  }

  /*
   * Ação para transferir 
   */
  private class TransferirHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      new TransferirPassagemController(getConteudo(), bundle);
    }
  }

}