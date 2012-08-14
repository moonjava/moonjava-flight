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

import br.com.moonjava.flight.base.PessoaFisica;
import br.com.moonjava.flight.base.Reembolso;
import br.com.moonjava.flight.jdbc.SqlStatement;
import br.com.moonjava.flight.jdbc.SqlStatementWrapper;

/**
 * @version 1.0, Aug 13, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class ReembolsoAction implements Reembolso.Jdbc {

  private SqlStatement query() {
    return new SqlStatementWrapper()
        .prepare()

        .with("select * from")
        .with("FLIGHT.BANCO as BANCO")

        .with("join FLIGHT.PESSOAFISICA as PESSOAFISICA")
        .with("on PESSOAFISICA_ID = BANCO.PESSOAFISICA_ID")

        .load(new ReembolsoLoader());
  }

  @Override
  public void criar(Reembolso reembolso) {
    PessoaFisica pessoaFisica = reembolso.getPessoaFisica();
    new SqlStatementWrapper()
        .prepare()

        .with("insert into FLIGHT.BANCO")
        .with("(ID, PESSOAFISICA_ID, BANCO, AGENCIA, CONTA)")

        .with("values (")
        .with("?,", reembolso.getID())
        .with("?,", pessoaFisica.getId())
        .with("?,", reembolso.getBanco())
        .with("?,", reembolso.getAgencia())
        .with("?)", reembolso.getConta())

        .andExecute();
  }

  @Override
  public Reembolso consultarPorPessoaFisica(int pessoaFisicaId) {
    return query()

        .with("where 1=1")
        .with("and BANCO.PESSOAFISICA_ID = ?", pessoaFisicaId)

        .andGet();
  }

  @Override
  public Reembolso consultarPorId(int id) {
    return query()

        .with("where 1=1")
        .with("and BANCO.ID = ?", id)

        .andGet();
  }

  @Override
  public void atualizar(Reembolso reembolso) {
    new SqlStatementWrapper()
        .prepare()

        .with("update FLIGHT.BANCO set")

        .with("BANCO = ?,", reembolso.getBanco())
        .with("AGENCIA = ?,", reembolso.getAgencia())
        .with("CONTA = ?", reembolso.getConta())

        .with("where ID = ?", reembolso.getID())

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