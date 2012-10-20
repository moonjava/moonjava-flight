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
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

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

public class ChequeUI extends AbstractFlightUI {

  private final JPanel conteudo;
  private final ResourceBundle bundle;
  private final JDialog frame;

  private JLabel tituloNomeTitular;
  private JLabel tituloNumero;
  private JLabel tituloCpf;
  private JLabel tituloBanco;
  private JLabel tituloAgencia;
  private JLabel tituloConta;

  private JLabel imagemNumero;
  private JLabel imagemCpf;
  private JLabel imagemBanco;
  private JLabel imagemAgencia;
  private JLabel imagemConta;

  private JLabel alertaNumero;
  private JLabel alertaCpf;
  private JLabel alertaBanco;
  private JLabel alertaAgencia;
  private JLabel alertaConta;

  private JTextField nomeTitular;
  private JTextField numero;
  private JTextField banco;
  private JTextField agencia;
  private JTextField conta;
  private JFormattedTextField cpf;

  private JButton oK;

  private boolean valid;
  private final double valorTotal;

  public ChequeUI(ResourceBundle bundle, double valorTotal) {
    this.bundle = bundle;
    this.valorTotal = valorTotal;

    frame = new JDialog();
    frame.setTitle(bundle.getString("cheque.titulo"));
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
    tituloNomeTitular = new JLabel(bundle.getString("cheque.titulo.nometitular"));
    tituloNumero = new JLabel(bundle.getString("cheque.titulo.numero"));
    tituloCpf = new JLabel(bundle.getString("cheque.titulo.cpf"));
    tituloBanco = new JLabel(bundle.getString("cheque.titulo.banco"));
    tituloAgencia = new JLabel(bundle.getString("cheque.titulo.agencia"));
    tituloConta = new JLabel(bundle.getString("cheque.titulo.conta"));

    nomeTitular = new JTextField(bundle.getString("cheque.nomeTitular"));
    numero = new JTextField(bundle.getString("cheque.numero"));
    banco = new JTextField(bundle.getString("cheque.banco"));
    agencia = new JTextField(bundle.getString("cheque.agencia"));
    conta = new JTextField(bundle.getString("cheque.conta"));

    try {
      cpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
    } catch (ParseException e) {
      ErrorSystem.addException(e, bundle);
    }

    oK = new JButton("Ok");

    imagemNumero = new JLabel();
    imagemCpf = new JLabel();
    imagemBanco = new JLabel();
    imagemAgencia = new JLabel();
    imagemConta = new JLabel();

    alertaNumero = new JLabel();
    alertaCpf = new JLabel();
    alertaBanco = new JLabel();
    alertaAgencia = new JLabel();
    alertaConta = new JLabel();

    Font font = new Font("Century Gothic", Font.ITALIC, 13);
    nomeTitular.setFont(font);
    numero.setFont(font);
    banco.setFont(font);
    agencia.setFont(font);
    conta.setFont(font);

    nomeTitular.setForeground(Color.GRAY);
    numero.setForeground(Color.GRAY);
    banco.setForeground(Color.GRAY);
    agencia.setForeground(Color.GRAY);
    conta.setForeground(Color.GRAY);

    imagemNumero.setBounds(390, 90, 100, 30);
    imagemCpf.setBounds(390, 130, 100, 30);
    imagemBanco.setBounds(390, 170, 100, 30);
    imagemAgencia.setBounds(390, 210, 100, 30);
    imagemConta.setBounds(390, 250, 100, 30);

    alertaNumero.setBounds(415, 90, 400, 30);
    alertaCpf.setBounds(415, 130, 100, 30);
    alertaBanco.setBounds(415, 170, 400, 30);
    alertaAgencia.setBounds(415, 210, 400, 30);
    alertaConta.setBounds(415, 250, 400, 30);

    tituloNomeTitular.setBounds(60, 50, 200, 30);
    tituloNumero.setBounds(60, 90, 200, 30);
    tituloCpf.setBounds(60, 130, 200, 30);
    tituloBanco.setBounds(60, 170, 200, 30);
    tituloAgencia.setBounds(60, 210, 200, 30);
    tituloConta.setBounds(60, 250, 200, 30);

    nomeTitular.setBounds(200, 50, 300, 30);
    numero.setBounds(200, 90, 180, 30);
    cpf.setBounds(200, 130, 180, 30);
    banco.setBounds(200, 170, 180, 30);
    agencia.setBounds(200, 210, 180, 30);
    conta.setBounds(200, 250, 180, 30);
    oK.setBounds(200, 290, 100, 30);

    conteudo.add(tituloNomeTitular);
    conteudo.add(tituloNumero);
    conteudo.add(tituloBanco);
    conteudo.add(tituloAgencia);
    conteudo.add(tituloConta);
    conteudo.add(tituloCpf);

    conteudo.add(nomeTitular);
    conteudo.add(numero);
    conteudo.add(banco);
    conteudo.add(agencia);
    conteudo.add(conta);
    conteudo.add(cpf);
    conteudo.add(oK);

    repaint();
  }

  protected void showAll() {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int width = dimension.width;
    int height = dimension.height;
    int frameWidth = 740;
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

  protected void addFocusBancoListener(FocusListener a) {
    banco.addFocusListener(a);
  }

  protected void addFocusAgenciaListener(FocusListener a) {
    agencia.addFocusListener(a);
  }

  protected void addFocusContaListener(FocusListener a) {
    conta.addFocusListener(a);
  }

  protected void addFocusListener(FocusListener a) {
    nomeTitular.addFocusListener(a);
    numero.addFocusListener(a);
    banco.addFocusListener(a);
    agencia.addFocusListener(a);
    conta.addFocusListener(a);

    ((FocusTextField) a).setField(nomeTitular, numero, banco, agencia, conta);
    ((FocusTextField) a).setText(bundle.getString("cheque.nomeTitular"),
        bundle.getString("cheque.numero"),
        bundle.getString("cheque.banco"),
        bundle.getString("cheque.agencia"),
        bundle.getString("cheque.conta"));
  }

  protected RequestParamWrapper getParameters() {
    RequestParamWrapper request = new RequestParamWrapper();
    request.set("numero", numero.getText());
    request.set("cpf", cpf.getText());
    request.set("banco", banco.getText());
    request.set("agencia", agencia.getText());
    request.set("conta", conta.getText());
    request.set("valor", valorTotal);

    return request;
  }

  protected RequestParamWrapper getDefaultTexts() {
    RequestParamWrapper request = new RequestParamWrapper();
    request.set("numero", bundle.getString("cheque.numero"));
    request.set("banco", bundle.getString("cheque.banco"));
    request.set("agencia", bundle.getString("cheque.agencia"));
    request.set("conta", bundle.getString("cheque.conta"));

    return request;
  }

  public boolean isParemeterValid() {
    return valid;
  }

  public void setParameterValid(boolean valid) {
    this.valid = valid;
  }

  protected void dispose() {
    frame.dispose();
  }

  // add Layout
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

  protected void addImageBancoParseException() {
    FlightImageUI.addError(imagemBanco, alertaBanco,
        bundle.getString("alerta.numero"), bundle, conteudo);
    repaint();
  }

  protected void addImageAgenciaParseException() {
    FlightImageUI.addError(imagemAgencia, alertaAgencia,
        bundle.getString("alerta.numero"), bundle, conteudo);
    repaint();
  }

  protected void addImageContaParseException() {
    FlightImageUI.addError(imagemConta, alertaConta,
        bundle.getString("alerta.numero"), bundle, conteudo);
    repaint();
  }

  // remove layout
  protected void removeImageNumeroParseException() {
    conteudo.remove(alertaNumero);
    conteudo.remove(imagemNumero);
    repaint();
  }

  protected void removeImageBancoParseException() {
    conteudo.remove(alertaBanco);
    conteudo.remove(imagemBanco);
    repaint();
  }

  protected void removeImageAgenciaParseException() {
    conteudo.remove(alertaAgencia);
    conteudo.remove(imagemAgencia);
    repaint();
  }

  protected void removeImageContaParseException() {
    conteudo.remove(alertaConta);
    conteudo.remove(imagemConta);
    repaint();
  }

}