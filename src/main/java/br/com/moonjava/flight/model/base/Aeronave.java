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

import br.com.moonjava.flight.util.RequestParam;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public interface Aeronave {

  interface Builder extends br.com.moonjava.flight.util.Builder<Aeronave> {

    String getCodigo();

    String getNome();

    int getQtdDeAssento();

    boolean isMapa();

  }

  interface Jdbc {

    void criar(Aeronave aeronave);

    List<Aeronave> consultar(RequestParam request);

    Aeronave consultarPorId(int id);

    void atualizar(Aeronave aeronave);

    void deletar(int id);
  }

  List<Aeronave> consultar(RequestParamWrapper request);

  int getId();

  String getCodigo();

  String getNome();

  int getQtdDeAssento();

  boolean isMapa();

  void criar(Aeronave pojo);

}