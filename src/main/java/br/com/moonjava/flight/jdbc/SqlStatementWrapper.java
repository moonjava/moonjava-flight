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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.moonjava.flight.util.Param;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class SqlStatementWrapper implements SqlStatement {

  private static final Logger logger = LoggerFactory.getLogger(SqlStatementWrapper.class);

  // Esta classe esta por trás de tudo q acontece na fluent Interface dos DAOs
  // Resumindo, Cria-se um statement de acordo com os parametros e executa o sql
  // montado
  private int value;
  private Connection connection;
  private PreparedStatement stm;
  private String syntax;
  private final List<Param<?>> params;
  private ResultSetJdbcLoader<?> loader;

  public SqlStatementWrapper() {
    this.syntax = "";
    this.params = new ArrayList<Param<?>>();
  }

  @Override
  public SqlStatement prepare() {
    Conexao impl = new ConexaoImpl();
    connection = impl.getConexao();
    return this;
  }

  @Override
  public SqlStatement with(String syntax) {
    this.syntax += syntax + " ";
    return this;
  }

  @Override
  public SqlStatement with(String syntax, Object object) {
    if (object != null) {
      Param<?> param = Param.parseValue(object);
      params.add(param);
      with(syntax);
    }
    return this;
  }

  @Override
  public SqlStatement load(ResultSetJdbcLoader<?> loader) {
    this.loader = loader;
    return this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> List<T> andList() {
    List<T> list = new ArrayList<T>();
    try {
      stm = connection.prepareStatement(this.syntax);

      SqlStatementExecute.setStmt(stm, params, value);
      logger.info(stm.toString());
      stm.execute();
      ResultSet resultSet = stm.getResultSet();

      while (resultSet.next()) {
        T t = (T) loader.get(resultSet);
        list.add(t);
      }

      stm.close();
      resultSet.close();
      connection.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return list;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T andGet() {
    T t = null;
    try {
      stm = connection.prepareStatement(this.syntax);

      SqlStatementExecute.setStmt(stm, params, value);
      logger.info(stm.toString());
      stm.execute();
      ResultSet resultSet = stm.getResultSet();

      while (resultSet.next()) {
        t = (T) loader.get(resultSet);
      }

      stm.close();
      resultSet.close();
      connection.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return t;
  }

  @Override
  public boolean andExecute() {
    boolean res = false;
    try {
      stm = connection.prepareStatement(this.syntax);
      SqlStatementExecute.setStmt(stm, params, value);
      logger.info(stm.toString());
      stm.executeUpdate();

      stm.close();
      connection.close();
      res = true;
      return res;
    } catch (SQLException e) {
      logger.error(e.toString());
      return res;
    }
  }

}