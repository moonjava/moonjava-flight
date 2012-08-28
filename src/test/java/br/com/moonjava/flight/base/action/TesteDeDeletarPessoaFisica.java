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

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.com.moonjava.flight.base.PessoaFisica;
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0, Aug 13, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
@Test
public class TesteDeDeletarPessoaFisica {

  @BeforeClass
  public void limparTabela() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void deletar_pf_por_id() {
    PessoaFisicaAction action = new PessoaFisicaAction();
    UsuarioAction usuarioAction = new UsuarioAction();

    RequestParamWrapper request = new RequestParamWrapper();

    request.set("nome", "Nome");
    int id = 2;

    PessoaFisica pessoaFisica = action.consultarPorId(id);

    int pfId = pessoaFisica.getId();

    List<PessoaFisica> antes = action.consultar(request);
    assertThat(antes.size(), equalTo(4));

    usuarioAction.deletarPorPessoaFisicaId(pfId);
    action.deletar(id);

    List<PessoaFisica> res = action.consultar(request);
    assertThat(res.size(), equalTo(3));
  }
}
