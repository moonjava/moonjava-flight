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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
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

import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.ErrorSystem;

/**
 * @version 1.0 Sep 2, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class CancelarPassagemUI {

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
  private JLabel alerta;

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
    alerta = new JLabel();

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
    alerta.setBounds(535, 230, 100, 30);

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

    conteudo.repaint();
    conteudo.validate();

    // TestsHandler
    solicitarCancelamento.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
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

        conteudo.repaint();
        conteudo.validate();
      }
    });

    oK.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,
            bundle.getString("cancelar.passagem.ok.solicitacao"),
            bundle.getString("cancelar.passagem.titulo"),
            JOptionPane.INFORMATION_MESSAGE);

        conteudo.add(cancelar);

        conteudo.repaint();
        conteudo.validate();

      }
    });

    cpf.addFocusListener(new FocusListener() {
      @Override
      public void focusLost(FocusEvent arg0) {
        String _cpf = cpf.getText();
        try {
          CPF.parse(_cpf);
          addImageCpfValido();
        } catch (Exception e) {
          addImageCpfInvalido();
        }
      }
      @Override
      public void focusGained(FocusEvent arg0) {
      }
    });

    cancelar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        JOptionPane.showMessageDialog(null,
            bundle.getString("cancelar.passagem.cancelado"),
            bundle.getString("cancelar.passagem.titulo"),
            JOptionPane.INFORMATION_MESSAGE);

        refresh();
      }
    });
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

  public void refresh() {
    conteudo.removeAll();
    conteudo.validate();
    conteudo.repaint();
  }

}