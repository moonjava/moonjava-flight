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
package br.com.moonjava.flight.view.aeronave;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.moonjava.flight.util.AbstractFlightUI;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class AeronaveUI extends AbstractFlightUI {

  private final JPanel conteudo;
  protected final ResourceBundle bundle;

  private JPanel subConteudo;
  private JButton consultar;
  private JButton cadastrar;
  protected JButton atualizar;
  protected JButton deletar;

  public AeronaveUI(JPanel conteudo, ResourceBundle bundle) {
    this.conteudo = conteudo;
    this.bundle = bundle;

    mainMenu();
  }

  @Override
  protected void mainMenu() {
    conteudo.removeAll();
    conteudo.validate();
    conteudo.repaint();

    subConteudo = new JPanel(null);
    JLabel titulo = new JLabel(bundle.getString("aeronave.titulo"));

    consultar = new JButton(bundle.getString("aeronave.consultar"));
    cadastrar = new JButton(bundle.getString("aeronave.cadastrar"));
    atualizar = new JButton(bundle.getString("aeronave.atualizar"));
    deletar = new JButton(bundle.getString("aeronave.deletar"));

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

    conteudo.add(titulo);
    conteudo.add(consultar);
    conteudo.add(cadastrar);
    conteudo.add(atualizar);
    conteudo.add(deletar);
    conteudo.add(subConteudo);
  }

  @Override
  protected JPanel getConteudo() {
    return subConteudo;
  }

  protected void addConsultarListener(ActionListener a) {
    consultar.addActionListener(a);
  }

  protected void addCadastrarListener(ActionListener a) {
    cadastrar.addActionListener(a);
  }

}