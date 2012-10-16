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
import javax.swing.JPanel;
import javax.swing.JTable;

import br.com.moonjava.flight.model.base.Usuario;
import br.com.moonjava.flight.model.base.UsuarioModel;
import br.com.moonjava.flight.view.usuario.DeletarUsuarioUI;

/**
 * @version 1.0 Sep 5, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class DeletarUsuarioController extends DeletarUsuarioUI {

  private static final DeletarUsuarioController ui = new DeletarUsuarioController();
  private boolean result;
  private List<Usuario> list;
  private JTable tabela;

  private DeletarUsuarioController() {
  }

  public static DeletarUsuarioController getInstance() {
    return ui;
  }

  public void setAttributes(JTable tabela,
                            JPanel subConteudo,
                            ResourceBundle bundle,
                            JButton atualizar,
                            JButton deletar) {
    this.tabela = tabela;
    super.setAttributes(subConteudo, bundle, atualizar, deletar);
    addDeletarListener(new DeletarHandler());
  }

  public void setResult(boolean result) {
    this.result = result;
  }

  public void setList(List<Usuario> list) {
    this.list = list;
  }

  private class DeletarHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      disableButtons();

      if (!result) {
        result = true;
        int res = messageDeleteAll();

        if (res == 0) {
          int[] rows = tabela.getSelectedRows();
          Usuario usuarioModel = new UsuarioModel();

          for (int i = 0; i < rows.length; i++) {
            Usuario pojo = list.get(rows[i]);
            usuarioModel.deletar(pojo.getId());
          }

          messageDeleteOK();
          refresh();
        }
      }
    }
  }

}