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
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.com.moonjava.flight.util.ErrorSystem;
import br.com.moonjava.flight.util.FocusTextField;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Sep 7, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */

public class ChequeUI {

  private final ResourceBundle bundle;
  private final JPanel conteudo;
  private final JDialog frame;

  private JLabel tituloNomeTitular;
  private JLabel tituloNumero;
  private JLabel tituloCpf;
  private JLabel tituloBanco;
  private JLabel tituloAgencia;
  private JLabel tituloConta;

  private JTextField nomeTitular;
  private JTextField numero;
  private JTextField banco;
  private JTextField agencia;
  private JTextField conta;
  private JFormattedTextField cpf;

  private JButton oK;
  private JLabel imagemCpf;
  private JLabel alerta;

  private boolean valid;

  public ChequeUI(ResourceBundle bundle) {
    this.bundle = bundle;

    frame = new JDialog();
    frame.setTitle(bundle.getString("cheque.titulo"));
    frame.setModal(true);

    conteudo = new JPanel(null);
    conteudo.setBounds(30, 30, 1130, 600);
    frame.getContentPane().add(conteudo);

    dialogMenu();
    showAll();
  }

  public void dialogMenu() {
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

    imagemCpf = new JLabel();
    alerta = new JLabel();

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

    imagemCpf.setBounds(510, 130, 100, 30);
    alerta.setBounds(535, 130, 100, 30);

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
    oK.setBounds(400, 290, 100, 30);

    conteudo.repaint();
    conteudo.validate();

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

    conteudo.repaint();
    conteudo.validate();
  }

  public void addFocusListener(FocusListener a) {
    nomeTitular.addFocusListener(a);
    numero.addFocusListener(a);
    banco.addFocusListener(a);
    agencia.addFocusListener(a);
    conta.addFocusListener(a);

    ((FocusTextField) a).setField(nomeTitular, numero, banco, agencia, conta);
    ((FocusTextField) a).setText(bundle.getString("cheque.titulo.nometitular"), "2", "3", "4", "5");
  }

  public void addOkListener(ActionListener a) {
    oK.addActionListener(a);
  }

  public void addFocusCpfListener(FocusListener a) {
    cpf.addFocusListener(a);
  }

  public RequestParamWrapper getParameters() {
    RequestParamWrapper request = new RequestParamWrapper();
    request.set("cpf", cpf.getText());

    return request;
  }

  public boolean isParemeterValid() {
    return valid;
  }

  public void setParameterValid(boolean valid) {
    this.valid = valid;
  }

  public void dispose() {
    frame.dispose();
  }

  public void addImageCpfValido() {
    try {
      InputStream stream = getClass().getResourceAsStream("/img/icon_disponivel.png");
      Image image = ImageIO.read(stream);
      ImageIcon icon = new ImageIcon(image);
      imagemCpf.setIcon(icon);
      alerta.setText("");

      conteudo.add(imagemCpf);
      conteudo.add(alerta);
      conteudo.repaint();
      conteudo.validate();
    } catch (IOException e) {
      ErrorSystem.addException(e, bundle);
    }
  }

  public void addImageCpfInvalido() {
    try {
      InputStream stream = getClass().getResourceAsStream("/img/icon_indisponivel.png");
      Image image = ImageIO.read(stream);
      ImageIcon icon = new ImageIcon(image);
      imagemCpf.setIcon(icon);

      alerta.setFont(new Font("Arial", Font.BOLD, 13));
      alerta.setForeground(Color.RED);
      alerta.setText(bundle.getString("criar.pessoafisica.cpf.alerta.erro"));

      conteudo.add(imagemCpf);
      conteudo.add(alerta);
      conteudo.repaint();
      conteudo.validate();
    } catch (IOException e) {
      ErrorSystem.addException(e, bundle);
    }
  }

  private void showAll() {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int width = dimension.width;
    int height = dimension.height;
    int frameWidth = 640;
    int frameHeight = 400;

    frame.setLocation((width / 2) - (frameWidth / 2), (height / 2) - (frameHeight / 2));
    frame.setSize(frameWidth, frameHeight);

    frame.setResizable(false);
    frame.setVisible(true);
  }

}