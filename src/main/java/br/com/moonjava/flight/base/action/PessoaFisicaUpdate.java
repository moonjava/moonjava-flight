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

import org.joda.time.LocalDate;

import br.com.moonjava.flight.base.PessoaFisica;
import br.com.moonjava.flight.base.PessoaFisicaImpl;
import br.com.moonjava.flight.util.RequestParam;

/**
 * @version 1.0, 10/08/2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class PessoaFisicaUpdate implements PessoaFisica.Builder {

  private final RequestParam request;

  public PessoaFisicaUpdate(RequestParam request) {
    this.request = request;
  }

  @Override
  public PessoaFisica createInstance() {
    PessoaFisicaImpl impl = new PessoaFisicaImpl(this);
    impl.setId(request.intParam("id"));
    return impl;
  }

  @Override
  public String getNome() {
    return request.stringParam("nome");
  }

  @Override
  public String getSobrenome() {
    return request.stringParam("sobrenome");
  }

  @Override
  public LocalDate getDataNascimento() {
    return request.localDateParam("nascimento");
  }

  @Override
  public long getCpf() {
    return request.longParam("cpf");
  }

  @Override
  public String getRg() {
    return request.stringParam("rg");
  }

  @Override
  public String getEndereco() {
    return request.stringParam("endereco");
  }

  @Override
  public int getTelResidencial() {
    return request.intParam("telResidencial");
  }

  @Override
  public int getTelCelular() {
    return request.intParam("telCelular");
  }

  @Override
  public String getEmail() {
    return request.stringParam("email");
  }

}