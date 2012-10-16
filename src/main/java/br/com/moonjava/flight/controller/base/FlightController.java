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

import java.util.ResourceBundle;

import br.com.moonjava.flight.model.base.Perfil;
import br.com.moonjava.flight.model.base.Usuario;
import br.com.moonjava.flight.view.FlightUI;
import br.com.moonjava.flight.view.SairHandler;
import br.com.moonjava.flight.view.SobreHandler;
import br.com.moonjava.flight.view.checkin.CheckinHandler;
import br.com.moonjava.flight.view.passagem.PassagemHandler;
import br.com.moonjava.flight.view.usuario.UsuarioHandler;

/**
 * @version 1.0 Sep 8, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class FlightController extends FlightUI {

  // Este construtor instância todos as logicas de negócio, isto é,
  // todos os controladores necessários
  public FlightController(Usuario usuarioLogado, ResourceBundle bundle) {
    super(usuarioLogado, bundle);

    VooHandler vooHandler = new VooHandler(getConteudo(), bundle, usuarioLogado);
    CheckinHandler checkinHandler = new CheckinHandler(getConteudo(), bundle);
    PassagemHandler passagemHandler = new PassagemHandler(getConteudo(), bundle);
    AeronaveHandler aeronaveHandler = new AeronaveHandler(getConteudo(), bundle);
    UsuarioHandler usuarioHandler = new UsuarioHandler(getConteudo(), bundle);

    addMenuVooListener(vooHandler);
    addMenuPassagemListener(passagemHandler);
    addMenuCheckinListener(checkinHandler);
    addMenuSobreListener(new SobreHandler(bundle));
    addMenuSairListener(new SairHandler());

    addVooListener(vooHandler);
    addPassagemListener(passagemHandler);
    addCheckinListener(checkinHandler);

    // Somente algumas funcionalidades são exibidas ao Atendente
    if (usuarioLogado.getPerfil() == Perfil.SUPERVISOR) {
      addMenuAeronaveListener(aeronaveHandler);
      addMenuUsuarioListener(usuarioHandler);

      addAeronaveListener(aeronaveHandler);
      addUsuarioListener(usuarioHandler);
    }
  }

}