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
package br.com.moonjava.flight.view.usuario;

import java.util.List;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import br.com.moonjava.flight.model.base.Usuario;

/**
 * @version 1.0 Sep 2, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
@SuppressWarnings("serial")
public class UsuarioTableModel extends AbstractTableModel {

  private final List<Usuario> list;
  private final ResourceBundle bundle;

  public UsuarioTableModel(List<Usuario> list, ResourceBundle bundle) {
    this.list = list;
    this.bundle = bundle;
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }

  @Override
  public int getColumnCount() {
    return 3;
  }

  @Override
  public int getRowCount() {
    return list.size();
  }

  @Override
  public String getColumnName(int column) {
    switch (column) {
    case 0:
      return bundle.getString("consultar.usuario.coluna.0");
    case 1:
      return bundle.getString("consultar.usuario.coluna.1");
    case 2:
      return bundle.getString("consultar.usuario.coluna.2");
    }
    return null;
  }

  @Override
  public Object getValueAt(int linha, int coluna) {
    Usuario usuario = list.get(linha);
    switch (coluna) {
    case 0:
      return usuario.getCodigo();
    case 1:
      return usuario.getLogin();
    case 2:
      return usuario.getPerfil().setBundle(bundle);
    }
    return null;
  }

}
