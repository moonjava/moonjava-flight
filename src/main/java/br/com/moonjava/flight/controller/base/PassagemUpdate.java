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
import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.model.base.VooModel;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 07/10/2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class PassagemUpdate implements Passagem.Builder {

  private final RequestParamWrapper request;

  public PassagemUpdate(RequestParamWrapper request) {
    this.request = request;
  }

  @Override
  public Passagem createInstance() {
    PassagemModel impl = new PassagemModel(this);
    impl.setId(request.intParam("id"));
    return impl;
  }

  @Override
  public Voo getVoo() {
    return new VooModel().consultarPorId(request.intParam("voo"));
  }

  @Override
  public PessoaFisica getPessoaFisica() {
    return null;
  }

  @Override
  public String getCodigoBilhete() {
    return null;
  }

  @Override
  public String getAssento() {
    return null;
  }

}