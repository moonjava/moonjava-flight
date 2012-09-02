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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.com.moonjava.flight.model.base.FormaDeTratamento;
import br.com.moonjava.flight.model.base.Tipo;

/**
 * @version 1.0 Aug 31, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class VenderPassagemUI {

  private final JPanel conteudo;
  private final ResourceBundle bundle;

  private JLabel tituloQuantidade;
  private JLabel tituloTipo;
  private JLabel tituloCodigo;
  private JLabel tituloTratamento;
  private JLabel tituloPagamento;
  private JLabel tituloNome;
  private JLabel tituloSobrenome;
  private JLabel tituloNascimento;
  private JLabel tituloCpf;
  private JLabel tituloRg;
  private JLabel tituloEndereco;
  private JLabel tituloTelRes;
  private JLabel tituloTelCelular;
  private JLabel tituloEmail;

  private JLabel imagem;
  private JLabel alerta;
  private JLabel tipoLabel;
  private JLabel codigo;

  private JTextField quantidade;
  private JTextField nome;
  private JTextField sobrenome;
  private JTextField rg;
  private JTextField endereco;
  private JTextField telResidencial;
  private JTextField telCelular;
  private JTextField email;

  private JFormattedTextField nascimento;
  private JFormattedTextField cpf;

  private JComboBox tipo;
  private JComboBox tratamento;
  private JComboBox pagamento;

  private JButton solicitarCompra;
  private JButton cadastrar;
  private JButton quantidadeOK;

  public VenderPassagemUI(JPanel conteudo, ResourceBundle bundle) {
    this.conteudo = conteudo;
    this.bundle = bundle;

    refresh();
    mainMenu();
  }

  public void mainMenu() {
    // Titulos
    tituloQuantidade = new JLabel(bundle.getString("vender.passagem.titulo.quantidade"));
    tituloTipo = new JLabel(bundle.getString("vender.passagem.titulo.tipo"));
    tituloCodigo = new JLabel(bundle.getString("vender.passagem.codigo"));
    tituloTratamento = new JLabel(bundle.getString("vender.passagem.titulo.tratamento"));
    tituloPagamento = new JLabel(bundle.getString("vender.passagem.titulo.pagamento"));
    tituloNome = new JLabel(bundle.getString("criar.pessoafisica.titulo.nome"));
    tituloSobrenome = new JLabel(bundle.getString("criar.pessoafisica.titulo.sobrenome"));
    tituloNascimento = new JLabel(bundle.getString("criar.pessoafisica.titulo.nascimento"));
    tituloCpf = new JLabel(bundle.getString("criar.pessoafisica.titulo.cpf"));
    tituloRg = new JLabel(bundle.getString("criar.pessoafisica.titulo.rg"));
    tituloEndereco = new JLabel(bundle.getString("criar.pessoafisica.titulo.endereco"));
    tituloTelRes = new JLabel(bundle.getString("criar.pessoafisica.titulo.telResidencial"));
    tituloTelCelular = new JLabel(bundle.getString("criar.pessoafisica.titulo.telCelular"));
    tituloEmail = new JLabel(bundle.getString("criar.pessoafisica.titulo.email"));

    // Botoes e caixas de textos
    // GerarCodigo gerarCodigo = new GerarCodigo("PASSAGEM");
    MaskFormatter mask = null;
    try {
      mask = new MaskFormatter("##");
      mask.setValidCharacters("0123456789");
      nascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
      cpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
    } catch (ParseException e1) {
      e1.printStackTrace();
    }
    quantidade = new JFormattedTextField(mask);
    codigo = new JLabel("Teste");
    nome = new JTextField(bundle.getString("criar.pessoafisica.antes.nome"));
    sobrenome = new JTextField(bundle.getString("criar.pessoafisica.antes.sobrenome"));
    rg = new JTextField(bundle.getString("criar.pessoafisica.antes.rg"));
    endereco = new JTextField(bundle.getString("criar.pessoafisica.antes.endereco"));
    telResidencial = new JTextField(bundle.getString("criar.pessoafisica.antes.telResidencial"));
    telCelular = new JTextField(bundle.getString("criar.pessoafisica.antes.telCelular"));
    email = new JTextField(bundle.getString("criar.pessoafisica.antes.email"));

    quantidadeOK = new JButton("Ok");
    solicitarCompra = new JButton(bundle.getString("vender.passagem.botao.solicitarCompra"));
    cadastrar = new JButton(bundle.getString("vender.passagem.botao.cadastrar"));

    Tipo[] tipos = Tipo.values();
    String valTipos[] = new String[tipos.length];
    for (int i = 0; i < valTipos.length; i++) {
      valTipos[i] = tipos[i].setBundle(bundle);
    }

    FormaDeTratamento[] tratamentos = FormaDeTratamento.values();
    String valTratamentos[] = new String[tratamentos.length];
    for (int i = 0; i < valTratamentos.length; i++) {
      valTratamentos[i] = tratamentos[i].setBundle(bundle);
    }

    String valPagamentos[] = {
        bundle.getString("vender.passagem.pagamento.cartao"),
        bundle.getString("vender.passagem.pagamento.cheque")
    };

    tipo = new JComboBox(valTipos);
    tratamento = new JComboBox(valTratamentos);
    pagamento = new JComboBox(valPagamentos);

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

    nome.setForeground(Color.GRAY);
    sobrenome.setForeground(Color.GRAY);
    rg.setForeground(Color.GRAY);
    endereco.setForeground(Color.GRAY);
    telResidencial.setForeground(Color.GRAY);
    telCelular.setForeground(Color.GRAY);
    email.setForeground(Color.GRAY);

    tituloQuantidade.setBounds(60, 35, 100, 30);
    tituloTipo.setBounds(60, 75, 100, 30);
    tituloCodigo.setBounds(60, 40, 100, 30);
    tituloNome.setBounds(60, 110, 200, 30);
    tituloSobrenome.setBounds(60, 145, 200, 30);
    tituloNascimento.setBounds(60, 180, 160, 30);
    tituloCpf.setBounds(60, 215, 40, 30);
    tituloRg.setBounds(60, 250, 40, 30);
    tituloEndereco.setBounds(60, 285, 100, 30);
    tituloTelRes.setBounds(60, 320, 140, 30);
    tituloTelCelular.setBounds(60, 355, 140, 30);
    tituloEmail.setBounds(60, 395, 160, 30);
    tituloTratamento.setBounds(60, 435, 160, 30);
    tituloPagamento.setBounds(60, 475, 160, 30);

    quantidade.setBounds(150, 35, 100, 30);
    tipo.setBounds(150, 75, 100, 30);
    quantidadeOK.setBounds(420, 35, 100, 30);
    solicitarCompra.setBounds(350, 75, 170, 30);

    codigo.setBounds(200, 40, 100, 30);
    nome.setBounds(200, 110, 300, 30);
    sobrenome.setBounds(200, 145, 300, 30);
    nascimento.setBounds(200, 180, 180, 30);
    cpf.setBounds(200, 215, 180, 30);
    rg.setBounds(200, 250, 180, 30);
    endereco.setBounds(200, 285, 300, 30);
    telResidencial.setBounds(200, 320, 180, 30);
    telCelular.setBounds(200, 355, 180, 30);
    email.setBounds(200, 395, 300, 30);
    tratamento.setBounds(200, 435, 100, 30);
    pagamento.setBounds(200, 475, 100, 30);

    cadastrar.setBounds(350, 475, 150, 30);

    alerta.setBounds(404, 215, 100, 30);
    imagem.setBounds(380, 215, 40, 30);

    conteudo.add(tituloQuantidade);
    conteudo.add(tituloTipo);

    conteudo.add(quantidade);
    conteudo.add(tipo);
    conteudo.add(quantidadeOK);

    conteudo.repaint();
    conteudo.validate();

    solicitarCompra.addActionListener(new Teste());
    quantidadeOK.addActionListener(new Teste2());
  }

  private class Teste2 implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      conteudo.add(solicitarCompra);
      conteudo.repaint();
      conteudo.validate();
    }
  }

  private class Teste implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      refresh();

      tituloTipo.setBounds(60, 75, 100, 30);
      conteudo.add(tituloCodigo);
      conteudo.add(tituloTipo);
      conteudo.add(tituloNome);
      conteudo.add(tituloSobrenome);
      conteudo.add(tituloNascimento);
      conteudo.add(tituloCpf);
      conteudo.add(tituloRg);
      conteudo.add(tituloEndereco);
      conteudo.add(tituloTelRes);
      conteudo.add(tituloTelCelular);
      conteudo.add(tituloEmail);
      conteudo.add(tituloTratamento);
      conteudo.add(tituloPagamento);

      String item = (String) tipo.getSelectedItem();
      tipoLabel = new JLabel(item);
      tipoLabel.setBounds(200, 75, 300, 30);

      conteudo.add(tipoLabel);
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
      conteudo.add(tratamento);
      conteudo.add(pagamento);
      conteudo.add(cadastrar);

      conteudo.repaint();
      conteudo.validate();
    }
  }

  public void refresh() {
    conteudo.removeAll();
    conteudo.validate();
    conteudo.repaint();
  }

}