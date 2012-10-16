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

import java.util.List;

import br.com.moonjava.flight.controller.base.PassagemControlLoader;
import br.com.moonjava.flight.jdbc.SqlStatement;
import br.com.moonjava.flight.jdbc.SqlStatementWrapper;
import br.com.moonjava.flight.model.base.Passagem;
import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.RequestParam;

/**
 * @version 1.0 06/10/2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class PassagemDAO implements Passagem.Jdbc {

  private SqlStatement query() {
    return new SqlStatementWrapper()
        .prepare()

        .with("select *")
        .with("from FLIGHT.PASSAGEM as PASSAGEM")

        .with("join FLIGHT.PESSOAFISICA as PESSOAFISICA")
        .with("on PASSAGEM.PESSOAFISICA_ID = PESSOAFISICA.ID")

        .with("join FLIGHT.VOO as VOO")
        .with("on PASSAGEM.VOO_ID = VOO.ID")

        .with("join FLIGHT.AERONAVE as AERONAVE")
        .with("on VOO.AERONAVE_ID = AERONAVE.ID")

        .load(new PassagemControlLoader());
  }

  @Override
  public boolean vender(Passagem passagem) {
    boolean executed = new SqlStatementWrapper()
        .prepare()

        .with("insert into FLIGHT.PASSAGEM")
        .with("(VOO_ID, PESSOAFISICA_ID, COD_BILHETE)")
        .with("values(")
        .with("?,", passagem.getVoo().getId())
        .with("?,", passagem.getPessoaFisica().getId())
        .with("?)", passagem.getCodigoBilhete())

        .andExecute();
    return executed;
  }

  @Override
  public List<Passagem> consultar(RequestParam request) {
    return query()

        .with("where 1 = 1 ")

        .andList();
  }

  @Override
  public Passagem consultarPorId(int id) {
    return query()

        .with("where PASSAGEM.ID = ?", id)

        .andGet();
  }

  @Override
  public Passagem consultarPorCodigoBilhete(String bilhete) {
    return query()

        .with("where PASSAGEM.COD_BILHETE = ?", bilhete)

        .andGet();
  }

  @Override
  public List<Passagem> consultarPorCpf(CPF cpf) {
    return query()

        .with("where PESSOAFISICA.CPF = ?", cpf.getDigito())

        .andList();

  }

  @Override
  public boolean transferir(Passagem passagem) {
    boolean executed = new SqlStatementWrapper()
        .prepare()

        .with("update FLIGHT.PASSAGEM as PASSAGEM set")
        .with("VOO_ID = ?,", passagem.getVoo().getId())
        .with("ASSENTO = ''")

        .with("where ID = ?", passagem.getId())

        .andExecute();
    return executed;
  }

  @Override
  public void deletar(int id) {
    new SqlStatementWrapper()
        .prepare()

        .with("delete from FLIGHT.PASSAGEM")
        .with("where PASSAGEM.ID = ?", id)

        .andExecute();
  }

  public List<Passagem> consultarPorVoo(Voo voo) {
    return query()

        .with("where PASSAGEM.VOO_ID = ?", voo.getId())

        .andList();
  }

  public boolean efetuarCheckin(Passagem pojo, String assento) {
    boolean executed = new SqlStatementWrapper()
        .prepare()

        .with("update FLIGHT.PASSAGEM as PASSAGEM set")
        .with("ASSENTO = ?", assento)

        .with("where ID = ?", pojo.getId())

        .andExecute();
    return executed;
  }

}