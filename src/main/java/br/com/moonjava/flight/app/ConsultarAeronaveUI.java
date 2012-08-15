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

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import br.com.moonjava.flight.base.Aeronave;
import br.com.moonjava.flight.base.action.AeronaveAction;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
class ConsultarAeronaveUI implements ActionListener {

  private final JPanel conteudo;
  private final AeronaveAction action;
  private final RequestParamWrapper request;
  private List<Aeronave> list;
  private JTable tabela;

  public ConsultarAeronaveUI(JPanel conteudo) {
    this.conteudo = conteudo;
    action = new AeronaveAction();
    request = new RequestParamWrapper();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    conteudo.removeAll();
    conteudo.validate();
    conteudo.repaint();
    Icon image = new ImageIcon("src/main/resources/img/search.png");
    JLabel imagem = new JLabel(image);
    JLabel titulo = new JLabel("Nome:");
    final JTextField nome = new JTextField();
    JButton filtrar = new JButton("Filtrar");

    tabela = new JTable();
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

    filtrar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        request.set("nome", nome.getText());
        list = action.consultar(request);

        TabelaDeAeronave aeronaves = new TabelaDeAeronave(list);
        tabela.setModel(aeronaves);

        JButton mapa = new JButton("Mapa de Assento");
        mapa.setBounds(730, 70, 150, 30);

        mapa.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            int linha = tabela.getSelectedRow();

            Aeronave aeronave = list.get(linha);

            String path = String.format("src/main/resources/img/%s.jpg", aeronave.getNome());
            Icon image = new ImageIcon(path);
            int width = image.getIconWidth();
            int height = image.getIconHeight();
            JLabel label = new JLabel(image);

            JDialog frame = new JDialog();

            frame.add(label);
            frame.setSize(width, height + 40);
            frame.setResizable(false);
            frame.setVisible(true);
          }
        });

        conteudo.add(mapa);
        conteudo.repaint();
        conteudo.validate();
      }
    });

    conteudo.add(imagem);
    conteudo.add(titulo);
    conteudo.add(nome);
    conteudo.add(filtrar);
    conteudo.add(scroll);

    conteudo.repaint();
    conteudo.validate();
  }

}