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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import org.joda.time.DateTime;

import br.com.moonjava.flight.controller.base.VooControlCreate;
import br.com.moonjava.flight.dao.base.AeronaveDAO;
import br.com.moonjava.flight.dao.base.VooDAO;
import br.com.moonjava.flight.model.base.Aeronave;
import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.util.FormatDateTime;
import br.com.moonjava.flight.util.GerarCodigo;
import br.com.moonjava.flight.util.JTextFieldLimit;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Aug 17, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
class CriarVooUI implements ActionListener {

  private final JPanel conteudo;
  private final ResourceBundle bundle;
  private final JButton atualizar;
  private final JButton deletar;
  private final JButton status;
  private final VooDAO vooDAO;
  private final AeronaveDAO aeronaveDAO;
  private final RequestParamWrapper request;

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

  public CriarVooUI(JPanel conteudo,
                    ResourceBundle bundle,
                    JButton atualizar,
                    JButton deletar,
                    JButton status) {
    this.conteudo = conteudo;
    this.bundle = bundle;
    this.atualizar = atualizar;
    this.deletar = deletar;
    this.status = status;

    vooDAO = new VooDAO();
    aeronaveDAO = new AeronaveDAO();
    request = new RequestParamWrapper();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
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

    JLabel alertaPartida = new JLabel(bundle.getString("alerta.data"));
    JButton cadastrar = new JButton(bundle.getString("criar.voo.botao.cadastrar"));

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

    List<Aeronave> aeronaves = aeronaveDAO.consultar(request);
    Vector<Aeronave> vector = new Vector<Aeronave>(aeronaves);
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

    alertaPartida.setBounds(400, 230, 500, 30);

    codigo.setBounds(180, 70, 200, 30);
    origem.setBounds(180, 110, 200, 30);
    destino.setBounds(180, 150, 200, 30);
    escala.setBounds(180, 190, 200, 30);
    partida.setBounds(180, 230, 200, 30);
    chegada.setBounds(180, 270, 200, 30);
    aeronave.setBounds(180, 310, 200, 30);
    preco.setBounds(180, 350, 200, 30);
    cadastrar.setBounds(205, 390, 150, 30);

    cadastrar.addActionListener(new CadastrarHandler());

    conteudo.add(tituloCodigo);
    conteudo.add(tituloOrigem);
    conteudo.add(tituloDestino);
    conteudo.add(tituloEscala);
    conteudo.add(tituloPartida);
    conteudo.add(tituloChegada);
    conteudo.add(tituloAeronave);
    conteudo.add(tituloPreco);

    conteudo.add(alertaPartida);

    conteudo.add(codigo);
    conteudo.add(origem);
    conteudo.add(destino);
    conteudo.add(escala);
    conteudo.add(partida);
    conteudo.add(chegada);
    conteudo.add(aeronave);
    conteudo.add(preco);

    conteudo.add(cadastrar);

    if (bundle.getLocale().getCountry().equals("US")) {
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

  private class CadastrarHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
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

      Aeronave _aeronave = ((Aeronave) aeronave.getSelectedItem());
      DateTime _partida = FormatDateTime.parseToDateTime(dataPartida, country);
      DateTime _chegada = FormatDateTime.parseToDateTime(dataChegada, country);

      try {
        double _preco = Double.parseDouble(preco.getText());
        request.set("preco", _preco);
        request.set("codigo", codigo.getText());
        request.set("origem", origem.getText());
        request.set("destino", destino.getText());
        request.set("escala", escala.getText());
        request.set("partida", _partida);
        request.set("chegada", _chegada);
        request.set("aeronave", _aeronave.getId());
        request.set("assentoLivre", _aeronave.getQtdDeAssento());

        Voo pojo = new VooControlCreate(request).createInstance();
        boolean executed = vooDAO.criar(pojo);

        if (executed) {
          JOptionPane.showMessageDialog(null,
              bundle.getString("criar.voo.joption.ok"),
              bundle.getString("criar.voo.joption.titulo"),
              JOptionPane.INFORMATION_MESSAGE);
        } else {
          JOptionPane.showMessageDialog(null,
              bundle.getString("criar.voo.joption.tempo"),
              bundle.getString("criar.voo.joption.titulo"),
              JOptionPane.ERROR_MESSAGE);
        }
      } catch (NumberFormatException e2) {
        JOptionPane.showMessageDialog(null,
            bundle.getString("alerta.numero"),
            bundle.getString("criar.voo.joption.titulo"),
            JOptionPane.ERROR_MESSAGE);
      }

      conteudo.removeAll();
      conteudo.validate();
      conteudo.repaint();
    }

  }

}