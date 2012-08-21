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
package br.com.moonjava.flight.app.voo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import br.com.moonjava.flight.base.Voo;
import br.com.moonjava.flight.base.action.VooAction;

/**
 * @version 1.0 Aug 21, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
class DeletarVooUI implements ActionListener {

  // Singleton
  private static final DeletarVooUI ui = new DeletarVooUI();

  private JButton atualizar;
  private JButton deletar;
  private JTable tabela;
  private List<Voo> list;
  private JPanel conteudo;
  private ResourceBundle bundle;

  private boolean result;

  private DeletarVooUI() {
  }

  public static DeletarVooUI getInstance() {
    return ui;
  }

  public void setResult(boolean result) {
    this.result = result;
  }
  public void setAttributes(JButton atualizar,
                            JButton deletar,
                            JTable tabela,
                            List<Voo> list,
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
    deletar.setEnabled(false);
    atualizar.setEnabled(false);
    if (!result) {
      result = true;

      int[] rows = tabela.getSelectedRows();
      VooAction action = new VooAction();

      for (int i = 0; i < rows.length; i++) {
        Voo pojo = list.get(rows[i]);
        action.deletar(pojo.getId());
      }

      JOptionPane.showMessageDialog(null, bundle.getString("deletar.voo.joption.ok"));

      conteudo.removeAll();
      conteudo.repaint();
      conteudo.validate();
    }
  }

}