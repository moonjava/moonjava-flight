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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import org.joda.time.DateTime;

import br.com.moonjava.flight.base.Voo;
import br.com.moonjava.flight.base.action.VooAction;
import br.com.moonjava.flight.base.action.VooUpdate;
import br.com.moonjava.flight.util.FormatDateTime;
import br.com.moonjava.flight.util.JTextFieldLimit;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Aug 21, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
class AtualizarVooUI implements ActionListener {

  // Singleton
  private static final AtualizarVooUI ui = new AtualizarVooUI();

  private JButton atualizar;
  private JButton deletar;
  private JTable tabela;
  private List<Voo> list;
  private JPanel conteudo;
  private ResourceBundle bundle;

  private Voo pojo;
  private boolean result;

  private JFormattedTextField chegada;
  private JFormattedTextField partida;
  private JTextField observacao;
  private JComboBox timePartida;
  private JComboBox timeChegada;

  private AtualizarVooUI() {
  }

  public static AtualizarVooUI getInstance() {
    return ui;
  }

  public void setResult(boolean result) {
    this.result = result;
  }

  public void setAttributes(JButton atualizar,
                            JButton deletar,
                            JTable tabela,
                            List<Voo> list,
                            JPanel conteudo,
                            ResourceBundle bundle) {
    this.atualizar = atualizar;
    this.deletar = deletar;
    this.tabela = tabela;
    this.list = list;
    this.conteudo = conteudo;
    this.bundle = bundle;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    atualizar.setEnabled(false);
    deletar.setEnabled(false);
    if (!result) {
      result = true;
      int[] rows = tabela.getSelectedRows();

      if (rows.length == 1) {
        pojo = list.get(rows[0]);

        conteudo.removeAll();
        conteudo.repaint();
        conteudo.validate();

        JLabel tituloPartida = new JLabel(bundle.getString("criar.voo.titulo.partida"));
        JLabel tituloChegada = new JLabel(bundle.getString("criar.voo.titulo.chegada"));
        JLabel tituloObservacao = new JLabel(bundle.getString("criar.voo.titulo.observacao"));
        JLabel alertaPartida = new JLabel(bundle.getString("alerta.data"));

        observacao = new JTextField();
        observacao.setDocument(new JTextFieldLimit(100));

        try {
          MaskFormatter mask = new MaskFormatter("##/##/#### ##:##");
          chegada = new JFormattedTextField(mask);
          partida = new JFormattedTextField(mask);
        } catch (ParseException e1) {
          JOptionPane.showMessageDialog(null, e1.getMessage());
        }

        JButton atualizar = new JButton(bundle.getString("atualizar.voo.botao.atualizar"));

        tituloPartida.setBounds(100, 70, 200, 30);
        tituloChegada.setBounds(100, 110, 200, 30);
        tituloObservacao.setBounds(100, 150, 200, 30);
        alertaPartida.setBounds(460, 70, 500, 30);

        partida.setBounds(250, 70, 200, 30);
        chegada.setBounds(250, 110, 200, 30);
        observacao.setBounds(250, 150, 400, 30);

        atualizar.setBounds(250, 190, 150, 30);
        atualizar.addActionListener(new Atualizar());

        conteudo.add(tituloPartida);
        conteudo.add(tituloChegada);
        conteudo.add(tituloObservacao);
        conteudo.add(alertaPartida);

        conteudo.add(partida);
        conteudo.add(chegada);
        conteudo.add(observacao);

        conteudo.add(atualizar);

        if (bundle.getLocale().getCountry().equals("US")) {
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

      } else {
        JOptionPane
            .showMessageDialog(null,
                bundle.getString("atualizar.voo.joption.err"),
                bundle.getString("atualizar.voo.joption.titulo"),
                JOptionPane.ERROR_MESSAGE);
        conteudo.removeAll();
        conteudo.repaint();
        conteudo.validate();
      }
    }

  }

  private class Atualizar implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      RequestParamWrapper request = new RequestParamWrapper();
      VooAction action = new VooAction();
      String _observacao = observacao.getText().isEmpty() ?
          "Motivo não fornecido" : observacao.getText();
      String country = bundle.getLocale().getCountry();
      String dataPartida = null;
      String dataChegada = null;

      if (country.equals("US")) {
        dataPartida = String.format("%s %s", partida.getText(), timePartida.getSelectedItem());
        dataChegada = String.format("%s %s", chegada.getText(), timeChegada.getSelectedItem());
      } else {
        dataPartida = partida.getText();
        dataChegada = chegada.getText();
      }

      DateTime _partida = FormatDateTime.parseToDateTime(dataPartida, country);
      DateTime _chegada = FormatDateTime.parseToDateTime(dataChegada, country);

      if (_partida.isBefore(_chegada) && _partida.isAfter(System.currentTimeMillis())) {
        request.set("id", pojo.getId());
        request.set("partida", _partida);
        request.set("chegada", _chegada);
        request.set("observacao", _observacao);

        Voo voo = new VooUpdate(request).createInstance();
        action.atualizar(voo);

        JOptionPane.showMessageDialog(null,
            bundle.getString("atualizar.voo.joption.ok"),
            bundle.getString("atualizar.voo.joption.titulo"),
            JOptionPane.INFORMATION_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(null,
            bundle.getString("criar.voo.joption.tempo"),
            bundle.getString("atualizar.voo.joption.titulo"),
            JOptionPane.ERROR_MESSAGE);
      }
      conteudo.removeAll();
      conteudo.validate();
      conteudo.repaint();
    }

  }

}