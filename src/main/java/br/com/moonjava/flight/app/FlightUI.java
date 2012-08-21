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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.joda.time.DateTime;

import br.com.moonjava.flight.app.aeronave.AeronaveHandler;
import br.com.moonjava.flight.app.voo.VooHandler;
import br.com.moonjava.flight.util.FormatDateTime;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class FlightUI {

  private final ResourceBundle bundle;

  private JFrame frame;
  private JPanel body;
  private JPanel conteudo;
  private JMenu relogio;

  public FlightUI(ResourceBundle bundle) {
    this.bundle = bundle;
    window();
    panel();
    mainMenu();
    showAll();
  }

  private void window() {
    frame = new JFrame("Flight");
    body = new JPanel(null);
    conteudo = new JPanel(null);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(body);
    conteudo.setBounds(30, 30, 1130, 600);
  }

  private void panel() {
    JMenu flight = new JMenu("FLIGHT :: FLIGHT");
    JMenu voo = new JMenu(bundle.getString("menubar.voo"));
    JMenu passagem = new JMenu(bundle.getString("menubar.passagem"));
    JMenu checkin = new JMenu(bundle.getString("menubar.checkin"));
    JMenu aeronave = new JMenu(bundle.getString("menubar.aeronave"));
    JMenu usuario = new JMenu(bundle.getString("menubar.usuario"));
    JMenu sobre = new JMenu(bundle.getString("menubar.sobre"));
    JMenu sair = new JMenu(bundle.getString("menubar.sair"));
    relogio = new JMenu();

    String country = bundle.getLocale().getCountry();

    Timer timer = new Timer(1000, new Clock(country));
    timer.start();

    JMenuBar menuBar = new JMenuBar();
    JLabel rodape = new JLabel(bundle.getString("rodape"));

    flight.setEnabled(false);
    flight.setFont(new Font("Arial Bold", 0, 14));

    menuBar.setBounds(new Rectangle(Integer.MAX_VALUE, 30));
    rodape.setBounds(50, 630, 500, 40);

    voo.addMenuListener(new VooHandler(conteudo, bundle));
    aeronave.addMenuListener(new AeronaveHandler(conteudo, bundle));

    menuBar.add(flight);
    menuBar.add(voo);
    menuBar.add(passagem);
    menuBar.add(checkin);
    menuBar.add(aeronave);
    menuBar.add(usuario);
    menuBar.add(sobre);
    menuBar.add(sair);
    menuBar.add(relogio);

    body.add(menuBar);
    body.add(conteudo);
    body.add(rodape);
  }

  private void mainMenu() {
    Image image = null;
    InputStream stream = getClass().getResourceAsStream("/img/aviao_principal.svg.png");

    try {
      image = ImageIO.read(stream);
    } catch (IOException e) {
      e.printStackTrace();
    }

    ImageIcon imageIcon = new ImageIcon(image);
    JLabel imagem = new JLabel(imageIcon);
    JButton voo = new JButton(bundle.getString("menubar.voo"));
    JButton passagem = new JButton(bundle.getString("menubar.passagem"));
    JButton checkin = new JButton(bundle.getString("menubar.checkin"));
    JButton aeronave = new JButton(bundle.getString("menubar.aeronave"));
    JButton usuario = new JButton(bundle.getString("menubar.usuario"));

    voo.setBounds(50, 80, 200, 50);
    passagem.setBounds(50, 150, 200, 50);
    checkin.setBounds(50, 220, 200, 50);
    aeronave.setBounds(50, 290, 200, 50);
    usuario.setBounds(50, 360, 200, 50);
    imagem.setBounds(50, 50, 1100, 600);
    imagem.setBackground(Color.DARK_GRAY);

    voo.addActionListener(new VooHandler(conteudo, bundle));
    aeronave.addActionListener(new AeronaveHandler(conteudo, bundle));

    conteudo.add(voo);
    conteudo.add(passagem);
    conteudo.add(checkin);
    conteudo.add(aeronave);
    conteudo.add(usuario);
    conteudo.add(imagem);
  }

  private void showAll() {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int width = dimension.width;
    int height = dimension.height;
    int frameWidth = 1200;
    int frameHeight = 700;

    frame.setLocation((width / 2) - (frameWidth / 2), (height / 2) - (frameHeight / 2));
    frame.setSize(frameWidth, frameHeight);

    frame.setSize(1200, 700);
    frame.setResizable(false);
    frame.setVisible(true);
  }

  private class Clock implements ActionListener {

    private final String country;

    public Clock(String country) {
      this.country = country;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      long currentTimeMillis = System.currentTimeMillis();
      String time = new DateTime(currentTimeMillis).toString();

      String newTime = FormatDateTime.parseToString(time, country);
      relogio.setText(newTime);
      body.repaint();
      body.validate();
    }

  }

}