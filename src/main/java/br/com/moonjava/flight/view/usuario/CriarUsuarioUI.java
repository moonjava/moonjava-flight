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
package br.com.moonjava.flight.view.usuario;

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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.com.moonjava.flight.model.base.Perfil;
import br.com.moonjava.flight.util.FocusTextField;
import br.com.moonjava.flight.util.GerarCodigo;
import br.com.moonjava.flight.util.JTextFieldLimit;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Aug 21, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class CriarUsuarioUI {

  private final JPanel conteudo;
  private final ResourceBundle bundle;
  private final JButton atualizar;
  private final JButton deletar;

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
  private JTextField login;
  private JPasswordField senha;
  private JLabel imagem;
  private JLabel codigo;
  private JLabel alerta;
  private JButton cadastrar;

  public CriarUsuarioUI(JPanel conteudo,
                        ResourceBundle bundle,
                        JButton atualizar,
                        JButton deletar) {
    this.conteudo = conteudo;
    this.bundle = bundle;
    this.atualizar = atualizar;
    this.deletar = deletar;

    disableButtons();
    refresh();
    mainMenu();
  }

  public void mainMenu() {
    // Titulos
    JLabel tituloCodigo = new JLabel(bundle.getString("criar.usuario.titulo.codigo"));
    JLabel tituloNome = new JLabel(bundle.getString("criar.pessoafisica.titulo.nome"));
    JLabel tituloSobrenome = new JLabel(bundle.getString("criar.pessoafisica.titulo.sobrenome"));
    JLabel tituloNascimento = new JLabel(bundle.getString("criar.pessoafisica.titulo.nascimento"));
    JLabel tituloCpf = new JLabel(bundle.getString("criar.pessoafisica.titulo.cpf"));
    JLabel tituloRg = new JLabel(bundle.getString("criar.pessoafisica.titulo.rg"));
    JLabel tituloEndereco = new JLabel(bundle.getString("criar.pessoafisica.titulo.endereco"));
    JLabel tituloTelRes = new JLabel(bundle.getString("criar.pessoafisica.titulo.telResidencial"));
    JLabel tituloTelCelular = new JLabel(bundle.getString("criar.pessoafisica.titulo.telCelular"));
    JLabel tituloEmail = new JLabel(bundle.getString("criar.pessoafisica.titulo.email"));
    JLabel tituloPerfil = new JLabel(bundle.getString("criar.usuario.titulo.perfil"));
    JLabel tituloLogin = new JLabel(bundle.getString("criar.usuario.titulo.usuario"));
    JLabel tituloSenha = new JLabel(bundle.getString("criar.usuario.titulo.senha"));

    // Botoes e caixas de textos
    GerarCodigo gerarCodigo = new GerarCodigo("USUARIO");
    codigo = new JLabel(gerarCodigo.getCodigo());
    nome = new JTextField(bundle.getString("criar.pessoafisica.antes.nome"));
    sobrenome = new JTextField(bundle.getString("criar.pessoafisica.antes.sobrenome"));
    rg = new JTextField(bundle.getString("criar.pessoafisica.antes.rg"));
    endereco = new JTextField(bundle.getString("criar.pessoafisica.antes.endereco"));
    telResidencial = new JTextField(bundle.getString("criar.pessoafisica.antes.telResidencial"));
    telCelular = new JTextField(bundle.getString("criar.pessoafisica.antes.telCelular"));
    email = new JTextField(bundle.getString("criar.pessoafisica.antes.email"));
    login = new JTextField(bundle.getString("criar.usuario.antes.login"));
    senha = new JPasswordField();
    cadastrar = new JButton(bundle.getString("criar.usuario.botao.cadastrar"));

    Perfil[] val = Perfil.values();
    String[] perfis = new String[val.length];
    for (int i = 0; i < perfis.length; i++) {
      perfis[i] = val[i].setBundle(bundle);
    }
    perfil = new JComboBox(perfis);

    try {
      nascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
      cpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
    } catch (ParseException e) {
      e.printStackTrace();
    }

    imagem = new JLabel();
    alerta = new JLabel();

    Font font = new Font("Century Gothic", Font.ITALIC, 13);
    nome.setFont(font);
    sobrenome.setFont(font);
    rg.setFont(font);
    endereco.setFont(font);
    telResidencial.setFont(font);
    telCelular.setFont(font);
    email.setFont(font);
    login.setFont(font);

    nome.setForeground(Color.GRAY);
    sobrenome.setForeground(Color.GRAY);
    rg.setForeground(Color.GRAY);
    endereco.setForeground(Color.GRAY);
    telResidencial.setForeground(Color.GRAY);
    telCelular.setForeground(Color.GRAY);
    email.setForeground(Color.GRAY);
    login.setForeground(Color.GRAY);

    senha.setDocument(new JTextFieldLimit(50));

    tituloCodigo.setBounds(60, 35, 100, 30);
    tituloNome.setBounds(60, 70, 200, 30);
    tituloSobrenome.setBounds(60, 105, 200, 30);
    tituloNascimento.setBounds(60, 140, 160, 30);
    tituloCpf.setBounds(60, 175, 40, 30);
    tituloRg.setBounds(60, 210, 40, 30);
    tituloEndereco.setBounds(60, 245, 100, 30);
    tituloTelRes.setBounds(60, 280, 140, 30);
    tituloTelCelular.setBounds(60, 315, 140, 30);
    tituloEmail.setBounds(60, 355, 160, 30);
    tituloPerfil.setBounds(60, 390, 80, 30);
    tituloLogin.setBounds(60, 425, 100, 30);
    tituloSenha.setBounds(60, 460, 100, 30);

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
    login.setBounds(200, 425, 230, 30);
    senha.setBounds(200, 460, 230, 30);
    cadastrar.setBounds(600, 460, 150, 30);

    alerta.setBounds(404, 175, 100, 30);
    imagem.setBounds(380, 175, 40, 30);

    conteudo.add(tituloCodigo);
    conteudo.add(tituloNome);
    conteudo.add(tituloSobrenome);
    conteudo.add(tituloNascimento);
    conteudo.add(tituloCpf);
    conteudo.add(tituloRg);
    conteudo.add(tituloEndereco);
    conteudo.add(tituloTelRes);
    conteudo.add(tituloTelCelular);
    conteudo.add(tituloEmail);
    conteudo.add(tituloPerfil);
    conteudo.add(tituloLogin);
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
    conteudo.add(login);
    conteudo.add(senha);
    conteudo.add(cadastrar);

    conteudo.repaint();
    conteudo.validate();
  }

  // Listeners
  public void addCadastrarListener(ActionListener a) {
    cadastrar.addActionListener(a);
  }

  public void addFocusListener(FocusListener a) {
    nome.addFocusListener(a);
    sobrenome.addFocusListener(a);
    rg.addFocusListener(a);
    endereco.addFocusListener(a);
    telResidencial.addFocusListener(a);
    telCelular.addFocusListener(a);
    email.addFocusListener(a);
    login.addFocusListener(a);

    ((FocusTextField) a).setField(
        nome, sobrenome, rg, endereco, telResidencial,
        telCelular, email, login);

    ((FocusTextField) a).setText(
        bundle.getString("criar.pessoafisica.antes.nome"),
        bundle.getString("criar.pessoafisica.antes.sobrenome"),
        bundle.getString("criar.pessoafisica.antes.rg"),
        bundle.getString("criar.pessoafisica.antes.endereco"),
        bundle.getString("criar.pessoafisica.antes.telResidencial"),
        bundle.getString("criar.pessoafisica.antes.telCelular"),
        bundle.getString("criar.pessoafisica.antes.email"),
        bundle.getString("criar.usuario.antes.login"));
  }

  public void addFocusCpfListener(FocusListener a) {
    cpf.addFocusListener(a);
  }

  public void addFocusTelResListener(FocusListener a) {
    telResidencial.addFocusListener(a);
  }

  public void addFocusTelCelListener(FocusListener a) {
    telCelular.addFocusListener(a);
  }

  // Getters
  public String getCountry() {
    return bundle.getLocale().getCountry();
  }

  public JTextField getCpf() {
    return cpf;
  }

  public JTextField getTelResidencial() {
    return telResidencial;
  }

  public JTextField getTelCelular() {
    return telCelular;
  }

  public String getTextTelResidencial() {
    return bundle.getString("criar.pessoafisica.antes.telResidencial");
  }

  public String getTextTelCelular() {
    return bundle.getString("criar.pessoafisica.antes.telCelular");
  }

  public RequestParamWrapper getParameters() {
    RequestParamWrapper request = new RequestParamWrapper();
    request.set("codigo", codigo.getText());
    request.set("nome", nome.getText());
    request.set("sobrenome", sobrenome.getText());
    request.set("nascimento", nascimento.getText());
    request.set("cpf", cpf.getText());
    request.set("rg", rg.getText());
    request.set("endereco", endereco.getText());
    request.set("telResidencial", telResidencial.getText());
    request.set("telCelular", telCelular.getText());
    request.set("email", email.getText());
    request.set("perfil", perfil.getSelectedItem());
    request.set("login", login.getText());
    request.set("senha", String.valueOf(senha.getPassword()));
    return request;
  }

  public RequestParamWrapper getTexts() {
    RequestParamWrapper request = new RequestParamWrapper();
    request.set("nome", bundle.getString("criar.pessoafisica.antes.nome"));
    request.set("sobrenome", bundle.getString("criar.pessoafisica.antes.sobrenome"));
    request.set("rg", bundle.getString("criar.pessoafisica.antes.rg"));
    request.set("endereco", bundle.getString("criar.pessoafisica.antes.endereco"));
    request.set("telResidencial", bundle.getString("criar.pessoafisica.antes.telResidencial"));
    request.set("telCelular", bundle.getString("criar.pessoafisica.antes.telCelular"));
    request.set("login", bundle.getString("criar.usuario.antes.login"));
    return request;
  }

  // Layouts
  public void addImageCpfValido() {
    try {
      InputStream stream = getClass().getResourceAsStream("/img/icon_disponivel.png");
      Image image;
      image = ImageIO.read(stream);
      ImageIcon icon = new ImageIcon(image);
      imagem.setIcon(icon);
      alerta.setText("");

      conteudo.add(imagem);
      conteudo.add(alerta);
      conteudo.repaint();
      conteudo.validate();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void addImageCpfInvalido() {
    try {
      InputStream stream = getClass().getResourceAsStream("/img/icon_indisponivel.png");
      Image image = ImageIO.read(stream);

      ImageIcon icon = new ImageIcon(image);
      imagem.setIcon(icon);

      alerta.setFont(new Font("Arial", Font.BOLD, 13));
      alerta.setForeground(Color.RED);
      alerta.setText(bundle.getString("criar.pessoafisica.cpf.alerta.erro"));

      conteudo.add(imagem);
      conteudo.add(alerta);
      conteudo.repaint();
      conteudo.validate();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }

  public void messageFailed() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("criar.voo.joption.tempo"),
        bundle.getString("criar.voo.joption.titulo"),
        JOptionPane.ERROR_MESSAGE);
  }

  // Adicionar titulo
  public void messageOK() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("criar.usuario.sucesso"),
        "OK",
        JOptionPane.INFORMATION_MESSAGE);
  }

  // Adicionar titulo
  public void messageTelParseExecption() {
    JOptionPane.showMessageDialog(null, bundle.getString("criar.usuario.erro.tel"));
  }

  public void messageCpfInvalidExecption() {
    JOptionPane.showMessageDialog(null, bundle.getString("criar.pessoafisica.cpf.alerta.erro"));
  }

  // Adicionar titulo
  public void messageUsuarioExistente() {
    JOptionPane.showMessageDialog(null, bundle.getString("criar.usuario.erro"),
        "",
        JOptionPane.ERROR_MESSAGE);
  }

  public void disableButtons() {
    atualizar.setEnabled(false);
    deletar.setEnabled(false);
  }

  public void refresh() {
    conteudo.removeAll();
    conteudo.validate();
    conteudo.repaint();
  }

}