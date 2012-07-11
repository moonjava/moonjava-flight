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

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class Param<T> {

  private T value;

  public Param(T value) {
    this.value = value;
  }

  public static Param<?> parseValue(Object object) {
    Param<?> val = null;

    if (object instanceof Integer) {
      val = new Param<Integer>((Integer) object);
    }

    if (object instanceof Long) {
      val = new Param<Long>((Long) object);
    }

    if (object instanceof Double) {
      val = new Param<Double>((Double) object);
    }

    if (object instanceof String) {
      val = new Param<String>((String) object);
    }

    if (object instanceof Boolean) {
      val = new Param<Boolean>((Boolean) object);
    }

    if (object instanceof LocalDate) {
      val = new Param<LocalDate>((LocalDate) object);
    }

    if (object instanceof DateTime) {
      val = new Param<DateTime>((DateTime) object);
    }

    return val;
  }

  public T getValue() {
    return value;
  }

}