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
import java.awt.event.FocusListener;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.com.moonjava.flight.util.AbstractFlightUI;
import br.com.moonjava.flight.util.ErrorSystem;
import br.com.moonjava.flight.util.FlightImageUI;
import br.com.moonjava.flight.util.FocusTextField;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Sep 2, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class CancelarPassagemUI extends AbstractFlightUI {

  private final JPanel conteudo;
  private final ResourceBundle bundle;

  private JLabel tituloNomeTitular;
  private JLabel tituloCpf;
  private JLabel tituloAgencia;
  private JLabel tituloConta;
  private JLabel tituloBanco;

  private JTextField _bilhete;
  private JTextField _cpf;
  private JLabel imagemCpf;
  private JLabel imagemBanco;
  private JLabel imagemAgencia;
  private JLabel imagemConta;

  private JLabel alertaCpf;
  private JLabel alertaBanco;
  private JLabel alertaAgencia;
  private JLabel alertaConta;

  private JTextField nomeTitular;
  private JFormattedTextField cpf;
  private JTextField banco;
  private JTextField agencia;
  private JTextField conta;

  private JButton solicitarCancelamento;
  private JButton cancelar;
  private JButton oK;

  public CancelarPassagemUI(JPanel conteudo, ResourceBundle bundle) {
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
  public void mainMenu() {
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

    JLabel filtroBilhete = new JLabel(bundle.getString("cancelar.passagem.titulo.filtroBilhete"));
    JLabel filtroCpf = new JLabel(bundle.getString("cancelar.passagem.titulo.filtroCpf"));

    tituloNomeTitular = new JLabel(bundle.getString("cancelar.passagem.titulo.nometitular"));
    tituloBanco = new JLabel(bundle.getString("cancelar.passagem.titulo.banco"));
    tituloAgencia = new JLabel(bundle.getString("cancelar.passagem.titulo.agencia"));
    tituloConta = new JLabel(bundle.getString("cancelar.passagem.titulo.conta"));
    tituloCpf = new JLabel(bundle.getString("cancelar.passagem.titulo.cpf"));

    _bilhete = new JTextField();
    _cpf = new JTextField();

    nomeTitular = new JTextField(bundle.getString("cancelar.passagem.nomeTitular"));
    banco = new JTextField(bundle.getString("cancelar.passagem.banco"));
    agencia = new JTextField(bundle.getString("cancelar.passagem.agencia"));
    conta = new JTextField(bundle.getString("cancelar.passagem.conta"));

    try {
      cpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
    } catch (ParseException e) {
      ErrorSystem.addException(e, bundle);
    }

    solicitarCancelamento = new JButton(bundle.getString("cancelar.passagem.solicitarCancelamento"));
    cancelar = new JButton(bundle.getString("cancelar.passagem.cancelar"));
    oK = new JButton("Ok");

    imagemCpf = new JLabel();
    imagemBanco = new JLabel();
    imagemAgencia = new JLabel();
    imagemConta = new JLabel();

    alertaCpf = new JLabel();
    alertaBanco = new JLabel();
    alertaAgencia = new JLabel();
    alertaConta = new JLabel();

    Font font = new Font("Century Gothic", Font.ITALIC, 13);
    nomeTitular.setFont(font);
    banco.setFont(font);
    agencia.setFont(font);
    conta.setFont(font);

    nomeTitular.setForeground(Color.GRAY);
    banco.setForeground(Color.GRAY);
    agencia.setForeground(Color.GRAY);
    conta.setForeground(Color.GRAY);

    imagem.setBounds(100, 70, 30, 30);
    filtroBilhete.setBounds(130, 45, 200, 30);
    filtroCpf.setBounds(220, 45, 200, 30);

    _bilhete.setBounds(130, 70, 80, 30);
    _cpf.setBounds(220, 70, 80, 30);
    solicitarCancelamento.setBounds(320, 70, 200, 30);

    imagemCpf.setBounds(510, 230, 100, 30);
    imagemBanco.setBounds(510, 270, 100, 30);
    imagemAgencia.setBounds(510, 310, 100, 30);
    imagemConta.setBounds(510, 350, 100, 30);

    alertaCpf.setBounds(535, 230, 100, 30);
    alertaBanco.setBounds(535, 270, 400, 30);
    alertaAgencia.setBounds(535, 310, 400, 30);
    alertaConta.setBounds(535, 350, 400, 30);

    tituloNomeTitular.setBounds(60, 190, 200, 30);
    tituloCpf.setBounds(60, 230, 200, 30);
    tituloBanco.setBounds(60, 270, 200, 30);
    tituloAgencia.setBounds(60, 310, 200, 30);
    tituloConta.setBounds(60, 350, 200, 30);

    nomeTitular.setBounds(200, 190, 300, 30);
    cpf.setBounds(200, 230, 300, 30);
    banco.setBounds(200, 270, 300, 30);
    agencia.setBounds(200, 310, 300, 30);
    conta.setBounds(200, 350, 300, 30);
    oK.setBounds(400, 390, 100, 30);
    cancelar.setBounds(320, 475, 200, 30);

    conteudo.add(imagem);
    conteudo.add(filtroBilhete);
    conteudo.add(filtroCpf);

    conteudo.add(_bilhete);
    conteudo.add(_cpf);
    conteudo.add(solicitarCancelamento);

    repaint();
  }

  // Get parameters
  protected RequestParamWrapper getParameters() {
    RequestParamWrapper request = new RequestParamWrapper();
    request.set("nometitular", nomeTitular.getText());
    request.set("cpf", cpf.getText());
    request.set("banco", banco.getText());
    request.set("agencia", agencia.getText());
    request.set("conta", conta.getText());

    return request;
  }

  protected RequestParamWrapper getDefaultTexts() {
    RequestParamWrapper request = new RequestParamWrapper();
    request.set("banco", bundle.getString("cancelar.passagem.banco"));
    request.set("agencia", bundle.getString("cancelar.passagem.agencia"));
    request.set("conta", bundle.getString("cancelar.passagem.conta"));

    return request;
  }

  protected void addSolicitarCancelamentoListener(ActionListener a) {
    solicitarCancelamento.addActionListener(a);
  }

  protected void addOKListener(ActionListener a) {
    oK.addActionListener(a);
  }

  protected void addEfetuarCancelamentoListener(ActionListener a) {
    cancelar.addActionListener(a);
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

  protected void addFocusCpfListener(FocusListener a) {
    cpf.addFocusListener(a);
  }

  protected void addFocusListener(FocusListener a) {
    nomeTitular.addFocusListener(a);
    banco.addFocusListener(a);
    agencia.addFocusListener(a);
    conta.addFocusListener(a);

    ((FocusTextField) a).setField(nomeTitular, banco, agencia, conta);
    ((FocusTextField) a).setText(bundle.getString("cancelar.passagem.nomeTitular"),
        bundle.getString("cancelar.passagem.banco"),
        bundle.getString("cancelar.passagem.agencia"),
        bundle.getString("cancelar.passagem.conta"));
  }

  protected void addCalcularPassagemButton() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("cancelar.passagem.erro.solicitacao"),
        bundle.getString("cancelar.passagem.titulo"),
        JOptionPane.ERROR_MESSAGE);

    conteudo.add(tituloNomeTitular);
    conteudo.add(tituloBanco);
    conteudo.add(tituloAgencia);
    conteudo.add(tituloConta);
    conteudo.add(tituloCpf);

    conteudo.add(nomeTitular);
    conteudo.add(banco);
    conteudo.add(agencia);
    conteudo.add(conta);
    conteudo.add(cpf);
    conteudo.add(oK);

    repaint();
  }

  protected void addEfetuarCancelamentoButton() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("cancelar.passagem.ok.solicitacao"),
        bundle.getString("cancelar.passagem.titulo"),
        JOptionPane.INFORMATION_MESSAGE);

    conteudo.add(cancelar);

    repaint();
  }

  protected void messageOK() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("cancelar.passagem.cancelado"),
        bundle.getString("cancelar.passagem.titulo"),
        JOptionPane.INFORMATION_MESSAGE);

    refresh();
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