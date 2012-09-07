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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.com.moonjava.flight.model.financeiro.Bandeira;
import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.ErrorSystem;

/**
 * @version 1.0 Sep 7, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class CartaoUI {

  private final ResourceBundle bundle;
  private final JPanel conteudo;
  private final JDialog frame;

  private JLabel tituloNomeTitular;
  private JLabel tituloNumero;
  private JLabel tituloCpf;
  private JLabel tituloValidade;
  private JLabel tituloCodSeguranca;
  private JLabel tituloBandeira;

  private JTextField nomeTitular;
  private JTextField numero;
  private JTextField codSeguranca;
  private JFormattedTextField validade;
  private JFormattedTextField cpf;
  private JComboBox bandeira;

  private JButton oK;
  private JLabel imagemCpf;
  private JLabel alerta;

  private boolean valid;

  public CartaoUI(ResourceBundle bundle) {
    this.bundle = bundle;

    frame = new JDialog();
    frame.setTitle(bundle.getString("cartao.titulo"));
    frame.setModal(true);

    conteudo = new JPanel(null);
    conteudo.setBounds(30, 30, 1130, 600);
    frame.getContentPane().add(conteudo);

    dialogMenu();
    showAll();
  }

  public void dialogMenu() {
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

    imagemCpf = new JLabel();
    alerta = new JLabel();

    Font font = new Font("Century Gothic", Font.ITALIC, 13);
    nomeTitular.setFont(font);
    numero.setFont(font);
    codSeguranca.setFont(font);
    validade.setFont(font);
    bandeira.setFont(font);

    nomeTitular.setForeground(Color.GRAY);
    numero.setForeground(Color.GRAY);
    codSeguranca.setForeground(Color.GRAY);
    validade.setForeground(Color.GRAY);
    bandeira.setForeground(Color.GRAY);

    imagemCpf.setBounds(540, 130, 100, 30);
    alerta.setBounds(565, 130, 100, 30);

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
    oK.setBounds(430, 290, 100, 30);

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

    conteudo.repaint();
    conteudo.validate();

    oK.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
        setValid(true);
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

  }

  public boolean isValid() {
    return valid;
  }

  public void setValid(boolean valid) {
    this.valid = valid;
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
    int frameWidth = 670;
    int frameHeight = 400;

    frame.setLocation((width / 2) - (frameWidth / 2), (height / 2) - (frameHeight / 2));
    frame.setSize(frameWidth, frameHeight);

    frame.setResizable(false);
    frame.setVisible(true);
  }

}
