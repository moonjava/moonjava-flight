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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import br.com.moonjava.flight.model.base.Usuario;

/**
 * @version 1.0 Aug 17, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class VooHandler implements ActionListener, MenuListener {

  private final JPanel conteudo;
  private final ResourceBundle bundle;
  private final Usuario usuarioLogado;

  public VooHandler(JPanel conteudo, ResourceBundle bundle, Usuario usuarioLogado) {
    this.conteudo = conteudo;
    this.bundle = bundle;
    this.usuarioLogado = usuarioLogado;
  }

  @Override
  public void menuCanceled(MenuEvent e) {
  }

  @Override
  public void menuDeselected(MenuEvent e) {
  }

  @Override
  public void menuSelected(MenuEvent e) {
    new VooController(conteudo, bundle, usuarioLogado);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    new VooController(conteudo, bundle, usuarioLogado);
  }

}