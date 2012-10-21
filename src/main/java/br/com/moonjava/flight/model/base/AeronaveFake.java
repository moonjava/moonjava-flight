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

import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class AeronaveFake implements Aeronave {

  @Override
  public List<Aeronave> consultar(RequestParamWrapper request) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getId() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getCodigo() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getNome() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getQtdDeAssento() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isMapa() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void criar(Aeronave pojo) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void deletar(int id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void atualizar(Aeronave aeronave) {
    throw new UnsupportedOperationException();
  }

}