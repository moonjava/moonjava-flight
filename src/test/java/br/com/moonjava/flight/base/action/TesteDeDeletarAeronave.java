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
package br.com.moonjava.flight.base.action;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.com.moonjava.flight.base.Aeronave;
import br.com.moonjava.flight.base.Status;
import br.com.moonjava.flight.base.Voo;
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0, 25/07/2012
 * @contact miqueias@moonjava.com.br
 * 
 */
@Test
public class TesteDeDeletarAeronave {

  @BeforeClass
  public void beforeClass() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void deletar() {
    VooAction actionVoo = new VooAction();
    AeronaveAction actionAeronave = new AeronaveAction();
    RequestParamWrapper request = new RequestParamWrapper();

    request.set("status", Status.DISPONIVEL);

    int id = 1;

    List<Voo> antesVoo = actionVoo.consultar(request);
    assertThat(antesVoo.size(), equalTo(3));

    List<Aeronave> antesAeronave = actionAeronave.consultar(request);
    assertThat(antesAeronave.size(), equalTo(2));

    actionVoo.deletarPorAeronaveId(id);
    actionAeronave.deletar(id);

    List<Voo> resVoo = actionVoo.consultar(request);
    assertThat(resVoo.size(), equalTo(1));

    List<Aeronave> resAeronave = actionAeronave.consultar(request);
    assertThat(resAeronave.size(), equalTo(1));
  }

}