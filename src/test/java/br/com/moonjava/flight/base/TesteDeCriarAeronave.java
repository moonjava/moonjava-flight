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

import br.com.moonjava.flight.controller.base.AeronaveCreate;
import br.com.moonjava.flight.dao.base.AeronaveDAO;
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.model.base.Aeronave;
import br.com.moonjava.flight.util.GerarCodigo;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0, 25/07/2012
 * @contact miqueias@moonjava.com.br
 * 
 */
@Test
public class TesteDeCriarAeronave {

  @BeforeClass
  public void beforeClass() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void criar_aeronave_com_sucesso() {
    String nome = "Teste 1";
    String codigo = new GerarCodigo("AERONAVE").getCodigo();
    int qtdAssentos = 50;
    boolean mapa = true;

    AeronaveDAO dao = new AeronaveDAO();
    RequestParamWrapper request = new RequestParamWrapper();

    List<Aeronave> antes = dao.consultar(request);
    assertThat(antes.size(), equalTo(2));

    request.set("nome", nome);
    request.set("codigo", codigo);
    request.set("qtdDeAssento", qtdAssentos);
    request.set("mapa", mapa);

    Aeronave aeronave = new AeronaveCreate(request).createInstance();
    dao.criar(aeronave);

    request = new RequestParamWrapper();

    List<Aeronave> res = dao.consultar(request);
    assertThat(res.size(), equalTo(3));

    Aeronave aero = res.get(2);

    assertThat(aero.getNome(), equalTo(nome));
    assertThat(aero.getCodigo(), equalTo(codigo));
    assertThat(aero.getQtdDeAssento(), equalTo(qtdAssentos));
    assertThat(aero.isMapa(), equalTo(mapa));
  }

}