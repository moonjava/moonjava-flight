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

import br.com.moonjava.flight.controller.base.PassagemUpdate;
import br.com.moonjava.flight.dao.base.PassagemDAO;
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.model.base.Passagem;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 07/10/2012
 * @contact miqueias@moonjava.com.br
 * 
 */

@Test
public class TesteDeAtualizarPassagem {

  @BeforeClass
  public void limparTabela() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void atualizar_passagem() {
    PassagemDAO dao = new PassagemDAO();
    RequestParamWrapper request = new RequestParamWrapper();

    int id = 1;
    int voo = 3;
    int pf = 1;
    String codBilhete = "P1000";

    Passagem antes = dao.consultarPorCodigoBilhete(codBilhete);
    assertThat(antes.getVoo().getId(), equalTo(voo));
    assertThat(antes.getPessoaFisica().getId(), equalTo(pf));
    assertThat(antes.getCodigoBilhete(), equalTo(codBilhete));

    int novoVoo = 2;
    request.set("id", id);
    request.set("voo", novoVoo);

    Passagem passagem = new PassagemUpdate(request).createInstance();
    dao.transferir(passagem);

    Passagem res = dao.consultarPorCodigoBilhete(codBilhete);
    assertThat(res.getVoo().getId(), equalTo(novoVoo));
    assertThat(res.getPessoaFisica().getId(), equalTo(pf));
    assertThat(res.getCodigoBilhete(), equalTo(codBilhete));
  }

}
