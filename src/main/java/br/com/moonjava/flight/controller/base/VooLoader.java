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
package br.com.moonjava.flight.controller.base;

import java.sql.ResultSet;

import org.joda.time.DateTime;

import br.com.moonjava.flight.jdbc.ResultSetJdbc;
import br.com.moonjava.flight.jdbc.ResultSetJdbcLoader;
import br.com.moonjava.flight.jdbc.ResultSetJdbcWrapper;
import br.com.moonjava.flight.model.base.Aeronave;
import br.com.moonjava.flight.model.base.Status;
import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.model.base.VooModel;

/**
 * @version 1.0 Jul 25, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class VooLoader implements ResultSetJdbcLoader<Voo> {

  private final String alias;

  public VooLoader() {
    this.alias = "VOO";
  }

  @Override
  public Voo get(ResultSet resultSet) {
    ResultSetJdbcWrapper rs = new ResultSetJdbcWrapper(resultSet, alias);
    return new VooBuilder(rs).createInstance();
  }

  private class VooBuilder implements Voo.Builder {

    private final ResultSetJdbc rs;

    public VooBuilder(ResultSetJdbc rs) {
      this.rs = rs;
    }

    @Override
    public Voo createInstance() {
      VooModel impl = new VooModel(this);
      impl.setId(rs.getInt("ID"));
      return impl;
    }

    @Override
    public String getCodigo() {
      return rs.getString("CODIGO");
    }

    @Override
    public Aeronave getAeronave() {
      ResultSet resultSet = rs.getResultSet();
      return new AeronaveLoader().get(resultSet);
    }

    @Override
    public String getOrigem() {
      return rs.getString("ORIGEM");
    }

    @Override
    public String getDestino() {
      return rs.getString("DESTINO");
    }

    @Override
    public String getEscala() {
      return rs.getString("ESCALA");
    }

    @Override
    public DateTime getDataDePartida() {
      return rs.getDateTime("DATA_PARTIDA");
    }

    @Override
    public DateTime getDataDeChegada() {
      return rs.getDateTime("DATA_CHEGADA");
    }

    @Override
    public String getObservacao() {
      return rs.getString("OBSERVACAO");
    }

    @Override
    public Status getStatus() {
      int val = rs.getInt("STATUS");
      Status[] status = Status.values();
      return status[val];
    }

    @Override
    public int getAssentoLivre() {
      return rs.getInt("ASSENTO_LIVRE");
    }

    @Override
    public double getPreco() {
      return rs.getDouble("PRECO");
    }

  }

}