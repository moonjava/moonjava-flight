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
package br.com.moonjava.flight.view.usuario;

import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @version 1.0 Aug 21, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class UsuarioUI {

  private final JPanel conteudo;
  private final ResourceBundle bundle;

  public UsuarioUI(JPanel conteudo, ResourceBundle bundle) {
    this.conteudo = conteudo;
    this.bundle = bundle;

    mainMenu();
  }

  private void mainMenu() {
    conteudo.removeAll();
    conteudo.validate();
    conteudo.repaint();

    JLabel titulo = new JLabel(bundle.getString("usuario.titulo"));
    JButton consultar = new JButton(bundle.getString("usuario.consultar"));
    JButton cadastrar = new JButton(bundle.getString("usuario.cadastrar"));
    JButton atualizar = new JButton(bundle.getString("usuario.atualizar"));
    JButton deletar = new JButton(bundle.getString("usuario.deletar"));
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

    cadastrar.addActionListener(new CriarUsuarioUI(subConteudo, bundle, atualizar, deletar));

    conteudo.add(titulo);
    conteudo.add(consultar);
    conteudo.add(cadastrar);
    conteudo.add(atualizar);
    conteudo.add(deletar);
    conteudo.add(subConteudo);
  }

}