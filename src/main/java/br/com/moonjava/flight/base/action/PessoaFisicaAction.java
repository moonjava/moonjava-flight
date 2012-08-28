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

import java.util.List;

import br.com.moonjava.flight.base.PessoaFisica;
import br.com.moonjava.flight.jdbc.SqlStatement;
import br.com.moonjava.flight.jdbc.SqlStatementWrapper;
import br.com.moonjava.flight.util.RequestParam;

/**
 * @version 1.0, 10/08/2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class PessoaFisicaAction implements PessoaFisica.Jdbc {

  private SqlStatement query() {
    return new SqlStatementWrapper()
        .prepare()

        .with("select *")
        .with("from FLIGHT.PESSOAFISICA as PESSOAFISICA")

        .load(new PessoaFisicaLoader());
  }

  @Override
  public void criar(PessoaFisica pessoaFisica) {
    new SqlStatementWrapper()
        .prepare()

        .with("insert into FLIGHT.PESSOAFISICA")
        .with("(NOME, SOBRENOME, DATA_NASCIMENTO, CPF, RG, ENDERECO, ")
        .with("TEL_RESIDENCIAL, TEL_CELULAR, EMAIL)")

        .with("values (")
        .with("?,", pessoaFisica.getNome())
        .with("?,", pessoaFisica.getSobrenome())
        .with("?,", pessoaFisica.getDataNascimento())
        .with("?,", pessoaFisica.getCpf())
        .with("?,", pessoaFisica.getRg())
        .with("?,", pessoaFisica.getEndereco())
        .with("?,", pessoaFisica.getTelResidencial())
        .with("?,", pessoaFisica.getTelCelular())
        .with("?)", pessoaFisica.getEmail())

        .andExecute();
  }

  @Override
  public List<PessoaFisica> consultar(RequestParam request) {
    String nome = request.stringParam("nome");
    String sobrenome = request.stringParam("sobrenome");

    return query()

        .with("where 1 = 1")
        .with("and PESSOAFISICA.NOME like concat ('%',?,'%')", nome)
        .with("and PESSOAFISICA.SOBRENOME like concat ('%',?,'%')", sobrenome)
        .with("order by PESSOAFISICA.NOME asc")

        .andList();
  }

  @Override
  public PessoaFisica consultarPorId(int id) {
    return query()

        .with("where PESSOAFISICA.ID = ?", id)

        .andGet();
  }

  @Override
  public PessoaFisica consultarPorCpf(long cpf) {
    return query()

        .with("where PESSOAFISICA.CPF = ?", cpf)

        .andGet();
  }

  @Override
  public void atualizar(PessoaFisica pessoaFisica) {
    new SqlStatementWrapper()
        .prepare()

        .with("update FLIGHT.PESSOAFISICA AS PESSOAFISICA set")
        .with("NOME = ?,", pessoaFisica.getNome())
        .with("SOBRENOME = ?,", pessoaFisica.getSobrenome())
        .with("DATA_NASCIMENTO = ?,", pessoaFisica.getDataNascimento())
        .with("CPF = ?,", pessoaFisica.getCpf())
        .with("RG = ?,", pessoaFisica.getRg())
        .with("ENDERECO = ?,", pessoaFisica.getEndereco())
        .with("TEL_RESIDENCIAL = ?,", pessoaFisica.getTelResidencial())
        .with("TEL_CELULAR = ?,", pessoaFisica.getTelCelular())
        .with("EMAIL = ?", pessoaFisica.getEmail())
        .with("where ID = ?", pessoaFisica.getId())

        .andExecute();
  }

  @Override
  public void deletar(int id) {
    new SqlStatementWrapper()
        .prepare()

        .with("delete from FLIGHT.PESSOAFISICA")
        .with("where PESSOAFISICA.ID = ?", id)

        .andExecute();
  }

}