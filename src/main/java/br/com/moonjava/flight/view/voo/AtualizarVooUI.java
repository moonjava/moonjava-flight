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
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.com.moonjava.flight.util.FlightImageUI;
import br.com.moonjava.flight.util.JTextFieldLimit;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Aug 21, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class AtualizarVooUI {

  private JPanel conteudo;
  protected ResourceBundle bundle;
  private JButton atualizar;
  private JButton deletar;
  private JButton status;

  private JLabel tituloPartida;
  private JLabel tituloChegada;
  private JLabel tituloObservacao;
  private JTextField observacao;
  private JComboBox timePartida;
  private JComboBox timeChegada;
  private JFormattedTextField partida;
  private JFormattedTextField chegada;
  private JButton enviar;
  private JLabel alertaPartida;
  private JLabel imagemPartida;
  private JLabel alertaChegada;
  private JLabel imagemChegada;

  public void setAttributes(JPanel conteudo,
                            ResourceBundle bundle,
                            JButton atualizar,
                            JButton deletar,
                            JButton status) {
    this.conteudo = conteudo;
    this.bundle = bundle;
    this.atualizar = atualizar;
    this.deletar = deletar;
    this.status = status;

    mainMenu();
  }

  protected void mainMenu() {
    tituloPartida = new JLabel(bundle.getString("criar.voo.titulo.partida"));
    tituloChegada = new JLabel(bundle.getString("criar.voo.titulo.chegada"));
    tituloObservacao = new JLabel(bundle.getString("criar.voo.titulo.observacao"));
    imagemPartida = new JLabel();
    alertaPartida = new JLabel();
    imagemChegada = new JLabel();
    alertaChegada = new JLabel();

    observacao = new JTextField();
    observacao.setDocument(new JTextFieldLimit(100));

    enviar = new JButton(bundle.getString("atualizar.voo.botao.atualizar"));

    try {
      MaskFormatter mask = new MaskFormatter("##/##/#### ##:##");
      partida = new JFormattedTextField(mask);
      chegada = new JFormattedTextField(mask);
    } catch (ParseException e1) {
      JOptionPane.showMessageDialog(null, e1.getMessage());
    }

    tituloPartida.setBounds(100, 70, 200, 30);
    tituloChegada.setBounds(100, 110, 200, 30);
    tituloObservacao.setBounds(100, 150, 200, 30);
    alertaPartida.setBounds(485, 70, 500, 30);
    imagemPartida.setBounds(460, 70, 500, 30);
    alertaChegada.setBounds(485, 110, 500, 30);
    imagemChegada.setBounds(460, 110, 500, 30);

    partida.setBounds(250, 70, 200, 30);
    chegada.setBounds(250, 110, 200, 30);
    observacao.setBounds(250, 150, 400, 30);
    enviar.setBounds(250, 190, 150, 30);
  }

  protected String getCountry() {
    return bundle.getString("country");
  }

  protected void addAtualizarListener(ActionListener a) {
    atualizar.addActionListener(a);
  }

  protected void addEnviarListener(ActionListener a) {
    enviar.addActionListener(a);
  }

  protected void addFocusDataPartidaListener(FocusListener a) {
    partida.addFocusListener(a);
  }

  protected void addFocusDataChegadaListener(FocusListener a) {
    chegada.addFocusListener(a);
  }

  protected RequestParamWrapper getParameters() {
    RequestParamWrapper request = new RequestParamWrapper();
    String _observacao = observacao.getText();
    if (_observacao.isEmpty()) {
      _observacao = "Motivo não fornecido";
    }
    request.set("observacao", _observacao);
    request.set("partida", partida.getText());
    request.set("chegada", chegada.getText());

    if (getCountry().equals("US")) {
      request.set("timePartida", timePartida.getSelectedItem());
      request.set("timeChegada", timeChegada.getSelectedItem());
    }
    return request;
  }

  protected void messageFailed() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("atualizar.voo.joption.err"),
        bundle.getString("atualizar.voo.joption.titulo"),
        JOptionPane.ERROR_MESSAGE);
  }

  protected void messageOK() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("atualizar.voo.joption.ok"),
        bundle.getString("atualizar.voo.joption.titulo"),
        JOptionPane.INFORMATION_MESSAGE);
  }

  protected void messageTimeException() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("criar.voo.joption.tempo"),
        bundle.getString("atualizar.voo.joption.titulo"),
        JOptionPane.ERROR_MESSAGE);
  }

  protected void showAll() {
    conteudo.add(tituloPartida);
    conteudo.add(tituloChegada);
    conteudo.add(tituloObservacao);
    conteudo.add(alertaPartida);

    conteudo.add(partida);
    conteudo.add(chegada);
    conteudo.add(observacao);

    conteudo.add(enviar);

    if (getCountry().equals("US")) {
      alertaPartida.setBounds(520, 70, 500, 30);

      String[] ampm = {
          "AM",
          "PM" };
      timePartida = new JComboBox(ampm);
      timeChegada = new JComboBox(ampm);

      timePartida.setBounds(455, 75, 60, 20);
      timeChegada.setBounds(455, 115, 60, 20);

      conteudo.add(timePartida);
      conteudo.add(timeChegada);
    }

    conteudo.repaint();
    conteudo.validate();
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

  protected void disableButtons() {
    atualizar.setEnabled(false);
    deletar.setEnabled(false);
    status.setEnabled(false);
  }

  protected void addMessageFailed() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("dadoincorreto"),
        "flight",
        JOptionPane.ERROR_MESSAGE);
  }

  protected void refresh() {
    conteudo.removeAll();
    repaint();
  }

  protected void repaint() {
    conteudo.repaint();
    conteudo.validate();
  }

}