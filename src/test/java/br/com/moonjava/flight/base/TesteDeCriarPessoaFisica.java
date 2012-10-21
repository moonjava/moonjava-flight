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

import org.joda.time.LocalDate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.com.moonjava.flight.controller.base.PessoaFisicaCreate;
import br.com.moonjava.flight.dao.base.PessoaFisicaDAO;
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.model.base.PessoaFisica;
import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0, Aug 13, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
@Test
public class TesteDeCriarPessoaFisica {

  @BeforeClass
  public void limparTabela() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void criar_pf_com_sucesso() {
    PessoaFisicaDAO dao = new PessoaFisicaDAO();
    RequestParamWrapper request = new RequestParamWrapper();

    String nome = "Nome E";
    String sobrenome = "Sobrenome E";
    LocalDate nascimento = new LocalDate(1990 - 04 - 15);
    long cpf = 55555555555l;
    String rg = "55777333X";
    String endereco = "Endereço E N°99";
    long telResidencial = 55334477l;
    long telCelular = 55446622l;
    String email = "testedecriacao@moonjava.com.br";

    request.set("nome", "Nome");
    PessoaFisica antes = dao.consultarPorCpf(CPF.valueOf(cpf));
    assertThat(antes, equalTo(null));

    request.set("nome", nome);
    request.set("sobrenome", sobrenome);
    request.set("nascimento", nascimento);
    request.set("cpf", cpf);
    request.set("rg", rg);
    request.set("endereco", endereco);
    request.set("telResidencial", telResidencial);
    request.set("telCelular", telCelular);
    request.set("email", email);

    PessoaFisica pf = new PessoaFisicaCreate(request).createInstance();
    dao.criar(pf);

    PessoaFisica res = dao.consultarPorCpf(CPF.valueOf(cpf));
    assertThat(res.getNome(), equalTo(nome));
    assertThat(res.getSobrenome(), equalTo(sobrenome));
    assertThat(res.getDataNascimento(), equalTo(nascimento));
    assertThat(res.getCpf().getDigito(), equalTo(cpf));
    assertThat(res.getRg(), equalTo(rg));
    assertThat(res.getEndereco(), equalTo(endereco));
    assertThat(res.getTelResidencial(), equalTo(telResidencial));
    assertThat(res.getTelCelular(), equalTo(telCelular));
    assertThat(res.getEmail(), equalTo(email));
  }

}