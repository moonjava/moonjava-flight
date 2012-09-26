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
package br.com.moonjava.flight.model.base;

import java.util.ResourceBundle;

/**
 * @version 1.0 Aug 31, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public enum FormaDeTratamento {

  SR("Sr.", "Mr.", "Sr."),
  SRA("Sra.", "Mrs.", "Sra."),
  SRTA("Srta.", "Miss", "Miss.");

  private String br;
  private String us;
  private String es;

  private FormaDeTratamento(String br, String us, String es) {
    this.br = br;
    this.us = us;
    this.es = es;
  }

  public String setBundle(ResourceBundle bundle) {
    String country = bundle.getString("country");
    String res = null;
    if (country.equals("ES")) {
      res = es;
    }
    else if (country.equals("US")) {
      res = us;
    }
    else {
      res = br;
    }
    return res;
  }

  public static FormaDeTratamento fromString(String nome) {
    if (nome != null) {
      for (FormaDeTratamento tratamento : FormaDeTratamento.values()) {
        if (nome.equals(tratamento.br) || nome.equals(tratamento.us) || nome.equals(tratamento.es)) {
          return tratamento;
        }
      }
    }
    return null;
  }

}