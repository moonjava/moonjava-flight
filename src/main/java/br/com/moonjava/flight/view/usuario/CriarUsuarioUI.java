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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import org.joda.time.LocalDate;

import br.com.moonjava.flight.controller.base.PessoaFisicaControlCreate;
import br.com.moonjava.flight.controller.base.UsuarioControlCreate;
import br.com.moonjava.flight.dao.base.PessoaFisicaDAO;
import br.com.moonjava.flight.dao.base.UsuarioDAO;
import br.com.moonjava.flight.model.base.Perfil;
import br.com.moonjava.flight.model.base.PessoaFisica;
import br.com.moonjava.flight.model.base.Usuario;
import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.CPFInvalidException;
import br.com.moonjava.flight.util.FocusTextField;
import br.com.moonjava.flight.util.FormatDateTime;
import br.com.moonjava.flight.util.GerarCodigo;
import br.com.moonjava.flight.util.JTextFieldLimit;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Aug 21, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class CriarUsuarioUI implements ActionListener {

  private final JPanel conteudo;
  private final ResourceBundle bundle;
  private final JButton atualizar;
  private final JButton deletar;
  private final UsuarioDAO usuarioDAO;
  private final PessoaFisicaDAO pessoaFisicaDAO;
  private final RequestParamWrapper request;
  private final Font font;

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

  public CriarUsuarioUI(JPanel conteudo,
                        ResourceBundle bundle,
                        JButton atualizar,
                        JButton deletar) {
    this.conteudo = conteudo;
    this.bundle = bundle;
    this.atualizar = atualizar;
    this.deletar = deletar;
    usuarioDAO = new UsuarioDAO();
    pessoaFisicaDAO = new PessoaFisicaDAO();
    request = new RequestParamWrapper();
    font = new Font("Century Gothic", Font.ITALIC, 13);
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
    JLabel tituloTelRes = new JLabel(bundle.getString("criar.pessoafisica.titulo.telResidencial"));
    JLabel tituloTelCelular = new JLabel(bundle.getString("criar.pessoafisica.titulo.telCelular"));
    JLabel tituloEmail = new JLabel(bundle.getString("criar.pessoafisica.titulo.email"));
    JLabel tituloPerfil = new JLabel(bundle.getString("criar.usuario.titulo.perfil"));
    JLabel tituloLogin = new JLabel(bundle.getString("criar.usuario.titulo.usuario"));
    JLabel tituloSenha = new JLabel(bundle.getString("criar.usuario.titulo.senha"));

    GerarCodigo gerarCodigo = new GerarCodigo("USUARIO");
    codigo = new JLabel(gerarCodigo.getCodigo());

    nome = new JTextField(bundle.getString("criar.pessoafisica.antes.nome"));
    sobrenome = new JTextField(bundle.getString("criar.pessoafisica.antes.sobrenome"));
    rg = new JTextField(bundle.getString("criar.pessoafisica.antes.rg"));
    endereco = new JTextField(bundle.getString("criar.pessoafisica.antes.endereco"));
    telResidencial = new JTextField(bundle.getString("criar.pessoafisica.antes.telResidencial"));
    telCelular = new JTextField(bundle.getString("criar.pessoafisica.antes.telCelular"));
    email = new JTextField(bundle.getString("criar.pessoafisica.antes.email"));
    login = new JTextField(bundle.getString("criar.usuario.antes.usuario"));
    senha = new JPasswordField();

    Perfil[] perfis = Perfil.values();
    perfil = new JComboBox(perfis);

    try {
      nascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
      cpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
    } catch (ParseException e) {
      e.printStackTrace();
    }

    imagem = new JLabel();
    alerta = new JLabel();

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

    FocusTextField focus = new FocusTextField();
    nome.addFocusListener(focus);
    sobrenome.addFocusListener(focus);
    rg.addFocusListener(focus);
    endereco.addFocusListener(focus);
    email.addFocusListener(focus);
    login.addFocusListener(focus);
    telResidencial.addFocusListener(focus);
    telCelular.addFocusListener(focus);

    cpf.addFocusListener(new FocusTextFields());
    telResidencial.addFocusListener(new FocusTextFields());
    telCelular.addFocusListener(new FocusTextFields());

    focus.setField(nome, sobrenome, rg, endereco, telResidencial, telCelular, email, login);

    focus.setText(bundle.getString("criar.pessoafisica.antes.nome"),
        bundle.getString("criar.pessoafisica.antes.sobrenome"),
        bundle.getString("criar.pessoafisica.antes.rg"),
        bundle.getString("criar.pessoafisica.antes.endereco"),
        bundle.getString("criar.pessoafisica.antes.telResidencial"),
        bundle.getString("criar.pessoafisica.antes.telCelular"),
        bundle.getString("criar.pessoafisica.antes.email"),
        bundle.getString("criar.usuario.antes.usuario"));

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

    alerta.setBounds(404, 175, 100, 30);
    imagem.setBounds(380, 175, 40, 30);

    JButton cadastrar = new JButton(bundle.getString("criar.usuario.botao.cadastrar"));
    cadastrar.setBounds(600, 460, 150, 30);

    cadastrar.addActionListener(new CadastrarHandler());

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

  private class CadastrarHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
      if (!nome.getText().equals(bundle.getString("criar.pessoafisica.antes.nome"))
          &&
          !sobrenome.getText().equals(bundle.getString("criar.pessoafisica.antes.sobrenome"))
          &&
          !nascimento.getText().equals("  /  /    ")
          &&
          !cpf.getText().equals("   .   .   -  ")
          &&
          !rg.getText().equals(bundle.getString("criar.pessoafisica.antes.rg"))
          &&
          !endereco.getText().equals(bundle.getString("criar.pessoafisica.antes.endereco"))
          &&
          !telResidencial.getText().equals(
              bundle.getString("criar.pessoafisica.antes.telResidencial")) &&
          !login.getText().equals(bundle.getString("criar.usuario.antes.senha"))) {

        CPF _cpf = CPF.parse(cpf.getText());

        if (usuarioDAO.consultarPorCpf(_cpf) != null) {
          JOptionPane.showMessageDialog(null, bundle.getString("criar.usuario.erro"), "ERRO",
              JOptionPane.ERROR_MESSAGE);
        } else {

          PessoaFisica pf = pessoaFisicaDAO.consultarPorCpf(_cpf);

          if (pf != null) {
            request.set("codigo", codigo.getText());
            request.set("pessoaFisica", pf.getId());
            request.set("perfil", perfil.getSelectedItem());
            request.set("login", login.getText());
            request.set("senha", String.valueOf(senha.getPassword()));

            Usuario pojo = new UsuarioControlCreate(request).createInstance();
            usuarioDAO.criar(pojo);

            JOptionPane.showMessageDialog(null,
                bundle.getString("criar.usuario.sucesso"), "OK", JOptionPane.INFORMATION_MESSAGE);

          }
          else {
            LocalDate date = FormatDateTime.parseToLocalDate(nascimento.getText(), bundle
                .getLocale()
                .getCountry());
            try {
              Integer.parseInt(telCelular.getText());
            } catch (Exception e) {
              JOptionPane
                  .showMessageDialog(null, bundle.getString("criar.usuario.erro.telCelular"));
              return;
            }
            try {
              Integer.parseInt(telResidencial.getText());
            } catch (Exception e) {
              JOptionPane.showMessageDialog(null,
                  bundle.getString("criar.usuario.erro.telResidencial"));
              return;
            }

            request.set("nome", nome.getText());
            request.set("sobrenome", sobrenome.getText());
            request.set("nascimento", date);
            request.set("cpf", _cpf.getDigito());
            request.set("rg", rg.getText());
            request.set("endereco", endereco.getText());
            request.set("telResidencial", Integer.parseInt(telResidencial.getText()));
            request.set("telCelular", Integer.parseInt(telCelular.getText()));
            request.set("email", email.getText());

            PessoaFisica pojo = new PessoaFisicaControlCreate(request).createInstance();
            pessoaFisicaDAO.criar(pojo);
            PessoaFisica pf2 = pessoaFisicaDAO.consultarPorCpf(_cpf);

            request.set("codigo", codigo.getText());
            request.set("pessoaFisica", pf2.getId());
            request.set("perfil", perfil.getSelectedItem());
            request.set("login", login.getText());
            request.set("senha", String.valueOf(senha.getPassword()));

            Usuario pojo2 = new UsuarioControlCreate(request).createInstance();
            usuarioDAO.criar(pojo2);

            JOptionPane.showMessageDialog(null,
                bundle.getString("criar.usuario.sucesso"), "OK", JOptionPane.INFORMATION_MESSAGE);

          }

        }
      }
    }
  }

  private class FocusTextFields implements FocusListener {

    @Override
    public void focusLost(FocusEvent e) {
      if (e.getSource() == cpf) {
        Image image = null;
        try {
          CPF.parse(cpf.getText());
          InputStream stream = getClass().getResourceAsStream("/img/icon_disponivel.png");
          image = ImageIO.read(stream);
          ImageIcon icon = new ImageIcon(image);
          imagem.setIcon(icon);
          alerta.setText("");

          conteudo.add(imagem);
          conteudo.add(alerta);
          conteudo.repaint();
          conteudo.validate();

        } catch (CPFInvalidException e2) {
          InputStream stream = getClass().getResourceAsStream("/img/icon_indisponivel.png");

          try {
            image = ImageIO.read(stream);
          } catch (IOException e1) {
            e1.printStackTrace();
          }

          ImageIcon icon = new ImageIcon(image);
          imagem.setIcon(icon);

          alerta.setFont(new Font("Arial", Font.BOLD, 13));
          alerta.setForeground(Color.RED);
          alerta.setText(bundle.getString("criar.pessoafisica.cpf.alerta.erro"));

          conteudo.add(imagem);
          conteudo.add(alerta);
          conteudo.repaint();
          conteudo.validate();

        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }

      if (e.getSource() == telResidencial
          && !telResidencial.getText().equals("")
          && !telResidencial.getText().equals(
              bundle.getString("criar.pessoafisica.antes.telResidencial"))) {
        try {
          Integer.parseInt(telResidencial.getText());
        } catch (Exception e2) {
          JOptionPane
              .showMessageDialog(null, bundle.getString("criar.usuario.erro.telResidencial"));
        }
      }
      if (e.getSource() == telCelular && !telCelular.getText().equals("")
          && !telCelular.getText().equals(bundle.getString("criar.pessoafisica.antes.telCelular"))) {
        try {
          Integer.parseInt(telCelular.getText());
        } catch (Exception e2) {
          JOptionPane.showMessageDialog(null, bundle.getString("criar.usuario.erro.telCelular"));
        }
      }
    }
    @Override
    public void focusGained(FocusEvent e) {
    }
  }
}