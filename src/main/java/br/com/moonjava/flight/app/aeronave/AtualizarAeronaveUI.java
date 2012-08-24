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
import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.com.moonjava.flight.base.Aeronave;
import br.com.moonjava.flight.base.action.AeronaveAction;
import br.com.moonjava.flight.base.action.AeronaveUpdate;
import br.com.moonjava.flight.util.JTextFieldLimit;
import br.com.moonjava.flight.util.RequestParam;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Aug 16, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
class AtualizarAeronaveUI implements ActionListener {

  private static final AtualizarAeronaveUI ui = new AtualizarAeronaveUI();

  private JButton atualizar;
  private JButton deletar;
  private JTable tabela;
  private List<Aeronave> list;
  private JPanel conteudo;
  private ResourceBundle bundle;

  private Aeronave pojo;
  private boolean result;

  private JTextField nome;

  private AtualizarAeronaveUI() {
  }

  public static AtualizarAeronaveUI getInstance() {
    return ui;
  }

  public void setResult(boolean result) {
    this.result = result;
  }

  public void setAttributes(JButton atualizar,
                            JButton deletar, JTable tabela,
                            List<Aeronave> list,
                            JPanel conteudo,
                            ResourceBundle bundle) {
    this.atualizar = atualizar;
    this.deletar = deletar;
    this.tabela = tabela;
    this.list = list;
    this.conteudo = conteudo;
    this.bundle = bundle;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    atualizar.setEnabled(false);
    deletar.setEnabled(false);
    if (!result) {
      result = true;
      int[] rows = tabela.getSelectedRows();

      if (rows.length == 1) {
        for (int i = 0; i < rows.length; i++) {
          pojo = list.get(rows[i]);
        }
        conteudo.removeAll();
        conteudo.repaint();
        conteudo.validate();

        JLabel tituloNome = new JLabel(bundle.getString("criar.aeronave.titulo.nome"));
        nome = new JTextField();
        nome.setDocument(new JTextFieldLimit(40));

        JButton atualizar =
            new JButton(bundle.getString("atualizar.aeronave.botao.atualizar"));

        tituloNome.setBounds(100, 70, 200, 30);

        nome.setBounds(180, 70, 300, 30);
        atualizar.setBounds(180, 110, 150, 30);

        atualizar.addActionListener(new Atualizar());

        conteudo.add(tituloNome);
        conteudo.add(nome);
        conteudo.add(atualizar);

        conteudo.repaint();
        conteudo.validate();

      } else {
        JOptionPane
            .showMessageDialog(null,
                bundle.getString("atualizar.joption.err"),
                bundle.getString("atualizar.joption.titulo"),
                JOptionPane.ERROR_MESSAGE);
        conteudo.removeAll();
        conteudo.repaint();
        conteudo.validate();
      }
    }

  }

  private class Atualizar implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      RequestParam request = new RequestParamWrapper();
      AeronaveAction action = new AeronaveAction();

      request.set("id", pojo.getId());
      request.set("nome", nome.getText());

      Aeronave aeronave = new AeronaveUpdate(request).createInstance();
      action.atualizar(aeronave);

      File oldFile = new File("airplanes/" + pojo.getNome() + ".jpg");
      File newFile = new File("airplanes/" + nome.getText() + ".jpg");
      oldFile.renameTo(newFile);

      JOptionPane.showMessageDialog(null,
          bundle.getString("atualizar.joption.ok"),
          bundle.getString("atualizar.joption.titulo"),
          JOptionPane.INFORMATION_MESSAGE);

      conteudo.removeAll();
      conteudo.repaint();
      conteudo.validate();
    }

  }

}