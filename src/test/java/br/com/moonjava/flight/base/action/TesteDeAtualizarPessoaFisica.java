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

import org.joda.time.LocalDate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.com.moonjava.flight.base.PessoaFisica;
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0, Aug 12, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
@Test
public class TesteDeAtualizarPessoaFisica {

  @BeforeClass
  public void limpaTabela() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void atualizar_pessoaFisica() {
    PessoaFisicaAction action = new PessoaFisicaAction();
    RequestParamWrapper request = new RequestParamWrapper();

    int id = 4;
    PessoaFisica antes = action.consultarPorId(id);
    assertThat(antes.getCpf(), equalTo(55500022250l));
    assertThat(antes.getNome(), equalTo("Nome D"));
    assertThat(antes.getEndereco(), equalTo("Endereço D Nº44"));

    String novoNome = "novo Nome D";
    long novoCpf = 44466622277l;
    String novoEmail = "updatetest@moonjava.com.br";
    LocalDate novaData = new LocalDate(2000 - 12 - 01);

    request.set("id", id);
    request.set("nome", novoNome);
    request.set("cpf", novoCpf);
    request.set("email", novoEmail);
    request.set("nascimento", novaData);

    request.set("sobrenome", "Sobrenome D");
    request.set("rg", "111000000");
    request.set("endereco", "Endereco D Nº44");
    request.set("telResidencial", 1133336666);
    request.set("telCelular", 1133335555);

    PessoaFisica pessoaFisica = new PessoaFisicaUpdate(request).createInstance();

    action.atualizar(pessoaFisica);

    PessoaFisica res = action.consultarPorId(id);
    assertThat(res.getCpf(), equalTo(novoCpf));
    assertThat(res.getNome(), equalTo(novoNome));
    assertThat(res.getEmail(), equalTo(novoEmail));

  }
}
