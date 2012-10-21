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
package br.com.moonjava.flight.model.base;

import org.joda.time.LocalDate;

import br.com.moonjava.flight.util.CPF;

/**
 * @version 1.0, 10/08/2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class PessoaFisicaFake implements PessoaFisica {

  @Override
  public int getId() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getNome() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getSobrenome() {
    throw new UnsupportedOperationException();
  }

  @Override
  public LocalDate getDataNascimento() {
    throw new UnsupportedOperationException();
  }

  @Override
  public CPF getCpf() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getRg() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getEndereco() {
    throw new UnsupportedOperationException();
  }

  @Override
  public long getTelResidencial() {
    throw new UnsupportedOperationException();
  }

  @Override
  public long getTelCelular() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getEmail() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean criar(PessoaFisica pojo) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void atualizar(PessoaFisica pojo) {
    throw new UnsupportedOperationException();
  }

  @Override
  public PessoaFisica consultarPorCPF(CPF cpf) {
    throw new UnsupportedOperationException();
  }

}