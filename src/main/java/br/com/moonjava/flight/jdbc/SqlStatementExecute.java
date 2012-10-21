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

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import br.com.moonjava.flight.util.Param;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
class SqlStatementExecute {

  // Esta classe popula um statement de acordo com o tipo passado junto ao sql
  // Utilizado para a Fluent Interface
  static PreparedStatement setStmt(PreparedStatement stm, List<Param<?>> params, int value) {

    for (int i = 0; i < params.size(); i++) {
      Object object = params.get(i).getValue();

      try {
        if (object instanceof Integer) {
          stm.setInt(++value, ((Integer) object).intValue());
        }

        if (object instanceof Long) {
          stm.setLong(++value, ((Long) object).longValue());
        }

        if (object instanceof Double) {
          stm.setDouble(++value, ((Double) object).doubleValue());
        }

        if (object instanceof String) {
          stm.setString(++value, ((String) object).toString());
        }

        if (object instanceof Boolean) {
          stm.setBoolean(++value, ((Boolean) object).booleanValue());
        }

        if (object instanceof LocalDate) {
          Date date = ((LocalDate) object).toDate();
          java.sql.Date localDate = new java.sql.Date(date.getTime());
          stm.setDate(++value, localDate);
        }

        if (object instanceof DateTime) {
          Date date = ((DateTime) object).toDate();
          Timestamp timestamp = new Timestamp(date.getTime());
          stm.setTimestamp(++value, timestamp);
        }

      } catch (SQLException e) {
        throw new RuntimeException(e);
      }

    }
    return stm;

  }

}