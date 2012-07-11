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
package br.com.moonjava.flight.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.moonjava.flight.jdbc.Conexao;
import br.com.moonjava.flight.jdbc.ConexaoImpl;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
class TestSqlStatementWrapper implements TestSqlStatement {

  private String syntax;
  private Connection connection;

  public TestSqlStatementWrapper() {
    this.syntax = "";
  }

  @Override
  public TestSqlStatement truncate() {
    Conexao impl = new ConexaoImpl();
    connection = impl.getConexao();
    String foreignKey = "set foreign_key_checks=0;";
    try {
      PreparedStatement stm = connection.prepareStatement(foreignKey);
      stm.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return this;
  }

  @Override
  public TestSqlStatement with(String syntax) {
    this.syntax += syntax + " ";
    return this;
  }

  @Override
  public TestSqlStatement execute() {
    try {
      PreparedStatement stm = connection.prepareStatement(syntax);
      stm.executeUpdate();
      this.syntax = "";
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return this;
  }

}