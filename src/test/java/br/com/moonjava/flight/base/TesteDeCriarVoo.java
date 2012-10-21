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

import br.com.moonjava.flight.controller.base.VooCreate;
import br.com.moonjava.flight.dao.base.AeronaveDAO;
import br.com.moonjava.flight.dao.base.VooDAO;
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.model.base.Aeronave;
import br.com.moonjava.flight.model.base.Status;
import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.util.GerarCodigo;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
@Test
public class TesteDeCriarVoo {

  @BeforeClass
  public void limparTabela() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void criar_voo_com_sucesso() {
    VooDAO vooDAO = new VooDAO();
    AeronaveDAO aeronaveDAO = new AeronaveDAO();
    RequestParamWrapper request = new RequestParamWrapper();

    int aeronaveId = 2;
    String codigo = new GerarCodigo("VOO").getCodigo();
    String origem = "nova origem";
    String destino = "novo destino";
    String escala = "";
    DateTime partida = new DateTime(2015, 8, 9, 0, 0, 0);
    DateTime chegada = new DateTime(2015, 8, 9, 3, 0, 0);
    double preco = 230.00;

    request.set("codigo", "A1001");
    Aeronave aeronave = aeronaveDAO.consultar(request).get(0);
    int assentoLivre = aeronave.getQtdDeAssento();

    request.set("status", Status.DISPONIVEL);

    List<Voo> antes = vooDAO.consultar(request);
    assertThat(antes.size(), equalTo(3));

    request.set("aeronave", aeronaveId);
    request.set("codigo", codigo);
    request.set("origem", origem);
    request.set("destino", destino);
    request.set("escala", escala);
    request.set("partida", partida);
    request.set("chegada", chegada);
    request.set("assentoLivre", assentoLivre);
    request.set("preco", preco);

    Voo voo = new VooCreate(request).createInstance();
    vooDAO.criar(voo);

    request = new RequestParamWrapper();

    request.set("status", Status.DISPONIVEL);

    List<Voo> res = vooDAO.consultar(request);
    assertThat(res.size(), equalTo(4));

    Voo r3 = res.get(3);
    assertThat(r3.getAeronave().getId(), equalTo(aeronaveId));
    assertThat(r3.getCodigo(), equalTo(codigo));
    assertThat(r3.getOrigem(), equalTo(origem));
    assertThat(r3.getDestino(), equalTo(destino));
    assertThat(r3.getEscala(), equalTo(escala));
    assertThat(r3.getObservacao(), equalTo(null));
    assertThat(r3.getDataDePartida(), equalTo(partida));
    assertThat(r3.getDataDeChegada(), equalTo(chegada));
    assertThat(r3.getStatus(), equalTo(Status.DISPONIVEL));
    assertThat(r3.getAssentoLivre(), equalTo(assentoLivre));
    assertThat(r3.getPreco(), equalTo(preco));
  }
}