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
import br.com.moonjava.flight.model.base.PassagemModel;
import br.com.moonjava.flight.model.base.PessoaFisica;
import br.com.moonjava.flight.model.base.PessoaFisicaFake;
import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.model.base.VooFake;
import br.com.moonjava.flight.util.RequestParam;

/**
 * @version 1.0 Oct 6, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class PassagemCreate implements Passagem.Builder {

  private final RequestParam request;

  public PassagemCreate(RequestParam request) {
    this.request = request;
  }

  @Override
  public Passagem createInstance() {
    return new PassagemModel(this);
  }

  @Override
  public Voo getVoo() {
    return new VooFake() {
      @Override
      public int getId() {
        return request.intParam("voo");
      }
    };
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
  public String getCodigoBilhete() {
    return request.stringParam("codBilhete");
  }

  @Override
  public String getAssento() {
    return null;
  }

}