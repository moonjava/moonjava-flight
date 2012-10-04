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
 * @version 1.0, Aug 10, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public enum Perfil {

  ATENDENTE("Atendente", "Attendant", "Asistente"),
  SUPERVISOR("Supervisor", "Supervisor", "Supervisor");

  private String br;
  private String us;
  private String es;

  private Perfil(String br, String us, String es) {
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

  public static Perfil fromString(String nome) {
    if (nome != null) {
      for (Perfil perfil : Perfil.values()) {
        if (nome.equals(perfil.br) || nome.equals(perfil.us) || nome.equals(perfil.es)) {
          return perfil;
        }
      }
    }
    return null;
  }

}