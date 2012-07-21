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

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;

import org.dbunit.dataset.IDataSet;
import org.testng.annotations.Test;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
@Test
public class TesteDeDbUnit {

  public void teste_de_insert_dbunit() throws Exception {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());

    IDataSet dataSet = dbUnit.getDataSet();
    int aeronave = dataSet.getTable("AERONAVE").getRowCount();
    int voo = dataSet.getTable("VOO").getRowCount();

    assertThat(aeronave, equalTo(2));
    assertThat(voo, equalTo(4));
  }

}