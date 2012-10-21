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
package br.com.moonjava.flight.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @version 1.0 Sep 8, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
// Esta classe tem o unico intuito de diminuir linhas de codigo,
// pois utilizamos muitas vezes o KeyListener
public abstract class FlightKeyPressedListener implements KeyListener {

  @Override
  public void keyReleased(KeyEvent e) {
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

}