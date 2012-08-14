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

import java.sql.ResultSet;

import br.com.moonjava.flight.base.Banco;
import br.com.moonjava.flight.base.BancoImpl;
import br.com.moonjava.flight.base.PessoaFisica;
import br.com.moonjava.flight.jdbc.ResultSetJdbc;
import br.com.moonjava.flight.jdbc.ResultSetJdbcLoader;
import br.com.moonjava.flight.jdbc.ResultSetJdbcWrapper;

/**
 * @version 1.0, Aug 13, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class BancoLoader implements ResultSetJdbcLoader<Banco> {

  private String alias;

  public BancoLoader() {
    this.alias = "BANCO";
  }

  @Override
  public Banco get(ResultSet resultSet) {
    ResultSetJdbcWrapper rs = new ResultSetJdbcWrapper(resultSet, alias);
    return new BancoBuilder(rs).createInstance();
  }

  private class BancoBuilder implements Banco.Builder {

    private final ResultSetJdbc rs;

    public BancoBuilder(ResultSetJdbc rs) {
      this.rs = rs;
    }

    @Override
    public Banco createInstance() {
      BancoImpl impl = new BancoImpl(this);
      impl.setId(rs.getInt("ID"));
      return impl;
    }

    @Override
    public PessoaFisica getPessoaFisica() {
      ResultSet resultSet = rs.getResultSet();
      return new PessoaFisicaLoader().get(resultSet);
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
  }
}