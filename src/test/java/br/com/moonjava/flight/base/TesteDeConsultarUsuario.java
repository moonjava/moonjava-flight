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
public class TesteDeConsultarUsuario {

  @BeforeClass
  public void limparTabela() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void consultar_usuario_por_codigo() {
    UsuarioDAO dao = new UsuarioDAO();
    RequestParamWrapper request = new RequestParamWrapper();
    EncryptPassword encrypt = new EncryptPassword();

    String codigo = "U1001";
    request.set("codigo", codigo);

    List<Usuario> lres = dao.consultar(request);
    Usuario res = lres.get(0);

    assertThat(res.getPerfil(), equalTo(Perfil.SUPERVISOR));
    assertThat(res.getLogin(), equalTo("teste2"));
    assertThat(res.getSenha(), equalTo(encrypt.toEncryptMD5("teste2")));
  }

  public void consultar_usuario_por_cpf() {
    UsuarioDAO dao = new UsuarioDAO();
    EncryptPassword encrypt = new EncryptPassword();

    CPF cpf = CPF.parse("222.222.222-22");
    Usuario res = dao.consultarPorCpf(cpf);
    assertThat(res.getPerfil(), equalTo(Perfil.SUPERVISOR));
    assertThat(res.getLogin(), equalTo("teste2"));
    assertThat(res.getSenha(), equalTo(encrypt.toEncryptMD5("teste2")));
  }

  public void consultar_usuario_por_login() {
    UsuarioDAO dao = new UsuarioDAO();
    RequestParamWrapper request = new RequestParamWrapper();
    EncryptPassword encrypt = new EncryptPassword();

    request.set("login", "teste");

    List<Usuario> res = dao.consultar(request);
    assertThat(res.size(), equalTo(2));

    Usuario r1 = res.get(1);
    assertThat(r1.getCodigo(), equalTo("U1001"));
    assertThat(r1.getPerfil(), equalTo(Perfil.SUPERVISOR));
    assertThat(r1.getLogin(), equalTo("teste2"));
    assertThat(r1.getSenha(), equalTo(encrypt.toEncryptMD5("teste2")));
  }

}