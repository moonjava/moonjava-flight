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
package br.com.moonjava.flight.jdbc;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;

import java.sql.Connection;
import java.sql.SQLException;

import org.testng.annotations.Test;

import br.com.moonjava.flight.jdbc.Conexao;
import br.com.moonjava.flight.jdbc.ConexaoImpl;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
@Test
public class TesteDeConexao {

  public void conexao_com_o_bd_deve_funcionar() {
    try {
      Conexao conexao = new ConexaoImpl();
      Connection connection = conexao.getConexao();

      assertThat(conexao.getUrl(), equalTo("jdbc:mysql://localhost"));
      assertThat(conexao.getUser(), equalTo("usjt"));
      assertThat(conexao.getPassword(), equalTo("usjt"));

      connection.close();

      assertThat(connection.isClosed(), equalTo(true));

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}