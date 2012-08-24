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

import org.joda.time.DateTime;

import br.com.moonjava.flight.base.Aeronave;
import br.com.moonjava.flight.base.Status;
import br.com.moonjava.flight.base.Voo;
import br.com.moonjava.flight.jdbc.SqlStatement;
import br.com.moonjava.flight.jdbc.SqlStatementWrapper;
import br.com.moonjava.flight.util.RequestParam;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class VooAction implements Voo.Jdbc {

  private SqlStatement query() {
    return new SqlStatementWrapper()
        .prepare()

        .with("select *")
        .with("from FLIGHT.VOO as VOO")

        .with("join FLIGHT.AERONAVE as AERONAVE")
        .with("on AERONAVE.ID = VOO.AERONAVE_ID")

        .load(new VooLoader());
  }

  @Override
  public void criar(Voo voo) {
    Aeronave aeronave = voo.getAeronave();
    new SqlStatementWrapper()
        .prepare()

        .with("insert into FLIGHT.VOO")
        .with("(AERONAVE_ID, CODIGO, ORIGEM, DESTINO, ESCALA,")
        .with("DATA_PARTIDA, DATA_CHEGADA, STATUS)")

        .with("values (")
        .with("?,", aeronave.getId())
        .with("?,", voo.getCodigo())
        .with("?,", voo.getOrigem())
        .with("?,", voo.getDestino())
        .with("?,", voo.getEscala())
        .with("?,", voo.getDataDePartida())
        .with("?,", voo.getDataDeChegada())
        .with("?)", voo.getStatus().ordinal())

        .andExecute();
  }

  @Override
  public List<Voo> consultar(RequestParam request) {
    Status status = request.enumParam(Status.class, "status");
    DateTime partida = request.dateTimeParam("partida");
    DateTime chegada = request.dateTimeParam("chegada");
    String origem = request.stringParam("origem");
    String destino = request.stringParam("destino");

    return query()

        .with("where 1 = 1")
        .with("and VOO.STATUS = ?", status.ordinal())
        .with("and VOO.DATA_PARTIDA >= ?", partida)
        .with("and VOO.DATA_CHEGADA <= ?", chegada)
        .with("and VOO.ORIGEM like concat ('%', ?, '%')", origem)
        .with("and VOO.DESTINO like concat ('%', ?, '%')", destino)

        .with("order by VOO.DATA_PARTIDA asc")

        .andList();
  }

  @Override
  public Voo consultarPorId(int id) {
    return query()

        .with("where VOO.ID = ?", id)

        .andGet();
  }

  @Override
  public List<Voo> consultarPorAeronaveId(RequestParam request) {
    int idAeronave = request.intParam("aeronaveId");

    return query()

        .with("where VOO.AERONAVE_ID = ?", idAeronave)

        .andList();
  }

  @Override
  public void atualizar(Voo voo) {
    new SqlStatementWrapper()
        .prepare()

        .with("update FLIGHT.VOO set")
        .with("DATA_PARTIDA = ?,", voo.getDataDePartida())
        .with("DATA_CHEGADA = ?,", voo.getDataDeChegada())
        .with("OBSERVACAO = ?", voo.getObservacao())

        .with("where ID = ?", voo.getId())

        .andExecute();
  }

  @Override
  public void deletar(int id) {
    new SqlStatementWrapper()
        .prepare()

        .with("delete from FLIGHT.VOO")
        .with("where ID = ?", id)

        .andExecute();
  }

  @Override
  public void deletarPorAeronaveId(int id) {
    new SqlStatementWrapper()
        .prepare()

        .with("delete from FLIGHT.VOO")
        .with("where AERONAVE_ID = ?", id)

        .andExecute();
  }

  @Override
  public void controlarStatus(int id, Status status) {
    new SqlStatementWrapper()
        .prepare()

        .with("update FLIGHT.VOO set")
        .with("STATUS = ?", status.ordinal())

        .with("where ID = ?", id)

        .andExecute();
  }

}