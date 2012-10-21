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

import org.joda.time.DateTime;

import br.com.moonjava.flight.model.base.Aeronave;
import br.com.moonjava.flight.model.base.AeronaveModel;
import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.model.base.VooModel;
import br.com.moonjava.flight.util.FlightFocusLostListener;
import br.com.moonjava.flight.util.FormatDateTime;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.util.VerifierString;
import br.com.moonjava.flight.view.voo.CriarVooUI;

/**
 * @version 1.0 Aug 29, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class CriarVooController extends CriarVooUI {

  private DateTime _partida;
  private DateTime _chegada;

  public CriarVooController(JPanel conteudo,
                            ResourceBundle bundle,
                            JButton atualizar,
                            JButton deletar,
                            JButton status) {
    super(conteudo, bundle, atualizar, deletar, status);

    addFocusPrecoListener(new FocusPrecoHandler());
    addFocusDataPartidaListener(new FocusDataPartidaHandler());
    addFocusDataChegadaListener(new FocusDataChegadaHandler());
    addCadastrarListener(new CadastrarHandler());
  }

  @Override
  public List<Aeronave> getList() {
    RequestParamWrapper request = new RequestParamWrapper();
    return new AeronaveModel().consultar(request);
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

  private class FocusPrecoHandler extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      try {
        RequestParamWrapper request = getParameters();
        String preco = request.stringParam("preco");
        double _preco = Double.parseDouble(preco);
        if (_preco <= 0) {
          throw new NumberFormatException();
        } else {
          messagePrecoOk();
        }
      } catch (NumberFormatException e2) {
        messagePrecoParseExecption();
      }
    }
  }

  private class CadastrarHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      RequestParamWrapper request = getParameters();
      String country = getCountry();
      String partida = request.stringParam("partida");
      String chegada = request.stringParam("chegada");
      String dataPartida = null;
      String dataChegada = null;

      // Teste para atribuir o padrão AM e PM
      if (country.equals("US")) {
        String timePartida = request.stringParam("timePartida");
        String timeChegada = request.stringParam("timeChegada");

        dataPartida = String.format("%s %s", partida, timePartida);
        dataChegada = String.format("%s %s", chegada, timeChegada);
      } else {
        dataPartida = partida;
        dataChegada = chegada;
      }

      try {
        DateTime _partida = FormatDateTime.parseToDateTime(dataPartida, country);
        DateTime _chegada = FormatDateTime.parseToDateTime(dataChegada, country);

        if (request.stringParam("origem").isEmpty() || request.stringParam("destino").isEmpty()) {
          throw new Exception();
        }

        if (!VerifierString.isDateValid(dataPartida, bundle)) {
          addImagePartidaInvalid();
          throw new Exception();
        }

        if (VerifierString.isDateValid(dataChegada, bundle)) {
          _chegada = FormatDateTime.parseToDateTime(dataChegada, country);
          if (!_chegada.isAfter(_partida)) {
            addImageChegadaInvalid();
            throw new Exception();
          }
        } else {
          addImageChegadaInvalid();
          throw new Exception();
        }

        double _preco = 0;
        try {
          String preco = request.stringParam("preco");
          _preco = Double.parseDouble(preco);
          if (_preco <= 0) {
            throw new Exception();
          }
        } catch (Exception e2) {
          messagePrecoParseExecption();
          throw new Exception();
        }
        request.set("preco", _preco);
        request.set("partida", _partida);
        request.set("chegada", _chegada);

        Voo pojo = new VooCreate(request).createInstance();
        boolean executed = new VooModel().criar(pojo);

        if (executed) {
          messageOK();
        } else {
          messageFailed();
        }
      } catch (Exception e2) {
        addMessageFailed();
      }
    }
  }

}