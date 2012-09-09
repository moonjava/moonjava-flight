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
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import br.com.moonjava.flight.model.base.Usuario;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Sep 2, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class ConsultarUsuarioUI {

  private final JPanel conteudo;
  private final ResourceBundle bundle;
  private final JButton atualizar;
  private final JButton deletar;
  private JButton detalhes;
  private JButton filtrar;
  private JTextField login;
  private JTextField codigo;
  private JTable tabela;

  public ConsultarUsuarioUI(JPanel conteudo,
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

  private void mainMenu() {
    JLabel tituloLogin = new JLabel(bundle.getString("consultar.usuario.titulo.login"));
    JLabel tituloCodigo = new JLabel(bundle.getString("consultar.usuario.titulo.codigo"));

    filtrar = new JButton(bundle.getString("consultar.usuario.filtrar"));
    detalhes = new JButton(bundle.getString("consultar.usuario.detalhes"));
    detalhes.setEnabled(false);

    login = new JTextField();
    codigo = new JTextField();

    Image image = null;
    InputStream stream = getClass().getResourceAsStream("/img/search.png");
    try {
      image = ImageIO.read(stream);
    } catch (IOException e2) {
      e2.printStackTrace();
    }

    Icon _imagem = new ImageIcon(image);
    JLabel imagem = new JLabel(_imagem);

    tabela = new JTable();
    tabela.setBorder(new LineBorder(Color.black));
    tabela.setGridColor(Color.black);
    tabela.setShowGrid(true);
    tabela.setFont(new Font("Century Gothic", Font.ITALIC, 13));

    JScrollPane scroll = new JScrollPane();
    scroll.getViewport().setBorder(null);
    scroll.getViewport().add(tabela);
    scroll.setBounds(130, 100, 750, 420);
    scroll.setSize(750, 420);

    imagem.setBounds(100, 70, 30, 30);

    tituloCodigo.setBounds(132, 45, 200, 30);
    tituloLogin.setBounds(332, 45, 200, 30);
    filtrar.setBounds(800, 70, 80, 30);
    detalhes.setBounds(760, 520, 120, 30);
    codigo.setBounds(130, 70, 200, 30);
    login.setBounds(330, 70, 200, 30);

    conteudo.add(imagem);
    conteudo.add(tituloLogin);
    conteudo.add(tituloCodigo);
    conteudo.add(codigo);
    conteudo.add(login);
    conteudo.add(filtrar);
    conteudo.add(detalhes);
    conteudo.add(scroll);

    conteudo.repaint();
    conteudo.validate();
  }

  public void addConsultarListener(ActionListener a) {
    filtrar.addActionListener(a);
  }

  public void addDetalhesListener(ActionListener a) {
    detalhes.addActionListener(a);
  }

  public void addItemTableSelectedListener(MouseListener a) {
    tabela.addMouseListener(a);
  }

  public RequestParamWrapper getParameters() {
    RequestParamWrapper request = new RequestParamWrapper();
    request.set("login", login.getText());
    request.set("codigo", codigo.getText());
    return request;
  }

  public JTable getTable() {
    return tabela;
  }

  public boolean showList(List<Usuario> lista) {
    UsuarioTableModel usuario = new UsuarioTableModel(lista, bundle);
    tabela.setModel(usuario);
    return tabela.getRowCount() == 0 ? true : false;
  }

  public void addButtonDetalhes() {
    conteudo.add(detalhes);
    conteudo.repaint();
    conteudo.validate();
  }

  public void messageFailed() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("consultar.usuario.joption.err"),
        bundle.getString("consultar.usuario.joption.titulo"),
        JOptionPane.ERROR_MESSAGE);
  }

  public void enableButtons() {
    atualizar.setEnabled(true);
    deletar.setEnabled(true);
    detalhes.setEnabled(true);
  }

  public void disableButtons() {
    atualizar.setEnabled(false);
    deletar.setEnabled(false);
  }

  public void refresh() {
    conteudo.removeAll();
    conteudo.repaint();
    conteudo.validate();
  }

}
