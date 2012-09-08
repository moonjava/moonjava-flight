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

import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.moonjava.flight.util.AbstractFlightUI;
import br.com.moonjava.flight.util.ErrorSystem;

/**
 * @version 1.0 Sep 8, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class TransferirPassagemUI extends AbstractFlightUI {

  private final JPanel conteudo;
  private final ResourceBundle bundle;
  private JTextField _bilhete;
  private JButton consultar;
  private JButton transferir;

  public TransferirPassagemUI(JPanel conteudo, ResourceBundle bundle) {
    this.conteudo = conteudo;
    this.bundle = bundle;

    refresh();
    mainMenu();
  }

  @Override
  protected JPanel getConteudo() {
    return conteudo;
  }

  @Override
  protected void mainMenu() {
    Image image = null;
    InputStream stream = getClass().getResourceAsStream("/img/search.png");
    try {
      image = ImageIO.read(stream);
    } catch (IllegalArgumentException e) {
      ErrorSystem.addException(e, bundle);
    } catch (IOException e) {
      ErrorSystem.addException(e, bundle);
    }
    Icon _imagem = new ImageIcon(image);
    JLabel imagem = new JLabel(_imagem);

    JLabel filtroBilhete = new JLabel(bundle.getString("cancelar.passagem.titulo.filtroBilhete"));

    _bilhete = new JTextField();

    consultar = new JButton(bundle.getString("cancelar.passagem.solicitarCancelamento"));
    transferir = new JButton(bundle.getString("cancelar.passagem.cancelar"));

    filtroBilhete.setBounds(130, 45, 200, 30);

    _bilhete.setBounds(130, 70, 80, 30);
    consultar.setBounds(320, 70, 200, 30);

    transferir.setBounds(320, 475, 200, 30);

    conteudo.add(filtroBilhete);
    conteudo.add(_bilhete);
    conteudo.add(consultar);

    repaint();
  }

  protected void addConsultarListener(ActionListener a) {
    consultar.addActionListener(a);
  }

  protected void addTransferirListener(ActionListener a) {
    transferir.addActionListener(a);
  }

  protected void addTransferirButton() {

  }

  protected void messageOK() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("cancelar.passagem.cancelado"),
        bundle.getString("cancelar.passagem.titulo"),
        JOptionPane.INFORMATION_MESSAGE);

    refresh();
  }

}