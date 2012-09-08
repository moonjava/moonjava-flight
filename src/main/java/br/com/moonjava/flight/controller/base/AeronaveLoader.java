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

import br.com.moonjava.flight.jdbc.ResultSetJdbcLoader;
import br.com.moonjava.flight.jdbc.ResultSetJdbcWrapper;
import br.com.moonjava.flight.model.base.Aeronave;
import br.com.moonjava.flight.model.base.AeronaveModel;

/**
 * @version 1.0 Jul 25, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class AeronaveLoader implements ResultSetJdbcLoader<Aeronave> {

  private final String alias;

  public AeronaveLoader() {
    this.alias = "AERONAVE";
  }

  @Override
  public Aeronave get(ResultSet resultSet) {
    ResultSetJdbcWrapper rs = new ResultSetJdbcWrapper(resultSet, alias);
    return new AeronaveBuilder(rs).createInstance();
  }

  private class AeronaveBuilder implements Aeronave.Builder {

    private final ResultSetJdbcWrapper rs;

    public AeronaveBuilder(ResultSetJdbcWrapper rs) {
      this.rs = rs;
    }

    @Override
    public Aeronave createInstance() {
      AeronaveModel impl = new AeronaveModel(this);
      impl.setId(rs.getInt("ID"));
      return impl;
    }

    @Override
    public String getCodigo() {
      return rs.getString("CODIGO");
    }

    @Override
    public String getNome() {
      return rs.getString("NOME");
    }

    @Override
    public int getQtdDeAssento() {
      return rs.getInt("QTD_ASSENTO");
    }

    @Override
    public boolean isMapa() {
      return rs.getBoolean("MAPA");
    }

  }

}