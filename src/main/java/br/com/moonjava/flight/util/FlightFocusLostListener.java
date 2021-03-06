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

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * @version 1.0 Sep 7, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
// Esta classe tem o unico intuito de diminuir linhas de codigo,
// pois utilizamos muitas vezes o FocusListener sem focusGained
public abstract class FlightFocusLostListener implements FocusListener {

  @Override
  public void focusGained(FocusEvent e) {
  }

}