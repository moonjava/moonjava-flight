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
package br.com.moonjava.flight.base.action;

import org.joda.time.DateTime;

import br.com.moonjava.flight.base.Aeronave;
import br.com.moonjava.flight.base.AeronaveFake;
import br.com.moonjava.flight.base.Status;
import br.com.moonjava.flight.base.Voo;
import br.com.moonjava.flight.base.VooImpl;
import br.com.moonjava.flight.util.RequestParam;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class VooCreate implements Voo.Builder {

  private final RequestParam request;

  public VooCreate(RequestParam request) {
    this.request = request;
  }

  @Override
  public Voo createInstance() {
    return new VooImpl(this);
  }

  @Override
  public int getCodigo() {
    return request.intParam("codigo");
  }

  @Override
  public Aeronave getAeronave() {
    return new AeronaveFake() {
      @Override
      public int getId() {
        return request.intParam("aeronave");
      }
    };
  }

  @Override
  public String getOrigem() {
    return request.stringParam("origem");
  }

  @Override
  public String getDestino() {
    return request.stringParam("destino");
  }

  @Override
  public String getEscala() {
    return request.stringParam("escala");
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
    return null;
  }

  @Override
  public Status getStatus() {
    return Status.DISPONIVEL;
  }

}