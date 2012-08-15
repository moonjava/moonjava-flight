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
package br.com.moonjava.flight.app;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.moonjava.flight.base.Aeronave;
import br.com.moonjava.flight.base.action.AeronaveAction;
import br.com.moonjava.flight.base.action.AeronaveCreate;
import br.com.moonjava.flight.util.CopyFile;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class CriarAeronaveUI implements ActionListener {

  private final JPanel conteudo;
  private final ResourceBundle bundle;
  private final AeronaveAction action;
  private final RequestParamWrapper request;

  private JTextField nome;
  private JTextField codigo;
  private JTextField assento;
  private JLabel imagem;

  public CriarAeronaveUI(JPanel conteudo, ResourceBundle bundle) {
    this.conteudo = conteudo;
    this.bundle = bundle;
    action = new AeronaveAction();
    request = new RequestParamWrapper();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    conteudo.removeAll();
    conteudo.validate();
    conteudo.repaint();

    JLabel tituloNome = new JLabel(bundle.getString("criar.aeronave.titulo.nome"));
    JLabel tituloCodigo = new JLabel(bundle.getString("criar.aeronave.titulo.codigo"));
    JLabel tituloAssento = new JLabel(bundle.getString("criar.aeronave.titulo.assento"));
    JLabel tituloMapa = new JLabel(bundle.getString("criar.aeronave.titulo.mapa"));

    InputStream stream = getClass().getResourceAsStream("/img/icon_disponivel.png");
    Image image = null;

    try {
      image = ImageIO.read(stream);
    } catch (IOException e1) {
      e1.printStackTrace();
    }

    ImageIcon icon = new ImageIcon(image);
    imagem = new JLabel(icon);

    nome = new JTextField();
    codigo = new JTextField();
    assento = new JTextField();

    JButton mapa = new JButton(bundle.getString("criar.aeronave.botao.mapa"));
    JButton cadastrar = new JButton(bundle.getString("criar.aeronave.botao.cadastrar"));

    tituloNome.setBounds(60, 70, 200, 30);
    nome.setBounds(180, 70, 300, 30);

    tituloCodigo.setBounds(60, 110, 200, 30);
    codigo.setBounds(180, 110, 300, 30);

    tituloAssento.setBounds(60, 150, 200, 30);
    assento.setBounds(180, 150, 150, 30);

    tituloMapa.setBounds(60, 190, 200, 30);
    mapa.setBounds(180, 190, 150, 30);

    imagem.setBounds(290, 190, 130, 30);

    cadastrar.setBounds(180, 240, 150, 30);

    mapa.addActionListener(new LoadFile());
    cadastrar.addActionListener(new Cadastro());

    conteudo.add(tituloNome);
    conteudo.add(nome);
    conteudo.add(tituloCodigo);
    conteudo.add(codigo);
    conteudo.add(tituloAssento);
    conteudo.add(assento);
    conteudo.add(tituloMapa);
    conteudo.add(mapa);
    conteudo.add(cadastrar);

    conteudo.repaint();
    conteudo.validate();
  }

  private class LoadFile implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      JFileChooser caixa = new JFileChooser();
      caixa.setFileFilter(new FileNameExtensionFilter("*.jpg", "jpg"));
      int retorno = caixa.showOpenDialog(null);

      if (retorno == JFileChooser.APPROVE_OPTION) {

        File file = caixa.getSelectedFile();
        String absolutePath = file.getAbsolutePath();
        String name = file.getName();

        File folder = new File("airplanes");

        if (!folder.exists()) {
          folder.mkdir();
          folder.setWritable(true, true);
        }

        int _codigo = 0;
        int _assento = 0;
        boolean mapa = false;

        try {
          _codigo = Integer.parseInt(codigo.getText());
          _assento = Integer.parseInt(assento.getText());
          mapa = CopyFile.copyfile(absolutePath, folder.getName() + "/" + name);
          request.set("nome", nome.getText());
          request.set("codigo", _codigo);
          request.set("qtdDeAssento", _assento);
          request.set("mapa", mapa);

          conteudo.add(imagem);
          conteudo.repaint();
          conteudo.validate();
        } catch (Exception e2) {
          JOptionPane
              .showMessageDialog(null,
                  bundle.getString("criar.joption.erro"),
                  bundle.getString("criar.joption.titulo"),
                  JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }

  private class Cadastro implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

      Aeronave pojo = new AeronaveCreate(request).createInstance();

      action.criar(pojo);

      JOptionPane
          .showMessageDialog(null,
              bundle.getString("criar.joption.ok"),
              bundle.getString("criar.joption.titulo"),
              JOptionPane.INFORMATION_MESSAGE);

      conteudo.removeAll();
      conteudo.validate();
      conteudo.repaint();

    }
  }
}