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

import br.com.moonjava.flight.base.Perfil;
import br.com.moonjava.flight.base.Usuario;
import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
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

  public void criar_usuario() {
    UsuarioAction action = new UsuarioAction();
    RequestParamWrapper request = new RequestParamWrapper();

    int pessoaFisica = 3;
    int codigo = 3;
    Perfil cargo = Perfil.ATENDENTE;
    String login = "teste3";
    String senha = "teste3";

    request.set("login", "teste");

    List<Usuario> antes = action.consultar(request);
    assertThat(antes.size(), equalTo(2));

    request.set("codigo", codigo);
    request.set("pessoaFisica", pessoaFisica);
    request.set("cargo", cargo);
    request.set("login", login);
    request.set("senha", senha);

    Usuario usuario = new UsuarioCreate(request).createInstance();

    action.criar(usuario);

    Usuario res = action.consultarPorCodigo(codigo);
    assertThat(res.getCodigo(), equalTo(codigo));
    assertThat(res.getPerfil(), equalTo(Perfil.ATENDENTE));
    assertThat(res.getLogin(), equalTo(login));
    assertThat(res.getSenha(), equalTo(senha));

  }
}