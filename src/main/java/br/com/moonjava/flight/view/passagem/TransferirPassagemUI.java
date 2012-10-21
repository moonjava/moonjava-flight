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
package br.com.moonjava.flight.view.passagem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.util.AbstractFlightUI;
import br.com.moonjava.flight.util.ErrorSystem;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.view.voo.VooTableModel;

/**
 * @version 1.0 Sep 8, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class TransferirPassagemUI extends AbstractFlightUI {

  private final JPanel conteudo;
  private final ResourceBundle bundle;
  private JTextField _bilhete;
  private JButton consultar;
  private JButton transferir;
  private JTable tabela;
  private JLabel tituloVooDisp;
  private JScrollPane scroll;

  public TransferirPassagemUI(JPanel conteudo, ResourceBundle bundle) {
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

    JLabel filtroBilhete = new JLabel(bundle.getString("transferir.passagem.titulo.filtroBilhete"));
    tituloVooDisp = new JLabel(bundle.getString("transferir.passagem.titulo.vooDisponivel"));

    _bilhete = new JTextField();
    consultar = new JButton(bundle.getString("transferir.passagem.consultar"));
    transferir = new JButton(bundle.getString("transferir.passagem.transferir"));

    tabela = new JTable();
    tabela.setBorder(new LineBorder(Color.black));
    tabela.setGridColor(Color.black);
    tabela.setShowGrid(true);
    tabela.setFont(new Font("Century Gothic", Font.ITALIC, 13));

    scroll = new JScrollPane();
    scroll.getViewport().setBorder(null);
    scroll.getViewport().add(tabela);
    scroll.setBounds(130, 250, 750, 200);
    scroll.setSize(750, 200);

    imagem.setBounds(100, 70, 30, 30);
    filtroBilhete.setBounds(130, 45, 200, 30);
    tituloVooDisp.setBounds(130, 130, 750, 200);

    _bilhete.setBounds(130, 70, 80, 30);
    consultar.setBounds(220, 70, 100, 30);

    transferir.setBounds(780, 475, 100, 30);
    transferir.setEnabled(false);

    conteudo.add(imagem);
    conteudo.add(filtroBilhete);
    conteudo.add(_bilhete);
    conteudo.add(consultar);

    repaint();
  }

  protected void addConsultarListener(ActionListener a) {
    consultar.addActionListener(a);
  }

  protected void addTransferirListener(ActionListener a) {
    transferir.addActionListener(a);
  }

  protected void addVooTable() {
    conteudo.add(tituloVooDisp);
    conteudo.add(scroll);
    conteudo.add(transferir);

    repaint();
  }

  protected void messageOK() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("transferir.passagem.transferido"),
        bundle.getString("transferir.passagem.titulo"),
        JOptionPane.INFORMATION_MESSAGE);

    refresh();
  }

  protected void messageFailed() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("transferir.passagem.erro.transferir"),
        bundle.getString("transferir.passagem.titulo"),
        JOptionPane.ERROR_MESSAGE);

    refresh();
  }

  protected void messageSelectFailed() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("consultar.voo.joption.err.selecao"),
        bundle.getString("consultar.voo.joption.titulo"),
        JOptionPane.ERROR_MESSAGE);
  }

  protected void messagePassagemOff() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("cancelar.passagem.erro.solicitacao"),
        bundle.getString("cancelar.passagem.titulo"),
        JOptionPane.ERROR_MESSAGE);
  }

  protected void messagemPasJaCancelada() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("cancelar.passagem.ja.cancelada"),
        bundle.getString("cancelar.passagem.titulo"),
        JOptionPane.INFORMATION_MESSAGE);
  }

  protected void messageDbOff() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("erro.bd.off"),
        bundle.getString("cancelar.passagem.titulo"),
        JOptionPane.ERROR_MESSAGE);
  }

  protected RequestParamWrapper getParametersPassagem() {
    RequestParamWrapper request = new RequestParamWrapper();
    request.set("codBilhete", _bilhete.getText());

    return request;
  }

  protected boolean showList(List<Voo> lista) {
    VooTableModel voos = new VooTableModel(lista, bundle);
    tabela.setModel(voos);
    return tabela.getRowCount() == 0 ? true : false;
  }

  protected void addItemTableSelectedListener(MouseListener a) {
    tabela.addMouseListener(a);
  }

  protected JTable getTable() {
    return tabela;
  }

  protected void habilitarBotao() {
    transferir.setEnabled(true);
  }

}