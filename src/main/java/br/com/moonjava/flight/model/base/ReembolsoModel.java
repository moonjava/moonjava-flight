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
package br.com.moonjava.flight.model.base;

import br.com.moonjava.flight.dao.base.ReembolsoDAO;
import br.com.moonjava.flight.util.CPF;

/**
 * @version 1.0, Aug 13, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class ReembolsoModel implements Reembolso {

  private int id;
  private Passagem passagem;
  private String titular;
  private CPF cpf;
  private int banco;
  private int agencia;
  private int conta;
  private double valor;
  private ReembolsoDAO dao;

  public ReembolsoModel(Builder builder) {
    this.passagem = builder.getPassagem();
    this.titular = builder.getTitular();
    this.cpf = builder.getCpf();
    this.banco = builder.getBanco();
    this.agencia = builder.getAgencia();
    this.conta = builder.getConta();
    this.valor = builder.getValor();
  }

  public ReembolsoModel() {
    dao = new ReembolsoDAO();
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public Passagem getPassagem() {
    return passagem;
  }

  @Override
  public String getTitular() {
    return titular;
  }

  @Override
  public CPF getCpf() {
    return cpf;
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

  @Override
  public double getValor() {
    return valor;
  }

  @Override
  public boolean criar(Reembolso reembolso) {
    return dao.criar(reembolso);
  }

}