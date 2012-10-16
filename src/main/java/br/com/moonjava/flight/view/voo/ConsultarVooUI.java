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

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import br.com.moonjava.flight.model.base.Status;
import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Aug 17, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class ConsultarVooUI {

  private final JPanel conteudo;
  private final ResourceBundle bundle;
  private JButton atualizar;
  private JButton deletar;
  private JButton controlarStatus;

  private JButton filtrar;
  private JButton vender;

  private JTextField origem;
  private JTextField destino;
  private JFormattedTextField partida;
  private JFormattedTextField chegada;

  private JComboBox status;
  private JComboBox timePartida;
  private JComboBox timeChegada;

  private JTable tabela;
  private boolean passagem;

  public ConsultarVooUI(JPanel conteudo,
                        ResourceBundle bundle,
                        JButton atualizar,
                        JButton deletar,
                        JButton controlarStatus) {
    this.conteudo = conteudo;
    this.bundle = bundle;
    this.atualizar = atualizar;
    this.deletar = deletar;
    this.controlarStatus = controlarStatus;

    disableButtons();
    refresh();
    mainMenu();
  }

  public ConsultarVooUI(JPanel conteudo, ResourceBundle bundle, boolean passagem) {
    this.passagem = passagem;
    this.conteudo = conteudo;
    this.bundle = bundle;
    refresh();
    mainMenu();
  }

  private void mainMenu() {
    Image image = null;
    InputStream stream = getClass().getResourceAsStream("/img/search.png");
    try {
      image = ImageIO.read(stream);
    } catch (IOException e2) {
      e2.printStackTrace();
    }

    Icon _imagem = new ImageIcon(image);
    JLabel imagem = new JLabel(_imagem);

    origem = new JTextField();
    destino = new JTextField();
    partida = null;
    chegada = null;
    status = new JComboBox();

    try {
      partida = new JFormattedTextField(new MaskFormatter("##/##/#### ##:##"));
      chegada = new JFormattedTextField(new MaskFormatter("##/##/#### ##:##"));
    } catch (ParseException e1) {
      JOptionPane.showMessageDialog(null, e1.getMessage());
    }

    Status[] values = Status.values();
    String[] nomes = new String[values.length];
    for (int i = 0; i < values.length; i++) {
      nomes[i] = values[i].setBundle(bundle);
    }

    DefaultComboBoxModel model = new DefaultComboBoxModel(nomes);
    status.setModel(model);

    JLabel tituloOrigem = new JLabel(bundle.getString("consultar.voo.titulo.origem"));
    JLabel tituloDestino = new JLabel(bundle.getString("consultar.voo.titulo.destino"));
    JLabel tituloPartida = new JLabel(bundle.getString("consultar.voo.titulo.partida"));
    JLabel tituloChegada = new JLabel(bundle.getString("consultar.voo.titulo.chegada"));
    JLabel tituloStatus = new JLabel(bundle.getString("consultar.voo.titulo.status"));
    filtrar = new JButton(bundle.getString("consultar.voo.campo"));
    vender = new JButton(bundle.getString("consultar.voo.vender.passagem"));

    tabela = new JTable();
    tabela.setBorder(new LineBorder(Color.BLACK));
    tabela.setGridColor(Color.BLACK);
    tabela.setShowGrid(true);
    tabela.setFont(new Font("Century Gothic", Font.ITALIC, 13));

    JScrollPane scroll = new JScrollPane();
    scroll.getViewport().setBorder(null);
    scroll.getViewport().add(tabela);
    scroll.setBounds(130, 100, 750, 400);
    scroll.setSize(750, 400);

    imagem.setBounds(100, 70, 30, 30);

    tituloOrigem.setBounds(132, 45, 100, 30);
    tituloDestino.setBounds(242, 45, 100, 30);
    tituloPartida.setBounds(352, 45, 130, 30);
    tituloChegada.setBounds(492, 45, 130, 30);
    tituloStatus.setBounds(632, 45, 150, 30);

    origem.setBounds(130, 70, 100, 30);
    destino.setBounds(240, 70, 100, 30);
    partida.setBounds(350, 70, 130, 30);
    chegada.setBounds(490, 70, 130, 30);
    status.setBounds(630, 70, 150, 30);
    filtrar.setBounds(800, 70, 80, 30);
    vender.setBounds(700, 500, 180, 30);

    conteudo.add(tituloOrigem);
    conteudo.add(tituloDestino);
    conteudo.add(tituloPartida);
    conteudo.add(tituloChegada);

    conteudo.add(origem);
    conteudo.add(destino);
    conteudo.add(partida);
    conteudo.add(chegada);

    if (!passagem) {
      conteudo.add(tituloStatus);
      conteudo.add(status);
    }

    conteudo.add(imagem);
    conteudo.add(filtrar);
    conteudo.add(scroll);

    if (getCountry().equals("US")) {

      String[] ampm = {
          "AM",
          "PM" };
      timePartida = new JComboBox(ampm);
      timeChegada = new JComboBox(ampm);

      tituloPartida.setBounds(350, 25, 130, 30);
      tituloChegada.setBounds(490, 25, 130, 30);

      timePartida.setBounds(350, 50, 60, 20);
      timeChegada.setBounds(490, 50, 60, 20);

      conteudo.add(timePartida);
      conteudo.add(timeChegada);
    }
    conteudo.repaint();
    conteudo.validate();
  }

  protected String getCountry() {
    return bundle.getString("country");
  }

  protected void addConsultarListener(ActionListener a) {
    filtrar.addActionListener(a);
  }

  protected void addVenderPassagemListener(ActionListener a) {
    vender.addActionListener(a);
  }

  protected void addItemTableSelectedListener(MouseListener a) {
    tabela.addMouseListener(a);
  }

  // Parameters
  protected RequestParamWrapper getParameters() {
    RequestParamWrapper request = new RequestParamWrapper();
    request.set("origem", origem.getText());
    request.set("destino", destino.getText());
    request.set("partida", partida.getText());
    request.set("chegada", chegada.getText());
    request.set("status", status.getSelectedIndex());
    if (getCountry().equals("US")) {
      request.set("timePartida", timePartida.getSelectedItem());
      request.set("timeChegada", timeChegada.getSelectedItem());
    }
    return request;
  }

  protected JTable getTable() {
    return tabela;
  }

  // Frames/Layouts
  protected boolean showList(List<Voo> lista) {
    VooTableModel voos = new VooTableModel(lista, bundle);
    tabela.setModel(voos);
    partida.setText("");
    chegada.setText("");
    return tabela.getRowCount() == 0 ? true : false;
  }

  protected void messageFailed() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("consultar.voo.joption.err"),
        bundle.getString("consultar.voo.joption.titulo"),
        JOptionPane.ERROR_MESSAGE);
  }

  protected void messageSelectFailed() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("consultar.voo.joption.err.selecao"),
        bundle.getString("consultar.voo.joption.titulo"),
        JOptionPane.ERROR_MESSAGE);
  }

  protected int messagePassagemIdaVolta() {
    UIManager.put("OptionPane.okButtonText", bundle.getString("sim"));
    UIManager.put("OptionPane.cancelButtonText", bundle.getString("nao"));
    return JOptionPane.showConfirmDialog(null,
        bundle.getString("consultar.voo.ida.volta"),
        bundle.getString("consultar.voo.joption.titulo"),
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.INFORMATION_MESSAGE);
  }

  protected void enableButtons() {
    atualizar.setEnabled(true);
    deletar.setEnabled(true);
    controlarStatus.setEnabled(true);
    conteudo.add(vender);

    repaint();
  }

  protected void disableButtons() {
    atualizar.setEnabled(false);
    deletar.setEnabled(false);
    controlarStatus.setEnabled(false);
  }

  protected void repaint() {
    conteudo.repaint();
    conteudo.validate();
  }

  protected void refresh() {
    conteudo.removeAll();
    conteudo.validate();
    conteudo.repaint();
  }

}