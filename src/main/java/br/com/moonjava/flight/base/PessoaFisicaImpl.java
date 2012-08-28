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

import org.joda.time.LocalDate;

/**
 * @version 1.0, 10/08/2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class PessoaFisicaImpl implements PessoaFisica {

  private int id;
  private final String nome;
  private final String sobrenome;
  private final LocalDate dataDeNascimento;
  private final long cpf;
  private final String rg;
  private final String endereco;
  private final int telResidencial;
  private final int telCelular;
  private final String email;

  public PessoaFisicaImpl(Builder builder) {
    this.nome = builder.getNome();
    this.sobrenome = builder.getSobrenome();
    this.dataDeNascimento = builder.getDataNascimento();
    this.cpf = builder.getCpf();
    this.rg = builder.getRg();
    this.endereco = builder.getEndereco();
    this.telResidencial = builder.getTelResidencial();
    this.telCelular = builder.getTelCelular();
    this.email = builder.getEmail();
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public String getNome() {
    return nome;
  }

  @Override
  public String getSobrenome() {
    return sobrenome;
  }

  @Override
  public LocalDate getDataNascimento() {
    return dataDeNascimento;
  }

  @Override
  public long getCpf() {
    return cpf;
  }

  @Override
  public String getRg() {
    return rg;
  }

  @Override
  public String getEndereco() {
    return endereco;
  }

  @Override
  public int getTelResidencial() {
    return telResidencial;
  }

  @Override
  public int getTelCelular() {
    return telCelular;
  }

  @Override
  public String getEmail() {
    return email;
  }

}