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
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;

import br.com.moonjava.flight.base.Aeronave;
import br.com.moonjava.flight.base.action.AeronaveAction;
import br.com.moonjava.flight.base.action.VooAction;

/**
 * @version 1.0 Aug 16, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
class DeletarAeronaveUI implements ActionListener {

  // Singleton
  private static final DeletarAeronaveUI ui = new DeletarAeronaveUI();
  private boolean result;

  private JButton atualizar;
  private JButton deletar;
  private JTable tabela;
  private List<Aeronave> list;
  private JPanel conteudo;
  private ResourceBundle bundle;

  private DeletarAeronaveUI() {
  }

  public static DeletarAeronaveUI getInstance() {
    return ui;
  }

  public void setResult(boolean result) {
    this.result = result;
  }

  public void setAttributes(JButton atualizar,
                            JButton deletar,
                            JTable tabela,
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
    deletar.setEnabled(false);
    atualizar.setEnabled(false);
    if (!result) {
      result = true;
      UIManager.put("OptionPane.cancelButtonText", bundle.getString("cancelar"));

      int result = JOptionPane.
          showConfirmDialog(null,
              bundle.getString("deletar.joption.msg"),
              bundle.getString("deletar.joption.titulo"),
              JOptionPane.OK_CANCEL_OPTION,
              JOptionPane.INFORMATION_MESSAGE);

      if (result == 0) {
        int[] rows = tabela.getSelectedRows();
        VooAction vooAction = new VooAction();
        AeronaveAction aeronaveAction = new AeronaveAction();

        for (int i = 0; i < rows.length; i++) {
          Aeronave pojo = list.get(rows[i]);
          vooAction.deletarPorAeronaveId(pojo.getId());
          aeronaveAction.deletar(pojo.getId());
        }

        JOptionPane.showMessageDialog(null, bundle.getString("deletar.joption.ok"));

        conteudo.removeAll();
        conteudo.repaint();
        conteudo.validate();

      } else {
        conteudo.removeAll();
        conteudo.repaint();
        conteudo.validate();
      }
    }
  }

}