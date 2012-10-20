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
package br.com.moonjava.flight.financeiro;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;

import org.joda.time.LocalDate;
import org.testng.annotations.Test;

import br.com.moonjava.flight.controller.financeiro.CartaoControl;
import br.com.moonjava.flight.controller.financeiro.CartaoCreate;
import br.com.moonjava.flight.model.financeiro.Bandeira;
import br.com.moonjava.flight.model.financeiro.Cartao;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
@Test
public class TesteDePagamentoCartao {

  public void pagamento_com_cartao_credito_deve_funcionar() {
    RequestParamWrapper request = new RequestParamWrapper();

    String titular = "Titular3";
    long numero = 555566667777l;
    LocalDate validade = new LocalDate()
        .withYear(2016)
        .withMonthOfYear(1)
        .withDayOfMonth(1);
    Bandeira bandeira = Bandeira.AMERICAN_EXPRESS;
    double valor = 500.50;

    request.set("titular", titular);
    request.set("numero", numero);
    request.set("validade", validade);
    request.set("bandeira", bandeira);
    request.set("valor", valor);

    Cartao cartao = new CartaoCreate(request).createInstance();
    assertThat(cartao.getTitular(), equalTo("Titular3"));
    assertThat(cartao.getNumero(), equalTo(555566667777l));
    assertThat(cartao.getDataDeValidade(), equalTo(new LocalDate()
        .withYear(2016)
        .withMonthOfYear(1)
        .withDayOfMonth(1)));
    assertThat(cartao.getBandeira(), equalTo(Bandeira.AMERICAN_EXPRESS));
    assertThat(cartao.getValor(), equalTo(500.50));

    CartaoControl control = new CartaoControl();
    boolean isValid = control.creditar(cartao);

    assertThat(isValid, equalTo(true));
  }

}