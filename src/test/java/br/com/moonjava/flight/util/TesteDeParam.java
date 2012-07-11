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

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.testng.annotations.Test;

import br.com.moonjava.flight.util.Param;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
@Test
public class TesteDeParam {

  public void object_deve_ter_casting_de_integer() {
    boolean res = false;
    int object = 1;

    Param<?> param = Param.parseValue(object);
    Object value = param.getValue();

    if (value instanceof Integer) {
      res = true;
    }
    assertThat(res, equalTo(true));
  }

  public void object_deve_ter_casting_de_long() {
    boolean res = false;
    long object = 1l;

    Param<?> param = Param.parseValue(object);
    Object value = param.getValue();

    if (value instanceof Long) {
      res = true;
    }
    assertThat(res, equalTo(true));
  }

  public void object_deve_ter_casting_de_double() {
    boolean res = false;
    double object = 3.43;

    Param<?> param = Param.parseValue(object);
    Object value = param.getValue();

    if (value instanceof Double) {
      res = true;
    }
    assertThat(res, equalTo(true));
  }

  public void object_deve_ter_casting_de_String() {
    boolean res = false;
    String object = "valor";

    Param<?> param = Param.parseValue(object);
    Object value = param.getValue();

    if (value instanceof String) {
      res = true;
    }
    assertThat(res, equalTo(true));
  }

  public void object_deve_ter_casting_de_boolean() {
    boolean res = false;
    boolean object = true;

    Param<?> param = Param.parseValue(object);
    Object value = param.getValue();

    if (value instanceof Boolean) {
      res = true;
    }
    assertThat(res, equalTo(true));
  }

  public void object_deve_ter_casting_de_local_date() {
    boolean res = false;
    LocalDate object = new LocalDate(2012, 1, 1);

    Param<?> param = Param.parseValue(object);
    Object value = param.getValue();

    if (value instanceof LocalDate) {
      res = true;
    }
    assertThat(res, equalTo(true));
  }

  public void object_deve_ter_casting_de_date_time() {
    boolean res = false;
    DateTime object = new DateTime(2012, 1, 1, 4, 0, 0);

    Param<?> param = Param.parseValue(object);
    Object value = param.getValue();

    if (value instanceof DateTime) {
      res = true;
    }
    assertThat(res, equalTo(true));
  }

}