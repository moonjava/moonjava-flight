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
package br.com.moonjava.flight.financeiro.action;

import java.util.List;

import br.com.moonjava.flight.financeiro.Cartao;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class CartaoAction implements Cartao.jdbc {

  @Override
  public boolean debitar() {
    return true;
  }

  @Override
  public boolean creditar(Cartao cartao) {
    OperadoraDeCartao operadora = new OperadoraDeCartao();
    operadora.openFile();
    List<Cartao> cartoes = operadora.readFile();

    boolean valid = false;

    for (Cartao card : cartoes) {
      if (card.getTitular().equals(cartao.getTitular())
          && card.getNumero() == cartao.getNumero()
          && card.getDataDeValidade().isEqual(cartao.getDataDeValidade())
          && card.getBandeira() == cartao.getBandeira()) {
        valid = true;
      }
    }
    operadora.closeFile();
    return valid;
  }

}