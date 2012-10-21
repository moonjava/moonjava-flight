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

import java.util.List;

import br.com.moonjava.flight.dao.base.AeronaveDAO;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class AeronaveModel implements Aeronave {

  private int id;
  private String codigo;
  private String nome;
  private int qtdDeAssento;
  private boolean mapa;
  private AeronaveDAO dao;

  public AeronaveModel(Builder builder) {
    this.codigo = builder.getCodigo();
    this.nome = builder.getNome();
    this.qtdDeAssento = builder.getQtdDeAssento();
    this.mapa = builder.isMapa();
  }

  public AeronaveModel() {
    this.dao = new AeronaveDAO();
  }

  @Override
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String getCodigo() {
    return codigo;
  }

  @Override
  public String getNome() {
    return nome;
  }

  @Override
  public int getQtdDeAssento() {
    return qtdDeAssento;
  }

  @Override
  public boolean isMapa() {
    return mapa;
  }

  @Override
  public String toString() {
    return nome;
  }

  @Override
  public void criar(Aeronave pojo) {
    dao.criar(pojo);
  }

  @Override
  public List<Aeronave> consultar(RequestParamWrapper request) {
    return dao.consultar(request);
  }

  @Override
  public void atualizar(Aeronave pojo) {
    dao.atualizar(pojo);
  }

  @Override
  public void deletar(int id) {
    dao.deletar(id);
  }

}