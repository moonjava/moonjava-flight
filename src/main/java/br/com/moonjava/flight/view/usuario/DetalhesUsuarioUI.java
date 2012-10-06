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

import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.com.moonjava.flight.model.base.PessoaFisica;
import br.com.moonjava.flight.model.base.Usuario;
import br.com.moonjava.flight.util.FormatDateTime;

/**
 * @version 1.0 Sep 3, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class DetalhesUsuarioUI {

  private JPanel conteudo;
  private ResourceBundle bundle;
  private JButton atualizar;
  private JButton deletar;

  private JLabel nome;
  private JLabel sobrenome;
  private JLabel nascimento;
  private JLabel cpf;
  private JLabel rg;
  private JLabel endereco;
  private JLabel telResidencial;
  private JLabel telCelular;
  private JLabel email;
  private JLabel perfil;
  private JLabel login;
  private JLabel senha;
  private JLabel codigo;

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
  private Usuario usuario;
  private PessoaFisica pf;

  public void setAttributes(JPanel conteudo, ResourceBundle bundle,
      JButton atualizar, JButton deletar) {
    this.conteudo = conteudo;
    this.bundle = bundle;
    this.atualizar = atualizar;
    this.deletar = deletar;

    refresh();
    mainMenu();
  }

  public void mainMenu() {
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

    codigo = new JLabel();
    nome = new JLabel();
    sobrenome = new JLabel();
    nascimento = new JLabel();
    cpf = new JLabel();
    rg = new JLabel();
    endereco = new JLabel();
    telResidencial = new JLabel();
    telCelular = new JLabel();
    email = new JLabel();
    perfil = new JLabel();
    login = new JLabel();
    senha = new JLabel("******");

    Font font = new Font("Arial", Font.BOLD, 13);

    tituloCodigo.setFont(font);
    tituloNome.setFont(font);
    tituloSobrenome.setFont(font);
    tituloNascimento.setFont(font);
    tituloCpf.setFont(font);
    tituloRg.setFont(font);
    tituloEndereco.setFont(font);
    tituloTelRes.setFont(font);
    tituloTelCelular.setFont(font);
    tituloEmail.setFont(font);
    tituloPerfil.setFont(font);
    tituloLogin.setFont(font);
    tituloSenha.setFont(font);

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

    codigo.setBounds(225, 35, 100, 30);
    nome.setBounds(225, 70, 300, 30);
    sobrenome.setBounds(225, 105, 300, 30);
    nascimento.setBounds(225, 140, 180, 30);
    cpf.setBounds(225, 175, 180, 30);
    rg.setBounds(225, 210, 180, 30);
    endereco.setBounds(225, 245, 300, 30);
    telResidencial.setBounds(225, 280, 180, 30);
    telCelular.setBounds(225, 315, 180, 30);
    email.setBounds(225, 355, 300, 30);
    perfil.setBounds(225, 390, 250, 30);
    login.setBounds(225, 425, 230, 30);
    senha.setBounds(225, 460, 230, 30);
  }

  public void setParameters(PessoaFisica pf, Usuario usuario) {
    String date = FormatDateTime.parseToStringLocalDate(
        pf.getDataNascimento().toString(), bundle.getString("country"));
    String str = String.valueOf(pf.getCpf());
    String _cpf = String.format("%1$2s.%2$2s.%3$2s-%4$2s",
        str.substring(0, 3), str.substring(3, 6), str.substring(6, 9),
        str.substring(9, 11));

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
    perfil.setText(usuario.getPerfil().name());
    login.setText(usuario.getLogin());

  }

  public void showAll() {
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

    conteudo.repaint();
    conteudo.validate();

  }

  public void refresh() {
    conteudo.removeAll();
    conteudo.validate();
    conteudo.repaint();
  }

  public void messageFailed() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("detalhes.usuario.joption.tempo"),
        bundle.getString("detalhes.usuario.joption.titulo"),
        JOptionPane.ERROR_MESSAGE);
  }
}