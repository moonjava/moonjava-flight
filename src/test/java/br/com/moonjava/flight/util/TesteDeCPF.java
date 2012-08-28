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
package br.com.moonjava.flight.util;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
@Test
public class TesteDeCPF {

  public void cpf_deve_ser_valido() {
    long val = 11111111111l;
    CPF cpf = CPF.valueOf(val);
    assertThat(cpf.toString(), equalTo("11111111111"));
  }

  public void cpf_deve_ser_convertido_em_numero() {
    String val = "222.222.222-22";
    CPF cpf = CPF.parse(val);
    assertThat(cpf.toString(), equalTo("22222222222"));
  }

  @Test(expectedExceptions = { CPFInvalidException.class })
  public void excecao_de_cpf_invalido_deve_ser_lancada_com_long() {
    long val = 22222222223l;
    CPF.valueOf(val);
  }

  @Test(expectedExceptions = { CPFInvalidException.class })
  public void excecao_de_cpf_invalido_deve_ser_lancada_com_string() {
    String val = "222.222.222-23";
    CPF.parse(val);
  }

  @Test(expectedExceptions = { CPFInvalidException.class })
  public void excecao_de_cpf_invalido_deve_ser_lancada_com_12_digitos() {
    String val = "222.222.222-233";
    CPF.parse(val);
  }

}