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

import br.com.moonjava.flight.util.CPF;

/**
 * @version 1.0, Aug 13, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public interface Reembolso {

  interface Builder extends br.com.moonjava.flight.util.Builder<Reembolso> {

    Passagem getPassagem();

    String getTitular();

    CPF getCpf();

    int getBanco();

    int getAgencia();

    int getConta();

    double getValor();
  }

  interface Jdbc {

    boolean criar(Reembolso reembolso);

  }

  int getId();

  Passagem getPassagem();

  String getTitular();

  CPF getCpf();

  int getBanco();

  int getAgencia();

  int getConta();

  double getValor();

  boolean criar(Reembolso reembolso);
}