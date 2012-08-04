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
package br.com.moonjava.flight.action;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.joda.time.DateTime;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
@Test
public class TesteDeConsultarVoo {

  @BeforeClass
  public void limparTabela() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void buscar_por_todos_voos_disponiveis() {
    VooAction action = new VooAction();
    RequestParamWrapper request = new RequestParamWrapper();

    Status status = Status.DISPONIVEL;

    request.set("status", status);

    List<Voo> res = action.consultar(request);
    assertThat(res.size(), equalTo(3));

    Voo r0 = res.get(0);
    Voo r1 = res.get(1);
    Voo r2 = res.get(2);

    assertThat(r0.getCodigo(), equalTo(1));
    assertThat(r1.getCodigo(), equalTo(2));
    assertThat(r2.getCodigo(), equalTo(4));

    assertThat(r0.getAeronave().getCodigo(), equalTo(1));
    assertThat(r1.getAeronave().getCodigo(), equalTo(1));
    assertThat(r2.getAeronave().getCodigo(), equalTo(2));
  }

  public void filtro_por_status_e_data_de_partida() {
    VooAction action = new VooAction();
    RequestParamWrapper request = new RequestParamWrapper();

    Status status = Status.DISPONIVEL;
    DateTime partida = new DateTime(2012, 2, 1, 0, 0, 0);

    request.set("status", status);
    request.set("partida", partida);

    List<Voo> res = action.consultar(request);
    assertThat(res.size(), equalTo(2));

    assertThat(res.get(0).getCodigo(), equalTo(2));
    assertThat(res.get(1).getCodigo(), equalTo(4));
  }

  public void filtro_por_status_e_data_de_chegada() {
    VooAction action = new VooAction();
    RequestParamWrapper request = new RequestParamWrapper();

    Status status = Status.DISPONIVEL;
    DateTime chegada = new DateTime(2012, 2, 1, 6, 0, 0);

    request.set("status", status);
    request.set("chegada", chegada);

    List<Voo> res = action.consultar(request);
    assertThat(res.size(), equalTo(2));

    assertThat(res.get(0).getCodigo(), equalTo(1));
    assertThat(res.get(1).getCodigo(), equalTo(2));
  }

  public void filtro_por_status_indisponivel() {
    VooAction action = new VooAction();
    RequestParamWrapper request = new RequestParamWrapper();

    Status status = Status.INDISPONIVEL;

    request.set("status", status);

    List<Voo> res = action.consultar(request);
    assertThat(res.size(), equalTo(1));

    assertThat(res.get(0).getCodigo(), equalTo(3));
  }

  public void filtro_por_origem() {
    VooAction action = new VooAction();
    RequestParamWrapper request = new RequestParamWrapper();

    String origem = "origem D";
    Status status = Status.DISPONIVEL;

    request.set("origem", origem);
    request.set("status", status);

    List<Voo> res = action.consultar(request);
    assertThat(res.size(), equalTo(1));

    assertThat(res.get(0).getCodigo(), equalTo(4));
  }

  public void filtro_por_destino() {
    VooAction action = new VooAction();
    RequestParamWrapper request = new RequestParamWrapper();

    String destino = "destino B";
    Status status = Status.DISPONIVEL;

    request.set("destino", destino);
    request.set("status", status);

    List<Voo> res = action.consultar(request);
    assertThat(res.size(), equalTo(1));

    assertThat(res.get(0).getCodigo(), equalTo(2));
  }

  public void consulta_por_codigo() {
    VooAction action = new VooAction();

    int codigo = 1;

    Voo res = action.consultarPorCodigo(codigo);
    assertThat(res.getCodigo(), equalTo(codigo));
    assertThat(res.getAeronave().getCodigo(), equalTo(1));
  }

  public void consultar_por_id_aeronave() {
    VooAction action = new VooAction();
    RequestParamWrapper request = new RequestParamWrapper();

    int idAeronave = 2;
    request.set("idAeronave", idAeronave);
    DateTime partida = new DateTime(2012, 3, 1, 0, 0, 0);
    DateTime chegada = new DateTime(2012, 3, 1, 6, 0, 0);
    Status status = Status.DISPONIVEL;

    List<Voo> antes = action.consultarPorAeronaveId(request);
    assertThat(antes.size(), equalTo(1));

    Voo voo = antes.get(0);

    assertThat(voo.getCodigo(), equalTo(4));
    assertThat(voo.getOrigem(), equalTo("origem D"));
    assertThat(voo.getDestino(), equalTo("destino D"));
    assertThat(voo.getEscala(), equalTo(null));
    assertThat(voo.getDataDePartida(), equalTo(partida));
    assertThat(voo.getDataDeChegada(), equalTo(chegada));
    assertThat(voo.getObservacao(), equalTo(null));
    assertThat(voo.getStatus(), equalTo(status));

  }

}