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

import java.sql.ResultSet;

import br.com.moonjava.flight.base.Perfil;
import br.com.moonjava.flight.base.PessoaFisica;
import br.com.moonjava.flight.base.Usuario;
import br.com.moonjava.flight.base.UsuarioImpl;
import br.com.moonjava.flight.jdbc.ResultSetJdbc;
import br.com.moonjava.flight.jdbc.ResultSetJdbcLoader;
import br.com.moonjava.flight.jdbc.ResultSetJdbcWrapper;

/**
 * @version 1.0, Aug 10, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class UsuarioLoader implements ResultSetJdbcLoader<Usuario> {

  private String alias;

  public UsuarioLoader() {
    this.alias = "USUARIO";
  }

  @Override
  public Usuario get(ResultSet resultSet) {
    ResultSetJdbcWrapper rs = new ResultSetJdbcWrapper(resultSet, alias);
    return new UsuarioBuilder(rs).createInstance();
  }

  private class UsuarioBuilder implements Usuario.Builder {

    private final ResultSetJdbc rs;

    public UsuarioBuilder(ResultSetJdbc rs) {
      this.rs = rs;
    }

    public Usuario createInstance() {
      UsuarioImpl impl = new UsuarioImpl(this);
      impl.setId(rs.getInt("ID"));
      return impl;
    }

    @Override
    public String getCodigo() {
      return rs.getString("CODIGO");
    }

    @Override
    public Perfil getPerfil() {
      int valor = rs.getInt("CARGO");
      Perfil[] cargos = Perfil.values();
      return cargos[valor];
    }

    @Override
    public String getLogin() {
      return rs.getString("LOGIN");
    }

    @Override
    public String getSenha() {
      return rs.getString("SENHA");
    }

    @Override
    public PessoaFisica getPessoaFisica() {
      ResultSet resultSet = rs.getResultSet();
      return new PessoaFisicaLoader().get(resultSet);

    }

  }
}
