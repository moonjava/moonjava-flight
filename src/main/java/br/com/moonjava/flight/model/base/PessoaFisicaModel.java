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

import org.joda.time.LocalDate;

import br.com.moonjava.flight.dao.base.PessoaFisicaDAO;
import br.com.moonjava.flight.util.CPF;

/**
 * @version 1.0, 10/08/2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class PessoaFisicaModel implements PessoaFisica {

  private int id;
  private String nome;
  private String sobrenome;
  private LocalDate dataDeNascimento;
  private CPF cpf;
  private String rg;
  private String endereco;
  private long telResidencial;
  private long telCelular;
  private String email;
  private PessoaFisicaDAO dao;

  public PessoaFisicaModel(Builder builder) {
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

  public PessoaFisicaModel() {
    dao = new PessoaFisicaDAO();
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
  public CPF getCpf() {
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
  public long getTelResidencial() {
    return telResidencial;
  }

  @Override
  public long getTelCelular() {
    return telCelular;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public boolean criar(PessoaFisica pojo) {
    return dao.criar(pojo);
  }

  @Override
  public void atualizar(PessoaFisica pojo) {
    dao.atualizar(pojo);
  }

  @Override
  public PessoaFisica consultarPorCPF(CPF cpf) {
    return dao.consultarPorCpf(cpf);
  }

}