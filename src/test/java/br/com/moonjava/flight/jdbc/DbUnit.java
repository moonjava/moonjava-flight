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

import org.dbunit.DatabaseUnitException;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

/**
 * @version 1.2 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class DbUnit {

  private Conexao conexao;
  private FlatXmlDataSet fileXml;
  private IDatabaseTester databaseTester;
  private DbUnitSupplier supplier;

  public void load(DbUnitSupplier supplier) {
    conexao = new ConexaoImpl();
    this.supplier = supplier;
    conexao.getConexao();

    try {
      this.databaseTester = new JdbcDatabaseTester(
          conexao.getDriverClass(),
          conexao.getUrl(),
          conexao.getUser(),
          conexao.getPassword());

      IDataSet dataSet = getDataSet();
      databaseTester.setDataSet(dataSet);
      IDatabaseConnection conn = databaseTester.getConnection();
      DatabaseOperation operation = getInsertOperation();

      DatabaseOperation insert = DatabaseOperation.TRANSACTION(operation);
      insert.execute(conn, dataSet);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("It's impossible to load the class");

    } catch (DatabaseUnitException e) {
      throw new RuntimeException("Your connection with DbUnit has been failed");

    } catch (Exception e) {
      throw new RuntimeException("Error to execute Insert/Delete");
    }

  }

  public IDataSet getDataSet() {
    String xml = String.format("src/test/resources/dbunit/%s", supplier.getXml());
    try {
      fileXml = new FlatXmlDataSetBuilder().build(new FileInputStream(xml));

      return fileXml;
    } catch (DataSetException e) {
      throw new RuntimeException("Failed to build a DataSet");
    } catch (FileNotFoundException e) {
      throw new RuntimeException("Verify your Class DbUnitSupplier and subclass");
    }
  }

  public DatabaseOperation getInsertOperation() throws Exception {
    return DatabaseOperation.CLEAN_INSERT;
  }

}