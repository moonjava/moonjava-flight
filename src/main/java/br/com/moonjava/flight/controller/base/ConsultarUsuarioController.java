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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import br.com.moonjava.flight.model.base.Usuario;
import br.com.moonjava.flight.model.base.UsuarioModel;
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
                                    final ResourceBundle bundle,
                                    final JButton atualizar,
                                    final JButton deletar) {
    super(subConteudo, bundle, atualizar, deletar);

    this.conteudo = subConteudo;
    this.bundle = bundle;
    this.atualizar = atualizar;
    this.deletar = deletar;

    addConsultarListener(new ConsultarHandler());
    addItemTableSelectedListener(new ItemTableSelectedHandler());
    addDetalhesListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        enableButtons();
        JTable tabela = getTable();

        DetalhesUsuarioController detalhes = DetalhesUsuarioController.getInstance();
        detalhes.setList(list);
        detalhes.setAttributes(tabela, conteudo, bundle);
        detalhes.setResult(false);

      }
    });
  }

  private class ConsultarHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      RequestParamWrapper request = getParameters();

      list = new UsuarioModel().consultar(request);

      boolean isEmpty = showList(list);

      if (isEmpty) {
        messageFailed();
      }
    }
  }

  private class ItemTableSelectedHandler extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
      enableButtons();
      JTable tabela = getTable();

      DeletarUsuarioController delete = DeletarUsuarioController.getInstance();
      delete.setList(list);
      delete.setAttributes(tabela, conteudo, bundle, atualizar, deletar);
      delete.setResult(false);

      AtualizarUsuarioController atualiza = AtualizarUsuarioController.getInstance();
      atualiza.setList(list);
      atualiza.setAttributes(tabela, conteudo, bundle, atualizar, deletar);
      atualiza.setResult(false);

    }
  }

}