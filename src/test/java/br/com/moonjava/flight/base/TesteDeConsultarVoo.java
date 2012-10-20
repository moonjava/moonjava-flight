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

import org.joda.time.DateTime;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.com.moonjava.flight.dao.base.VooDAO;
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.model.base.Status;
import br.com.moonjava.flight.model.base.Voo;
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
    VooDAO dao = new VooDAO();
    RequestParamWrapper request = new RequestParamWrapper();

    Status status = Status.DISPONIVEL;

    request.set("status", status);

    List<Voo> res = dao.consultar(request);
    assertThat(res.size(), equalTo(3));

    Voo r0 = res.get(0);
    Voo r1 = res.get(1);
    Voo r2 = res.get(2);

    assertThat(r0.getCodigo(), equalTo("V1000"));
    assertThat(r1.getCodigo(), equalTo("V1001"));
    assertThat(r2.getCodigo(), equalTo("V1003"));

    assertThat(r0.getAeronave().getCodigo(), equalTo("A1000"));
    assertThat(r1.getAeronave().getCodigo(), equalTo("A1000"));
    assertThat(r2.getAeronave().getCodigo(), equalTo("A1001"));
  }

  public void filtro_por_status_e_data_de_partida() {
    VooDAO dao = new VooDAO();
    RequestParamWrapper request = new RequestParamWrapper();

    Status status = Status.DISPONIVEL;
    DateTime partida = new DateTime(2012, 2, 1, 0, 0, 0);

    request.set("status", status);
    request.set("partida", partida);

    List<Voo> res = dao.consultar(request);
    assertThat(res.size(), equalTo(2));

    assertThat(res.get(0).getCodigo(), equalTo("V1001"));
    assertThat(res.get(1).getCodigo(), equalTo("V1003"));
  }

  public void filtro_por_status_e_data_de_chegada() {
    VooDAO dao = new VooDAO();
    RequestParamWrapper request = new RequestParamWrapper();

    Status status = Status.DISPONIVEL;
    DateTime chegada = new DateTime(2012, 2, 1, 6, 0, 0);

    request.set("status", status);
    request.set("chegada", chegada);

    List<Voo> res = dao.consultar(request);
    assertThat(res.size(), equalTo(2));

    assertThat(res.get(0).getCodigo(), equalTo("V1000"));
    assertThat(res.get(1).getCodigo(), equalTo("V1001"));
  }

  public void filtro_por_status_indisponivel() {
    VooDAO dao = new VooDAO();
    RequestParamWrapper request = new RequestParamWrapper();

    Status status = Status.INDISPONIVEL;

    request.set("status", status);

    List<Voo> res = dao.consultar(request);
    assertThat(res.size(), equalTo(1));

    assertThat(res.get(0).getCodigo(), equalTo("V1002"));
  }

  public void filtro_por_origem() {
    VooDAO dao = new VooDAO();
    RequestParamWrapper request = new RequestParamWrapper();

    String origem = "origem D";
    Status status = Status.DISPONIVEL;

    request.set("origem", origem);
    request.set("status", status);

    List<Voo> res = dao.consultar(request);
    assertThat(res.size(), equalTo(1));

    assertThat(res.get(0).getCodigo(), equalTo("V1003"));
  }

  public void filtro_por_destino() {
    VooDAO dao = new VooDAO();
    RequestParamWrapper request = new RequestParamWrapper();

    String destino = "destino B";
    Status status = Status.DISPONIVEL;

    request.set("destino", destino);
    request.set("status", status);

    List<Voo> res = dao.consultar(request);
    assertThat(res.size(), equalTo(1));

    assertThat(res.get(0).getCodigo(), equalTo("V1001"));
  }

  public void consulta_por_id() {
    VooDAO dao = new VooDAO();
    int id = 1;

    Voo res = dao.consultarPorId(id);
    assertThat(res.getId(), equalTo(id));
    assertThat(res.getAeronave().getCodigo(), equalTo("A1000"));
  }

  public void consultar_por_aeronave() {
    VooDAO dao = new VooDAO();

    int aeronaveId = 2;

    List<Voo> res = dao.consultarPorAeronaveId(aeronaveId);
    assertThat(res.size(), equalTo(1));

    Voo voo = res.get(0);
    assertThat(voo.getCodigo(), equalTo("V1003"));
    assertThat(voo.getOrigem(), equalTo("origem D"));
    assertThat(voo.getDestino(), equalTo("destino D"));
    assertThat(voo.getEscala(), equalTo(null));
    assertThat(voo.getDataDePartida(), equalTo(new DateTime(2012, 3, 1, 0, 0, 0)));
    assertThat(voo.getDataDeChegada(), equalTo(new DateTime(2012, 3, 1, 6, 0, 0)));
    assertThat(voo.getObservacao(), equalTo(null));
    assertThat(voo.getStatus(), equalTo(Status.DISPONIVEL));
    assertThat(voo.getAssentoLivre(), equalTo(200));
    assertThat(voo.getPreco(), equalTo(550.50));
  }

}