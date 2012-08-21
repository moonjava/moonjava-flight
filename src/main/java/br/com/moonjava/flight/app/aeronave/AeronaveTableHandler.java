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
package br.com.moonjava.flight.app.aeronave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.com.moonjava.flight.base.Aeronave;
import br.com.moonjava.flight.base.action.AeronaveAction;
import br.com.moonjava.flight.util.RequestParam;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Aug 16, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
class AeronaveTableHandler implements ActionListener {

  private final JTable tabela;
  private final JTextField nome;
  private final ResourceBundle bundle;
  private final JPanel conteudo;
  private final JButton atualizar;
  private final JButton deletar;

  private List<Aeronave> list;

  public AeronaveTableHandler(JTable tabela,
                              JTextField nome,
                              ResourceBundle bundle,
                              JPanel conteudo,
                              JButton atualizar,
                              JButton deletar) {
    this.tabela = tabela;
    this.nome = nome;
    this.bundle = bundle;
    this.conteudo = conteudo;
    this.atualizar = atualizar;
    this.deletar = deletar;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton mapa = new JButton(bundle.getString("consultar.aeronave.botao.mapa"));
    RequestParam request = new RequestParamWrapper();
    request.set("nome", nome.getText());

    list = new AeronaveAction().consultar(request);
    mapa.setBounds(730, 70, 150, 30);

    AeronaveTableModel aeronaves = new AeronaveTableModel(list, bundle);
    tabela.setModel(aeronaves);

    if (tabela.getRowCount() == 0) {
      JOptionPane.showMessageDialog(null,
          bundle.getString("consultar.joption.err"),
          bundle.getString("consultar.joption.titulo"),
          JOptionPane.ERROR_MESSAGE);
    } else {
      mapa.addActionListener(new MapaHandler());
      tabela.addMouseListener(new TabelaHandler());

      conteudo.add(mapa);
      conteudo.repaint();
      conteudo.validate();
    }
  }

  private class MapaHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      int linha = tabela.getSelectedRow();

      try {
        Aeronave aeronave = list.get(linha);

        String path = String.format("airplanes/%s.jpg", aeronave.getNome());
        Icon image = new ImageIcon(path);
        int width = image.getIconWidth();
        int height = image.getIconHeight();

        JLabel label = new JLabel(image);

        JDialog frame = new JDialog();
        frame.add(label);
        frame.setSize(width + 40, height + 40);
        frame.setResizable(false);
        frame.setVisible(true);

      } catch (IndexOutOfBoundsException e2) {
        JOptionPane.showMessageDialog(null,
            bundle.getString("consultar.joption.erro"),
            bundle.getString("consultar.joption.titulo"),
            JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private class TabelaHandler implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
      atualizar.setEnabled(true);
      deletar.setEnabled(true);

      // Singleton Object doesn't allow duplicate events
      AtualizarAeronaveUI update = AtualizarAeronaveUI.getInstance();
      update.setAttributes(atualizar, deletar, tabela, list, conteudo, bundle);
      update.setResult(false);
      atualizar.addActionListener(update);

      DeletarAeronaveUI delete = DeletarAeronaveUI.getInstance();
      delete.setAttributes(atualizar, deletar, tabela, list, conteudo, bundle);
      delete.setResult(false);
      deletar.addActionListener(delete);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

  }

}