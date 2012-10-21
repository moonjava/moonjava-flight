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

import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JTable;

import br.com.moonjava.flight.model.base.PessoaFisica;
import br.com.moonjava.flight.model.base.Usuario;
import br.com.moonjava.flight.view.usuario.DetalhesUsuarioUI;

/**
 * @version 1.0 Sep 3, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class DetalhesUsuarioController extends DetalhesUsuarioUI {

  private static final DetalhesUsuarioController ui = new DetalhesUsuarioController();
  private boolean result;
  private List<Usuario> list;
  private JTable tabela;

  private Usuario pojo;
  private PessoaFisica pojoPF;

  private DetalhesUsuarioController() {
  }

  public static DetalhesUsuarioController getInstance() {
    return ui;
  }

  public void setAttributes(JTable tabela,
                            JPanel subConteudo,
                            ResourceBundle bundle) {

    this.tabela = tabela;

    setAttributes(subConteudo, bundle);
    detalhesHandler();
  }

  public void setResult(boolean result) {
    this.result = result;
  }

  public void setList(List<Usuario> list) {
    this.list = list;
  }

  private void detalhesHandler() {

    if (!result) {
      result = true;
      int[] rows = tabela.getSelectedRows();

      if (rows.length == 1) {
        pojo = list.get(rows[0]);
        pojoPF = pojo.getPessoaFisica();

        setParameters(pojoPF, pojo);
        showAll();
      } else {
        messageFailed();
        refresh();
      }
    }
  }
}