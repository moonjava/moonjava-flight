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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.joda.time.DateTime;

import br.com.moonjava.flight.base.Status;
import br.com.moonjava.flight.base.Voo;
import br.com.moonjava.flight.base.action.VooAction;
import br.com.moonjava.flight.util.FormatDateTime;
import br.com.moonjava.flight.util.RequestParam;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Aug 17, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
class VooTableHandler implements ActionListener {

  private final JTable tabela;
  private final JTextField origem;
  private final JTextField destino;
  private final JFormattedTextField partida;
  private final JFormattedTextField chegada;
  private final JComboBox status;
  private final ResourceBundle bundle;
  private final JPanel conteudo;
  private final JButton atualizar;
  private final JButton deletar;
  private final JButton controlarStatus;

  private JComboBox timePartida;
  private JComboBox timeChegada;
  private List<Voo> list;

  public VooTableHandler(JTable tabela,
                         JTextField origem,
                         JTextField destino,
                         JFormattedTextField partida,
                         JFormattedTextField chegada,
                         JComboBox status,
                         ResourceBundle bundle,
                         JPanel conteudo,
                         JButton atualizar,
                         JButton deletar,
                         JButton controlarStatus) {
    this.tabela = tabela;
    this.origem = origem;
    this.destino = destino;
    this.partida = partida;
    this.chegada = chegada;
    this.status = status;
    this.bundle = bundle;
    this.conteudo = conteudo;
    this.atualizar = atualizar;
    this.deletar = deletar;
    this.controlarStatus = controlarStatus;
  }

  public void setAmPm(JComboBox timePartida, JComboBox timeChegada) {
    this.timePartida = timePartida;
    this.timeChegada = timeChegada;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String country = bundle.getLocale().getCountry();
    String _partida = null;
    String _chegada = null;

    if (country.equals("US")) {
      _partida = String.format("%s %s", partida.getText(), timePartida.getSelectedItem());
      _chegada = String.format("%s %s", chegada.getText(), timeChegada.getSelectedItem());
    } else {
      _partida = partida.getText();
      _chegada = chegada.getText();
    }

    int index = status.getSelectedIndex();
    Status[] values = Status.values();
    Status _status = values[index];

    String maskEmpty = "  /  /       :  ";
    DateTime dataPartida = null;
    DateTime dataChegada = null;

    if (!_partida.startsWith(maskEmpty)) {
      dataPartida = FormatDateTime.parseToDateTime(_partida, country);
    }

    if (!_chegada.startsWith(maskEmpty)) {
      dataChegada = FormatDateTime.parseToDateTime(_chegada, country);
    }

    RequestParam request = new RequestParamWrapper();
    request.set("origem", origem.getText());
    request.set("destino", destino.getText());
    request.set("partida", dataPartida);
    request.set("chegada", dataChegada);
    request.set("status", _status);

    list = new VooAction().consultar(request);

    VooTableModel aeronaves = new VooTableModel(list, bundle);
    tabela.setModel(aeronaves);

    partida.setText("");
    chegada.setText("");

    if (tabela.getRowCount() == 0) {
      JOptionPane.showMessageDialog(null,
          bundle.getString("consultar.voo.joption.err"),
          bundle.getString("consultar.voo.joption.titulo"),
          JOptionPane.ERROR_MESSAGE);
    } else {
      tabela.addMouseListener(new TabelaHandler());

      conteudo.repaint();
      conteudo.validate();
    }

  }

  private class TabelaHandler implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
      atualizar.setEnabled(true);
      deletar.setEnabled(true);
      controlarStatus.setEnabled(true);

      // Singleton Object doesn't allow duplicate events
      AtualizarVooUI update = AtualizarVooUI.getInstance();
      update.setAttributes(atualizar, deletar, tabela, list, conteudo, bundle);
      update.setResult(false);
      atualizar.addActionListener(update);

      DeletarVooUI delete = DeletarVooUI.getInstance();
      delete.setAttributes(atualizar, deletar, tabela, list, conteudo, bundle);
      delete.setResult(false);
      deletar.addActionListener(delete);

      ControlarStatusVooUI status = ControlarStatusVooUI.getInstance();
      status.setAttributes(tabela, list, conteudo, bundle);
      status.setResult(false);
      controlarStatus.addActionListener(status);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

  }

}