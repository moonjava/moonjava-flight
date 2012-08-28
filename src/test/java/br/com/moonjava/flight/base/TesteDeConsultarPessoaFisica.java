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
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.model.base.PessoaFisica;
import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0, Aug 12, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
@Test
public class TesteDeConsultarPessoaFisica {

  @BeforeClass
  public void limparTabela() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void buscar_pessoaFisica_porCpf() {
    PessoaFisicaDAO dao = new PessoaFisicaDAO();

    PessoaFisica res = dao.consultarPorCpf(CPF.valueOf(22222222222l));
    assertThat(res.getEmail(), equalTo("moonjava@moonjava.com.br"));
    assertThat(res.getNome(), equalTo("Nome B"));
    assertThat(res.getTelCelular(), equalTo(1199996666));
    assertThat(res.getRg(), equalTo("557773339"));

  }

  public void buscar_pessoaFisica_por_id() {
    PessoaFisicaDAO dao = new PessoaFisicaDAO();

    int id = 3;
    PessoaFisica res = dao.consultarPorId(id);
    assertThat(res.getCpf().getDigito(), equalTo((33333333333l)));
    assertThat(res.getTelCelular(), equalTo(1122229999));
    assertThat(res.getNome(), equalTo("Nome C"));
  }

  public void buscar_pf_por_nome() {
    PessoaFisicaDAO dao = new PessoaFisicaDAO();
    RequestParamWrapper request = new RequestParamWrapper();

    request.set("nome", "Nome B");
    List<PessoaFisica> res = dao.consultar(request);
    assertThat(res.size(), equalTo(1));

    PessoaFisica r1 = res.get(0);
    assertThat(r1.getCpf().getDigito(), equalTo((22222222222l)));
  }

}