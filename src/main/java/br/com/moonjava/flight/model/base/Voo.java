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

import br.com.moonjava.flight.util.RequestParam;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public interface Voo {

  interface Builder extends br.com.moonjava.flight.util.Builder<Voo> {

    String getCodigo();

    Aeronave getAeronave();

    String getOrigem();

    String getDestino();

    String getEscala();

    DateTime getDataDePartida();

    DateTime getDataDeChegada();

    String getObservacao();

    Status getStatus();

    int getAssentoLivre();

    double getPreco();

  }

  interface Jdbc {

    List<Voo> consultaPainel();

    boolean criar(Voo voo);

    List<Voo> consultar(RequestParam request);

    Voo consultarPorId(int id);

    boolean atualizar(Voo voo);

    void deletar(int id);

    void deletarPorAeronaveId(int id);

    void controlarStatus(int id, Status status);

    void incrementarAssento(int id);

    void decrementarAssento(int id);

    List<Voo> consultarPorAeronaveId(int id);
  }

  int getId();

  String getCodigo();

  Aeronave getAeronave();

  String getOrigem();

  String getDestino();

  String getEscala();

  DateTime getDataDePartida();

  DateTime getDataDeChegada();

  String getObservacao();

  Status getStatus();

  int getAssentoLivre();

  double getPreco();

  List<Voo> consultaPainel();

  boolean criar(Voo pojo);

  List<Voo> consultar(RequestParamWrapper request);

  Voo consultarPorId(int id);

  boolean atualizar(Voo pojo);

  void deletar(int id);

  void deletaPorAeronaveId(int aeronaveId);

  void controlarStatus(int id, Status fromString);

  void incrementarAssento(int vooId);

  void decrementarAssento(int vooId);

  List<Voo> consultarPorAeronaveId(int id);

}