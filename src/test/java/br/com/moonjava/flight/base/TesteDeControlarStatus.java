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
package br.com.moonjava.flight.base;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.com.moonjava.flight.dao.base.VooDAO;
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.model.base.Status;
import br.com.moonjava.flight.model.base.Voo;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
@Test
public class TesteDeControlarStatus {

  @BeforeClass
  public void limparTabela() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void status_deve_ser_atualizado() {
    VooDAO dao = new VooDAO();

    int id = 4;
    Status status = Status.ATRASADO;

    Voo antes = dao.consultarPorId(id);
    assertThat(antes.getId(), equalTo(4));
    assertThat(antes.getStatus(), equalTo(Status.DISPONIVEL));

    dao.controlarStatus(id, status);

    Voo res = dao.consultarPorId(id);
    assertThat(antes.getId(), equalTo(4));
    assertThat(res.getStatus(), equalTo(Status.ATRASADO));
  }

}