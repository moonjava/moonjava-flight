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

import br.com.moonjava.flight.model.base.Aeronave;
import br.com.moonjava.flight.model.base.AeronaveModel;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.view.aeronave.ConsultarAeronaveUI;

/**
 * @version 1.0 Aug 29, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class ConsultarAeronaveController extends ConsultarAeronaveUI {

  private final JPanel conteudo;
  private final ResourceBundle bundle;
  private final JButton atualizar;
  private final JButton deletar;
  private List<Aeronave> list;

  public ConsultarAeronaveController(JPanel subConteudo,
                                     ResourceBundle bundle,
                                     JButton atualizar,
                                     JButton deletar) {
    super(subConteudo, bundle, atualizar, deletar);

    this.conteudo = subConteudo;
    this.bundle = bundle;
    this.atualizar = atualizar;
    this.deletar = deletar;

    addConsultarListener(new ConsultarHandler());
    addShowSeatMapListener(new SeatMapHandler());
    addItemTableSelectedListener(new ItemTableSelectedHandler());
  }

  private class ConsultarHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      RequestParamWrapper request = getParameters();

      Aeronave aeronave = new AeronaveModel();
      list = aeronave.consultar(request);

      boolean isEmpty = showList(list);

      if (isEmpty) {
        messageFailed();
      } else {
        addButtonMapa();
      }
    }
  }

  private class SeatMapHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      int index = getTable().getSelectedRow();

      try {
        // Carrega imagem do mapa de assento de acordo
        // com o indice na tabela
        Aeronave aeronave = list.get(index);
        String pathFile = String.format("airplanes/%s.jpg", aeronave.getNome());
        showSeatMap(pathFile);

      } catch (IndexOutOfBoundsException e2) {
        messageSeatMapFailed();
      }
    }
  }

  private class ItemTableSelectedHandler extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
      enableButtons();
      JTable tabela = getTable();

      DeletarAeronaveController delete = DeletarAeronaveController.getInstance();
      delete.setAttributes(tabela, conteudo, bundle, atualizar, deletar);
      delete.setResult(false);
      delete.setList(list);

      AtualizarAeronaveController atualiza = AtualizarAeronaveController.getInstance();
      atualiza.setAttributes(tabela, conteudo, bundle, atualizar, deletar);
      atualiza.setResult(false);
      atualiza.setList(list);
    }
  }

}
