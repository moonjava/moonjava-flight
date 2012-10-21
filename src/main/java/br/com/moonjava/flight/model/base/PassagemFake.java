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

/**
 * @version 1.0 07/10/2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class PassagemFake implements Passagem {

  @Override
  public int getId() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Voo getVoo() {
    throw new UnsupportedOperationException();
  }

  @Override
  public PessoaFisica getPessoaFisica() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getCodigoBilhete() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getAssento() {
    throw new UnsupportedOperationException();
  }

  @Override
  public double getPreco(Passagem passagem) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean vender(Passagem pojo) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Passagem consultarPorCodigoBilhete(String bilhete) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Passagem> consultarPorVoo(Voo voo) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean efetuarCheckin(Passagem pojo, String assento) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean cancelarPorVoo(Voo voo) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean efetuarCancelamento(Passagem pojo) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean transferir(Passagem passagem) {
    throw new UnsupportedOperationException();
  }

}