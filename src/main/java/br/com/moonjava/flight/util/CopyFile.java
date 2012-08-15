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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JOptionPane;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class CopyFile {

  public static boolean copyfile(String srFile, String dtFile) {
    boolean res = false;
    try {
      File f1 = new File(srFile);
      File f2 = new File(dtFile);

      InputStream in = new FileInputStream(f1);
      OutputStream out = new FileOutputStream(f2);

      byte[] buf = new byte[1024];
      int len;
      while ((len = in.read(buf)) > 0) {
        out.write(buf, 0, len);
      }
      in.close();
      out.close();

      res = true;

    } catch (FileNotFoundException ex) {
      JOptionPane.showMessageDialog(
          null, "Arquivo não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
          null, "Arquivo não suportado", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    return res;
  }

}