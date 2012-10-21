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

import br.com.moonjava.flight.controller.base.UsuarioCreate;
import br.com.moonjava.flight.dao.base.UsuarioDAO;
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.model.base.Perfil;
import br.com.moonjava.flight.model.base.Usuario;
import br.com.moonjava.flight.util.EncryptPassword;
import br.com.moonjava.flight.util.GerarCodigo;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0, Aug 13, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
@Test
public class TesteDeCriarUsuario {

  @BeforeClass
  public void limparTabela() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void criar_usuario_com_sucesso() {
    UsuarioDAO dao = new UsuarioDAO();
    RequestParamWrapper request = new RequestParamWrapper();
    RequestParamWrapper request2 = new RequestParamWrapper();
    EncryptPassword encrypt = new EncryptPassword();

    int pessoaFisica = 3;
    String codigo = new GerarCodigo("USUARIO").getCodigo();
    Perfil perfil = Perfil.ATENDENTE;
    String login = "teste3";
    String senha = encrypt.toEncryptMD5("teste3");

    request.set("login", "teste");
    List<Usuario> antes = dao.consultar(request);
    assertThat(antes.size(), equalTo(2));

    request.set("codigo", codigo);
    request.set("pessoaFisica", pessoaFisica);
    request.set("perfil", perfil);
    request.set("login", login);
    request.set("senha", senha);

    Usuario usuario = new UsuarioCreate(request).createInstance();
    dao.criar(usuario);

    request2.set("login", "teste");
    List<Usuario> lres = dao.consultar(request2);
    assertThat(lres.size(), equalTo(3));

    Usuario res = lres.get(2);

    assertThat(res.getCodigo(), equalTo(codigo));
    assertThat(res.getPerfil(), equalTo(Perfil.ATENDENTE));
    assertThat(res.getLogin(), equalTo(login));
    assertThat(res.getSenha(), equalTo(senha));
  }

}