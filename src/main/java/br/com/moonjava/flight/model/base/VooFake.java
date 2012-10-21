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

import org.joda.time.DateTime;

import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Oct 6, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class VooFake implements Voo {

  @Override
  public int getId() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getCodigo() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Aeronave getAeronave() {
    throw new UnsupportedOperationException();

  }

  @Override
  public String getOrigem() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getDestino() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getEscala() {
    throw new UnsupportedOperationException();
  }

  @Override
  public DateTime getDataDePartida() {
    throw new UnsupportedOperationException();
  }

  @Override
  public DateTime getDataDeChegada() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getObservacao() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Status getStatus() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getAssentoLivre() {
    throw new UnsupportedOperationException();
  }

  @Override
  public double getPreco() {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Voo> consultaPainel() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean criar(Voo pojo) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Voo> consultar(RequestParamWrapper request) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void deletar(int id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean atualizar(Voo pojo) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void controlarStatus(int id, Status fromString) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void decrementarAssento(int vooId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void deletaPorAeronaveId(int aeronaveId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Voo> consultarPorAeronaveId(int id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void incrementarAssento(int vooId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Voo consultarPorId(int id) {
    throw new UnsupportedOperationException();
  }

}