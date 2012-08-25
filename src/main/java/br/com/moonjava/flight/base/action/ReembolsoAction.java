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

import br.com.moonjava.flight.base.Passagem;
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
        .with("FLIGHT.REEMBOLSO as REEMBOLSO")

        .with("join FLIGHT.PASSAGEM as PASSAGEM")
        .with("on PASSAGEM_ID = REEMBOLSO.PASSAGEM_ID")

        .load(new ReembolsoLoader());
  }

  @Override
  public void criar(Reembolso reembolso) {
    Passagem passagem = reembolso.getPassagem();
    new SqlStatementWrapper()
        .prepare()

        .with("insert into FLIGHT.REEMBOLSO")
        .with("(ID, PASSAGEM_ID, BANCO, AGENCIA, CONTA)")

        .with("values (")
        .with("?,", reembolso.getId())
        .with("?,", passagem.getId())
        .with("?,", reembolso.getBanco())
        .with("?,", reembolso.getAgencia())
        .with("?)", reembolso.getConta())

        .andExecute();
  }

  @Override
  public Reembolso consultarPorPassagemId(int passagemId) {
    return query()

        .with("where 1=1")
        .with("and REEMBOLSO.PASSAGEM_ID = ?", passagemId)

        .andGet();
  }

  @Override
  public Reembolso consultarPorId(int id) {
    return query()

        .with("where 1=1")
        .with("and REEMBOLSO.ID = ?", id)

        .andGet();
  }

  @Override
  public void atualizar(Reembolso reembolso) {
    new SqlStatementWrapper()
        .prepare()

        .with("update FLIGHT.REEMBOLSO set")

        .with("BANCO = ?,", reembolso.getBanco())
        .with("AGENCIA = ?,", reembolso.getAgencia())
        .with("CONTA = ?", reembolso.getConta())

        .with("where ID = ?", reembolso.getId())

        .andExecute();
  }

  @Override
  public void deletar(int id) {
    new SqlStatementWrapper()
        .prepare()

        .with("delete from FLIGHT.REEMBOLSO")
        .with("where ID = ?", id)

        .andExecute();
  }

  public void deletarPorPassagemId(int passagemId) {
    new SqlStatementWrapper()
        .prepare()

        .with("delete from FLIGHT.REEMBOLSO")
        .with("where PASSAGEM_ID = ?", passagemId)

        .andExecute();
  }

}