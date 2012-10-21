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
package br.com.moonjava.flight.controller.financeiro;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import br.com.moonjava.flight.model.financeiro.Bandeira;
import br.com.moonjava.flight.model.financeiro.Cartao;
import br.com.moonjava.flight.model.financeiro.CartaoModel;
import br.com.moonjava.flight.util.RequestParam;

/**
 * @version 1.0 Jul 25, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class CartaoCreate implements Cartao.Builder {

  private final RequestParam request;

  public CartaoCreate(RequestParam request) {
    this.request = request;
  }

  @Override
  public Cartao createInstance() {
    return new CartaoModel(this);
  }

  @Override
  public String getTitular() {
    return request.stringParam("titular");
  }

  @Override
  public long getNumero() {
    return request.longParam("numero");
  }

  @Override
  public LocalDate getDataDeValidade() {
    return request.localDateParam("validade").withDayOfMonth(1);
  }

  @Override
  public Bandeira getBandeira() {
    return request.enumParam(Bandeira.class, "bandeira");
  }

  @Override
  public double getValor() {
    return request.doubleParam("valor");
  }

  @Override
  public DateTime getDataDeCriacao() {
    return new DateTime();
  }

}