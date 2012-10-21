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
package br.com.moonjava.flight.view.painel;

import java.util.List;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import br.com.moonjava.flight.model.base.Voo;

/**
 * @version 1.0 Sep 8, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
@SuppressWarnings("serial")
public class PainelDeDecolagemTableModel extends AbstractTableModel {

  private final List<Voo> list;
  private final ResourceBundle bundle;

  public PainelDeDecolagemTableModel(List<Voo> list, ResourceBundle bundle) {
    this.list = list;
    this.bundle = bundle;
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }

  @Override
  public int getColumnCount() {
    return 7;
  }

  @Override
  public int getRowCount() {
    return list.size();
  }

  @Override
  public String getColumnName(int column) {
    switch (column) {
    case 0:
      return bundle.getString("consultar.voo.coluna.0");
    case 1:
      return bundle.getString("consultar.voo.coluna.1");
    case 2:
      return bundle.getString("consultar.voo.coluna.2");
    case 3:
      return bundle.getString("consultar.voo.coluna.3");
    case 4:
      return bundle.getString("consultar.voo.coluna.4");
    case 5:
      return bundle.getString("consultar.voo.coluna.5");
    case 6:
      return bundle.getString("consultar.voo.coluna.6");
    }
    return null;
  }

  @Override
  public Object getValueAt(int linha, int coluna) {
    Voo voo = list.get(linha);
    switch (coluna) {
    case 0:
      return voo.getCodigo();
    case 1:
      return voo.getOrigem();
    case 2:
      return voo.getDestino();
    case 3:
      return voo.getEscala();
    case 4:
      String partida = null;
      if (bundle.getString("country").equals("US")) {
        partida = voo.getDataDePartida().toString("MM/dd/yyyy HH:mm aa");
      } else {
        partida = voo.getDataDePartida().toString("dd/MM/yyyy HH:mm");
      }
      return partida;
    case 5:
      String chegada = null;
      if (bundle.getString("country").equals("US")) {
        chegada = voo.getDataDeChegada().toString("MM/dd/yyyy HH:mm aa");
      } else {
        chegada = voo.getDataDeChegada().toString("dd/MM/yyyy HH:mm");
      }
      return chegada;
    case 6:
      return voo.getStatus().setBundle(bundle);
    }
    return null;
  }

}