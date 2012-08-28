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
package br.com.moonjava.flight.app.voo;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
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
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import br.com.moonjava.flight.base.Status;

/**
 * @version 1.0 Aug 17, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
class ConsultarVooUI implements ActionListener {

  private final JPanel conteudo;
  private final ResourceBundle bundle;
  private final JButton atualizar;
  private final JButton deletar;

  public ConsultarVooUI(JPanel conteudo,
                        ResourceBundle bundle,
                        JButton atualizar,
                        JButton deletar) {
    this.conteudo = conteudo;
    this.bundle = bundle;
    this.atualizar = atualizar;
    this.deletar = deletar;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    atualizar.setEnabled(false);
    deletar.setEnabled(false);
    conteudo.removeAll();
    conteudo.validate();
    conteudo.repaint();

    Image image = null;
    InputStream stream = getClass().getResourceAsStream("/img/search.png");
    try {
      image = ImageIO.read(stream);
    } catch (IOException e2) {
      e2.printStackTrace();
    }

    Icon _imagem = new ImageIcon(image);
    JLabel imagem = new JLabel(_imagem);

    JTextField origem = new JTextField();
    JTextField destino = new JTextField();
    JFormattedTextField partida = null;
    JFormattedTextField chegada = null;
    JComboBox status = new JComboBox();

    try {
      partida = new JFormattedTextField(new MaskFormatter("##/##/#### ##:##"));
      chegada = new JFormattedTextField(new MaskFormatter("##/##/#### ##:##"));
    } catch (ParseException e1) {
      JOptionPane.showMessageDialog(null, e1.getMessage());
    }

    Status[] values = Status.values();
    DefaultComboBoxModel model = new DefaultComboBoxModel(values);
    status.setModel(model);

    JLabel tituloOrigem = new JLabel(bundle.getString("consultar.voo.titulo.origem"));
    JLabel tituloDestino = new JLabel(bundle.getString("consultar.voo.titulo.destino"));
    JLabel tituloPartida = new JLabel(bundle.getString("consultar.voo.titulo.partida"));
    JLabel tituloChegada = new JLabel(bundle.getString("consultar.voo.titulo.chegada"));
    JLabel tituloStatus = new JLabel(bundle.getString("consultar.voo.titulo.status"));
    JButton filtrar = new JButton(bundle.getString("consultar.voo.campo"));

    JTable tabela = new JTable();
    tabela.setBorder(new LineBorder(Color.black));
    tabela.setGridColor(Color.black);
    tabela.setShowGrid(true);

    JScrollPane scroll = new JScrollPane();
    scroll.getViewport().setBorder(null);
    scroll.getViewport().add(tabela);
    scroll.setBounds(130, 100, 750, 450);
    scroll.setSize(750, 450);

    imagem.setBounds(100, 70, 30, 30);

    tituloOrigem.setBounds(130, 45, 100, 30);
    tituloDestino.setBounds(240, 45, 100, 30);
    tituloPartida.setBounds(350, 45, 130, 30);
    tituloChegada.setBounds(490, 45, 130, 30);
    tituloStatus.setBounds(630, 45, 150, 30);

    origem.setBounds(130, 70, 100, 30);
    destino.setBounds(240, 70, 100, 30);
    partida.setBounds(350, 70, 130, 30);
    chegada.setBounds(490, 70, 130, 30);
    status.setBounds(630, 70, 150, 30);

    VooTableHandler vooHandler = new VooTableHandler(tabela, origem, destino, partida, chegada,
        status, bundle, conteudo, atualizar, deletar);

    filtrar.setBounds(800, 70, 80, 30);
    filtrar.addActionListener(vooHandler);

    conteudo.add(tituloOrigem);
    conteudo.add(tituloDestino);
    conteudo.add(tituloPartida);
    conteudo.add(tituloChegada);
    conteudo.add(tituloStatus);

    conteudo.add(origem);
    conteudo.add(destino);
    conteudo.add(partida);
    conteudo.add(chegada);
    conteudo.add(status);

    conteudo.add(imagem);
    conteudo.add(filtrar);
    conteudo.add(scroll);

    if (bundle.getLocale().getCountry().equals("US")) {

      String[] ampm = {
          "AM",
          "PM" };
      JComboBox timePartida = new JComboBox(ampm);
      JComboBox timeChegada = new JComboBox(ampm);

      tituloPartida.setBounds(350, 25, 130, 30);
      tituloChegada.setBounds(490, 25, 130, 30);

      timePartida.setBounds(350, 50, 60, 20);
      timeChegada.setBounds(490, 50, 60, 20);

      conteudo.add(timePartida);
      conteudo.add(timeChegada);

      vooHandler.setAmPm(timePartida, timeChegada);
    }
    conteudo.repaint();
    conteudo.validate();
  }

}