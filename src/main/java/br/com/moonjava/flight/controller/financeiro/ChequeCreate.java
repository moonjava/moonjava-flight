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

import br.com.moonjava.flight.model.financeiro.Cheque;
import br.com.moonjava.flight.model.financeiro.ChequeModel;
import br.com.moonjava.flight.util.RequestParam;

/**
 * @version 1.0 Jul 25, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class ChequeCreate implements Cheque.Builder {

  private final RequestParam request;

  public ChequeCreate(RequestParam request) {
    this.request = request;
  }

  @Override
  public Cheque createInstance() {
    return new ChequeModel(this);
  }

  @Override
  public String getTitular() {
    return request.stringParam("titular");
  }

  @Override
  public int getNumero() {
    return request.intParam("numero");
  }

  @Override
  public int getBanco() {
    return request.intParam("banco");
  }

  @Override
  public int getAgencia() {
    return request.intParam("agencia");
  }

  @Override
  public int getConta() {
    return request.intParam("conta");
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