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
import java.awt.event.FocusEvent;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.joda.time.DateTime;

import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.model.base.VooModel;
import br.com.moonjava.flight.util.FlightFocusLostListener;
import br.com.moonjava.flight.util.FormatDateTime;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.util.VerifierString;
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

  private DateTime _partida;
  private DateTime _chegada;

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
    addFocusDataPartidaListener(new FocusDataPartidaHandler());
    addFocusDataChegadaListener(new FocusDataChegadaHandler());
  }

  public void setResult(boolean result) {
    this.result = result;
  }

  public void setList(List<Voo> list) {
    this.list = list;
  }

  private class FocusDataPartidaHandler extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      // Valida a data de acordo com o país
      try {
        RequestParamWrapper request = getParameters();
        String country = getCountry();
        String partida = request.stringParam("partida");
        String dataPartida = null;

        if (country.equals("US")) {
          String timePartida = request.stringParam("timePartida");
          dataPartida = String.format("%s %s", partida, timePartida);
        } else {
          dataPartida = partida;
        }
        if (VerifierString.isDateValid(dataPartida, bundle)) {
          addImagePartidaValid();
          _partida = FormatDateTime.parseToDateTime(dataPartida, country);
        } else {
          addImagePartidaInvalid();
        }
      } catch (Exception e2) {
        addImagePartidaInvalid();
      }
    }
  }

  private class FocusDataChegadaHandler extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      // Valida a data de acordo com o país
      try {
        RequestParamWrapper request = getParameters();
        String country = getCountry();
        String chegada = request.stringParam("chegada");
        String dataChegada = null;

        if (country.equals("US")) {
          String timeChegada = request.stringParam("timeChegada");
          dataChegada = String.format("%s %s", chegada, timeChegada);
        } else {
          dataChegada = chegada;
        }
        if (VerifierString.isDateValid(dataChegada, bundle)) {
          _chegada = FormatDateTime.parseToDateTime(dataChegada, country);
          if (_chegada.isAfter(_partida)) {
            addImageChegadaValid();
          } else {
            addImageChegadaInvalid();
          }
        } else {
          addImageChegadaInvalid();
        }
      } catch (Exception e2) {
        addImageChegadaInvalid();
      }
    }
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
      try {
        RequestParamWrapper request = getParameters();
        request.set("id", pojo.getId());
        request.set("partida", _partida);
        request.set("chegada", _chegada);

        Voo pojo = new VooUpdate(request).createInstance();
        boolean executed = new VooModel().atualizar(pojo);
        if (executed) {
          messageOK();
          refresh();
        } else {
          messageTimeException();
        }
      } catch (Exception e2) {
        addMessageFailed();
      }
    }
  }

}