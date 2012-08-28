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

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;

import br.com.moonjava.flight.base.Status;
import br.com.moonjava.flight.base.Voo;
import br.com.moonjava.flight.base.action.VooAction;

/**
 * @version 1.0 Aug 17, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
class ControlarStatusVooUI implements ActionListener {

  // Singleton
  private static final ControlarStatusVooUI ui = new ControlarStatusVooUI();

  private JTable tabela;
  private List<Voo> list;
  private JPanel conteudo;
  private ResourceBundle bundle;

  private boolean result;

  private ControlarStatusVooUI() {
  }

  public static ControlarStatusVooUI getInstance() {
    return ui;
  }

  public void setAttributes(JTable tabela, List<Voo> list, JPanel conteudo, ResourceBundle bundle) {
    this.tabela = tabela;
    this.list = list;
    this.conteudo = conteudo;
    this.bundle = bundle;
  }

  public void setResult(boolean result) {
    this.result = result;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (!result) {
      result = true;

      Status[] values = Status.values();
      String[] nomes = new String[values.length];
      for (int i = 0; i < values.length; i++) {
        nomes[i] = values[i].setBundle(bundle);
      }

      UIManager.put("OptionPane.cancelButtonText", bundle.getString("cancelar"));
      String status = (String) JOptionPane.showInputDialog(null, "Status:", "Status", 1, null,
          nomes, Status.DISPONIVEL);

      VooAction action = new VooAction();

      if (status != null) {
        int[] rows = tabela.getSelectedRows();
        for (int i = 0; i < rows.length; i++) {
          Voo pojo = list.get(rows[i]);
          action.controlarStatus(pojo.getId(), Status.fromString(status));
        }

        JOptionPane.showMessageDialog(null, bundle.getString("status.voo.joption.ok"));

        conteudo.removeAll();
        conteudo.repaint();
        conteudo.validate();
      }
    }
  }
}