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

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.moonjava.flight.controller.base.LoginController;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class IdiomaUI implements MouseListener {

  private JFrame idioma;
  private JPanel body;
  private JPanel conteudo;
  private ResourceBundle bundle;

  private JLabel portugues;
  private JLabel ingles;
  private JLabel espanhol;

  public IdiomaUI() {
    window();
    showAll();
  }

  private void window() {
    idioma = new JFrame("Flight :: Flight");
    body = new JPanel(null);
    conteudo = new JPanel(null);

    body.setBackground(Color.WHITE);
    conteudo.setBackground(Color.WHITE);

    idioma.getContentPane().add(body);

    ImageIcon imageBrasil = new ImageIcon("flags/brazil.gif");
    ImageIcon imageUSA = new ImageIcon("flags/usa.gif");
    ImageIcon imageEspanha = new ImageIcon("flags/spain.gif");

    portugues = new JLabel(imageBrasil);
    ingles = new JLabel(imageUSA);
    espanhol = new JLabel(imageEspanha);

    JLabel entrar = new JLabel("ENTRAR");
    JLabel enter = new JLabel("ENTER");
    JLabel _entrar = new JLabel("ENTRAR");

    entrar.setFont(new Font("Arial Bold", 0, 14));
    enter.setFont(new Font("Arial Bold", 0, 14));
    _entrar.setFont(new Font("Arial Bold", 0, 14));

    conteudo.setBounds(0, 30, 600, 300);
    portugues.setBounds(10, 0, 180, 135);
    ingles.setBounds(190, 0, 180, 135);
    espanhol.setBounds(370, 0, 180, 135);

    entrar.setBounds(60, 100, 180, 135);
    enter.setBounds(240, 100, 180, 135);
    _entrar.setBounds(420, 100, 180, 135);

    body.add(conteudo);
    conteudo.add(portugues);
    conteudo.add(ingles);
    conteudo.add(espanhol);

    conteudo.add(entrar);
    conteudo.add(enter);
    conteudo.add(_entrar);

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
    idioma.setVisible(true);
    idioma.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private static class ResourceControl extends ResourceBundle.Control {
    @Override
    public ResourceBundle newBundle(String baseName, Locale locale,
        String format, ClassLoader loader,
        boolean reload)
        throws IllegalAccessException, InstantiationException, IOException {
      String bundlename = toBundleName(baseName, locale);
      String resName = toResourceName(bundlename, "properties");
      InputStream stream = loader.getResourceAsStream(resName);
      return new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.getSource() == portugues) {
      bundle = ResourceBundle.getBundle("idioma/arquivo_pt_BR", new ResourceControl());
    }
    if (e.getSource() == ingles) {
      bundle = ResourceBundle.getBundle("idioma/arquivo_en_US", new ResourceControl());
    }
    if (e.getSource() == espanhol) {
      bundle = ResourceBundle.getBundle("idioma/arquivo_es_ES", new ResourceControl());
    }
    idioma.dispose();
    new LoginController(bundle);
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