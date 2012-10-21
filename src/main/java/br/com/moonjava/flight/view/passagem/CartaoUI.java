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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.com.moonjava.flight.model.financeiro.Bandeira;
import br.com.moonjava.flight.util.AbstractFlightUI;
import br.com.moonjava.flight.util.ErrorSystem;
import br.com.moonjava.flight.util.FlightImageUI;
import br.com.moonjava.flight.util.FocusTextField;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Sep 7, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class CartaoUI extends AbstractFlightUI {

  private final JPanel conteudo;
  protected final ResourceBundle bundle;
  private final JDialog frame;

  private JLabel tituloNomeTitular;
  private JLabel tituloNumero;
  private JLabel tituloCpf;
  private JLabel tituloValidade;
  private JLabel tituloCodSeguranca;
  private JLabel tituloBandeira;

  private JLabel imagemNumero;
  private JLabel imagemCpf;
  private JLabel imagemCodigo;
  private JLabel imagemValidade;

  private JLabel alertaNumero;
  private JLabel alertaCpf;
  private JLabel alertaCodigo;
  private JLabel alertaValidade;

  private JTextField nomeTitular;
  private JTextField numero;
  private JTextField codSeguranca;
  private JFormattedTextField validade;
  private JFormattedTextField cpf;
  private JComboBox bandeira;

  private JButton oK;

  private boolean valid;
  private final double valorTotal;

  public CartaoUI(ResourceBundle bundle, double valorTotal) {
    this.bundle = bundle;
    this.valorTotal = valorTotal;

    frame = new JDialog();
    frame.setTitle(bundle.getString("cartao.titulo"));
    frame.setModal(true);

    conteudo = new JPanel(null);
    conteudo.setBounds(30, 30, 1130, 600);
    frame.getContentPane().add(conteudo);

    mainMenu();
  }

  @Override
  protected JPanel getConteudo() {
    return conteudo;
  }

  @Override
  protected void mainMenu() {
    tituloNomeTitular = new JLabel(bundle.getString("cartao.titulo.nometitular"));
    tituloNumero = new JLabel(bundle.getString("cartao.titulo.numero"));
    tituloCpf = new JLabel(bundle.getString("cartao.titulo.cpf"));
    tituloCodSeguranca = new JLabel(bundle.getString("cartao.titulo.codSeguranca"));
    tituloValidade = new JLabel(bundle.getString("cartao.titulo.validade"));
    tituloBandeira = new JLabel(bundle.getString("cartao.titulo.bandeira"));

    nomeTitular = new JTextField(bundle.getString("cartao.nomeTitular"));
    numero = new JTextField(bundle.getString("cartao.numero"));
    codSeguranca = new JTextField(bundle.getString("cartao.codSeguranca"));
    bandeira = new JComboBox(Bandeira.values());

    try {
      cpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
      validade = new JFormattedTextField(new MaskFormatter("##/####"));
    } catch (ParseException e) {
      ErrorSystem.addException(e, bundle);
    }

    oK = new JButton("Ok");

    imagemNumero = new JLabel();
    imagemCpf = new JLabel();
    imagemCodigo = new JLabel();
    imagemValidade = new JLabel();

    alertaNumero = new JLabel();
    alertaCpf = new JLabel();
    alertaCodigo = new JLabel();
    alertaValidade = new JLabel();

    Font font = new Font("Century Gothic", Font.ITALIC, 13);
    nomeTitular.setFont(font);
    numero.setFont(font);
    codSeguranca.setFont(font);
    bandeira.setFont(font);

    nomeTitular.setForeground(Color.GRAY);
    numero.setForeground(Color.GRAY);
    codSeguranca.setForeground(Color.GRAY);
    bandeira.setForeground(Color.GRAY);

    imagemNumero.setBounds(420, 90, 400, 30);
    imagemCpf.setBounds(420, 130, 100, 30);
    imagemCodigo.setBounds(420, 170, 400, 30);
    imagemValidade.setBounds(420, 210, 400, 30);

    alertaNumero.setBounds(445, 90, 400, 30);
    alertaCpf.setBounds(445, 130, 100, 30);
    alertaCodigo.setBounds(445, 170, 400, 30);
    alertaValidade.setBounds(445, 210, 400, 30);

    tituloNomeTitular.setBounds(60, 50, 200, 30);
    tituloNumero.setBounds(60, 90, 200, 30);
    tituloCpf.setBounds(60, 130, 200, 30);
    tituloCodSeguranca.setBounds(60, 170, 200, 30);
    tituloValidade.setBounds(60, 210, 200, 30);
    tituloBandeira.setBounds(60, 250, 200, 30);

    nomeTitular.setBounds(230, 50, 300, 30);
    numero.setBounds(230, 90, 180, 30);
    cpf.setBounds(230, 130, 180, 30);
    codSeguranca.setBounds(230, 170, 180, 30);
    validade.setBounds(230, 210, 180, 30);
    bandeira.setBounds(230, 250, 180, 30);
    oK.setBounds(230, 290, 100, 30);

    conteudo.repaint();
    conteudo.validate();

    conteudo.add(tituloNomeTitular);
    conteudo.add(tituloNumero);
    conteudo.add(tituloCpf);
    conteudo.add(tituloCodSeguranca);
    conteudo.add(tituloValidade);
    conteudo.add(tituloBandeira);

    conteudo.add(nomeTitular);
    conteudo.add(numero);
    conteudo.add(cpf);
    conteudo.add(codSeguranca);
    conteudo.add(validade);
    conteudo.add(bandeira);
    conteudo.add(oK);

    repaint();
  }

  protected void showAll() {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int width = dimension.width;
    int height = dimension.height;
    int frameWidth = 800;
    int frameHeight = 400;

    frame.setLocation((width / 2) - (frameWidth / 2), (height / 2) - (frameHeight / 2));
    frame.setSize(frameWidth, frameHeight);

    frame.setResizable(false);
    frame.setVisible(true);
  }

  protected void addOkListener(ActionListener a) {
    oK.addActionListener(a);
  }

  protected void addFocusCpfListener(FocusListener a) {
    cpf.addFocusListener(a);
  }

  protected void addFocusNumeroListener(FocusListener a) {
    numero.addFocusListener(a);
  }

  protected void addFocusCodigoListener(FocusListener a) {
    codSeguranca.addFocusListener(a);
  }

  protected void addFocusDataListener(FocusListener a) {
    validade.addFocusListener(a);
  }

  protected void addFocusListener(FocusListener a) {
    nomeTitular.addFocusListener(a);
    numero.addFocusListener(a);
    codSeguranca.addFocusListener(a);

    ((FocusTextField) a).setField(nomeTitular, numero, codSeguranca);
    ((FocusTextField) a).setText(bundle.getString("cartao.nomeTitular"),
        bundle.getString("cartao.numero"),
        bundle.getString("cartao.codSeguranca"));
  }

  protected RequestParamWrapper getParameters() {
    RequestParamWrapper request = new RequestParamWrapper();
    request.set("titular", nomeTitular.getText());
    request.set("numero", numero.getText());
    request.set("validade", validade.getText());
    request.set("bandeira", bandeira.getSelectedItem());
    request.set("cpf", cpf.getText());
    request.set("codigo", codSeguranca.getText());
    request.set("valor", valorTotal);

    return request;
  }

  protected RequestParamWrapper getDefaultTexts() {
    RequestParamWrapper request = new RequestParamWrapper();
    request.set("numero", bundle.getString("cartao.numero"));
    request.set("codigo", bundle.getString("cartao.codSeguranca"));

    return request;
  }

  public boolean isParameterValid() {
    return valid;
  }

  public void setParameterValid(boolean valid) {
    this.valid = valid;
  }

  protected void dispose() {
    frame.dispose();
  }

  // add Layout
  protected void addImageCardValid() {
    FlightImageUI.add(imagemValidade, alertaValidade,
        bundle.getString("validade.valido"), bundle, conteudo);
    repaint();
  }

  protected void addImageCardInvalid() {
    FlightImageUI.addError(imagemValidade, alertaValidade,
        bundle.getString("validade.invalido"), bundle, conteudo);
    repaint();
  }

  protected void addImageCpfValido() {
    FlightImageUI.add(imagemCpf, alertaCpf,
        bundle.getString("criar.pessoafisica.cpf.alerta.ok"), bundle, conteudo);
    repaint();
  }

  protected void addImageCpfInvalido() {
    FlightImageUI.addError(imagemCpf, alertaCpf,
        bundle.getString("criar.pessoafisica.cpf.alerta.erro"), bundle, conteudo);
    repaint();
  }

  protected void addImageNumeroParseException() {
    FlightImageUI.addError(imagemNumero, alertaNumero,
        bundle.getString("alerta.numero"), bundle, conteudo);
    repaint();
  }

  protected void addImageCodigoParseException() {
    FlightImageUI.addError(imagemCodigo, alertaCodigo,
        bundle.getString("alerta.numero"), bundle, conteudo);
    repaint();
  }

  protected void addMessageFailed() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("cartao.erro"),
        bundle.getString("vender.passagem.pagamento.cartao"),
        JOptionPane.ERROR_MESSAGE);
  }

  // remove layout
  protected void removeImageNumeroParseException() {
    conteudo.remove(alertaNumero);
    conteudo.remove(imagemNumero);
    repaint();
  }

  protected void removeImageCodigoParseException() {
    conteudo.remove(alertaCodigo);
    conteudo.remove(imagemCodigo);
    repaint();
  }

}