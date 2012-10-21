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

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.com.moonjava.flight.model.base.Aeronave;
import br.com.moonjava.flight.util.AbstractFlightUI;
import br.com.moonjava.flight.util.FlightImageUI;
import br.com.moonjava.flight.util.GerarCodigo;
import br.com.moonjava.flight.util.JTextFieldLimit;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Aug 17, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public abstract class CriarVooUI extends AbstractFlightUI {

  private final JPanel conteudo;
  protected final ResourceBundle bundle;
  private final JButton atualizar;
  private final JButton deletar;
  private final JButton status;

  private JLabel codigo;
  private JTextField origem;
  private JTextField destino;
  private JTextField escala;
  private JTextField partida;
  private JTextField chegada;
  private JComboBox aeronave;
  private JComboBox timePartida;
  private JComboBox timeChegada;
  private JTextField preco;
  private JButton cadastrar;
  private JLabel imagemPartida;
  private JLabel alertaPartida;
  private JLabel imagemChegada;
  private JLabel alertaChegada;
  private JLabel imagemPreco;
  private JLabel alertaPreco;

  protected CriarVooUI(JPanel conteudo,
                       ResourceBundle bundle,
                       JButton atualizar,
                       JButton deletar,
                       JButton status) {
    this.conteudo = conteudo;
    this.bundle = bundle;
    this.atualizar = atualizar;
    this.deletar = deletar;
    this.status = status;

    disableButtons();
    refresh();
    mainMenu();
  }

  @Override
  protected JPanel getConteudo() {
    return conteudo;
  }

  protected abstract List<Aeronave> getList();

  @Override
  protected void mainMenu() {
    atualizar.setEnabled(false);
    deletar.setEnabled(false);
    status.setEnabled(false);
    conteudo.removeAll();
    conteudo.validate();
    conteudo.repaint();

    JLabel tituloCodigo = new JLabel(bundle.getString("criar.voo.titulo.codigo"));
    JLabel tituloOrigem = new JLabel(bundle.getString("criar.voo.titulo.origem"));
    JLabel tituloDestino = new JLabel(bundle.getString("criar.voo.titulo.destino"));
    JLabel tituloEscala = new JLabel(bundle.getString("criar.voo.titulo.escala"));
    JLabel tituloPartida = new JLabel(bundle.getString("criar.voo.titulo.partida"));
    JLabel tituloChegada = new JLabel(bundle.getString("criar.voo.titulo.chegada"));
    JLabel tituloAeronave = new JLabel(bundle.getString("criar.voo.titulo.aeronave"));
    JLabel tituloPreco = new JLabel(bundle.getString("criar.voo.titulo.preco"));

    imagemPartida = new JLabel();
    alertaPartida = new JLabel();
    imagemChegada = new JLabel();
    alertaChegada = new JLabel();
    imagemPreco = new JLabel();
    alertaPreco = new JLabel();
    cadastrar = new JButton(bundle.getString("criar.voo.botao.cadastrar"));

    String _codigo = new GerarCodigo("VOO").getCodigo();
    codigo = new JLabel(_codigo);
    origem = new JTextField();
    destino = new JTextField();
    preco = new JTextField();
    escala = new JTextField();
    aeronave = new JComboBox();

    try {
      chegada = new JFormattedTextField(new MaskFormatter("##/##/#### ##:##"));
      partida = new JFormattedTextField(new MaskFormatter("##/##/#### ##:##"));
    } catch (ParseException e1) {
      JOptionPane.showMessageDialog(null, e1.getMessage());
    }

    Vector<Aeronave> vector = new Vector<Aeronave>(getList());
    DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
    aeronave.setModel(model);

    origem.setDocument(new JTextFieldLimit(40));
    destino.setDocument(new JTextFieldLimit(40));
    escala.setDocument(new JTextFieldLimit(40));

    tituloCodigo.setBounds(60, 70, 200, 30);
    tituloOrigem.setBounds(60, 110, 200, 30);
    tituloDestino.setBounds(60, 150, 200, 30);
    tituloEscala.setBounds(60, 190, 200, 30);
    tituloPartida.setBounds(60, 230, 200, 30);
    tituloChegada.setBounds(60, 270, 200, 30);
    tituloAeronave.setBounds(60, 310, 200, 30);
    tituloPreco.setBounds(60, 350, 200, 30);

    imagemPartida.setBounds(400, 230, 500, 30);
    alertaPartida.setBounds(430, 230, 500, 30);

    imagemChegada.setBounds(400, 270, 200, 30);
    alertaChegada.setBounds(430, 270, 500, 30);

    imagemPreco.setBounds(400, 350, 200, 30);
    alertaPreco.setBounds(430, 350, 400, 30);

    codigo.setBounds(180, 70, 200, 30);
    origem.setBounds(180, 110, 200, 30);
    destino.setBounds(180, 150, 200, 30);
    escala.setBounds(180, 190, 200, 30);
    partida.setBounds(180, 230, 200, 30);
    chegada.setBounds(180, 270, 200, 30);
    aeronave.setBounds(180, 310, 200, 30);
    preco.setBounds(180, 350, 200, 30);
    cadastrar.setBounds(205, 390, 150, 30);

    conteudo.add(tituloCodigo);
    conteudo.add(tituloOrigem);
    conteudo.add(tituloDestino);
    conteudo.add(tituloEscala);
    conteudo.add(tituloPartida);
    conteudo.add(tituloChegada);
    conteudo.add(tituloAeronave);
    conteudo.add(tituloPreco);

    conteudo.add(alertaPartida);
    conteudo.add(imagemPartida);
    conteudo.add(alertaChegada);
    conteudo.add(imagemChegada);
    conteudo.add(alertaPreco);
    conteudo.add(imagemPreco);

    conteudo.add(codigo);
    conteudo.add(origem);
    conteudo.add(destino);
    conteudo.add(escala);
    conteudo.add(partida);
    conteudo.add(chegada);
    conteudo.add(aeronave);
    conteudo.add(preco);
    conteudo.add(cadastrar);

    if (getCountry().equals("US")) {
      alertaPartida.setBounds(450, 230, 500, 30);

      String[] ampm = {
          "AM",
          "PM" };
      timePartida = new JComboBox(ampm);
      timeChegada = new JComboBox(ampm);

      timePartida.setBounds(385, 235, 60, 20);
      timeChegada.setBounds(385, 275, 60, 20);

      conteudo.add(timePartida);
      conteudo.add(timeChegada);
    }

    conteudo.repaint();
    conteudo.validate();
  }

  protected String getCountry() {
    return bundle.getString("country");
  }

  protected void addFocusDataPartidaListener(FocusListener a) {
    partida.addFocusListener(a);
  }

  protected void addFocusDataChegadaListener(FocusListener a) {
    chegada.addFocusListener(a);
  }

  protected void addFocusPrecoListener(FocusListener a) {
    preco.addFocusListener(a);
  }

  protected void addCadastrarListener(ActionListener a) {
    cadastrar.addActionListener(a);
  }

  protected RequestParamWrapper getParameters() {
    RequestParamWrapper request = new RequestParamWrapper();
    Aeronave _aeronave = (Aeronave) aeronave.getSelectedItem();
    request.set("preco", preco.getText());
    request.set("codigo", codigo.getText());
    request.set("origem", origem.getText());
    request.set("destino", destino.getText());
    request.set("escala", escala.getText());
    request.set("partida", partida.getText());
    request.set("chegada", chegada.getText());
    request.set("aeronave", _aeronave.getId());
    request.set("assentoLivre", _aeronave.getQtdDeAssento());
    if (getCountry().equals("US")) {
      request.set("timePartida", timePartida.getSelectedItem());
      request.set("timeChegada", timeChegada.getSelectedItem());
    }
    return request;
  }

  protected void addImagePartidaValid() {
    FlightImageUI.add(imagemPartida, alertaPartida,
        bundle.getString("validade.valido"), bundle, conteudo);
    repaint();
  }

  protected void addImageChegadaValid() {
    FlightImageUI.add(imagemChegada, alertaChegada,
        bundle.getString("validade.valido"), bundle, conteudo);
    repaint();
  }

  protected void addImagePartidaInvalid() {
    FlightImageUI.addError(imagemPartida, alertaPartida,
        bundle.getString("validade.invalido"), bundle, conteudo);
    repaint();
  }

  protected void addImageChegadaInvalid() {
    FlightImageUI.addError(imagemChegada, alertaChegada,
        bundle.getString("alerta.data"), bundle, conteudo);
    repaint();
  }

  protected void messageOK() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("criar.voo.joption.ok"),
        bundle.getString("criar.voo.joption.titulo"),
        JOptionPane.INFORMATION_MESSAGE);
    refresh();
  }

  protected void messagePrecoOk() {
    conteudo.remove(imagemPreco);
    conteudo.remove(alertaPreco);
    repaint();
  }

  protected void messagePrecoParseExecption() {
    FlightImageUI.addError(imagemPreco, alertaPreco,
        bundle.getString("criar.voo.erro.preco"), bundle, conteudo);
    repaint();
  }

  protected void messageFailed() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("criar.voo.joption.tempo"),
        bundle.getString("criar.voo.joption.titulo"),
        JOptionPane.ERROR_MESSAGE);
  }

  protected void addMessageFailed() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("dadoincorreto"),
        "flight",
        JOptionPane.ERROR_MESSAGE);
  }

  protected void disableButtons() {
    atualizar.setEnabled(false);
    deletar.setEnabled(false);
  }

}