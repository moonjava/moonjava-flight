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

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

/**
 * @version 1.0 Aug 28, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class FocusTextField implements FocusListener {

  private final Font antes = new Font("Century Gothic", Font.ITALIC, 13);
  private final Font depois = new Font("Arial", Font.PLAIN, 13);

  private JTextField[] fields;
  private String[] strings;

  public void setField(JTextField... fields) {
    this.fields = fields;
  }

  public void setText(String... strings) {
    this.strings = strings;
  }

  @Override
  public void focusGained(FocusEvent e) {
    for (int i = 0; i < fields.length; i++) {
      if (e.getSource() == fields[i] && fields[i].getText().equals(strings[i])) {
        fields[i].setText("");
        fields[i].setFont(depois);
        fields[i].setForeground(Color.BLACK);
      }
    }
  }

  @Override
  public void focusLost(FocusEvent e) {
    for (int i = 0; i < fields.length; i++) {
      if (e.getSource() == fields[i] && fields[i].getText().equals("")) {
        fields[i].setText(strings[i]);
        fields[i].setFont(antes);
        fields[i].setForeground(Color.GRAY);
      }

    }
  }

}