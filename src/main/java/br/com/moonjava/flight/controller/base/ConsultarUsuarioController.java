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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import br.com.moonjava.flight.dao.base.UsuarioDAO;
import br.com.moonjava.flight.model.base.Usuario;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.view.usuario.ConsultarUsuarioUI;

/**
 * @version 1.0 Sep 2, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class ConsultarUsuarioController extends ConsultarUsuarioUI {

  private final JPanel conteudo;
  private final ResourceBundle bundle;
  private final JButton atualizar;
  private final JButton deletar;
  private List<Usuario> list;

  public ConsultarUsuarioController(JPanel subConteudo,
                                    ResourceBundle bundle,
                                    JButton atualizar,
                                    JButton deletar) {
    super(subConteudo, bundle, atualizar, deletar);

    this.conteudo = subConteudo;
    this.bundle = bundle;
    this.atualizar = atualizar;
    this.deletar = deletar;

    addConsultarListener(new ConsultarHandler());
    addItemTableSelectedListener(new ItemTableSelectedHandler());
  }

  private class ConsultarHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      RequestParamWrapper request = getParameters();

      list = new UsuarioDAO().consultar(request);

      boolean isEmpty = showList(list);

      if (isEmpty) {
        messageFailed();
      }
    }
  }

  private class ItemTableSelectedHandler implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
      enableButtons();
      JTable tabela = getTable();

      // DeletarUsuarioController delete =
      // DeletarUsuarioController.getInstance();
      // delete.setAttributes(tabela, conteudo, bundle, atualizar, deletar);
      // delete.setResult(false);
      // delete.setList(list);
      //
      // AtualizarUsuarioController atualiza =
      // AtualizarUsuarioController.getInstance();
      // atualiza.setAttributes(tabela, conteudo, bundle, atualizar, deletar);
      // atualiza.setResult(false);
      // atualiza.setList(list);
      //
      // DetalhesUsuarioController detalhes =
      // DetalhesUsuarioController.getInstance();
      // detalhes.setAttributes(tabela, conteudo, bundle, atualizar, deletar);
      // detalhes.setResult(false);
      // detalhes.setList(list);

    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
  }

}
