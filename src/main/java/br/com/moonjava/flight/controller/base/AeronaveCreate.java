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
package br.com.moonjava.flight.controller.base;

import br.com.moonjava.flight.model.base.Aeronave;
import br.com.moonjava.flight.model.base.AeronaveModel;
import br.com.moonjava.flight.util.RequestParam;

/**
 * @version 1.0, 25/07/2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class AeronaveCreate implements Aeronave.Builder {

  private final RequestParam request;

  public AeronaveCreate(RequestParam request) {
    this.request = request;
  }

  @Override
  public Aeronave createInstance() {
    return new AeronaveModel(this);
  }

  @Override
  public String getCodigo() {
    return request.stringParam("codigo");
  }

  @Override
  public String getNome() {
    return request.stringParam("nome");
  }

  @Override
  public int getQtdDeAssento() {
    return request.intParam("qtdDeAssento");
  }

  @Override
  public boolean isMapa() {
    return request.booleanParam("mapa");
  }

}