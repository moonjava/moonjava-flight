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
package br.com.moonjava.flight.base;

import java.util.List;

import org.joda.time.LocalDate;

import br.com.moonjava.flight.util.RequestParam;

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

    long getCpf();

    String getRg();

    String getEndereco();

    int getTelResidencial();

    int getTelCelular();

    String getEmail();

  }

  interface Jdbc {
    void criar(PessoaFisica pessoaFisica);

    List<PessoaFisica> consultar(RequestParam request);

    PessoaFisica consultarPorId(int id);

    PessoaFisica consultarPorCpf(long cpf);

    void atualizar(PessoaFisica pessoaFisica);

    void deletar(int id);
  }

  int getId();

  String getNome();

  String getSobrenome();

  LocalDate getDataNascimento();

  long getCpf();

  String getRg();

  String getEndereco();

  int getTelResidencial();

  int getTelCelular();

  String getEmail();

}
