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
import java.util.ResourceBundle;

import br.com.moonjava.flight.model.base.Usuario;
import br.com.moonjava.flight.model.base.UsuarioModel;
import br.com.moonjava.flight.util.EncryptPassword;
import br.com.moonjava.flight.util.FlightKeyPressedListener;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.view.LoginUI;

/**
 * @version 1.0 Sep 7, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class LoginController extends LoginUI {

  public LoginController(ResourceBundle bundle) {
    super(bundle);

    addLogarListener(new LogarHandler());
    addLogarKeyListener(new LogarHandler());
  }

  private class LogarHandler extends FlightKeyPressedListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      logar();
    }

    // Adiciona a ação de login quando a tecla 'Enter'(10) for pressionada
    @Override
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == 10) {
        logar();
      }
    }

    private void logar() {
      // Encriptografia para senha utilizando codigos 'sun.misc.BASE64Encoder'
      EncryptPassword encrypt = new EncryptPassword();
      RequestParamWrapper request = getLogin();
      request.set("senha", encrypt.toEncryptMD5(request.stringParam("senha")));
      Usuario usuarioLogado = new UsuarioModel().consultarUsuario(request);

      // Usuario Logado finaliza Frame atual e inicializa a Frame principal
      if (usuarioLogado != null) {
        new FlightController(usuarioLogado, bundle);
        new PainelController(bundle);
        dispose();
      } else {
        incorrectLoginMessage();
      }
    }

  }

}