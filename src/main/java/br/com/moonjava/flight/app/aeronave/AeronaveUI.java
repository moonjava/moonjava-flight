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
package br.com.moonjava.flight.app.aeronave;

import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class AeronaveUI {

  private final JPanel conteudo;
  private final ResourceBundle bundle;

  public AeronaveUI(JPanel conteudo, ResourceBundle bundle) {
    this.conteudo = conteudo;
    this.bundle = bundle;

    mainMenu();
  }

  private void mainMenu() {
    conteudo.removeAll();
    conteudo.validate();
    conteudo.repaint();

    JLabel titulo = new JLabel(bundle.getString("aeronave.titulo"));
    JButton consultar = new JButton(bundle.getString("aeronave.consultar"));
    JButton cadastrar = new JButton(bundle.getString("aeronave.cadastrar"));
    JButton atualizar = new JButton(bundle.getString("aeronave.atualizar"));
    JButton deletar = new JButton(bundle.getString("aeronave.deletar"));
    JPanel subConteudo = new JPanel(null);

    titulo.setFont(new Font("Arial Bold", 0, 14));

    titulo.setEnabled(false);
    deletar.setEnabled(false);
    atualizar.setEnabled(false);

    titulo.setBounds(0, 30, 100, 30);
    consultar.setBounds(0, 70, 200, 50);
    cadastrar.setBounds(0, 140, 200, 50);
    atualizar.setBounds(0, 210, 200, 50);
    deletar.setBounds(0, 280, 200, 50);
    subConteudo.setBounds(200, 30, 960, 600);

    consultar.addActionListener(new ConsultarAeronaveUI(subConteudo, bundle, atualizar, deletar));
    cadastrar.addActionListener(new CriarAeronaveUI(subConteudo, bundle, atualizar, deletar));

    conteudo.add(titulo);
    conteudo.add(consultar);
    conteudo.add(cadastrar);
    conteudo.add(atualizar);
    conteudo.add(deletar);
    conteudo.add(subConteudo);
  }

}