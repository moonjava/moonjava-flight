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

import org.joda.time.DateTime;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class VooImpl implements Voo {

  private int id;
  private final int codigo;
  private final Aeronave aeronave;
  private final String origem;
  private final String destino;
  private final String escala;
  private final DateTime dataDePartida;
  private final DateTime dataDeChegada;
  private final String observacao;
  private final Status status;

  public VooImpl(Builder builder) {
    this.codigo = builder.getCodigo();
    this.aeronave = builder.getAeronave();
    this.origem = builder.getOrigem();
    this.destino = builder.getDestino();
    this.escala = builder.getEscala();
    this.dataDePartida = builder.getDataDePartida();
    this.dataDeChegada = builder.getDataDeChegada();
    this.observacao = builder.getObservacao();
    this.status = builder.getStatus();
  }

  @Override
  public int getId() {
    return id;
  }

  void setId(int id) {
    this.id = id;
  }

  @Override
  public int getCodigo() {
    return codigo;
  }

  @Override
  public Aeronave getAeronave() {
    return aeronave;
  }

  @Override
  public String getOrigem() {
    return origem;
  }

  @Override
  public String getDestino() {
    return destino;
  }

  @Override
  public String getEscala() {
    return escala;
  }

  @Override
  public DateTime getDataDePartida() {
    return dataDePartida;
  }

  @Override
  public DateTime getDataDeChegada() {
    return dataDeChegada;
  }

  @Override
  public String getObservacao() {
    return observacao;
  }

  @Override
  public Status getStatus() {
    return status;
  }

}