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
public interface PessoaFisica {

  interface Builder extends br.com.moonjava.flight.util.Builder<PessoaFisica> {

    String getNome();

    String getSobrenome();

    LocalDate getDataNascimento();

    CPF getCpf();

    String getRg();

    String getEndereco();

    long getTelResidencial();

    long getTelCelular();

    String getEmail();

  }

  interface Jdbc {

    boolean criar(PessoaFisica pessoaFisica);

    PessoaFisica consultarPorCpf(CPF cpf);

    boolean atualizar(PessoaFisica pessoaFisica);

  }

  int getId();

  String getNome();

  String getSobrenome();

  LocalDate getDataNascimento();

  CPF getCpf();

  String getRg();

  String getEndereco();

  long getTelResidencial();

  long getTelCelular();

  String getEmail();

  boolean criar(PessoaFisica pojo);

  PessoaFisica consultarPorCPF(CPF cpf);

  void atualizar(PessoaFisica pojo);

}