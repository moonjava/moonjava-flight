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
package br.com.moonjava.flight.app;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class IdiomaUI implements MouseListener {

  private JLabel portugues;
  private JLabel ingles;
  private JLabel espanhol;

  private ResourceBundle bundle;
  private JFrame idioma;
  private JPanel body;
  private JPanel conteudo;

  public IdiomaUI() {
    window();
    showAll();
  }

  private void window() {
    idioma = new JFrame("FLIGHT :: FLIGHT");

    body = new JPanel(null);
    body.setBackground(Color.WHITE);

    conteudo = new JPanel(null);

    idioma.getContentPane().add(body);
    conteudo.setBounds(0, 30, 600, 300);
    conteudo.setBackground(Color.WHITE);

    ImageIcon imageBrasil = new ImageIcon("flags/brazil.gif");
    ImageIcon imageUSA = new ImageIcon("flags/usa.gif");
    ImageIcon imageEspanha = new ImageIcon("flags/spain.gif");

    portugues = new JLabel(imageBrasil);
    portugues.setBounds(10, 0, 180, 135);

    ingles = new JLabel(imageUSA);
    ingles.setBounds(190, 0, 180, 135);

    espanhol = new JLabel(imageEspanha);
    espanhol.setBounds(370, 0, 180, 135);

    JLabel entrar = new JLabel("ENTRAR");
    entrar.setFont(new Font("Arial Bold", 0, 14));
    entrar.setBounds(60, 100, 180, 135);

    JLabel enter = new JLabel("ENTER");
    enter.setFont(new Font("Arial Bold", 0, 14));
    enter.setBounds(240, 100, 180, 135);

    JLabel _entrar = new JLabel("ENTRAR");
    _entrar.setFont(new Font("Arial Bold", 0, 14));
    _entrar.setBounds(420, 100, 180, 135);

    conteudo.add(portugues);
    conteudo.add(ingles);
    conteudo.add(espanhol);
    conteudo.add(entrar);
    conteudo.add(enter);
    conteudo.add(_entrar);
    body.add(conteudo);

    portugues.addMouseListener(this);
    ingles.addMouseListener(this);
    espanhol.addMouseListener(this);
  }

  private void showAll() {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int width = dimension.width;
    int height = dimension.height;
    int frameWidth = 600;
    int frameHeight = 300;

    idioma.setLocation((width / 2) - (frameWidth / 2), (height / 2) - (frameHeight / 2));
    idioma.setSize(frameWidth, frameHeight);
    idioma.setResizable(false);
    idioma.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    idioma.setVisible(true);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.getSource() == portugues) {
      bundle = ResourceBundle.getBundle("idioma/arquivo", new Locale("pt", "BR"));
    }
    if (e.getSource() == ingles) {
      bundle = ResourceBundle.getBundle("idioma/arquivo", Locale.US);
    }
    if (e.getSource() == espanhol) {
      bundle = ResourceBundle.getBundle("idioma/arquivo", new Locale("es", "ES"));
    }
    new FlightUI(bundle);
    idioma.setVisible(false);
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    portugues.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    ingles.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    espanhol.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  @Override
  public void mousePressed(MouseEvent e) {
  }

  @Override
  public void mouseReleased(MouseEvent e) {
  }

}