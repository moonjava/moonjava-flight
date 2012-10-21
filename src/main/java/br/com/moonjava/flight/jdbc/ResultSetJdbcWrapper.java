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

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class ResultSetJdbcWrapper implements ResultSetJdbc {

  private final ResultSet rs;
  private final String alias;

  // Esta classe, como o nome ja diz, é um Wrapper de ResultSets
  public ResultSetJdbcWrapper(ResultSet rs, String alias) {
    this.rs = rs;
    this.alias = alias;
  }

  @Override
  public ResultSet getResultSet() {
    return rs;
  }

  @Override
  public String getString(String columnLabel) {
    try {
      return rs.getString(alias + "." + columnLabel);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int getInt(String columnLabel) {
    try {
      return rs.getInt(alias + "." + columnLabel);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public double getDouble(String columnLabel) {
    try {
      return rs.getDouble(alias + "." + columnLabel);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean getBoolean(String columnLabel) {
    try {
      return rs.getBoolean(alias + "." + columnLabel);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public DateTime getDateTime(String columnLabel) {
    try {
      Timestamp timestamp = rs.getTimestamp(alias + "." + columnLabel);
      return new DateTime(timestamp);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public LocalDate getLocalDate(String columnLabel) {
    try {
      Date date = rs.getDate(alias + "." + columnLabel);
      return new LocalDate(date);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public long getLong(String columnLabel) {
    try {
      return rs.getLong(alias + "." + columnLabel);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}