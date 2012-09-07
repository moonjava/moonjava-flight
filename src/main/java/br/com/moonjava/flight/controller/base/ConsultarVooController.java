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

import org.joda.time.DateTime;

import br.com.moonjava.flight.dao.base.VooDAO;
import br.com.moonjava.flight.model.base.Status;
import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.util.FormatDateTime;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.view.voo.ConsultarVooUI;

/**
 * @version 1.0 Aug 29, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class ConsultarVooController extends ConsultarVooUI {

  private final JPanel conteudo;
  private final ResourceBundle bundle;
  private final JButton atualizar;
  private final JButton deletar;
  private final JButton status;

  private List<Voo> list;

  public ConsultarVooController(JPanel conteudo,
                                ResourceBundle bundle,
                                JButton atualizar,
                                JButton deletar,
                                JButton status) {
    super(conteudo, bundle, atualizar, deletar, status);

    this.conteudo = conteudo;
    this.bundle = bundle;
    this.atualizar = atualizar;
    this.deletar = deletar;
    this.status = status;

    addConsultarListener(new ConsultarHandler());
    addItemTableSelectedListener(new ItemTableSelectedHandler());
  }

  private class ConsultarHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      RequestParamWrapper request = getParameters();
      String partida = request.stringParam("partida");
      String chegada = request.stringParam("chegada");

      String country = getCountry();
      String _partida = null;
      String _chegada = null;

      if (country.equals("US")) {
        String timePartida = request.stringParam("timePartida");
        String timeChegada = request.stringParam("timeChegada");

        _partida = String.format("%s %s", partida, timePartida);
        _chegada = String.format("%s %s", chegada, timeChegada);
      } else {
        _partida = partida;
        _chegada = chegada;
      }

      int index = request.intParam("status");
      Status[] values = Status.values();
      Status _status = values[index];

      String maskEmpty = "  /  /       :  ";
      DateTime dataPartida = null;
      DateTime dataChegada = null;

      if (!_partida.startsWith(maskEmpty)) {
        dataPartida = FormatDateTime.parseToDateTime(_partida, country);
      }

      if (!_chegada.startsWith(maskEmpty)) {
        dataChegada = FormatDateTime.parseToDateTime(_chegada, country);
      }

      request.set("partida", dataPartida);
      request.set("chegada", dataChegada);
      request.set("status", _status);

      list = new VooDAO().consultar(request);

      boolean isEmpty = showList(list);

      if (isEmpty) {
        messageFailed();
      } else {
        repaint();
      }
    }
  }

  private class ItemTableSelectedHandler implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
      enableButtons();
      JTable tabela = getTable();

      DeletarVooController delete = DeletarVooController.getInstance();
      delete.setAttributes(tabela, conteudo, bundle, atualizar, deletar, status);
      delete.setResult(false);
      delete.setList(list);

      AtualizarVooController atualiza = AtualizarVooController.getInstance();
      atualiza.setAttributes(tabela, conteudo, bundle, atualizar, deletar, status);
      atualiza.setResult(false);
      atualiza.setList(list);
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