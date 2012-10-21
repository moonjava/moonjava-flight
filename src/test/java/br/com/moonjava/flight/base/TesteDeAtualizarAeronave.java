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

import br.com.moonjava.flight.controller.base.AeronaveUpdate;
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
public class TesteDeAtualizarAeronave {

  @BeforeClass
  public void limpaTabela() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void atualizar_aeronave_com_sucesso() {
    AeronaveDAO dao = new AeronaveDAO();
    RequestParamWrapper request = new RequestParamWrapper();

    int id = 1;
    String nome = "nave A";
    String codigo = "A1000";
    int qtdAssento = 100;
    boolean mapa = true;
    request.set("codigo", codigo);

    List<Aeronave> aeronaves = dao.consultar(request);
    Aeronave antes = aeronaves.get(0);
    assertThat(antes.getNome(), equalTo(nome));
    assertThat(antes.getCodigo(), equalTo(codigo));
    assertThat(antes.getQtdDeAssento(), equalTo(qtdAssento));
    assertThat(antes.isMapa(), equalTo(mapa));

    String novoNome = "nova nave A";

    request.set("id", id);
    request.set("nome", novoNome);

    Aeronave aeronave = new AeronaveUpdate(request).createInstance();
    dao.atualizar(aeronave);

    List<Aeronave> aeronavesRes = dao.consultar(request);
    Aeronave res = aeronavesRes.get(0);
    assertThat(res.getNome(), equalTo(novoNome));
    assertThat(res.getCodigo(), equalTo(codigo));
    assertThat(res.getQtdDeAssento(), equalTo(qtdAssento));
    assertThat(res.isMapa(), equalTo(mapa));
  }

}