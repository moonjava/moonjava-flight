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

import org.joda.time.DateTime;

import br.com.moonjava.flight.model.base.Aeronave;
import br.com.moonjava.flight.model.base.AeronaveModel;
import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.model.base.VooModel;
import br.com.moonjava.flight.util.FormatDateTime;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.view.voo.CriarVooUI;

/**
 * @version 1.0 Aug 29, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class CriarVooController extends CriarVooUI {

  public CriarVooController(JPanel conteudo,
                            ResourceBundle bundle,
                            JButton atualizar,
                            JButton deletar,
                            JButton status) {
    super(conteudo, bundle, atualizar, deletar, status);

    addCadastrarListener(new CadastrarHandler());
  }

  @Override
  public List<Aeronave> getList() {
    RequestParamWrapper request = new RequestParamWrapper();
    return new AeronaveModel().consultar(request);
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

      try {
        String preco = request.stringParam("preco");
        double _preco = Double.parseDouble(preco);
        if (_preco <= 0) {
          throw new NumberFormatException();
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
      } catch (NumberFormatException e2) {
        messageNumberException();
      }
      refresh();
    }
  }

}