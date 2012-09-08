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
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.joda.time.DateTime;

import br.com.moonjava.flight.dao.base.VooDAO;
import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.util.FormatDateTime;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.view.voo.AtualizarVooUI;

/**
 * @version 1.0 Aug 30, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class AtualizarVooController extends AtualizarVooUI {

  // Singleton
  private static final AtualizarVooController ui = new AtualizarVooController();
  private boolean result;
  private List<Voo> list;
  private JTable tabela;

  private Voo pojo;

  private AtualizarVooController() {
  }

  public static AtualizarVooController getInstance() {
    return ui;
  }

  public void setAttributes(JTable tabela,
                            JPanel subConteudo,
                            ResourceBundle bundle,
                            JButton atualizar,
                            JButton deletar,
                            JButton status) {
    this.tabela = tabela;
    setAttributes(subConteudo, bundle, atualizar, deletar, status);
    addAtualizarListener(new AtualizarHandler());
    addEnviarListener(new EnviarHandler());
  }

  public void setResult(boolean result) {
    this.result = result;
  }

  public void setList(List<Voo> list) {
    this.list = list;
  }

  private class AtualizarHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      disableButtons();

      // busca voo selecionada
      if (!result) {
        result = true;
        int[] rows = tabela.getSelectedRows();

        if (rows.length == 1) {
          pojo = list.get(rows[0]);
          refresh();
          showAll();
        } else {
          messageFailed();
          refresh();
        }

      }
    }
  }

  private class EnviarHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      RequestParamWrapper request = getParameters();

      String country = getCountry();
      String partida = request.stringParam("partida");
      String chegada = request.stringParam("chegada");
      String dataPartida = null;
      String dataChegada = null;

      if (country.equals("US")) {
        String timePartida = request.stringParam("timePartida");
        String timeChegada = request.stringParam("timeChegada");

        dataPartida = String.format("%s %s", partida, timePartida);
        dataChegada = String.format("%s %s", chegada, timeChegada);
      } else {
        dataPartida = partida;
        dataChegada = chegada;
      }

      DateTime _partida = FormatDateTime.parseToDateTime(dataPartida, country);
      DateTime _chegada = FormatDateTime.parseToDateTime(dataChegada, country);

      request.set("id", pojo.getId());
      request.set("partida", _partida);
      request.set("chegada", _chegada);

      Voo voo = new VooUpdate(request).createInstance();
      boolean executed = new VooDAO().atualizar(voo);
      if (executed) {
        messageOK();
        refresh();
      } else {
        messageTimeException();
      }
    }
  }

}