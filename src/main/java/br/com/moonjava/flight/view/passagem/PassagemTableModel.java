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
package br.com.moonjava.flight.view.passagem;

import java.util.List;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import br.com.moonjava.flight.model.base.Passagem;

/**
 * @version 1.0 Oct 15, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
@SuppressWarnings("serial")
public class PassagemTableModel extends AbstractTableModel {

  private final ResourceBundle bundle;
  private final List<Passagem> passagens;

  public PassagemTableModel(List<Passagem> passagens, ResourceBundle bundle) {
    this.passagens = passagens;
    this.bundle = bundle;
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }

  @Override
  public int getColumnCount() {
    return 1;
  }

  @Override
  public int getRowCount() {
    return passagens.size();
  }

  @Override
  public Object getValueAt(int linha, int coluna) {
    Passagem passagem = passagens.get(linha);
    return passagem.getAssento();
  }

  @Override
  public String getColumnName(int column) {
    return bundle.getString("vender.passagem.quantidade.indisponivel");
  }

}
