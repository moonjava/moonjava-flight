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

import br.com.moonjava.flight.controller.base.UsuarioUpdate;
import br.com.moonjava.flight.dao.base.UsuarioDAO;
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.model.base.Perfil;
import br.com.moonjava.flight.model.base.Usuario;
import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.EncryptPassword;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0, Aug 13, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
@Test
public class TesteDeAtualizarUsuario {

  @BeforeClass
  public void limparTabela() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void atualizar_usuario_com_sucesso() {
    UsuarioDAO dao = new UsuarioDAO();
    RequestParamWrapper request = new RequestParamWrapper();
    EncryptPassword encrypt = new EncryptPassword();

    int id = 1;
    String codigo = "U1000";
    int pessoaFisica = 1;
    Perfil perfil = Perfil.ATENDENTE;
    String login = "teste1";
    String senha = "6VkIjGBJ8RBMhMm95VYKEw==";

    Usuario antes = dao.consultarPorCpf(CPF.valueOf(11111111111l));
    assertThat(antes.getCodigo(), equalTo(codigo));
    assertThat(antes.getPessoaFisica().getId(), equalTo(pessoaFisica));
    assertThat(antes.getPerfil(), equalTo(perfil));
    assertThat(antes.getLogin(), equalTo(login));
    assertThat(antes.getSenha(), equalTo(senha));

    Perfil novoPerfil = Perfil.SUPERVISOR;
    String novoLogin = "testeDeUpdate";
    String novaSenha = encrypt.toEncryptMD5("testeDeUpdate");

    request.set("id", id);
    request.set("perfil", novoPerfil);
    request.set("login", novoLogin);
    request.set("senha", novaSenha);

    Usuario usuario = new UsuarioUpdate(request).createInstance();
    boolean executed = dao.atualizar(usuario);
    assertThat(executed, equalTo(true));

    Usuario res = dao.consultarPorCpf(CPF.valueOf(11111111111l));
    assertThat(res.getCodigo(), equalTo(codigo));
    assertThat(res.getPessoaFisica().getId(), equalTo(pessoaFisica));
    assertThat(res.getPerfil(), equalTo(novoPerfil));
    assertThat(res.getLogin(), equalTo(novoLogin));
    assertThat(res.getSenha(), equalTo(novaSenha));
  }

  public void atualizar_usuario_sem_sucesso_por_login_e_senha_invalido() {
    UsuarioDAO dao = new UsuarioDAO();
    RequestParamWrapper request = new RequestParamWrapper();

    int id = 1;

    Perfil novoPerfil = Perfil.SUPERVISOR;
    String novoLogin = "teste2";
    String novaSenha = "OIUVNth3AdIZGZDiSn+NTg==";

    request.set("id", id);
    request.set("perfil", novoPerfil);
    request.set("login", novoLogin);
    request.set("senha", novaSenha);

    Usuario usuario = new UsuarioUpdate(request).createInstance();
    boolean executed = dao.atualizar(usuario);
    assertThat(executed, equalTo(false));
  }

}