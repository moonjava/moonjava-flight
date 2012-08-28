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

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.com.moonjava.flight.dao.base.AeronaveDAO;
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.model.base.Aeronave;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0, 25/07/2012
 * @contact miqueias@moonjava.com.br
 * 
 */
@Test
public class TesteDeConsultarAeronave {

  @BeforeClass
  public void limpaTabela() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void consultar_todas_aeronaves() {
    AeronaveDAO dao = new AeronaveDAO();
    RequestParamWrapper request = new RequestParamWrapper();

    List<Aeronave> res = dao.consultar(request);
    assertThat(res.size(), equalTo(2));

    Aeronave aero1 = res.get(0);
    Aeronave aero2 = res.get(1);

    assertThat(aero1.getId(), equalTo(1));
    assertThat(aero1.getCodigo(), equalTo("A1000"));
    assertThat(aero1.getNome(), equalTo("nave A"));
    assertThat(aero1.getQtdDeAssento(), equalTo(100));

    assertThat(aero2.getId(), equalTo(2));
    assertThat(aero2.getCodigo(), equalTo("A1001"));
    assertThat(aero2.getNome(), equalTo("nave B"));
    assertThat(aero2.getQtdDeAssento(), equalTo(200));
  }

  public void consultar_por_filtro_nome() {
    AeronaveDAO dao = new AeronaveDAO();
    RequestParamWrapper request = new RequestParamWrapper();

    String nome = "nave A";
    request.set("nome", nome);

    List<Aeronave> res = dao.consultar(request);
    assertThat(res.size(), equalTo(1));

    Aeronave aero1 = res.get(0);

    assertThat(aero1.getCodigo(), equalTo("A1000"));
    assertThat(aero1.getNome(), equalTo("nave A"));
    assertThat(aero1.getQtdDeAssento(), equalTo(100));
  }

}