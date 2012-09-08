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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ResourceBundle;

import br.com.moonjava.flight.dao.base.UsuarioDAO;
import br.com.moonjava.flight.model.base.Usuario;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.view.FlightUI;
import br.com.moonjava.flight.view.LoginUI;

/**
 * @version 1.0 Sep 7, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class LogarFlightController extends LoginUI {

  private static final LogarFlightController ui = new LogarFlightController();
  private ResourceBundle bundle;

  public LogarFlightController() {
  }

  public static LogarFlightController getInstance() {
    return ui;
  }

  public void setsetAttributes(ResourceBundle bundle) {
    setAttributes(bundle);
    this.bundle = bundle;

    addActionListenerEntrar(new LogarBotaoHandler());
    addKeyListenerEntrar(new LogarKeyHandler());
  }

  private class LogarBotaoHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
      RequestParamWrapper request = getLogin();
      UsuarioDAO dao = new UsuarioDAO();

      Usuario usuarioLogado = dao.consultarUsuario(request);

      if (usuarioLogado != null) {
        new FlightUI(usuarioLogado, bundle);
        dispose();
      } else {
        incorrectLoginMessage();
      }
    }
  }

  private class LogarKeyHandler implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == 10) {
        RequestParamWrapper request = getLogin();
        UsuarioDAO dao = new UsuarioDAO();

        Usuario usuarioLogado = dao.consultarUsuario(request);

        if (usuarioLogado != null) {
          new FlightUI(usuarioLogado, bundle);
          dispose();
        } else {
          incorrectLoginMessage();
        }
      }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

  }
}
