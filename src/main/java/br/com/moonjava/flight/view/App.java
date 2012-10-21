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
package br.com.moonjava.flight.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.moonjava.flight.controller.base.IdiomaController;

/**
 * @version 1.0 Aug 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class App {

  public static void main(String[] args) {
    try {

      // Configura último 'Look and Feel' da Sun (Nimbus)
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }

    try {
      File folder = new File("Log");
      // Cria uma pasta no computador caso nao exista para salvar os LOGs
      // diários do sistema
      if (!folder.exists()) {
        folder.mkdir();
        folder.setWritable(true, true);
      }
      String fileName = "Log/log-" + new LocalDate() + ".txt";
      boolean append = true;
      PrintStream printStream = new PrintStream(new FileOutputStream(fileName, append));
      // Adiciona log INFO, WARN e ERROR
      System.setOut(printStream);
      System.setErr(printStream);
    } catch (IOException e) {
      System.out.println("Error during reading/writing");
    }

    final Logger logger = LoggerFactory.getLogger(App.class);
    logger.info("Starting Flight...");
    start();
  }

  private static void start() {
    new IdiomaController();
  }

}