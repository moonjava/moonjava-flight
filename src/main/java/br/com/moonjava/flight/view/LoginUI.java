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
package br.com.moonjava.flight.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.moonjava.flight.util.ErrorSystem;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Sep 6, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class LoginUI {

  private JFrame conteudo;
  protected final ResourceBundle bundle;

  private JLabel tituloLogin;
  private JLabel tituloSenha;
  private JLabel alerta;

  private JTextField login;
  private JPasswordField senha;
  private JButton entrar;

  public LoginUI(ResourceBundle bundle) {
    this.bundle = bundle;
    window();
    showAll();
  }

  private void window() {
    conteudo = new JFrame("Flight :: Flight");
    conteudo.setLayout(new BorderLayout());
    conteudo.getContentPane().setBackground(Color.WHITE);

    JPanel panel1 = new JPanel();
    panel1.setLayout(new GridLayout(6, 1, 5, 5));
    panel1.setBackground(Color.WHITE);
    panel1.setBorder(BorderFactory.createTitledBorder(bundle.getString("login.titulo")));

    JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout(40, 40, 40));
    panel2.setBackground(Color.WHITE);

    JPanel panel3 = new JPanel();
    panel3.setLayout(new FlowLayout(40, 40, 40));
    panel3.setBackground(Color.WHITE);

    JPanel panel4 = new JPanel();
    panel4.setLayout(new FlowLayout(40, 40, 40));
    panel4.setBackground(Color.WHITE);

    JPanel panel5 = new JPanel();
    panel5.setLayout(new GridLayout(1, 2, 5, 5));
    panel5.setBackground(Color.WHITE);

    tituloLogin = new JLabel(bundle.getString("login.titulo.usuario"));
    tituloSenha = new JLabel(bundle.getString("login.titulo.senha"));
    login = new JTextField(20);
    senha = new JPasswordField(20);
    entrar = new JButton(bundle.getString("login.botao.entrar"));
    JLabel spaceBlank = new JLabel();

    alerta = new JLabel();
    alerta.setForeground(Color.RED);

    InputStream stream = getClass().getResourceAsStream("/img/aviao_login.svg.png");
    Image image = null;
    try {
      image = ImageIO.read(stream);
    } catch (IOException e) {
      ErrorSystem.addException(e, bundle);
    }
    ImageIcon icon = new ImageIcon(image);
    JLabel imagem = new JLabel(icon);
    JLabel rodape = new JLabel(bundle.getString("rodape"));

    panel5.add(spaceBlank);
    panel5.add(entrar);

    panel1.add(tituloLogin);
    panel1.add(login);
    panel1.add(tituloSenha);
    panel1.add(senha);
    panel1.add(panel5);

    panel2.add(panel1);
    panel1.add(alerta);
    panel3.add(imagem);
    panel4.add(rodape);

    conteudo.add(panel2, BorderLayout.EAST);
    conteudo.add(panel3, BorderLayout.WEST);
    conteudo.add(panel4, BorderLayout.SOUTH);
  }

  private void showAll() {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int width = dimension.width;
    int height = dimension.height;
    int frameWidth = 765;
    int frameHeight = 497;

    conteudo.setLocation((width / 2) - (frameWidth / 2), (height / 2) - (frameHeight / 2));
    conteudo.setSize(frameWidth, frameHeight);
    conteudo.setResizable(false);
    conteudo.setVisible(true);
    conteudo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  protected void addLogarListener(ActionListener a) {
    if (login.getText() != null && (String.valueOf(senha.getPassword()) != null)) {
      entrar.addActionListener(a);
    }
  }

  protected void addLogarKeyListener(KeyListener a) {
    if (login.getText() != null && (String.valueOf(senha.getPassword()) != null)) {
      senha.addKeyListener(a);
    }
  }

  protected void incorrectLoginMessage() {
    alerta.setText(bundle.getString("login.incorreto.messagem"));
  }

  protected RequestParamWrapper getLogin() {
    RequestParamWrapper request = new RequestParamWrapper();
    request.set("login", login.getText());
    request.set("senha", String.valueOf(senha.getPassword()));

    return request;
  }

  protected void dispose() {
    conteudo.dispose();
  }

}