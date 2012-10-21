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
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.com.moonjava.flight.model.base.FormaDeTratamento;
import br.com.moonjava.flight.model.base.Tipo;
import br.com.moonjava.flight.util.AbstractFlightUI;
import br.com.moonjava.flight.util.ErrorSystem;
import br.com.moonjava.flight.util.FlightImageUI;
import br.com.moonjava.flight.util.FocusTextField;
import br.com.moonjava.flight.util.GerarCodigo;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Aug 31, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class VenderPassagemUI extends AbstractFlightUI {

  private final JPanel conteudo;
  protected final ResourceBundle bundle;

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

  private JLabel imagemCpf;
  private JLabel alertaCpf;
  private JLabel imagemNascimento;
  private JLabel alertaNascimento;
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

  private String[] valTipos;

  private JFormattedTextField nascimento;
  private JFormattedTextField cpf;

  private JComboBox tratamento;
  private JComboBox pagamento;

  private JButton solicitarCompra;
  private JButton concluir;
  private JButton quantidadeOK;
  private JLabel imagemTelResidencial;
  private JLabel imagemTelCelular;
  private JLabel alertaTelResidencial;
  private JLabel alertaTelCelular;
  private ArrayList<JComboBox> tipos;
  private double valorTotal;
  private boolean firstCreate = true;
  private String number;

  public VenderPassagemUI(JPanel conteudo, ResourceBundle bundle) {
    this.conteudo = conteudo;
    this.bundle = bundle;

    refresh();
    mainMenu();
  }

  @Override
  protected void mainMenu() {
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
    tipos = new ArrayList<JComboBox>();

    // Botoes e caixas de textos
    // GerarCodigo gerarCodigo = new GerarCodigo("PASSAGEM");
    MaskFormatter mask = null;
    try {
      mask = new MaskFormatter("#");
      mask.setValidCharacters("123456789");
      nascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
      cpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
    } catch (ParseException e1) {
      ErrorSystem.addException(e1, bundle);
    }
    quantidade = new JFormattedTextField(mask);
    nome = new JTextField(bundle.getString("criar.pessoafisica.antes.nome"));
    sobrenome = new JTextField(bundle.getString("criar.pessoafisica.antes.sobrenome"));
    rg = new JTextField(bundle.getString("criar.pessoafisica.antes.rg"));
    endereco = new JTextField(bundle.getString("criar.pessoafisica.antes.endereco"));
    telResidencial = new JTextField(bundle.getString("criar.pessoafisica.antes.telResidencial"));
    telCelular = new JTextField(bundle.getString("criar.pessoafisica.antes.telCelular"));
    email = new JTextField(bundle.getString("criar.pessoafisica.antes.email"));

    quantidadeOK = new JButton("Ok");
    solicitarCompra = new JButton(bundle.getString("vender.passagem.botao.solicitarCompra"));
    concluir = new JButton(bundle.getString("vender.passagem.botao.concluir"));

    Tipo[] tipos = Tipo.values();
    valTipos = new String[tipos.length];
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

    tratamento = new JComboBox(valTratamentos);
    pagamento = new JComboBox(valPagamentos);
    pagamento.setSelectedItem(null);

    imagemCpf = new JLabel();
    imagemNascimento = new JLabel();
    imagemTelResidencial = new JLabel();
    imagemTelCelular = new JLabel();

    alertaCpf = new JLabel();
    alertaNascimento = new JLabel();
    alertaTelResidencial = new JLabel();
    alertaTelCelular = new JLabel();

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
    quantidadeOK.setBounds(420, 35, 100, 30);
    solicitarCompra.setBounds(350, 75, 170, 30);

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

    concluir.setBounds(350, 475, 150, 30);

    imagemCpf.setBounds(385, 215, 40, 30);
    imagemNascimento.setBounds(385, 180, 40, 30);
    imagemTelResidencial.setBounds(385, 320, 40, 30);
    imagemTelCelular.setBounds(385, 355, 40, 30);

    alertaCpf.setBounds(410, 215, 100, 30);
    alertaNascimento.setBounds(410, 180, 300, 30);
    alertaTelResidencial.setBounds(410, 320, 400, 30);
    alertaTelCelular.setBounds(410, 355, 400, 30);

    conteudo.add(tituloQuantidade);
    conteudo.add(tituloTipo);

    conteudo.add(quantidade);
    conteudo.add(quantidadeOK);

    repaint();
  }

  @Override
  protected JPanel getConteudo() {
    return conteudo;
  }

  protected JTextField getQuantidade() {
    return quantidade;
  }

  protected List<JComboBox> getTipos() {
    return tipos;
  }

  protected void setValorTotal(double valor) {
    this.valorTotal = valor;
  }

  protected double getValorTotal() {
    return valorTotal;
  }

  // Get parameters
  protected RequestParamWrapper getParametersPF() {
    RequestParamWrapper request = new RequestParamWrapper();
    request.set("cpf", cpf.getText());
    request.set("nome", nome.getText());
    request.set("sobrenome", sobrenome.getText());
    request.set("rg", rg.getText());
    request.set("endereco", endereco.getText());
    request.set("email", email.getText());
    request.set("nascimento", nascimento.getText());
    request.set("telResidencial", telResidencial.getText());
    request.set("telCelular", telCelular.getText());
    request.set("pagamentoIndex", pagamento.getSelectedIndex());
    request.set("codigo", codigo.getText());

    return request;
  }

  protected RequestParamWrapper getDefaultTexts() {
    RequestParamWrapper request = new RequestParamWrapper();
    request.set("telResidencial", bundle.getString("criar.pessoafisica.antes.telResidencial"));
    request.set("telCelular", bundle.getString("criar.pessoafisica.antes.telCelular"));

    return request;
  }

  // Add listeners
  protected void addSolicitarCompraListener(ActionListener a) {
    solicitarCompra.addActionListener(a);
  }

  protected void addChangeQuantidadeListener(KeyListener a) {
    quantidade.addKeyListener(a);
  }

  protected void addQuantidadeOKListener(ActionListener a) {
    quantidadeOK.addActionListener(a);
  }

  protected void addFocusCpfListener(FocusListener a) {
    cpf.addFocusListener(a);
  }

  protected void addFocusDataDeNascimentoListener(FocusListener a) {
    nascimento.addFocusListener(a);
  }

  protected void addPagamentoChangeListener(ActionListener a) {
    pagamento.addActionListener(a);
  }

  protected void addConcluirListener(ActionListener a) {
    concluir.addActionListener(a);
  }

  protected void addFocusTelResidencialListener(FocusListener a) {
    telResidencial.addFocusListener(a);
  }

  protected void addFocusTelCelularListener(FocusListener a) {
    telCelular.addFocusListener(a);
  }

  protected void addFocusListener(FocusListener a) {
    nome.addFocusListener(a);
    sobrenome.addFocusListener(a);
    rg.addFocusListener(a);
    endereco.addFocusListener(a);
    telResidencial.addFocusListener(a);
    telCelular.addFocusListener(a);
    email.addFocusListener(a);

    ((FocusTextField) a).setField(nome, sobrenome, rg, endereco, telResidencial, telCelular, email);
    ((FocusTextField) a).setText(bundle.getString("criar.pessoafisica.antes.nome"),
        bundle.getString("criar.pessoafisica.antes.sobrenome"),
        bundle.getString("criar.pessoafisica.antes.rg"),
        bundle.getString("criar.pessoafisica.antes.endereco"),
        bundle.getString("criar.pessoafisica.antes.telResidencial"),
        bundle.getString("criar.pessoafisica.antes.telCelular"),
        bundle.getString("criar.pessoafisica.antes.email"));
  }

  // Add layout
  protected void addConcluirButton() {
    concluir.setEnabled(true);
  }

  protected void addSolicitarCompraButton() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("vender.passagem.valor") + ": R$" + String.format("%,.2f", valorTotal),
        bundle.getString("vender.passagem.titulo"),
        JOptionPane.INFORMATION_MESSAGE);

    conteudo.add(solicitarCompra);
    repaint();
  }

  protected void removeSolicitarComprabutton() {
    conteudo.remove(solicitarCompra);
    repaint();
  }

  protected void messageFailedQtd(int qtd) {
    JOptionPane.showMessageDialog(null,
        bundle.getString("vender.passagem.quantidade.erro") + " " + qtd + " " +
            bundle.getString("vender.passagem.quantidade.disponivel"),
        bundle.getString("vender.passagem.titulo"),
        JOptionPane.QUESTION_MESSAGE);
  }

  protected void addComboBoxTipo(int qtd) {
    int y = 75;
    for (int i = 0; i < tipos.size(); i++) {
      conteudo.remove(tipos.get(i));
    }
    tipos.clear();

    for (int i = 0; i < qtd; i++) {
      tipos.add(new JComboBox(valTipos));
      tipos.get(i).setBounds(150, y, 100, 30);
      conteudo.add(tipos.get(i));
      y += 40;
    }
    repaint();
  }

  protected void addForm(JComboBox element) {
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

    String item = (String) element.getSelectedItem();
    tipoLabel = new JLabel(item);
    tipoLabel.setBounds(200, 75, 300, 30);

    // Na primeira execução o código é criado com base no BD (ver
    // GerarCodigo.java).
    // A partir da segunda o codigo é gerado internamente,
    // isto devido ao processo de vender ser em massa, isto é,
    // cria-se todos os passageiros primeiros e após a confirmação
    // no sistema de cartão, o sistema emite (cadastra) as respectivas
    // passagens
    String cod = null;
    if (firstCreate) {
      cod = new GerarCodigo("PASSAGEM").getCodigo();
      firstCreate = false;
    } else {
      long numberFormatted = Long.parseLong(number) + 1;
      cod = "P" + numberFormatted;
    }
    codigo = new JLabel(cod);
    number = codigo.getText().substring(1);
    codigo.setBounds(200, 40, 100, 30);
    conteudo.add(codigo);
    conteudo.add(tipoLabel);
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
    if (tipos.size() == 1) {
      conteudo.add(tituloPagamento);
      conteudo.add(pagamento);
      concluir.setEnabled(false);
    }
    conteudo.add(concluir);

    repaint();
  }

  protected void removeForm() {
    tipos.remove(tipos.size() - 1);
    repaint();
  }

  protected void addImageBirthDayValid() {
    FlightImageUI.add(imagemNascimento, alertaNascimento,
        bundle.getString("nascimento.valido"), bundle, conteudo);
    repaint();
  }

  protected void addImageBirthDayInvalid() {
    FlightImageUI.addError(imagemNascimento, alertaNascimento,
        bundle.getString("nascimento.invalido"), bundle, conteudo);
    repaint();
  }

  protected void addImageTelResidencialParseException() {
    FlightImageUI.addError(imagemTelResidencial, alertaTelResidencial,
        bundle.getString("alerta.numero"), bundle, conteudo);
    repaint();
  }

  protected void addImageTelCelularParseException() {
    FlightImageUI.addError(imagemTelCelular, alertaTelCelular,
        bundle.getString("alerta.numero"), bundle, conteudo);
    repaint();
  }

  protected void addImageCpfValido() {
    FlightImageUI.add(imagemCpf, alertaCpf,
        bundle.getString("criar.pessoafisica.cpf.alerta.ok"), bundle, conteudo);
    repaint();
  }

  public void addImageCpfInvalido() {
    FlightImageUI.addError(imagemCpf, alertaCpf,
        bundle.getString("criar.pessoafisica.cpf.alerta.erro"), bundle, conteudo);
    repaint();
  }

  protected void messagePFExistente() {
    JOptionPane.showMessageDialog(null, bundle.getString("criar.usuario.erro"),
        "",
        JOptionPane.ERROR_MESSAGE);
  }

  protected void messageDbUniqueKey() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("erro.bd.uniquekey"),
        bundle.getString("cancelar.passagem.titulo"),
        JOptionPane.ERROR_MESSAGE);
  }

  // remove layout
  protected void removeImageTelResidencialParseException() {
    conteudo.remove(alertaTelResidencial);
    conteudo.remove(imagemTelResidencial);
    repaint();
  }

  protected void removeImageTelCelularParseException() {
    conteudo.remove(alertaTelCelular);
    conteudo.remove(imagemTelCelular);
    repaint();
  }

}