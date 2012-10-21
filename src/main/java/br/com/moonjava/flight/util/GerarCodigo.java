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
import br.com.moonjava.flight.jdbc.SqlStatementWrapper;

/**
 * @version 1.0, Aug 17, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class GerarCodigo {

  private final String tabela;

  // Gerar o codigo das tabelas automaticamente
  public GerarCodigo(String tabela) {
    this.tabela = tabela;
  }

  public String getCodigo() {
    String res = query().getCodigo();
    if (res != null) {
      return res;
    } else {
      return tabela.substring(0, 1) + "1000";
    }
  }

  private GerarCodigoBuilder query() {
    return new SqlStatementWrapper()
        .prepare()

        .with("select concat('" + tabela.substring(0, 1) + "',max(ID)+1000) CODIGO")
        .with("from FLIGHT." + tabela)

        .load(new GerarCodigoLoader())

        .andGet();
  }

  private class GerarCodigoLoader implements ResultSetJdbcLoader<GerarCodigoBuilder> {
    @Override
    public GerarCodigoBuilder get(ResultSet resultSet) {
      ResultSetJdbcWrapper rs = new ResultSetJdbcWrapper(resultSet, "");
      return new GerarCodigoBuilder(rs).createInstance();
    }
  }

  private class GerarCodigoBuilder implements Builder<GerarCodigoBuilder> {

    private final ResultSetJdbcWrapper rs;
    private String codigo;

    public GerarCodigoBuilder(ResultSetJdbcWrapper rs) {
      this.rs = rs;
    }

    @Override
    public GerarCodigoBuilder createInstance() {
      codigo = rs.getString("CODIGO");
      return this;
    }

    public String getCodigo() {
      return codigo;
    }

  }

}