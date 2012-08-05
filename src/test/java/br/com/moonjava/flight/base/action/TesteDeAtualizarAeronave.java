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

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.com.moonjava.flight.base.Aeronave;
import br.com.moonjava.flight.base.action.AeronaveAction;
import br.com.moonjava.flight.base.action.AeronaveUpdate;
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0, 25/07/2012
 * @contact miqueias@moonjava.com.br
 * 
 */
@Test
public class TesteDeAtualizarAeronave {

  @BeforeClass
  public void limpaTabela() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void atualizar_aeronave() {
    AeronaveAction action = new AeronaveAction();
    RequestParamWrapper request = new RequestParamWrapper();

    int id = 1;
    Aeronave antes = action.consultarPorId(1);
    assertThat(antes.getCodigo(), equalTo(1));
    assertThat(antes.getNome(), equalTo("nave A"));

    int codigo = 3;
    String nome = "nova nave A";

    request.set("id", id);
    request.set("codigo", codigo);
    request.set("nome", nome);

    Aeronave aeronave = new AeronaveUpdate(request).createInstance();

    action.atualizar(aeronave);

    Aeronave res = action.consultarPorId(id);
    assertThat(res.getCodigo(), equalTo(codigo));
    assertThat(res.getNome(), equalTo(nome));
  }

}