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
package br.com.moonjava.flight.base;

/**
 * @version 1.0, Aug 10, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class UsuarioImpl implements Usuario {

  private int id;
  private int codigo;
  private PessoaFisica pessoaFisica;
  private Perfil cargo;
  private String login;
  private String senha;

  public UsuarioImpl(Builder builder) {
    this.codigo = builder.getCodigo();
    this.pessoaFisica = builder.getPessoaFisica();
    this.cargo = builder.getPerfil();
    this.login = builder.getLogin();
    this.senha = builder.getSenha();
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public int getCodigo() {
    return codigo;
  }

  @Override
  public PessoaFisica getPessoaFisica() {
    return pessoaFisica;
  }

  @Override
  public Perfil getPerfil() {
    return cargo;
  }

  @Override
  public String getLogin() {
    return login;
  }

  @Override
  public String getSenha() {
    return senha;
  }

}