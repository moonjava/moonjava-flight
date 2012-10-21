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

import br.com.moonjava.flight.model.base.Perfil;
import br.com.moonjava.flight.model.base.PessoaFisica;
import br.com.moonjava.flight.model.base.Usuario;
import br.com.moonjava.flight.model.base.UsuarioModel;
import br.com.moonjava.flight.util.RequestParam;

/**
 * @version 1.0, Aug 10, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class UsuarioUpdate implements Usuario.Builder {

  private final RequestParam request;

  public UsuarioUpdate(RequestParam request) {
    this.request = request;
  }

  @Override
  public Usuario createInstance() {
    UsuarioModel impl = new UsuarioModel(this);
    impl.setId(request.intParam("id"));
    return impl;
  }

  @Override
  public String getCodigo() {
    return null;
  }

  @Override
  public PessoaFisica getPessoaFisica() {
    return null;
  }

  @Override
  public Perfil getPerfil() {
    return request.enumParam(Perfil.class, "perfil");
  }

  @Override
  public String getLogin() {
    return request.stringParam("login");
  }

  @Override
  public String getSenha() {
    return request.stringParam("senha");
  }

}