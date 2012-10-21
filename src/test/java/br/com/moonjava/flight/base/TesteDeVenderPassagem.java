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
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.com.moonjava.flight.controller.base.PassagemCreate;
import br.com.moonjava.flight.dao.base.PassagemDAO;
import br.com.moonjava.flight.dao.base.VooDAO;
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.model.base.Passagem;
import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.util.GerarCodigo;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Oct 20, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
@Test
public class TesteDeVenderPassagem {

  @BeforeClass
  public void beforeClass() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void vender_passagem_com_sucesso() {
    int vooId = 1;
    String codigo = new GerarCodigo("PASSAGEM").getCodigo();
    int pessoaFisicaId = 1;

    PassagemDAO dao = new PassagemDAO();
    Voo voo = new VooDAO().consultarPorId(vooId);
    assertThat(voo, is(notNullValue()));

    RequestParamWrapper request = new RequestParamWrapper();

    List<Passagem> antes = dao.consultarPorVoo(voo);
    assertThat(antes.size(), equalTo(0));

    request.set("voo", vooId);
    request.set("codBilhete", codigo);
    request.set("pessoaFisica", pessoaFisicaId);

    Passagem pojo = new PassagemCreate(request).createInstance();
    dao.vender(pojo);

    request = new RequestParamWrapper();

    List<Passagem> res = dao.consultarPorVoo(voo);
    assertThat(res.size(), equalTo(1));

    Passagem passagem = res.get(0);

    assertThat(passagem.getPessoaFisica().getId(), equalTo(pessoaFisicaId));
    assertThat(passagem.getCodigoBilhete(), equalTo(codigo));
    assertThat(passagem.getVoo().getId(), equalTo(vooId));
  }

}