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
import br.com.moonjava.flight.model.base.PassagemModel;
import br.com.moonjava.flight.model.base.PessoaFisica;
import br.com.moonjava.flight.model.base.Voo;

/**
 * @version 1.0 06/10/2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class PassagemLoader implements ResultSetJdbcLoader<Passagem> {

  private final String alias;

  public PassagemLoader() {
    this.alias = "PASSAGEM";
  }

  @Override
  public Passagem get(ResultSet resultSet) {
    ResultSetJdbcWrapper rs = new ResultSetJdbcWrapper(resultSet, alias);
    return new PassagemBuilder(rs).createInstance();
  }

  private class PassagemBuilder implements Passagem.Builder {

    private final ResultSetJdbc rs;

    public PassagemBuilder(ResultSetJdbc rs) {
      this.rs = rs;
    }

    @Override
    public Passagem createInstance() {
      PassagemModel impl = new PassagemModel(this);
      impl.setId(rs.getInt("ID"));
      return impl;
    }

    @Override
    public Voo getVoo() {
      ResultSet resultSet = rs.getResultSet();
      return new VooLoader().get(resultSet);
    }

    @Override
    public PessoaFisica getPessoaFisica() {
      ResultSet resultSet = rs.getResultSet();
      return new PessoaFisicaLoader().get(resultSet);
    }

    @Override
    public String getCodigoBilhete() {
      return rs.getString("COD_BILHETE");
    }

    @Override
    public String getAssento() {
      return rs.getString("ASSENTO");
    }
  }

}
