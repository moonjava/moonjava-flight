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
package br.com.moonjava.flight.view.checkin;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import br.com.moonjava.flight.model.base.Passagem;
import br.com.moonjava.flight.util.AbstractFlightUI;
import br.com.moonjava.flight.util.ErrorSystem;
import br.com.moonjava.flight.util.JTextFieldLimit;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.view.passagem.PassagemTableModel;

/**
 * @version 1.0 Sep 8, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class EfetuarCheckinUI extends AbstractFlightUI {

  private final JPanel conteudo;
  private final ResourceBundle bundle;
  private JTextField _bilhete;
  private JButton consultar;
  private JButton finalizarCheckin;
  private JButton alocarAssento;
  private JTable tabela;
  private JDialog dialog;
  private JTextField _alocarAssento;
  private JScrollPane scroll;

  public EfetuarCheckinUI(JPanel conteudo, ResourceBundle bundle) {
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

    JLabel filtroBilhete = new JLabel(bundle.getString("checkin.titulo.filtroBilhete"));

    _bilhete = new JTextField();
    consultar = new JButton(bundle.getString("checkin.consultar"));
    finalizarCheckin = new JButton(bundle.getString("checkin.finalizar"));
    alocarAssento = new JButton(bundle.getString("checkin.alocarAssento"));
    _alocarAssento = new JTextField();
    _alocarAssento.setDocument(new JTextFieldLimit(4));

    imagem.setBounds(0, 70, 30, 30);
    filtroBilhete.setBounds(30, 45, 200, 30);

    _bilhete.setBounds(30, 70, 80, 30);
    consultar.setBounds(120, 70, 100, 30);
    alocarAssento.setBounds(30, 225, 170, 30);
    _alocarAssento.setBounds(220, 225, 80, 30);
    finalizarCheckin.setBounds(30, 475, 100, 30);

    conteudo.add(imagem);
    conteudo.add(filtroBilhete);
    conteudo.add(_bilhete);
    conteudo.add(consultar);

    repaint();
  }

  protected RequestParamWrapper getParameters() {
    RequestParamWrapper request = new RequestParamWrapper();
    request.set("bilhete", _bilhete.getText());
    request.set("assento", _alocarAssento.getText());

    return request;
  }

  protected void addConsultarListener(ActionListener a) {
    consultar.addActionListener(a);
  }

  protected void addAlocarAssentoListener(ActionListener a) {
    alocarAssento.addActionListener(a);
  }

  protected void addFinalizarCheckinListener(ActionListener a) {
    finalizarCheckin.addActionListener(a);
  }

  protected void showSeatMap(String pathFile) {
    conteudo.add(alocarAssento);
    conteudo.add(_alocarAssento);

    Icon image = new ImageIcon(pathFile);
    int width = image.getIconWidth();
    int height = image.getIconHeight();

    JLabel label = new JLabel(image);

    dialog = new JDialog();
    dialog.add(label);
    dialog.setSize(width + 40, height + 40);
    dialog.setResizable(false);
    dialog.setVisible(true);

    repaint();
  }

  protected void showList(List<Passagem> passagens) {
    PassagemTableModel assentos = new PassagemTableModel(passagens, bundle);
    tabela = new JTable();
    tabela.setModel(assentos);
    tabela.setBorder(new LineBorder(Color.black));
    tabela.setGridColor(Color.black);
    tabela.setShowGrid(true);

    scroll = new JScrollPane();
    scroll.getViewport().setBorder(null);
    scroll.getViewport().add(tabela);
    scroll.setBounds(30, 265, 275, 200);
    scroll.setSize(275, 200);

    conteudo.add(scroll);

    repaint();
  }

  protected void messageAssentoOK() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("checkin.assento.ok"),
        bundle.getString("checkin.titulo"),
        JOptionPane.INFORMATION_MESSAGE);

    conteudo.add(finalizarCheckin);
    repaint();
  }

  protected void messageAssentoFailed() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("checkin.assento.failed"),
        bundle.getString("checkin.titulo"),
        JOptionPane.ERROR_MESSAGE);
  }

  protected void messageSolicitacaoErro() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("checkin.erro.solicitacao"),
        bundle.getString("checkin.titulo"),
        JOptionPane.ERROR_MESSAGE);
  }

  protected void messageOK() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("checkin.finalizado"),
        bundle.getString("checkin.titulo"),
        JOptionPane.INFORMATION_MESSAGE);

    dialog.dispose();
    refresh();
  }

}