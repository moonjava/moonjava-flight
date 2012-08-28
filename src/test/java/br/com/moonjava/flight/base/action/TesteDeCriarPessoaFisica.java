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

import org.joda.time.LocalDate;
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
public class TesteDeCriarPessoaFisica {

  @BeforeClass
  public void limparTabela() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void teste_como_pf_deve_ser_criado() {
    String nome = "Nome E";
    String sobrenome = "Sobrenome E";
    LocalDate nascimento = new LocalDate(1990 - 04 - 15);
    long cpf = 11122233344l;
    String rg = "55777333X";
    String endereco = "Endereço E N°99";
    int telResidencial = 55334477;
    int telCelular = 55446622;
    String email = "testedecriacao@moonjava.com.br";

    PessoaFisicaAction action = new PessoaFisicaAction();
    RequestParamWrapper req = new RequestParamWrapper();

    req.set("nome", "Nome");

    List<PessoaFisica> antes = action.consultar(req);
    assertThat(antes.size(), equalTo(4));

    RequestParamWrapper request = new RequestParamWrapper();

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
    action.criar(pf);

    List<PessoaFisica> res = action.consultar(req);
    assertThat(res.size(), equalTo(5));

    PessoaFisica r1 = res.get(4);

    assertThat(r1.getNome(), equalTo(nome));
    assertThat(r1.getSobrenome(), equalTo(sobrenome));
    assertThat(r1.getDataNascimento(), equalTo(nascimento));
    assertThat(r1.getCpf(), equalTo(cpf));
    assertThat(r1.getRg(), equalTo(rg));
    assertThat(r1.getEndereco(), equalTo(endereco));
    assertThat(r1.getTelResidencial(), equalTo(telResidencial));
    assertThat(r1.getTelCelular(), equalTo(telCelular));
    assertThat(r1.getEmail(), equalTo(email));

  }
}