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
package br.com.moonjava.flight.view.voo;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.moonjava.flight.model.base.Perfil;
import br.com.moonjava.flight.model.base.Usuario;
import br.com.moonjava.flight.util.AbstractFlightUI;

/**
 * @version 1.0 Aug 17, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class VooUI extends AbstractFlightUI {

  private final JPanel conteudo;
  private final Usuario usuarioLogado;
  private JPanel subConteudo;
  private JButton consultar;

  protected JButton status;
  protected JButton cadastrar;
  protected JButton atualizar;
  protected JButton deletar;
  protected final ResourceBundle bundle;

  public VooUI(JPanel conteudo, ResourceBundle bundle, Usuario usuarioLogado) {
    this.conteudo = conteudo;
    this.bundle = bundle;
    this.usuarioLogado = usuarioLogado;

    mainMenu();
  }

  @Override
  protected void mainMenu() {
    conteudo.removeAll();
    conteudo.validate();
    conteudo.repaint();

    subConteudo = new JPanel(null);
    JLabel titulo = new JLabel(bundle.getString("voo.titulo"));

    consultar = new JButton(bundle.getString("voo.consultar"));
    status = new JButton(bundle.getString("voo.status"));
    atualizar = new JButton(bundle.getString("voo.atualizar"));
    deletar = new JButton(bundle.getString("voo.deletar"));

    titulo.setFont(new Font("Arial Bold", 0, 14));

    titulo.setEnabled(false);
    deletar.setEnabled(false);
    atualizar.setEnabled(false);
    status.setEnabled(false);

    titulo.setBounds(0, 30, 100, 30);
    consultar.setBounds(0, 70, 200, 50);
    subConteudo.setBounds(200, 30, 960, 600);

    conteudo.add(titulo);
    conteudo.add(consultar);
    conteudo.add(status);
    conteudo.add(atualizar);
    conteudo.add(deletar);
    conteudo.add(subConteudo);

    // Adiciona cadastrar se for Supervisor
    if (usuarioLogado.getPerfil() == Perfil.SUPERVISOR) {
      cadastrar = new JButton(bundle.getString("voo.cadastrar"));

      cadastrar.setBounds(0, 140, 200, 50);
      status.setBounds(0, 210, 200, 50);
      atualizar.setBounds(0, 280, 200, 50);
      deletar.setBounds(0, 350, 200, 50);

      conteudo.add(cadastrar);
    } else {
      status.setBounds(0, 140, 200, 50);
      atualizar.setBounds(0, 210, 200, 50);
      deletar.setBounds(0, 280, 200, 50);
    }

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