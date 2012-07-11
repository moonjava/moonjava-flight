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

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class DbUnit extends DBTestCase {

  private FlatXmlDataSet fileXml;

  public DbUnit() {
    System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
        "com.mysql.jdbc.Driver");
    System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
        "jdbc:mysql://localhost/FLIGHT");
    System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "usjt");
    System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "usjt");
  }

  @Override
  protected IDataSet getDataSet() {
    try {
      fileXml = new FlatXmlDataSetBuilder()
          .build(new FileInputStream("src/test/resources/dbunit/dbunit-flight.xml"));

      return fileXml;
    } catch (DataSetException e) {
      throw new RuntimeException(e);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  protected DatabaseOperation getInsertOperation() throws Exception {
    return DatabaseOperation.CLEAN_INSERT;
  }

  protected DatabaseOperation getTruncateOperation() throws Exception {
    return DatabaseOperation.TRUNCATE_TABLE;
  }

  public void load() {
    try {
      IDataSet dataSet = getDataSet();
      IDatabaseConnection conn = getConnection();
      DatabaseOperation operation = getInsertOperation();

      DatabaseOperation insert = DatabaseOperation.TRANSACTION(operation);
      insert.execute(conn, dataSet);
    } catch (Exception e) {
      throw new DbUnitException("Falha na operação: " + e);
    }
  }

  public void truncate() {
    try {
      IDataSet dataSet = getDataSet();
      IDatabaseConnection conn = getConnection();
      DatabaseOperation operation = getTruncateOperation();

      disableForeignKey(conn);
      DatabaseOperation truncate = DatabaseOperation.TRANSACTION(operation);
      truncate.execute(conn, dataSet);

      enableForeignKey(conn);
    } catch (Exception e) {
      throw new DbUnitException("Falha na operação: " + e);
    }
  }

  protected void disableForeignKey(IDatabaseConnection conn) {
    statement(conn, "set foreign_key_checks=0"); // MySQL
  }

  protected void enableForeignKey(IDatabaseConnection conn) {
    statement(conn, "set foreign_key_checks=1"); // MySQL
  }

  private void statement(IDatabaseConnection con, String sql) {
    try {
      con.getConnection().prepareStatement(sql).execute();
    } catch (Exception e) {
      throw new DbUnitException("Erro em statement: " + e);
    }
  }

}