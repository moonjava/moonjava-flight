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

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
// Calculo para Construir CPFs válidos
public class CPF {

  private static final int[] CONSTANTE1 = {
      10,
      9,
      8,
      7,
      6,
      5,
      4,
      3,
      2 };

  private static final int[] CONSTANTE2 = {
      11,
      10,
      9,
      8,
      7,
      6,
      5,
      4,
      3,
      2 };

  private final long digito;

  public CPF(long digito) {
    this.digito = digito;
  }

  public long getDigito() {
    return digito;
  }

  @Override
  public String toString() {
    return String.valueOf(digito);
  }

  public static CPF valueOf(long digito) {
    if (isValido(digito)) {
      return new CPF(digito);
    } else {
      throw new CPFInvalidException(String.valueOf(digito));
    }
  }

  public static CPF parse(String val) {
    try {
      String somenteNumeros = val.replaceAll("[^\\d]", "");
      long longValue = Long.parseLong(somenteNumeros);
      return CPF.valueOf(longValue);
    } catch (NumberFormatException e) {
      throw new CPFInvalidException(val);
    }

  }

  private static boolean isValido(long digito) {
    int[] cpf = new int[11];
    int[] array = new int[11];

    for (int i = 10; i >= 0; i--) {
      array[i] = (int) (digito % 10);
      cpf[i] = (int) (digito % 10);
      digito = digito / 10;
    }

    int primeiroDigito = gerarDigito(CONSTANTE1, array, 0);
    int segundoDigito = gerarDigito(CONSTANTE2, array, 1);
    return cpf[9] == primeiroDigito && cpf[10] == segundoDigito;
  }

  private static int gerarDigito(int[] constante, int[] array, int d) {
    int novoDigito = 0;
    for (int i = 0; i < 9 + d; i++) {
      novoDigito += array[i] * constante[i];
    }
    int digito = novoDigito % 11 < 2 ? 0 : 11 - (novoDigito % 11);
    array[9 + d] = digito;
    return digito;
  }

}