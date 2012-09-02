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
package br.com.moonjava.flight.view.passagem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.moonjava.flight.controller.base.CancelarPassagemController;
import br.com.moonjava.flight.controller.base.TransferirPassagemController;
import br.com.moonjava.flight.controller.base.VenderPassagemController;

/**
 * @version 1.0 Aug 31, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class PassagemUI {

  private final JPanel conteudo;
  private final ResourceBundle bundle;
  private JPanel subConteudo;

  private JButton vender;
  private JButton cancelar;
  private JButton transferir;

  public PassagemUI(JPanel conteudo, ResourceBundle bundle) {
    this.conteudo = conteudo;
    this.bundle = bundle;

    mainMenu();
  }

  private void mainMenu() {
    conteudo.removeAll();
    conteudo.validate();
    conteudo.repaint();

    subConteudo = new JPanel(null);
    JLabel titulo = new JLabel(bundle.getString("passagem.titulo"));

    vender = new JButton(bundle.getString("passagem.vender"));
    cancelar = new JButton(bundle.getString("passagem.cancelar"));
    transferir = new JButton(bundle.getString("passagem.transferir"));

    titulo.setFont(new Font("Arial Bold", 0, 14));

    titulo.setEnabled(false);

    titulo.setBounds(0, 30, 100, 30);
    vender.setBounds(0, 70, 200, 50);
    cancelar.setBounds(0, 140, 200, 50);
    transferir.setBounds(0, 210, 200, 50);
    subConteudo.setBounds(200, 30, 960, 600);

    conteudo.add(titulo);
    conteudo.add(vender);
    conteudo.add(cancelar);
    conteudo.add(transferir);
    conteudo.add(subConteudo);

    vender.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new VenderPassagemController(subConteudo, bundle);
      }
    });

    cancelar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new CancelarPassagemController(subConteudo, bundle);
      }
    });

    transferir.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new TransferirPassagemController(subConteudo, bundle);
      }
    });
  }

}