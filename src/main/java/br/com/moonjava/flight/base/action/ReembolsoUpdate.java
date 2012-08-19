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
package br.com.moonjava.flight.base.action;

import br.com.moonjava.flight.base.Reembolso;
import br.com.moonjava.flight.base.ReembolsoImpl;
import br.com.moonjava.flight.util.RequestParam;

/**
 * @version 1.0, Aug 13, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class ReembolsoUpdate implements Reembolso.Builder {

  private final RequestParam request;

  public ReembolsoUpdate(RequestParam request) {
    this.request = request;
  }

  @Override
  public Reembolso createInstance() {
    ReembolsoImpl impl = new ReembolsoImpl(this);
    impl.setId(request.intParam("id"));
    return impl;
  }

  @Override
  public Passagem getPassagem() {
    return null;
  }

  @Override
  public int getBanco() {
    return request.intParam("banco");
  }

  @Override
  public int getAgencia() {
    return request.intParam("agencia");
  }

  @Override
  public int getConta() {
    return request.intParam("conta");
  }
}