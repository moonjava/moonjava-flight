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
public class TesteDeControlarStatus {

  @BeforeClass
  public void limparTabela() {
    new DbUnit().load();
  }

  public void status_deve_ser_atualizado() {
    VooAction action = new VooAction();
    RequestParamWrapper request = new RequestParamWrapper();

    int id = 4;
    Status status = Status.ATRASADO;

    request.set("id", id);
    request.set("status", status);

    Voo antes = action.consultarPorCodigo(4);
    assertThat(antes.getCodigo(), equalTo(4));
    assertThat(antes.getStatus(), equalTo(Status.DISPONIVEL));

    Voo voo = new VooUpdate(request).createInstance();

    action.controlarStatus(voo);

    Voo res = action.consultarPorCodigo(4);
    assertThat(res.getCodigo(), equalTo(4));
    assertThat(res.getStatus(), equalTo(Status.ATRASADO));
  }

}