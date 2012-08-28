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

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class AeronaveModel implements Aeronave {

  private int id;
  private final String codigo;
  private final String nome;
  private final int qtdDeAssento;
  private final boolean mapa;

  public AeronaveModel(Builder builder) {
    this.codigo = builder.getCodigo();
    this.nome = builder.getNome();
    this.qtdDeAssento = builder.getQtdDeAssento();
    this.mapa = builder.isMapa();
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

}