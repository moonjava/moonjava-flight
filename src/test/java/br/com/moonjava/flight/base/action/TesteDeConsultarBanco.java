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

import br.com.moonjava.flight.base.Banco;
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;

/**
 * @version 1.0, Aug 14, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
@Test
public class TesteDeConsultarBanco {

  @BeforeClass
  public void limparTabela() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void teste_de_consulta_por_id() {
    BancoAction action = new BancoAction();

    int id = 2;

    Banco res = action.consultarPorId(id);
    assertThat(res.getBanco(), equalTo(22));
    assertThat(res.getAgencia(), equalTo(222));
    assertThat(res.getConta(), equalTo(333366669));
  }

  public void teste_de_consulta_por_pf() {
    BancoAction action = new BancoAction();

    int pf = 2;

    Banco res = action.consultarPorPessoaFisica(pf);

    assertThat(res.getBanco(), equalTo(11));
    assertThat(res.getAgencia(), equalTo(111));
    assertThat(res.getConta(), equalTo(222255558));
  }
}