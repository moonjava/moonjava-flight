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
package br.com.moonjava.flight.util;

import java.sql.ResultSet;

import br.com.moonjava.flight.jdbc.ResultSetJdbcLoader;
import br.com.moonjava.flight.jdbc.ResultSetJdbcWrapper;
import br.com.moonjava.flight.jdbc.SqlStatement;
import br.com.moonjava.flight.jdbc.SqlStatementWrapper;

/**
 * @version 1.0, Aug 17, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class GerarCodigo {

  private String tabela;
  private String codigo;

  public GerarCodigo(String tabela) {
    this.tabela = tabela;
    GerarCodigoAction action = new GerarCodigoAction();
    GerarCodigoutil teste = action.gerarCodigo();
    codigo = teste.gerarCcodigo();
  }

  public String getCodigo() {
    return codigo;
  }

  private class GerarCodigoAction implements GerarCodigoutil.Jdbc {

    private SqlStatement query() {
      String linha1 = "select concat('" + tabela.substring(0, 1) + "',max(ID)+1000) CCODIGO";
      String linha2 = "from FLIGHT." + tabela;

      return new SqlStatementWrapper()
          .prepare()

          .with(linha1)
          .with(linha2)

          .load(new GerarCodigoLoader());
    }

    public GerarCodigoutil gerarCodigo() {
      return query()

          .with("where 1=1")

          .andGet();
    }
  }

  private class GerarCodigoLoader implements ResultSetJdbcLoader<GerarCodigoutil> {

    private final String alias;

    public GerarCodigoLoader() {
      this.alias = "";
    }

    @Override
    public GerarCodigoutil get(ResultSet resultSet) {
      ResultSetJdbcWrapper rs = new ResultSetJdbcWrapper(resultSet, alias);
      return new GerarCodigoBuilder(rs).createInstance();
    }

    private class GerarCodigoBuilder implements GerarCodigoutil.Builder {

      private final ResultSetJdbcWrapper rs;

      public GerarCodigoBuilder(ResultSetJdbcWrapper rs) {
        this.rs = rs;
      }

      @Override
      public GerarCodigoutil createInstance() {
        GerarCodigoImpl impl = new GerarCodigoImpl(this);
        return impl;
      }

      @Override
      public String gerarCodico() {
        return rs.getString("CCODIGO");
      }

    }

  }

  private class GerarCodigoImpl implements GerarCodigoutil {

    private String codigo;

    public GerarCodigoImpl(Builder builder) {
      this.codigo = builder.gerarCodico();
    }
    @Override
    public String gerarCcodigo() {
      return codigo;
    }

  }

  private interface GerarCodigoutil {

    interface Builder extends br.com.moonjava.flight.util.Builder<GerarCodigoutil> {

      String gerarCodico();
    }

    interface Jdbc {

      GerarCodigoutil gerarCodigo();
    }

    String gerarCcodigo();
  }

}
