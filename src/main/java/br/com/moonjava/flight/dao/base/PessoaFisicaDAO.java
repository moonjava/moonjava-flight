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
package br.com.moonjava.flight.dao.base;

import br.com.moonjava.flight.controller.base.PessoaFisicaLoader;
import br.com.moonjava.flight.jdbc.SqlStatement;
import br.com.moonjava.flight.jdbc.SqlStatementWrapper;
import br.com.moonjava.flight.model.base.PessoaFisica;
import br.com.moonjava.flight.util.CPF;

/**
 * @version 1.0, 10/08/2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class PessoaFisicaDAO implements PessoaFisica.Jdbc {

  private SqlStatement query() {
    return new SqlStatementWrapper()
        .prepare()

        .with("select *")
        .with("from FLIGHT.PESSOAFISICA as PESSOAFISICA")

        .load(new PessoaFisicaLoader());
  }

  @Override
  public boolean criar(PessoaFisica pessoaFisica) {
    boolean executed = new SqlStatementWrapper()
        .prepare()

        .with("insert into FLIGHT.PESSOAFISICA")
        .with("(NOME, SOBRENOME, DATA_NASCIMENTO, CPF, RG, ENDERECO, ")
        .with("TEL_RESIDENCIAL, TEL_CELULAR, EMAIL)")

        .with("values (")
        .with("?,", pessoaFisica.getNome())
        .with("?,", pessoaFisica.getSobrenome())
        .with("?,", pessoaFisica.getDataNascimento())
        .with("?,", pessoaFisica.getCpf().getDigito())
        .with("?,", pessoaFisica.getRg())
        .with("?,", pessoaFisica.getEndereco())
        .with("?,", pessoaFisica.getTelResidencial())
        .with("?,", pessoaFisica.getTelCelular())
        .with("?)", pessoaFisica.getEmail())

        .andExecute();

    return executed;
  }

  @Override
  public PessoaFisica consultarPorCpf(CPF cpf) {
    return query()

        .with("where PESSOAFISICA.CPF = ?", cpf.getDigito())

        .andGet();
  }

  @Override
  public boolean atualizar(PessoaFisica pessoaFisica) {
    boolean executed = new SqlStatementWrapper()
        .prepare()

        .with("update FLIGHT.PESSOAFISICA AS PESSOAFISICA set")
        .with("NOME = ?,", pessoaFisica.getNome())
        .with("SOBRENOME = ?,", pessoaFisica.getSobrenome())
        .with("DATA_NASCIMENTO = ?,", pessoaFisica.getDataNascimento())
        .with("CPF = ?,", pessoaFisica.getCpf().getDigito())
        .with("RG = ?,", pessoaFisica.getRg())
        .with("ENDERECO = ?,", pessoaFisica.getEndereco())
        .with("TEL_RESIDENCIAL = ?,", pessoaFisica.getTelResidencial())
        .with("TEL_CELULAR = ?,", pessoaFisica.getTelCelular())
        .with("EMAIL = ?", pessoaFisica.getEmail())
        .with("where ID = ?", pessoaFisica.getId())

        .andExecute();

    return executed;
  }

}