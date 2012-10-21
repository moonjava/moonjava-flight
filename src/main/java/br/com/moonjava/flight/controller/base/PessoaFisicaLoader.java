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
package br.com.moonjava.flight.controller.base;

import java.sql.ResultSet;

import org.joda.time.LocalDate;

import br.com.moonjava.flight.jdbc.ResultSetJdbc;
import br.com.moonjava.flight.jdbc.ResultSetJdbcLoader;
import br.com.moonjava.flight.jdbc.ResultSetJdbcWrapper;
import br.com.moonjava.flight.model.base.PessoaFisica;
import br.com.moonjava.flight.model.base.PessoaFisicaModel;
import br.com.moonjava.flight.util.CPF;

/**
 * @version 1.0, 10/08/2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class PessoaFisicaLoader implements ResultSetJdbcLoader<PessoaFisica> {

  private final String alias;

  public PessoaFisicaLoader() {
    this.alias = "PESSOAFISICA";
  }

  @Override
  public PessoaFisica get(ResultSet resultSet) {
    ResultSetJdbcWrapper rs = new ResultSetJdbcWrapper(resultSet, alias);
    return new PessoaFisicaBuilder(rs).createInstance();
  }

  private class PessoaFisicaBuilder implements PessoaFisica.Builder {

    private final ResultSetJdbc rs;

    public PessoaFisicaBuilder(ResultSetJdbc rs) {
      this.rs = rs;
    }

    @Override
    public PessoaFisica createInstance() {
      PessoaFisicaModel impl = new PessoaFisicaModel(this);
      impl.setId(rs.getInt("ID"));
      return impl;
    }

    @Override
    public String getNome() {
      return rs.getString("NOME");
    }

    @Override
    public String getSobrenome() {
      return rs.getString("SOBRENOME");
    }

    @Override
    public LocalDate getDataNascimento() {
      return rs.getLocalDate("DATA_NASCIMENTO");
    }

    @Override
    public CPF getCpf() {
      long value = rs.getLong("CPF");
      return CPF.valueOf(value);
    }

    @Override
    public String getRg() {
      return rs.getString("RG");
    }

    @Override
    public String getEndereco() {
      return rs.getString("ENDERECO");
    }

    @Override
    public long getTelResidencial() {
      return rs.getInt("TEL_RESIDENCIAL");
    }

    @Override
    public long getTelCelular() {
      return rs.getInt("TEL_CELULAR");
    }

    @Override
    public String getEmail() {
      return rs.getString("EMAIL");
    }

  }

}