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

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
class ConsultarAeronaveUI implements ActionListener {

  private final JPanel conteudo;
  private final ResourceBundle bundle;
  private final JButton atualizar;
  private final JButton deletar;

  public ConsultarAeronaveUI(JPanel conteudo,
                             ResourceBundle bundle,
                             JButton atualizar,
                             JButton deletar) {
    this.conteudo = conteudo;
    this.bundle = bundle;
    this.atualizar = atualizar;
    this.deletar = deletar;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    atualizar.setEnabled(false);
    deletar.setEnabled(false);
    conteudo.removeAll();
    conteudo.validate();
    conteudo.repaint();

    Image image = null;
    InputStream stream = getClass().getResourceAsStream("/img/search.png");
    try {
      image = ImageIO.read(stream);
    } catch (IOException e2) {
      e2.printStackTrace();
    }

    Icon _imagem = new ImageIcon(image);
    JLabel imagem = new JLabel(_imagem);
    JTextField nome = new JTextField();

    JLabel titulo = new JLabel(bundle.getString("consultar.aeronave.titulo"));
    JButton filtrar = new JButton(bundle.getString("consultar.aeronave.campo"));

    JTable tabela = new JTable();
    tabela.setBorder(new LineBorder(Color.black));
    tabela.setGridColor(Color.black);
    tabela.setShowGrid(true);

    JScrollPane scroll = new JScrollPane();
    scroll.getViewport().setBorder(null);
    scroll.getViewport().add(tabela);
    scroll.setBounds(130, 100, 750, 450);
    scroll.setSize(750, 450);

    imagem.setBounds(100, 70, 30, 30);
    titulo.setBounds(130, 45, 200, 30);
    nome.setBounds(130, 70, 200, 30);
    filtrar.setBounds(330, 70, 80, 30);

    filtrar.addActionListener(
        new AeronaveTableHandler(tabela, nome, bundle, conteudo, atualizar, deletar));

    conteudo.add(imagem);
    conteudo.add(titulo);
    conteudo.add(nome);
    conteudo.add(filtrar);
    conteudo.add(scroll);

    conteudo.repaint();
    conteudo.validate();
  }

}