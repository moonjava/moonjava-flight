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
    new DbUnit().load();
  }

  public void voo_deve_ser_criado() {
    int codigo = 5;
    int aeronave = 2;
    String origem = "nova origem";
    String destino = "novo destino";
    String escala = "";
    DateTime partida = new DateTime(2012, 8, 9, 0, 0, 0);
    DateTime chegada = new DateTime(2012, 8, 9, 3, 0, 0);

    VooAction action = new VooAction();
    RequestParamWrapper req = new RequestParamWrapper();

    req.set("status", Status.DISPONIVEL);

    List<Voo> antes = action.consultar(req);
    assertThat(antes.size(), equalTo(3));

    req.set("codigo", codigo);
    req.set("aeronave", aeronave);
    req.set("origem", origem);
    req.set("destino", destino);
    req.set("escala", escala);
    req.set("partida", partida);
    req.set("chegada", chegada);

    Voo voo = new VooCreate(req).createInstance();
    action.criar(voo);

    RequestParamWrapper request = new RequestParamWrapper();

    request.set("status", Status.DISPONIVEL);

    List<Voo> res = action.consultar(request);
    assertThat(res.size(), equalTo(4));

    Voo r3 = res.get(3);
    assertThat(r3.getCodigo(), equalTo(codigo));
    assertThat(r3.getAeronave().getId(), equalTo(aeronave));
    assertThat(r3.getOrigem(), equalTo(origem));
    assertThat(r3.getDestino(), equalTo(destino));
    assertThat(r3.getEscala(), equalTo(escala));
    assertThat(r3.getObservacao(), equalTo(null));
    assertThat(r3.getDataDePartida(), equalTo(partida));
    assertThat(r3.getDataDeChegada(), equalTo(chegada));
    assertThat(r3.getStatus(), equalTo(Status.DISPONIVEL));
  }

}