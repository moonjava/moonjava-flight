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
package br.com.moonjava.flight.app.usuario.copy;

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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.com.moonjava.flight.base.Perfil;
import br.com.moonjava.flight.base.action.UsuarioAction;
import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.GerarCodigo;
import br.com.moonjava.flight.util.JTextFieldLimit;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Aug 21, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class CriarUsuarioUI implements ActionListener, FocusListener {

  private final JPanel conteudo;
  private final ResourceBundle bundle;
  private final JButton atualizar;
  private final JButton deletar;
  private final UsuarioAction action;
  private final RequestParamWrapper request;

  private JTextField nome;
  private JTextField sobrenome;
  private JTextField nascimento;
  private JTextField cpf;
  private JTextField rg;
  private JTextField endereco;
  private JTextField telResidencial;
  private JTextField telCelular;
  private JTextField email;

  private JComboBox perfil;
  private JTextField usuario;
  private JPasswordField senha;
  private JLabel imagem;
  private Font antes;
  private JLabel codigo;
  private JLabel alerta;

  public CriarUsuarioUI(JPanel conteudo, ResourceBundle bundle, JButton atualizar, JButton deletar) {
    this.conteudo = conteudo;
    this.bundle = bundle;
    this.atualizar = atualizar;
    this.deletar = deletar;
    action = new UsuarioAction();
    request = new RequestParamWrapper();
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    atualizar.setEnabled(false);
    deletar.setEnabled(false);
    conteudo.removeAll();
    conteudo.validate();
    conteudo.repaint();

    JLabel tituloCodigo = new JLabel(bundle.getString("criar.usuario.titulo.codigo"));
    JLabel tituloNome = new JLabel(bundle.getString("criar.pessoafisica.titulo.nome"));
    JLabel tituloSobrenome = new JLabel(bundle.getString("criar.pessoafisica.titulo.sobrenome"));
    JLabel tituloNascimento = new JLabel(bundle.getString("criar.pessoafisica.titulo.nascimento"));
    JLabel tituloCpf = new JLabel(bundle.getString("criar.pessoafisica.titulo.cpf"));
    JLabel tituloRg = new JLabel(bundle.getString("criar.pessoafisica.titulo.rg"));
    JLabel tituloEndereco = new JLabel(bundle.getString("criar.pessoafisica.titulo.endereco"));
    JLabel tituloTelResidencial = new JLabel(
        bundle.getString("criar.pessoafisica.titulo.telResidencial"));
    JLabel tituloTelCelular = new JLabel(bundle.getString("criar.pessoafisica.titulo.telCelular"));
    JLabel tituloEmail = new JLabel(bundle.getString("criar.pessoafisica.titulo.email"));

    JLabel tituloPerfil = new JLabel(bundle.getString("criar.usuario.titulo.perfil"));
    JLabel tituloUsuario = new JLabel(bundle.getString("criar.usuario.titulo.usuario"));
    JLabel tituloSenha = new JLabel(bundle.getString("criar.usuario.titulo.senha"));

    antes = new Font("Century Gothic", Font.ITALIC, 13);

    GerarCodigo gerarCodigo = new GerarCodigo("USUARIO");
    codigo = new JLabel(gerarCodigo.getCodigo());

    nome = new JTextField(bundle.getString("criar.pessoafisica.antes.nome"));
    nome.setFont(antes);
    nome.setForeground(Color.GRAY);

    sobrenome = new JTextField(bundle.getString("criar.pessoafisica.antes.sobrenome"));
    sobrenome.setFont(antes);
    sobrenome.setForeground(Color.GRAY);

    try {
      nascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
      cpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
    } catch (ParseException e) {
      e.printStackTrace();
    }

    rg = new JTextField(bundle.getString("criar.pessoafisica.antes.rg"));
    rg.setFont(antes);
    rg.setForeground(Color.GRAY);

    endereco = new JTextField(bundle.getString("criar.pessoafisica.antes.endereco"));
    endereco.setFont(antes);
    endereco.setForeground(Color.GRAY);

    telResidencial = new JTextField(bundle.getString("criar.pessoafisica.antes.telResidencial"));
    telResidencial.setFont(antes);
    telResidencial.setForeground(Color.GRAY);

    telCelular = new JTextField(bundle.getString("criar.pessoafisica.antes.telCelular"));
    telCelular.setFont(antes);
    telCelular.setForeground(Color.GRAY);

    email = new JTextField(bundle.getString("criar.pessoafisica.antes.email"));
    email.setFont(antes);
    email.setForeground(Color.GRAY);

    Perfil[] perfis = Perfil.values();
    perfil = new JComboBox(perfis);

    usuario = new JTextField(bundle.getString("criar.usuario.antes.usuario"));
    usuario.setFont(antes);
    usuario.setForeground(Color.GRAY);

    senha = new JPasswordField();
    senha.setDocument(new JTextFieldLimit(50));

    imagem = new JLabel();
    alerta = new JLabel();

    nome.addFocusListener(this);
    sobrenome.addFocusListener(this);
    cpf.addFocusListener(this);
    rg.addFocusListener(this);
    endereco.addFocusListener(this);
    telResidencial.addFocusListener(this);
    telCelular.addFocusListener(this);
    email.addFocusListener(this);

    usuario.addFocusListener(this);

    tituloCodigo.setBounds(60, 35, 100, 30);
    tituloNome.setBounds(60, 70, 200, 30);
    tituloSobrenome.setBounds(60, 105, 200, 30);
    tituloNascimento.setBounds(60, 140, 160, 30);
    tituloCpf.setBounds(60, 175, 40, 30);
    tituloRg.setBounds(60, 210, 40, 30);
    tituloEndereco.setBounds(60, 245, 100, 30);
    tituloTelResidencial.setBounds(60, 280, 140, 30);
    tituloTelCelular.setBounds(60, 315, 140, 30);
    tituloEmail.setBounds(60, 355, 160, 30);

    tituloPerfil.setBounds(60, 390, 80, 30);
    tituloUsuario.setBounds(60, 425, 100, 30);
    tituloSenha.setBounds(60, 460, 100, 30);

    alerta.setBounds(404, 175, 100, 30);

    codigo.setBounds(200, 35, 100, 30);
    nome.setBounds(200, 70, 300, 30);
    sobrenome.setBounds(200, 105, 300, 30);
    nascimento.setBounds(200, 140, 180, 30);
    cpf.setBounds(200, 175, 180, 30);
    rg.setBounds(200, 210, 180, 30);
    endereco.setBounds(200, 245, 300, 30);
    telResidencial.setBounds(200, 280, 180, 30);
    telCelular.setBounds(200, 315, 180, 30);
    email.setBounds(200, 355, 300, 30);

    perfil.setBounds(200, 390, 250, 30);
    usuario.setBounds(200, 425, 230, 30);
    senha.setBounds(200, 460, 230, 30);

    imagem.setBounds(380, 175, 40, 30);

    JButton cadastrar = new JButton(bundle.getString("criar.usuario.botao.cadastrar"));

    cadastrar.addActionListener(new CadastrarHandler());

    conteudo.add(tituloCodigo);
    conteudo.add(tituloNome);
    conteudo.add(tituloSobrenome);
    conteudo.add(tituloNascimento);
    conteudo.add(tituloCpf);
    conteudo.add(tituloRg);
    conteudo.add(tituloEndereco);
    conteudo.add(tituloTelResidencial);
    conteudo.add(tituloTelCelular);
    conteudo.add(tituloEmail);

    conteudo.add(tituloPerfil);
    conteudo.add(tituloUsuario);
    conteudo.add(tituloSenha);

    conteudo.add(codigo);
    conteudo.add(nome);
    conteudo.add(sobrenome);
    conteudo.add(nascimento);
    conteudo.add(cpf);
    conteudo.add(rg);
    conteudo.add(endereco);
    conteudo.add(telResidencial);
    conteudo.add(telCelular);
    conteudo.add(email);

    conteudo.add(perfil);
    conteudo.add(usuario);
    conteudo.add(senha);

    conteudo.add(cadastrar);

    conteudo.repaint();
    conteudo.validate();
  }

  private class CadastrarHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {

    }

  }

  @Override
  public void focusGained(FocusEvent e) {

    Font depois = new Font("Arial", Font.PLAIN, 13);

    if (e.getSource() == nome
        && nome.getText().equals(bundle.getString("criar.pessoafisica.antes.nome"))) {
      nome.setText("");
      nome.setFont(depois);
      nome.setForeground(Color.BLACK);
      nome.setDocument(new JTextFieldLimit(30));
    }
    if (e.getSource() == sobrenome
        && sobrenome.getText().equals(bundle.getString("criar.pessoafisica.antes.sobrenome"))) {
      sobrenome.setText("");
      sobrenome.setFont(depois);
      sobrenome.setForeground(Color.BLACK);
      sobrenome.setDocument(new JTextFieldLimit(80));
    }
    if (e.getSource() == rg && rg.getText().equals(bundle.getString("criar.pessoafisica.antes.rg"))) {
      rg.setText("");
      rg.setFont(depois);
      rg.setForeground(Color.BLACK);
      rg.setDocument(new JTextFieldLimit(15));
    }
    if (e.getSource() == endereco
        && endereco.getText().equals(bundle.getString("criar.pessoafisica.antes.endereco"))) {
      endereco.setText("");
      endereco.setFont(depois);
      endereco.setForeground(Color.BLACK);
      endereco.setDocument(new JTextFieldLimit(100));
    }
    if (e.getSource() == telResidencial
        && telResidencial.getText().equals(
            bundle.getString("criar.pessoafisica.antes.telResidencial"))) {
      telResidencial.setText("");
      telResidencial.setFont(depois);
      telResidencial.setForeground(Color.BLACK);
      telResidencial.setDocument(new JTextFieldLimit(30));
    }
    if (e.getSource() == telCelular
        && telCelular.getText().equals(bundle.getString("criar.pessoafisica.antes.telCelular"))) {
      telCelular.setText("");
      telCelular.setFont(depois);
      telCelular.setForeground(Color.BLACK);
      telCelular.setDocument(new JTextFieldLimit(30));
    }
    if (e.getSource() == email
        && email.getText().equals(bundle.getString("criar.pessoafisica.antes.email"))) {
      email.setText("");
      email.setFont(depois);
      email.setForeground(Color.BLACK);
      email.setDocument(new JTextFieldLimit(100));
    }
    if (e.getSource() == usuario
        && usuario.getText().equals(bundle.getString("criar.usuario.antes.usuario"))) {
      usuario.setText("");
      usuario.setFont(depois);
      usuario.setForeground(Color.BLACK);
      usuario.setDocument(new JTextFieldLimit(50));
    }
  }

  @Override
  public void focusLost(FocusEvent e) {
    if (e.getSource() == nome && nome.getText().equals("")) {
      nome.setText(bundle.getString("criar.pessoafisica.antes.nome"));
      nome.setFont(antes);
      nome.setForeground(Color.GRAY);
    }
    if (e.getSource() == sobrenome && sobrenome.getText().equals("")) {
      sobrenome.setText(bundle.getString("criar.pessoafisica.antes.sobrenome"));
      sobrenome.setFont(antes);
      sobrenome.setForeground(Color.GRAY);
    }
    if (e.getSource() == cpf) {
      try {
        CPF.parse(cpf.getText());
        InputStream stream = getClass().getResourceAsStream("/img/icon_disponivel.png");
        Image image = null;

        try {
          image = ImageIO.read(stream);
        } catch (IOException e1) {
          e1.printStackTrace();
        }

        ImageIcon icon = new ImageIcon(image);
        imagem.setIcon(icon);
        alerta.setText("");
        conteudo.add(imagem);
        conteudo.add(alerta);
        conteudo.repaint();
        conteudo.validate();
      } catch (Exception e2) {
        InputStream stream = getClass().getResourceAsStream("/img/icon_indisponivel.png");
        Image image = null;

        try {
          image = ImageIO.read(stream);
        } catch (IOException e1) {
          e1.printStackTrace();
        }

        ImageIcon icon = new ImageIcon(image);
        imagem.setIcon(icon);

        Font fontAlerta = new Font("Arial", Font.BOLD, 13);
        alerta.setFont(fontAlerta);
        alerta.setForeground(Color.RED);
        alerta.setText(bundle.getString("criar.pessoafisica.cpf.alerta.erro"));
        conteudo.add(imagem);
        conteudo.add(alerta);
        conteudo.repaint();
        conteudo.validate();
      }
    }
    if (e.getSource() == rg && rg.getText().equals("")) {
      rg.setText(bundle.getString("criar.pessoafisica.antes.rg"));
      rg.setFont(antes);
      rg.setForeground(Color.GRAY);
    }
    if (e.getSource() == endereco && endereco.getText().equals("")) {
      endereco.setText(bundle.getString("criar.pessoafisica.antes.endereco"));
      endereco.setFont(antes);
      endereco.setForeground(Color.GRAY);
    }
    if (e.getSource() == telResidencial && telResidencial.getText().equals("")) {
      telResidencial.setText(bundle.getString("criar.pessoafisica.antes.telResidencial"));
      telResidencial.setFont(antes);
      telResidencial.setForeground(Color.GRAY);
    }
    if (e.getSource() == telCelular && telCelular.getText().equals("")) {
      telCelular.setText(bundle.getString("criar.pessoafisica.antes.telCelular"));
      telCelular.setFont(antes);
      telCelular.setForeground(Color.GRAY);
    }
    if (e.getSource() == email && email.getText().equals("")) {
      email.setText(bundle.getString("criar.pessoafisica.antes.email"));
      email.setFont(antes);
      email.setForeground(Color.GRAY);
    }
    if (e.getSource() == usuario && usuario.getText().equals("")) {
      usuario.setText(bundle.getString("criar.usuario.antes.usuario"));
      usuario.setFont(antes);
      usuario.setForeground(Color.GRAY);
    }

  }
}