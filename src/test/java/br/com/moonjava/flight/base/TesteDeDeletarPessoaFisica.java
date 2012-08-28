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

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.com.moonjava.flight.dao.base.PessoaFisicaDAO;
import br.com.moonjava.flight.dao.base.UsuarioDAO;
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.model.base.PessoaFisica;
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
    PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    RequestParamWrapper request = new RequestParamWrapper();

    request.set("nome", "Nome");
    int id = 2;

    PessoaFisica pessoaFisica = pessoaFisicaDAO.consultarPorId(id);
    int pfId = pessoaFisica.getId();

    List<PessoaFisica> antes = pessoaFisicaDAO.consultar(request);
    assertThat(antes.size(), equalTo(4));

    usuarioDAO.deletarPorPessoaFisicaId(pfId);
    pessoaFisicaDAO.deletar(id);

    List<PessoaFisica> res = pessoaFisicaDAO.consultar(request);
    assertThat(res.size(), equalTo(3));
  }

}