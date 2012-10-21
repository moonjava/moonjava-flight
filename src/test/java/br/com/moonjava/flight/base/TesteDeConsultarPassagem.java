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

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.com.moonjava.flight.dao.base.PassagemDAO;
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.model.base.Passagem;

/**
 * @version 1.0 06/10/2012
 * @contact miqueias@moonjava.com.br
 * 
 */
@Test
public class TesteDeConsultarPassagem {

  @BeforeClass
  public void limparTabela() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void consultar_por_codigo_p1003() {
    PassagemDAO dao = new PassagemDAO();

    Passagem res = dao.consultarPorCodigoBilhete("P1003");

    assertThat(res.getCodigoBilhete(), equalTo("P1003"));
    assertThat(res.getAssento(), equalTo("A11"));
    assertThat(res.getPessoaFisica().getNome(), equalTo("Nome D"));
    assertThat(res.getVoo().getCodigo(), equalTo(null));
  }

  public void consultar_por_codigo_p1000() {
    PassagemDAO dao = new PassagemDAO();

    String codigo = "P1000";

    Passagem res = dao.consultarPorCodigoBilhete(codigo);

    assertThat(res.getCodigoBilhete(), equalTo(codigo));
    assertThat(res.getAssento(), equalTo("B15"));
    assertThat(res.getPessoaFisica().getNome(), equalTo("Nome A"));
    assertThat(res.getVoo().getCodigo(), equalTo("V1002"));
  }

}