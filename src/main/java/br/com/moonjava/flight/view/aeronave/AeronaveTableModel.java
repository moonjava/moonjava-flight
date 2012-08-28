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
package br.com.moonjava.flight.view.aeronave;

import java.util.List;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import br.com.moonjava.flight.model.base.Aeronave;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
@SuppressWarnings("serial")
public class AeronaveTableModel extends AbstractTableModel {

  private final List<Aeronave> list;
  private final ResourceBundle bundle;

  public AeronaveTableModel(List<Aeronave> list, ResourceBundle bundle) {
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
      return bundle.getString("consultar.aeronave.coluna.0");
    case 1:
      return bundle.getString("consultar.aeronave.coluna.1");
    case 2:
      return bundle.getString("consultar.aeronave.coluna.2");
    }
    return null;
  }

  @Override
  public Object getValueAt(int linha, int coluna) {
    Aeronave aeronave = list.get(linha);
    switch (coluna) {
    case 0:
      return aeronave.getCodigo();
    case 1:
      return aeronave.getNome();
    case 2:
      return aeronave.getQtdDeAssento();
    }
    return null;
  }

}