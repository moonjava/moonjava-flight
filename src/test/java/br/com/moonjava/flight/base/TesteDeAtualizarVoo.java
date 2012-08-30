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

import org.joda.time.DateTime;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.com.moonjava.flight.controller.base.VooUpdate;
import br.com.moonjava.flight.dao.base.VooDAO;
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
@Test
public class TesteDeAtualizarVoo {

  @BeforeClass
  public void limparTabela() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void atualizar_voo_com_sucesso() {
    VooDAO dao = new VooDAO();
    RequestParamWrapper request = new RequestParamWrapper();

    int id = 1;
    String codigo = "V1000";
    Voo antes = dao.consultarPorId(id);
    assertThat(antes.getCodigo(), equalTo(codigo));
    assertThat(antes.getDataDePartida(), equalTo(new DateTime(2012, 1, 1, 0, 0)));
    assertThat(antes.getDataDeChegada(), equalTo(new DateTime(2012, 1, 1, 6, 0)));
    assertThat(antes.getObservacao(), equalTo(null));

    DateTime partida = new DateTime(2015, 1, 1, 0, 30);
    DateTime chegada = new DateTime(2015, 1, 1, 7, 0);
    String observacao = "nova observacao";

    request.set("id", id);
    request.set("partida", partida);
    request.set("chegada", chegada);
    request.set("observacao", observacao);

    Voo voo = new VooUpdate(request).createInstance();
    boolean executed = dao.atualizar(voo);
    assertThat(executed, equalTo(true));

    Voo res = dao.consultarPorId(id);
    assertThat(res.getCodigo(), equalTo(codigo));
    assertThat(res.getDataDePartida(), equalTo(partida));
    assertThat(res.getDataDeChegada(), equalTo(chegada));
    assertThat(res.getObservacao(), equalTo(observacao));
  }

  public void atualizar_voo_sem_sucesso_por_datas_incorretas() {
    VooDAO dao = new VooDAO();
    RequestParamWrapper request = new RequestParamWrapper();

    int id = 2;
    String codigo = "V1001";
    Voo antes = dao.consultarPorId(id);
    assertThat(antes.getCodigo(), equalTo(codigo));
    assertThat(antes.getDataDePartida(), equalTo(new DateTime(2012, 2, 1, 0, 0)));
    assertThat(antes.getDataDeChegada(), equalTo(new DateTime(2012, 2, 1, 6, 0)));
    assertThat(antes.getObservacao(), equalTo(null));

    DateTime partida = new DateTime(2013, 1, 1, 0, 30);
    DateTime chegada = new DateTime(2012, 1, 1, 7, 0);
    String observacao = "nova observacao";

    request.set("id", id);
    request.set("partida", partida);
    request.set("chegada", chegada);
    request.set("observacao", observacao);

    Voo voo = new VooUpdate(request).createInstance();
    boolean executed = dao.atualizar(voo);
    assertThat(executed, equalTo(false));
  }

}