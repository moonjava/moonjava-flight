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

import br.com.moonjava.flight.base.Banco;
import br.com.moonjava.flight.base.BancoImpl;
import br.com.moonjava.flight.base.PessoaFisica;
import br.com.moonjava.flight.base.PessoaFisicaFake;
import br.com.moonjava.flight.util.RequestParam;

/**
 * @version 1.0, Aug 13, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class BancoCreate implements Banco.Builder {

  private final RequestParam request;

  public BancoCreate(RequestParam request) {
    this.request = request;
  }

  @Override
  public Banco createInstance() {
    return new BancoImpl(this);
  }

  @Override
  public PessoaFisica getPessoaFisica() {
    return new PessoaFisicaFake() {
      @Override
      public int getId() {
        return request.intParam("pessoaFisica");
      }
    };
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

}