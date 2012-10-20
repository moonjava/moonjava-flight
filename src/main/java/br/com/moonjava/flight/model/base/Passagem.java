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

import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.RequestParam;

/**
 * @version 1.0 Aug 24, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public interface Passagem {

  interface Builder extends br.com.moonjava.flight.util.Builder<Passagem> {

    Voo getVoo();

    PessoaFisica getPessoaFisica();

    String getCodigoBilhete();

    String getAssento();
  }

  interface Jdbc {

    boolean vender(Passagem passagem);

    List<Passagem> consultar(RequestParam request);

    Passagem consultarPorId(int id);

    Passagem consultarPorCodigoBilhete(String bilhete);

    List<Passagem> consultarPorCpf(CPF cpf);

    boolean transferir(Passagem passagem);

    void deletar(int id);

  }

  int getId();

  Voo getVoo();

  PessoaFisica getPessoaFisica();

  String getCodigoBilhete();

  String getAssento();

  double cancelarPassagem(Passagem passagem);

  boolean transferirPassagem(Passagem passagem, Voo voo);

  void venderPassagem(Passagem pojo);

  Passagem consultarPorCodigoBilhete(String bilhete);

  List<Passagem> consultarPorVoo(Voo voo);

  boolean efetuarCheckin(Passagem pojo, String assento);

  boolean cancelarPorVoo(Voo voo);

}
