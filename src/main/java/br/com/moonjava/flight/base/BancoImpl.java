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
package br.com.moonjava.flight.base;

/**
 * @version 1.0, Aug 13, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class BancoImpl implements Banco {

  private int id;
  private PessoaFisica pessoaFisica;
  private int banco;
  private int agencia;
  private int conta;

  public BancoImpl(Builder builder) {
    this.pessoaFisica = builder.getPessoaFisica();
    this.banco = builder.getBanco();
    this.agencia = builder.getAgencia();
    this.conta = builder.getConta();
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public int getID() {
    return id;
  }

  @Override
  public PessoaFisica getPessoaFisica() {
    return pessoaFisica;
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