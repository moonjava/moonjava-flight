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
package br.com.moonjava.flight.model.financeiro;

import org.joda.time.DateTime;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class PagamentoModel implements Pagamento {

  private final double valor;
  private final DateTime dataDeCriacao;

  public PagamentoModel(Builder builder) {
    this.valor = builder.getValor();
    this.dataDeCriacao = builder.getDataDeCriacao();
  }

  @Override
  public double getValor() {
    return valor;
  }

  @Override
  public DateTime getDataDeCriacao() {
    return dataDeCriacao;
  }

}