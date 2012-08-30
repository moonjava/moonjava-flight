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

import org.joda.time.DateTime;

import br.com.moonjava.flight.model.base.Aeronave;
import br.com.moonjava.flight.model.base.Status;
import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.model.base.VooModel;
import br.com.moonjava.flight.util.RequestParam;

/**
 * @version 1.0 Jul 25, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class VooUpdate implements Voo.Builder {

  private final RequestParam request;

  public VooUpdate(RequestParam request) {
    this.request = request;
  }

  @Override
  public Voo createInstance() {
    if (getDataDePartida().isBefore(getDataDeChegada()) &&
        getDataDePartida().isAfter(System.currentTimeMillis())) {
      VooModel impl = new VooModel(this);
      impl.setId(request.intParam("id"));
      return impl;
    } else {
      return null;
    }
  }

  @Override
  public String getCodigo() {
    return null;
  }

  @Override
  public Aeronave getAeronave() {
    return null;
  }

  @Override
  public String getOrigem() {
    return null;
  }

  @Override
  public String getDestino() {
    return null;
  }

  @Override
  public String getEscala() {
    return null;
  }

  @Override
  public DateTime getDataDePartida() {
    return request.dateTimeParam("partida");
  }

  @Override
  public DateTime getDataDeChegada() {
    return request.dateTimeParam("chegada");
  }

  @Override
  public String getObservacao() {
    return request.stringParam("observacao");
  }

  @Override
  public Status getStatus() {
    return request.enumParam(Status.class, "status");
  }

  @Override
  public int getAssentoLivre() {
    return 0;
  }

  @Override
  public double getPreco() {
    return 0;
  }

}