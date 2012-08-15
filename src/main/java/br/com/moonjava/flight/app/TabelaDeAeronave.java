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
package br.com.moonjava.flight.app;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.moonjava.flight.base.Aeronave;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
@SuppressWarnings("serial")
public class TabelaDeAeronave extends AbstractTableModel {

  private final List<Aeronave> list;

  public TabelaDeAeronave(List<Aeronave> list) {
    this.list = list;
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
      return "NOME";
    case 1:
      return "CÓDIGO";
    case 2:
      return "QTD DE ASSENTOS";
    }
    return null;
  }

  @Override
  public Object getValueAt(int linha, int coluna) {
    Aeronave aeronave = list.get(linha);
    switch (coluna) {
    case 0:
      return aeronave.getNome();
    case 1:
      return aeronave.getCodigo();
    case 2:
      return aeronave.getQtdDeAssento();
    }
    return null;
  }

}