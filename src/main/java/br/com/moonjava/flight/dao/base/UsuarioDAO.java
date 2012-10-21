package br.com.moonjava.flight.dao.base;

import java.util.List;

import br.com.moonjava.flight.controller.base.UsuarioLoader;
import br.com.moonjava.flight.jdbc.SqlStatement;
import br.com.moonjava.flight.jdbc.SqlStatementWrapper;
import br.com.moonjava.flight.model.base.PessoaFisica;
import br.com.moonjava.flight.model.base.Usuario;
import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.RequestParam;

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

/**
 * @version 1.0, Aug 10, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class UsuarioDAO implements Usuario.Jdbc {

  private SqlStatement query() {
    return new SqlStatementWrapper()
        .prepare()

        .with("select *")
        .with("from FLIGHT.USUARIO as USUARIO")

        .with("join FLIGHT.PESSOAFISICA as PESSOAFISICA")
        .with("on PESSOAFISICA.ID = USUARIO.PESSOAFISICA_ID")

        .load(new UsuarioLoader());
  }

  @Override
  public void criar(Usuario usuario) {
    PessoaFisica pessoaFisica = usuario.getPessoaFisica();
    new SqlStatementWrapper()
        .prepare()

        .with("insert into FLIGHT.USUARIO")
        .with("(ID, PESSOAFISICA_ID, CODIGO, PERFIL, LOGIN, SENHA)")

        .with("values (")
        .with("?,", usuario.getId())
        .with("?,", pessoaFisica.getId())
        .with("?,", usuario.getCodigo())
        .with("?,", usuario.getPerfil().ordinal())
        .with("?,", usuario.getLogin())
        .with("?)", usuario.getSenha())

        .andExecute();
  }

  @Override
  public List<Usuario> consultar(RequestParam request) {
    String login = request.stringParam("login");
    String codigo = request.stringParam("codigo");

    return query()

        .with("where 1 = 1")
        .with("and USUARIO.LOGIN like concat ('%',?,'%')", login)
        .with("and USUARIO.CODIGO like concat ('%',?,'%')", codigo)
        .with("order by USUARIO.CODIGO asc")

        .andList();
  }

  @Override
  public Usuario consultarUsuario(RequestParam request) {
    String login = request.stringParam("login");
    String senha = request.stringParam("senha");

    return query()

        .with("where 1 = 1")
        .with("and USUARIO.LOGIN = ?", login)
        .with("and USUARIO.SENHA = ?", senha)

        .andGet();
  }

  @Override
  public Usuario consultarPorCpf(CPF cpf) {
    return query()

        .with("where PESSOAFISICA.CPF = ?", cpf.getDigito())

        .andGet();
  }

  @Override
  public boolean atualizar(Usuario usuario) {
    boolean executed = new SqlStatementWrapper()
        .prepare()

        .with("update FLIGHT.USUARIO set")

        .with("PERFIL = ?,", usuario.getPerfil().ordinal())
        .with("LOGIN = ?,", usuario.getLogin())
        .with("SENHA = ?", usuario.getSenha())

        .with("where ID = ?", usuario.getId())

        .andExecute();

    return executed;
  }

  @Override
  public void deletar(int id) {
    new SqlStatementWrapper()
        .prepare()

        .with("delete from FLIGHT.USUARIO")
        .with("where ID = ?", id)

        .andExecute();
  }

}