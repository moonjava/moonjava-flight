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
package br.com.moonjava.flight.controller.base;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;

import br.com.moonjava.flight.model.base.PassagemModel;
import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.model.base.VooModel;
import br.com.moonjava.flight.view.voo.DeletarVooUI;

/**
 * @version 1.0 Aug 30, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class DeletarVooController extends DeletarVooUI {

  // Singleton
  private static final DeletarVooController ui = new DeletarVooController();
  private boolean result;
  private List<Voo> list;
  private JTable tabela;

  private DeletarVooController() {
  }

  public static DeletarVooController getInstance() {
    return ui;
  }

  @Override
  public void setAttributes(JTable tabela,
                            JPanel conteudo,
                            ResourceBundle bundle,
                            JButton atualizar,
                            JButton deletar,
                            JButton status) {
    this.tabela = tabela;
    super.setAttributes(tabela, conteudo, bundle, atualizar, deletar, status);
    addDeletarListener(new DeletarHandler());
  }

  public void setResult(boolean result) {
    this.result = result;
  }

  public void setList(List<Voo> list) {
    this.list = list;
  }

  private class DeletarHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (!result) {
        UIManager.put("OptionPane.cancelButtonText", bundle.getString("cancelar"));
        int confirmed = JOptionPane.showConfirmDialog(null,
            bundle.getString("deletar.voo.joption"),
            bundle.getString("criar.voo.joption.titulo"),
            JOptionPane.OK_CANCEL_OPTION);
        if (confirmed == 0) {
          disableButtons();

          result = true;
          int[] rows = tabela.getSelectedRows();
          Voo vooModel = new VooModel();
          PassagemModel passagemModel = new PassagemModel();

          for (int i = 0; i < rows.length; i++) {
            Voo pojo = list.get(rows[i]);
            passagemModel.cancelarPorVoo(pojo);
            vooModel.deletar(pojo.getId());
          }

          messageDeleteOK();
          refresh();
        }
      }
    }
  }

}