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

import br.com.moonjava.flight.jdbc.ResultSetJdbc;
import br.com.moonjava.flight.jdbc.ResultSetJdbcLoader;
import br.com.moonjava.flight.jdbc.ResultSetJdbcWrapper;
import br.com.moonjava.flight.model.base.Passagem;
import br.com.moonjava.flight.model.base.Reembolso;
import br.com.moonjava.flight.model.base.ReembolsoModel;
import br.com.moonjava.flight.util.CPF;

/**
 * @version 1.0, Aug 13, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class ReembolsoControlLoader implements ResultSetJdbcLoader<Reembolso> {

  private final String alias;

  public ReembolsoControlLoader() {
    this.alias = "REEMBOLSO";
  }

  @Override
  public Reembolso get(ResultSet resultSet) {
    ResultSetJdbcWrapper rs = new ResultSetJdbcWrapper(resultSet, alias);
    return new ReembolsoBuilder(rs).createInstance();
  }

  private class ReembolsoBuilder implements Reembolso.Builder {

    private final ResultSetJdbc rs;

    public ReembolsoBuilder(ResultSetJdbc rs) {
      this.rs = rs;
    }

    @Override
    public Reembolso createInstance() {
      ReembolsoModel impl = new ReembolsoModel(this);
      impl.setId(rs.getInt("ID"));
      return impl;
    }

    @Override
    public Passagem getPassagem() {
      ResultSet resultSet = rs.getResultSet();
      return new PassagemControlLoader().get(resultSet);
    }

    @Override
    public String getTitular() {
      return rs.getString("TITULAR");
    }

    @Override
    public CPF getCpf() {
      long value = rs.getLong("CPF");
      return CPF.valueOf(value);
    }

    @Override
    public int getBanco() {
      return rs.getInt("BANCO");
    }

    @Override
    public int getAgencia() {
      return rs.getInt("AGENCIA");
    }

    @Override
    public int getConta() {
      return rs.getInt("CONTA");
    }

    @Override
    public double getValor() {
      return rs.getDouble("VALOR");
    }
  }
}