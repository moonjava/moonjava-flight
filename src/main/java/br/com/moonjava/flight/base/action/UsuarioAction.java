package br.com.moonjava.flight.base.action;

import java.util.List;

import br.com.moonjava.flight.base.PessoaFisica;
import br.com.moonjava.flight.base.Usuario;
import br.com.moonjava.flight.jdbc.SqlStatement;
import br.com.moonjava.flight.jdbc.SqlStatementWrapper;
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
public class UsuarioAction implements Usuario.Jdbc {

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
        .with("(ID, PESSOAFISICA_ID, CODIGO, CARGO, LOGIN, SENHA)")

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

    return query()

        .with("where 1 = 1")
        .with("and USUARIO.LOGIN like concat ('%',?,'%')", login)
        .with("order by USUARIO.CODIGO asc")

        .andList();
  }

  @Override
  public Usuario consultarPorId(int id) {
    return query()

        .with("where USUARIO.ID = ?", id)

        .andGet();
  }

  @Override
  public Usuario consultarPorCodigo(int codigo) {
    return query()

        .with("where USUARIO.CODIGO = ?", codigo)

        .andGet();
  }

  @Override
  public Usuario consultarPorCpf(long cpf) {
    return query()

        .with("where PESSOAFISICA.CPF = ?", cpf)

        .andGet();
  }

  @Override
  public void atualizar(Usuario usuario) {
    new SqlStatementWrapper()
        .prepare()

        .with("update FLIGHT.USUARIO set")

        .with("CARGO = ?,", usuario.getPerfil().ordinal())
        .with("LOGIN = ?,", usuario.getLogin())
        .with("SENHA = ?", usuario.getSenha())

        .with("where ID = ?", usuario.getId())

        .andExecute();
  }

  @Override
  public void deletar(int id) {
    new SqlStatementWrapper()
        .prepare()

        .with("delete from FLIGHT.USUARIO")
        .with("where ID = ?", id)

        .andExecute();
  }

  @Override
  public void deletarPorPessoaFisicaId(int pessoaFisica) {
    new SqlStatementWrapper()
        .prepare()

        .with("delete from FLIGHT.USUARIO")
        .with("where PESSOAFISICA_ID = ?", pessoaFisica)

        .andExecute();
  }

}