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

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
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
import br.com.moonjava.flight.model.base.PessoaFisica;
import br.com.moonjava.flight.model.base.Usuario;
import br.com.moonjava.flight.util.FlightImageUI;
import br.com.moonjava.flight.util.FocusTextField;
import br.com.moonjava.flight.util.FormatDateTime;
import br.com.moonjava.flight.util.JTextFieldLimit;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Sep 3, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class AtualizarUsuarioUI {

  private JPanel conteudo;
  protected ResourceBundle bundle;
  private JButton atualizar;
  private JButton deletar;
  private JLabel codigo;
  private JTextField nome;
  private JTextField sobrenome;
  private JTextField rg;
  private JTextField endereco;
  private JTextField telResidencial;
  private JTextField telCelular;
  private JTextField email;
  private JTextField login;
  private JPasswordField senha;
  private JButton enviar;
  private JComboBox perfil;
  private JFormattedTextField nascimento;
  private JFormattedTextField cpf;
  private JLabel imagem;
  private JLabel alerta;
  private PessoaFisica pf;
  private Usuario usuario;
  private JLabel tituloCodigo;
  private JLabel tituloNome;
  private JLabel tituloSobrenome;
  private JLabel tituloNascimento;
  private JLabel tituloCpf;
  private JLabel tituloRg;
  private JLabel tituloEndereco;
  private JLabel tituloTelRes;
  private JLabel tituloTelCelular;
  private JLabel tituloEmail;
  private JLabel tituloPerfil;
  private JLabel tituloLogin;
  private JLabel tituloSenha;
  private JLabel imagemTelResidencial;
  private JLabel alertaTelResidencial;
  private JLabel imagemTelCelular;
  private JLabel alertaTelCelular;
  private JLabel imagemNascimento;
  private JLabel alertaNascimento;
  private JLabel imagemCpf;
  private JLabel alertaCpf;

  protected void setAttributes(JPanel conteudo, ResourceBundle bundle,
                               JButton atualizar, JButton deletar) {
    this.conteudo = conteudo;
    this.bundle = bundle;
    this.atualizar = atualizar;
    this.deletar = deletar;

    mainMenu();
  }

  protected void mainMenu() {
    // Titulos
    tituloCodigo = new JLabel(
        bundle.getString("criar.usuario.titulo.codigo"));
    tituloNome = new JLabel(
        bundle.getString("criar.pessoafisica.titulo.nome"));
    tituloSobrenome = new JLabel(
        bundle.getString("criar.pessoafisica.titulo.sobrenome"));
    tituloNascimento = new JLabel(
        bundle.getString("criar.pessoafisica.titulo.nascimento"));
    tituloCpf = new JLabel(
        bundle.getString("criar.pessoafisica.titulo.cpf"));
    tituloRg = new JLabel(bundle.getString("criar.pessoafisica.titulo.rg"));
    tituloEndereco = new JLabel(
        bundle.getString("criar.pessoafisica.titulo.endereco"));
    tituloTelRes = new JLabel(
        bundle.getString("criar.pessoafisica.titulo.telResidencial"));
    tituloTelCelular = new JLabel(
        bundle.getString("criar.pessoafisica.titulo.telCelular"));
    tituloEmail = new JLabel(
        bundle.getString("criar.pessoafisica.titulo.email"));
    tituloPerfil = new JLabel(
        bundle.getString("criar.usuario.titulo.perfil"));
    tituloLogin = new JLabel(
        bundle.getString("criar.usuario.titulo.usuario"));
    tituloSenha = new JLabel(bundle.getString("criar.usuario.titulo.senha"));

    // Botoes e caixas de textos
    codigo = new JLabel();
    nome = new JTextField();
    sobrenome = new JTextField();
    rg = new JTextField();
    endereco = new JTextField();
    telResidencial = new JTextField();
    telCelular = new JTextField();
    email = new JTextField();
    login = new JTextField();
    senha = new JPasswordField();
    imagemNascimento = new JLabel();
    alertaNascimento = new JLabel();
    imagemTelResidencial = new JLabel();
    alertaTelResidencial = new JLabel();
    imagemTelCelular = new JLabel();
    alertaTelCelular = new JLabel();
    imagemCpf = new JLabel();
    alertaCpf = new JLabel();

    Perfil[] val = Perfil.values();
    String[] perfis = new String[val.length];
    for (int i = 0; i < perfis.length; i++) {
      perfis[i] = val[i].setBundle(bundle);
    }

    DefaultComboBoxModel model = new DefaultComboBoxModel(perfis);
    perfil = new JComboBox(model);

    try {
      nascimento = new JFormattedTextField(
          new MaskFormatter("##/##/####"));
      cpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
    } catch (ParseException e) {
      e.printStackTrace();
    }

    imagem = new JLabel();
    alerta = new JLabel();

    enviar = new JButton(
        bundle.getString("atualizar.usuario.botao.atualizar"));

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
    imagemNascimento.setBounds(390, 140, 180, 30);
    alertaNascimento.setBounds(415, 140, 180, 30);
    cpf.setBounds(200, 175, 180, 30);
    alertaCpf.setBounds(410, 175, 100, 30);
    imagemCpf.setBounds(385, 175, 40, 30);
    rg.setBounds(200, 210, 180, 30);
    endereco.setBounds(200, 245, 300, 30);
    telResidencial.setBounds(200, 280, 180, 30);
    imagemTelResidencial.setBounds(390, 280, 180, 30);
    alertaTelResidencial.setBounds(415, 280, 300, 30);
    telCelular.setBounds(200, 315, 180, 30);
    imagemTelCelular.setBounds(390, 315, 180, 30);
    alertaTelCelular.setBounds(415, 315, 300, 30);
    email.setBounds(200, 355, 300, 30);
    perfil.setBounds(200, 390, 250, 30);
    login.setBounds(200, 425, 230, 30);
    senha.setBounds(200, 460, 230, 30);
    enviar.setBounds(600, 460, 150, 30);

    alerta.setBounds(404, 175, 100, 30);
    imagem.setBounds(380, 175, 40, 30);
  }

  protected void addAtualizarListener(ActionListener a) {
    atualizar.addActionListener(a);
  }

  protected void addFocusListener(FocusListener a) {
    nome.addFocusListener(a);
    sobrenome.addFocusListener(a);
    rg.addFocusListener(a);
    endereco.addFocusListener(a);
    telResidencial.addFocusListener(a);
    telCelular.addFocusListener(a);
    email.addFocusListener(a);
    login.addFocusListener(a);

    ((FocusTextField) a).setField(nome, sobrenome, rg, endereco,
        telResidencial, telCelular, email, login);

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

  protected void addFocusDataListener(FocusListener a) {
    nascimento.addFocusListener(a);
  }

  protected void addFocusCpfListener(FocusListener a) {
    cpf.addFocusListener(a);
  }

  protected void addFocusTelResListener(FocusListener a) {
    telResidencial.addFocusListener(a);
  }

  protected void addFocusTelCelListener(FocusListener a) {
    telCelular.addFocusListener(a);
  }

  protected String getCountry() {
    return bundle.getString("country");
  }

  protected JTextField getCpf() {
    return cpf;
  }

  protected JTextField getTelResidencial() {
    return telResidencial;
  }

  protected JTextField getTelCelular() {
    return telCelular;
  }

  protected String getTextTelResidencial() {
    return bundle.getString("criar.pessoafisica.antes.telResidencial");
  }

  protected String getTextTelCelular() {
    return bundle.getString("criar.pessoafisica.antes.telCelular");
  }

  protected RequestParamWrapper getParametersPessoaFisica() {
    RequestParamWrapper request = new RequestParamWrapper();

    request.set("id", pf.getId());
    request.set("nome", nome.getText());
    request.set("sobrenome", sobrenome.getText());
    request.set("nascimento", nascimento.getText());
    request.set("cpf", cpf.getText());
    request.set("rg", rg.getText());
    request.set("endereco", endereco.getText());
    request.set("telResidencial", telResidencial.getText());
    request.set("telCelular", telCelular.getText());
    request.set("email", email.getText());

    return request;
  }

  protected RequestParamWrapper getParametersUsuario() {
    RequestParamWrapper request = new RequestParamWrapper();
    Perfil[] val = Perfil.values();
    Perfil item = val[perfil.getSelectedIndex()];

    request.set("id", usuario.getId());
    request.set("codigo", codigo.getText());
    request.set("perfil", item);
    request.set("login", login.getText());
    request.set("senha", String.valueOf(senha.getPassword()));

    return request;
  }

  protected RequestParamWrapper getTexts() {
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

  protected void setParameters(PessoaFisica pf, Usuario usuario) {
    this.pf = pf;
    this.usuario = usuario;

    String date = FormatDateTime.parseToStringLocalDate(String.valueOf(pf
        .getDataNascimento()), bundle.getString("country"));
    String _cpf = String.valueOf(pf.getCpf());

    codigo.setText(usuario.getCodigo());
    nome.setText(pf.getNome());
    sobrenome.setText(pf.getSobrenome());
    nascimento.setText(date);
    cpf.setText(_cpf);
    rg.setText(pf.getRg());
    endereco.setText(pf.getEndereco());
    telResidencial.setText(String.valueOf(pf.getTelResidencial()));
    telCelular.setText(String.valueOf(pf.getTelCelular()));
    email.setText(pf.getEmail());
    perfil.setSelectedItem(usuario.getPerfil());
    login.setText(usuario.getLogin());
    senha.setText(usuario.getSenha());
  }

  protected void showAll() {
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
    conteudo.add(imagemNascimento);
    conteudo.add(alertaNascimento);
    conteudo.add(cpf);
    conteudo.add(rg);
    conteudo.add(endereco);
    conteudo.add(telResidencial);
    conteudo.add(telCelular);
    conteudo.add(email);
    conteudo.add(perfil);
    conteudo.add(login);
    conteudo.add(senha);
    conteudo.add(enviar);

    conteudo.repaint();
    conteudo.validate();
  }

  protected void messageFailed() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("detalhes.usuario.joption.tempo"),
        bundle.getString("detalhes.usuario.joption.titulo"),
        JOptionPane.ERROR_MESSAGE);
  }

  protected void messageTelResidencialOk() {
    conteudo.remove(imagemTelResidencial);
    conteudo.remove(alertaTelResidencial);
    repaint();
  }

  protected void messageTelCelularOk() {
    conteudo.remove(imagemTelCelular);
    conteudo.remove(alertaTelCelular);
    repaint();
  }

  protected void addImageNascimentoValid() {
    FlightImageUI.add(imagemNascimento, alertaNascimento,
        bundle.getString("validade.valido"), bundle, conteudo);
    repaint();
  }

  protected void addImageNascimentoInvalid() {
    FlightImageUI.addError(imagemNascimento, alertaNascimento,
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

  protected void messageOK() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("atualizar.usuario.sucesso"), "OK",
        JOptionPane.INFORMATION_MESSAGE);
  }

  protected void messageTelParseExecption() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("criar.usuario.erro.tel"));
  }

  protected void messageCpfInvalidExecption() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("criar.pessoafisica.cpf.alerta.erro"));
  }

  protected void messageTelResidencialParseExecption() {
    FlightImageUI.addError(imagemTelResidencial, alertaTelResidencial,
        bundle.getString("criar.usuario.erro.tel"), bundle, conteudo);
    repaint();
  }

  protected void messageTelCelularParseExecption() {
    FlightImageUI.addError(imagemTelCelular, alertaTelCelular,
        bundle.getString("criar.usuario.erro.tel"), bundle, conteudo);
    repaint();
  }

  protected void addMessageFailed() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("dadoincorreto"),
        "flight",
        JOptionPane.ERROR_MESSAGE);
  }

  protected void disableButtons() {
    atualizar.setEnabled(false);
    deletar.setEnabled(false);
  }

  protected void refresh() {
    conteudo.removeAll();
    repaint();
  }

  protected void repaint() {
    conteudo.validate();
    conteudo.repaint();
  }

  protected void addEnviarListener(ActionListener a) {
    enviar.addActionListener(a);
  }

}