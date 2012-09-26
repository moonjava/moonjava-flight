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

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class ChequeModel extends PagamentoModel implements Cheque {

  private final int numero;
  private final int banco;
  private final int agencia;
  private final int conta;

  public ChequeModel(Cheque.Builder builder) {
    super(builder);
    this.numero = builder.getNumero();
    this.banco = builder.getBanco();
    this.agencia = builder.getAgencia();
    this.conta = builder.getConta();
  }

  @Override
  public int getNumero() {
    return numero;
  }

  @Override
  public int getBanco() {
    return banco;
  }

  @Override
  public int getAgencia() {
    return agencia;
  }

  @Override
  public int getConta() {
    return conta;
  }

}