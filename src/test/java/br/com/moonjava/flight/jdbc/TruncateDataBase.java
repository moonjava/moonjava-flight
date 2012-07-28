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

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class TruncateDataBase {

  private TestSqlStatementWrapper sql;

  public TruncateDataBase() {
    this.sql = new TestSqlStatementWrapper();
  }

  public void load() {
    truncateVoo();
    truncateAeronave();
  }

  private void truncateVoo() {
    sql.truncate()

    .with("truncate FLIGHT.VOO;")
    .execute()
    
    .with("insert into FLIGHT.VOO (AERONAVE_ID, CODIGO, ORIGEM, DESTINO, DATA_PARTIDA, DATA_CHEGADA, STATUS)")
    .with("values (1, 1, 'origem A', 'destino A', '2012-01-01 00:00:00', '2012-01-01 06:00:00', 0);")
    .execute()
    
    .with("insert into FLIGHT.VOO (AERONAVE_ID, CODIGO, ORIGEM, DESTINO, DATA_PARTIDA, DATA_CHEGADA, STATUS)")
    .with("values (1, 2, 'origem B', 'destino B', '2012-02-01 00:00:00', '2012-02-01 06:00:00', 0);")
    .execute()
    
    .with("insert into FLIGHT.VOO (AERONAVE_ID, CODIGO, ORIGEM, DESTINO, DATA_PARTIDA, DATA_CHEGADA, STATUS)")
    .with("values (1, 3, 'origem C', 'destino C', '2012-02-01 00:00:00', '2012-02-01 06:00:00', 1);")
    .execute()

    .with("insert into FLIGHT.VOO (AERONAVE_ID, CODIGO, ORIGEM, DESTINO, DATA_PARTIDA, DATA_CHEGADA, STATUS)")
    .with("values (2, 4, 'origem D', 'destino D', '2012-03-01 00:00:00', '2012-03-01 06:00:00', 0);")
    .execute();
  }
  
  private void truncateAeronave() {
    sql.truncate()
    
    .with("truncate FLIGHT.AERONAVE;")
    .execute()
    
    .with("insert into FLIGHT.AERONAVE (NOME, CODIGO, QTD_ASSENTO, MAPA)")
    .with("values ('nave A', 1, 100, true);")
    .execute()
    
    .with("insert into FLIGHT.AERONAVE (NOME, CODIGO, QTD_ASSENTO, MAPA)")
    .with("values ('nave B', 2, 200, true);")
    .execute();
  }
  
}