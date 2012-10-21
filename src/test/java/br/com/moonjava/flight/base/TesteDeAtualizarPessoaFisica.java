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

import br.com.moonjava.flight.controller.base.PessoaFisicaUpdate;
import br.com.moonjava.flight.dao.base.PessoaFisicaDAO;
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.model.base.PessoaFisica;
import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.CPFInvalidException;
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

  public void atualizar_pessoa_fisica_com_sucesso() {
    PessoaFisicaDAO dao = new PessoaFisicaDAO();
    RequestParamWrapper request = new RequestParamWrapper();

    int id = 4;
    PessoaFisica antes = dao.consultarPorCpf(CPF.valueOf(44444444444l));
    assertThat(antes.getNome(), equalTo("Nome D"));
    assertThat(antes.getSobrenome(), equalTo("Sobrenome D"));
    assertThat(antes.getDataNascimento(), equalTo(new LocalDate(2000, 12, 01)));
    assertThat(antes.getCpf().getDigito(), equalTo((44444444444l)));
    assertThat(antes.getRg(), equalTo("111000000"));
    assertThat(antes.getEndereco(), equalTo("Endereço D Nº44"));
    assertThat(antes.getTelResidencial(), equalTo(1133336666l));
    assertThat(antes.getTelCelular(), equalTo(1133335555l));
    assertThat(antes.getEmail(), equalTo(null));

    String novoNome = "novo Nome D";
    long novoCpf = 55555555555l;
    String novoEmail = "updatetest@moonjava.com.br";
    LocalDate novaData = new LocalDate(2000, 12, 01);

    request.set("id", id);
    request.set("nome", novoNome);
    request.set("cpf", novoCpf);
    request.set("email", novoEmail);
    request.set("nascimento", novaData);
    request.set("sobrenome", antes.getSobrenome());
    request.set("rg", antes.getRg());
    request.set("endereco", antes.getEndereco());
    request.set("telResidencial", antes.getTelResidencial());
    request.set("telCelular", antes.getTelCelular());

    PessoaFisica pessoaFisica = new PessoaFisicaUpdate(request).createInstance();

    boolean executed = dao.atualizar(pessoaFisica);
    assertThat(executed, equalTo(true));

    PessoaFisica res = dao.consultarPorCpf(CPF.valueOf(novoCpf));
    assertThat(res.getNome(), equalTo(novoNome));
    assertThat(res.getCpf().getDigito(), equalTo(novoCpf));
    assertThat(res.getNome(), equalTo(novoNome));
    assertThat(res.getEmail(), equalTo(novoEmail));
  }

  public void atualizar_pf_sem_sucesso_por_cpf_repetido() {
    PessoaFisicaDAO dao = new PessoaFisicaDAO();
    RequestParamWrapper request = new RequestParamWrapper();

    int id = 2;
    String nome = "Nome B";
    String sobrenome = "Sobrenome B";
    LocalDate dataDeNascimento = new LocalDate(2000, 8, 17);
    long cpf = 22222222222l;
    String rg = "557773339";
    String endereco = "Endereço B Nº22";
    long telResidencial = 1144448888l;
    long telCelular = 1199996666l;
    String email = "moonjava@moonjava.com.br";

    PessoaFisica antes = dao.consultarPorCpf(CPF.valueOf(cpf));
    assertThat(antes.getNome(), equalTo(nome));
    assertThat(antes.getSobrenome(), equalTo(sobrenome));
    assertThat(antes.getDataNascimento(), equalTo(dataDeNascimento));
    assertThat(antes.getCpf().getDigito(), equalTo((cpf)));
    assertThat(antes.getRg(), equalTo(rg));
    assertThat(antes.getEndereco(), equalTo(endereco));
    assertThat(antes.getTelResidencial(), equalTo(telResidencial));
    assertThat(antes.getTelCelular(), equalTo(telCelular));
    assertThat(antes.getEmail(), equalTo(email));

    String novoNome = "novo Nome D";
    long novoCpf = 11111111111l;
    String novoEmail = "updatetest@moonjava.com.br";
    LocalDate novaData = new LocalDate(2000, 12, 01);

    request.set("id", id);
    request.set("nome", novoNome);
    request.set("cpf", novoCpf);
    request.set("email", novoEmail);
    request.set("nascimento", novaData);
    request.set("sobrenome", "Sobrenome D");
    request.set("rg", "111000000");
    request.set("endereco", "Endereco D Nº44");
    request.set("telResidencial", 1133336666l);
    request.set("telCelular", 1133335555l);

    PessoaFisica pessoaFisica = new PessoaFisicaUpdate(request).createInstance();
    boolean executed = dao.atualizar(pessoaFisica);
    assertThat(executed, equalTo(false));

    PessoaFisica res = dao.consultarPorCpf(CPF.valueOf(cpf));
    assertThat(res.getNome(), equalTo(nome));
    assertThat(res.getSobrenome(), equalTo(sobrenome));
    assertThat(res.getDataNascimento(), equalTo(dataDeNascimento));
    assertThat(res.getCpf().getDigito(), equalTo((cpf)));
    assertThat(res.getRg(), equalTo(rg));
    assertThat(res.getEndereco(), equalTo(endereco));
    assertThat(res.getTelResidencial(), equalTo(telResidencial));
    assertThat(res.getTelCelular(), equalTo(telCelular));
    assertThat(res.getEmail(), equalTo(email));
  }

  @Test(expectedExceptions = { CPFInvalidException.class })
  public void atualizar_pf_sem_sucesso_por_cpf_invalido() {
    PessoaFisicaDAO dao = new PessoaFisicaDAO();
    RequestParamWrapper request = new RequestParamWrapper();

    int id = 2;
    String novoNome = "novo Nome D";
    long novoCpf = 11111111112l;
    String novoEmail = "updatetest@moonjava.com.br";
    LocalDate novaData = new LocalDate(2000, 12, 01);

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
    boolean executed = dao.atualizar(pessoaFisica);
    assertThat(executed, equalTo(false));
  }

}