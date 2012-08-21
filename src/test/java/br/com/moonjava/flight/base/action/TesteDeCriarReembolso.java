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

import br.com.moonjava.flight.base.Reembolso;
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0, Aug 14, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
@Test
public class TesteDeCriarReembolso {

  @BeforeClass
  public void limparTabela() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void teste_criar_reembolso() {
    ReembolsoAction action = new ReembolsoAction();
    RequestParamWrapper request = new RequestParamWrapper();

    int passagem = 1;
    int banco = 99;
    int agencia = 999;
    int conta = 999955556;

    Reembolso antes = action.consultarPorPassagemId(passagem);
    assertThat(antes, equalTo(null));

    request.set("passagem", passagem);
    request.set("banco", banco);
    request.set("agencia", agencia);
    request.set("conta", conta);

    Reembolso reembolso = new ReembolsoCreate(request).createInstance();

    action.criar(reembolso);

    Reembolso res = action.consultarPorPassagemId(passagem);
    assertThat(res.getBanco(), equalTo(banco));
    assertThat(res.getAgencia(), equalTo(agencia));
    assertThat(res.getConta(), equalTo(conta));
  }
}