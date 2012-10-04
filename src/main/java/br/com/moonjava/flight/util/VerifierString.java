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

import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.IllegalFieldValueException;
import org.joda.time.LocalDate;

/**
 * @version 1.0 Sep 7, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class VerifierString {

  public static boolean containsSpace(String word) {
    Pattern pattern = Pattern.compile("\\s");
    Matcher matcher = pattern.matcher(word);
    return matcher.find();
  }

  public static boolean isBirthDay(String word, ResourceBundle bundle) {
    boolean birthDay = false;
    String country = bundle.getString("country");
    LocalDate date = FormatDateTime.parseToLocalDate(word, country);
    boolean leapYear = new GregorianCalendar().isLeapYear(date.getYear());
    LocalDate now = new LocalDate();

    // US MM/DD/YYYY
    if (country.equals("US")) {
      // Não pode conter 30 e 31 em fevereiro e nem ser maior que a data atual
      if (!word.startsWith("02/30") && !word.startsWith("02/31")
          && date.isBefore(now) || date.isEqual(now)) {
        // Se for bissexto deve considerar 02/29
        if (word.startsWith("02/29") && leapYear || !word.startsWith("02/29")) {
          Pattern pattern = Pattern
              .compile("(0[1-9]|1[012])/(0[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)");
          Matcher matcher = pattern.matcher(word);
          birthDay = matcher.find();
        }
      }

      // BR DD/MM/YYYY
    } else {
      if (!word.startsWith("30/02") && !word.startsWith("31/02")
          && date.isBefore(now) || date.isEqual(now)) {
        // Não pode conter 30 e 31 em fevereiro e nem ser maior que a data atual
        if (word.startsWith("29/02") && leapYear || !word.startsWith("29/02")) {
          // Se for bissexto deve considerar 29/02
          Pattern pattern = Pattern
              .compile("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/((19|20)\\d\\d)");
          Matcher matcher = pattern.matcher(word);
          birthDay = matcher.find();
        }
      }
    }

    return birthDay;
  }

  public static boolean isValidate(String word) {
    try {
      boolean validate = false;
      LocalDate now = new LocalDate();
      String year = word.substring(3, 7);
      String month = word.substring(0, 2);

      LocalDate date = new LocalDate()
          .withYear(Integer.parseInt(year))
          .withMonthOfYear(Integer.parseInt(month));

      if (date.isAfter(now) || date.isEqual(now)) {
        Pattern pattern = Pattern.compile("(0[1-9]|1[012])/((19|20)\\d\\d)");
        Matcher matcher = pattern.matcher(word);
        validate = matcher.find();
      }

      return validate;
    } catch (IllegalFieldValueException e) {
      return false;
    }
  }

}