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
package br.com.moonjava.flight.view.usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import br.com.moonjava.flight.controller.base.UsuarioController;

/**
 * @version 1.0 Aug 21, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class UsuarioHandler implements ActionListener, MenuListener {

  private final JPanel conteudo;
  private final ResourceBundle bundle;

  public UsuarioHandler(JPanel conteudo, ResourceBundle bundle) {
    this.conteudo = conteudo;
    this.bundle = bundle;
  }

  @Override
  public void menuCanceled(MenuEvent e) {
  }

  @Override
  public void menuDeselected(MenuEvent e) {
  }

  @Override
  public void menuSelected(MenuEvent e) {
    new UsuarioController(conteudo, bundle);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    new UsuarioController(conteudo, bundle);
  }
}