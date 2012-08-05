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
package br.com.moonjava.flight.action;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.joda.time.LocalDate;
import org.testng.annotations.Test;

import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
@Test
public class TesteDeOperadoraDeCartao {

  public void ler_arquivo_texto_de_operadora() {
    RequestParamWrapper request = new RequestParamWrapper();

    String titular = "Titular3";
    long numero = 555566667777l;
    LocalDate validade = new LocalDate(2016, 1, 1);
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
    assertThat(cartao.getDataDeValidade(), equalTo(new LocalDate(2016, 1, 1)));
    assertThat(cartao.getBandeira(), equalTo(Bandeira.AMERICAN_EXPRESS));
    assertThat(cartao.getValor(), equalTo(500.50));

    OperadoraDeCartao operadora = new OperadoraDeCartao();
    operadora.openFile();

    List<Cartao> res = operadora.readFile();
    assertThat(res.size(), equalTo(3));

    boolean valid = false;

    for (Cartao card : res) {
      if (card.getTitular().equals(cartao.getTitular())
          && card.getNumero() == cartao.getNumero()
          && card.getDataDeValidade().isEqual(cartao.getDataDeValidade())
          && card.getBandeira() == cartao.getBandeira()) {
        valid = true;
      }
    }
    assertThat(valid, equalTo(true));

    operadora.closeFile();
  }

}