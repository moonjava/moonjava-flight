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

import br.com.moonjava.flight.model.base.Passagem;
import br.com.moonjava.flight.model.base.PassagemFake;
import br.com.moonjava.flight.model.base.Reembolso;
import br.com.moonjava.flight.model.base.ReembolsoModel;
import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.RequestParam;

/**
 * @version 1.0, Aug 13, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class ReembolsoCreate implements Reembolso.Builder {

  private final RequestParam request;

  public ReembolsoCreate(RequestParam request) {
    this.request = request;
  }

  @Override
  public Reembolso createInstance() {
    return new ReembolsoModel(this);
  }

  @Override
  public Passagem getPassagem() {
    return new PassagemFake() {

      @Override
      public int getId() {
        return request.intParam("passagem");
      }
    };
  }

  @Override
  public String getTitular() {
    return request.stringParam("titular");
  }

  @Override
  public CPF getCpf() {
    Long value = request.longParam("cpf");
    return CPF.valueOf(value);
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
}