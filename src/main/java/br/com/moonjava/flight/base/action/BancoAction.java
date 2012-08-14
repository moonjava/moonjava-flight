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
import br.com.moonjava.flight.base.PessoaFisica;
import br.com.moonjava.flight.jdbc.SqlStatement;
import br.com.moonjava.flight.jdbc.SqlStatementWrapper;

/**
 * @version 1.0, Aug 13, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class BancoAction implements Banco.Jdbc {

  private SqlStatement query() {
    return new SqlStatementWrapper()
        .prepare()

        .with("select * from")
        .with("FLIGHT.BANCO as BANCO")

        .with("join FLIGHT.PESSOAFISICA as PESSOAFISICA")
        .with("on PESSOAFISICA_ID = BANCO.PESSOAFISICA_ID")

        .load(new BancoLoader());
  }

  @Override
  public void criar(Banco banco) {
    PessoaFisica pessoaFisica = banco.getPessoaFisica();
    new SqlStatementWrapper()
        .prepare()

        .with("insert into FLIGHT.BANCO")
        .with("(ID, PESSOAFISICA_ID, BANCO, AGENCIA, CONTA)")

        .with("values (")
        .with("?,", banco.getID())
        .with("?,", pessoaFisica.getId())
        .with("?,", banco.getBanco())
        .with("?,", banco.getAgencia())
        .with("?)", banco.getConta())

        .andExecute();
  }

  @Override
  public Banco consultarPorPessoaFisica(int pessoaFisicaId) {
    return query()

        .with("where 1=1")
        .with("and BANCO.PESSOAFISICA_ID = ?", pessoaFisicaId)

        .andGet();
  }

  @Override
  public Banco consultarPorId(int id) {
    return query()

        .with("where 1=1")
        .with("and BANCO.ID = ?", id)

        .andGet();
  }

  @Override
  public void atualizar(Banco banco) {
    new SqlStatementWrapper()
        .prepare()

        .with("update FLIGHT.BANCO set")

        .with("BANCO = ?,", banco.getBanco())
        .with("AGENCIA = ?,", banco.getAgencia())
        .with("CONTA = ?", banco.getConta())

        .with("where ID = ?", banco.getID())

        .andExecute();
  }

  @Override
  public void deletar(int id) {
    new SqlStatementWrapper()
        .prepare()

        .with("delete from FLIGHT.BANCO")
        .with("where ID = ?", id)

        .andExecute();
  }

  public void deletarPorPessoaFisicaId(int pessoaFisica) {
    new SqlStatementWrapper()
        .prepare()

        .with("delete from FLIGHT.BANCO")
        .with("where PESSOAFISICA_ID = ?", pessoaFisica)

        .andExecute();
  }

}