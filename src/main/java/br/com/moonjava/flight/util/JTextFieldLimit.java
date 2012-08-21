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

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * @version 1.0 Aug 17, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
@SuppressWarnings("serial")
public class JTextFieldLimit extends PlainDocument {

  private final int limit;
  private boolean toUppercase = false;

  public JTextFieldLimit(int limit) {
    this.limit = limit;
  }

  public JTextFieldLimit(int limit, boolean upper) {
    this.limit = limit;
    toUppercase = upper;
  }

  @Override
  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
    if ((getLength() + str.length()) <= limit) {
      if (toUppercase) {
        str = str.toUpperCase();
      }
      super.insertString(offset, str, attr);
    }
  }

}