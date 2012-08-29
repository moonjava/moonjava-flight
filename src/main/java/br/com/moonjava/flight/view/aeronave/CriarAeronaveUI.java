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
package br.com.moonjava.flight.view.aeronave;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.moonjava.flight.util.GerarCodigo;
import br.com.moonjava.flight.util.JTextFieldLimit;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class CriarAeronaveUI {

  private final JPanel conteudo;
  private final ResourceBundle bundle;
  private final JButton atualizar;
  private final JButton deletar;

  private JLabel codigo;
  private JTextField nome;
  private JTextField assento;
  private JLabel imagem;
  private JButton mapa;
  private JButton cadastrar;

  public CriarAeronaveUI(JPanel conteudo, ResourceBundle bundle, JButton atualizar, JButton deletar) {
    this.conteudo = conteudo;
    this.bundle = bundle;
    this.atualizar = atualizar;
    this.deletar = deletar;

    mainMenu();
  }

  private void mainMenu() {
    atualizar.setEnabled(false);
    deletar.setEnabled(false);

    refresh();
    JLabel tituloCodigo = new JLabel(bundle.getString("criar.aeronave.titulo.codigo"));
    JLabel tituloNome = new JLabel(bundle.getString("criar.aeronave.titulo.nome"));
    JLabel tituloAssento = new JLabel(bundle.getString("criar.aeronave.titulo.assento"));
    JLabel tituloMapa = new JLabel(bundle.getString("criar.aeronave.titulo.mapa"));

    JLabel alertaAssento = new JLabel(bundle.getString("alerta.numero"));
    JLabel alertaMapa = new JLabel(bundle.getString("criar.aeronave.alerta.mapa"));

    InputStream stream = getClass().getResourceAsStream("/img/icon_disponivel.png");
    Image image = null;

    try {
      image = ImageIO.read(stream);
    } catch (IOException e1) {
      e1.printStackTrace();
    }

    ImageIcon icon = new ImageIcon(image);
    imagem = new JLabel(icon);

    String _codigo = new GerarCodigo("AERONAVE").getCodigo();
    codigo = new JLabel(_codigo);
    nome = new JTextField();
    assento = new JTextField();

    nome.setDocument(new JTextFieldLimit(40));
    assento.setDocument(new JTextFieldLimit(3));

    mapa = new JButton(bundle.getString("criar.aeronave.botao.mapa"));
    cadastrar = new JButton(bundle.getString("criar.aeronave.botao.cadastrar"));

    tituloCodigo.setBounds(60, 70, 200, 30);
    tituloNome.setBounds(60, 110, 200, 30);
    tituloAssento.setBounds(60, 150, 200, 30);
    tituloMapa.setBounds(60, 190, 200, 30);

    alertaAssento.setBounds(360, 150, 200, 30);
    alertaMapa.setBounds(360, 190, 500, 30);

    codigo.setBounds(180, 70, 300, 30);
    nome.setBounds(180, 110, 300, 30);
    assento.setBounds(180, 150, 150, 30);
    imagem.setBounds(280, 190, 130, 30);
    mapa.setBounds(180, 190, 150, 30);
    cadastrar.setBounds(180, 240, 150, 30);

    conteudo.add(tituloCodigo);
    conteudo.add(tituloNome);
    conteudo.add(tituloAssento);
    conteudo.add(tituloMapa);

    conteudo.add(alertaAssento);
    conteudo.add(alertaMapa);

    conteudo.add(codigo);
    conteudo.add(nome);
    conteudo.add(assento);
    conteudo.add(mapa);
    conteudo.add(cadastrar);

    conteudo.repaint();
    conteudo.validate();
  }

  public void addCadastrarListener(ActionListener a) {
    cadastrar.addActionListener(a);
  }

  public void addLoaderFileListener(ActionListener a) {
    mapa.addActionListener(a);
  }

  public RequestParamWrapper getParameters() {
    RequestParamWrapper request = new RequestParamWrapper();
    request.set("codigo", codigo.getText());
    request.set("nome", nome.getText());
    request.set("qtdDeAssento", assento.getText());
    return request;
  }

  public void addImagem() {
    conteudo.add(imagem);
    conteudo.validate();
    conteudo.repaint();
  }

  public void messageOK() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("criar.joption.ok"),
        bundle.getString("criar.joption.titulo"),
        JOptionPane.INFORMATION_MESSAGE);

    refresh();
  }

  public void messageFailed() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("criar.joption.err"),
        bundle.getString("criar.joption.titulo"),
        JOptionPane.ERROR_MESSAGE);

    refresh();
  }

  public void messageNumberException() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("alerta.numero"),
        bundle.getString("criar.joption.titulo"),
        JOptionPane.ERROR_MESSAGE);
  }

  private void refresh() {
    conteudo.removeAll();
    conteudo.validate();
    conteudo.repaint();
  }

}