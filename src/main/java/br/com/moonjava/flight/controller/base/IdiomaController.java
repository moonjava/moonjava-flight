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
package br.com.moonjava.flight.controller.base;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import br.com.moonjava.flight.view.IdiomaUI;

/**
 * @version 1.0 Sep 8, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class IdiomaController extends IdiomaUI {

  private ResourceBundle bundle;

  public IdiomaController() {
    addPortuguesListener(new IdiomaHandler());
    addInglesListener(new IdiomaHandler());
    addEspanholListener(new IdiomaHandler());
  }

  private class IdiomaHandler extends MouseAdapter {

    // Verifica qual idioma será carregado
    @Override
    public void mouseClicked(MouseEvent e) {
      if (e.getSource() == getPortugues()) {
        bundle = ResourceBundle.getBundle("idioma/arquivo_pt_BR", new ResourceControl());
      }
      if (e.getSource() == getIngles()) {
        bundle = ResourceBundle.getBundle("idioma/arquivo_en_US", new ResourceControl());
      }
      if (e.getSource() == getEspanhol()) {
        bundle = ResourceBundle.getBundle("idioma/arquivo_es_ES", new ResourceControl());
      }
      // Finaliza Frame de idiomas e inicia Login
      getIdioma().dispose();
      new LoginController(bundle);
    }

    // Mudando o cursor para 'Hands' ao entrar nas imagens
    @Override
    public void mouseEntered(MouseEvent e) {
      getPortugues().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      getIngles().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      getEspanhol().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

  }

  /*
   * Classe que define o padrão UTF-8 aos arquivos 'properties'
   * 
   */
  private static class ResourceControl extends ResourceBundle.Control {
    @Override
    public ResourceBundle newBundle(String baseName, Locale locale,
                                    String format, ClassLoader loader, boolean reload)
        throws IllegalAccessException, InstantiationException, IOException {
      String bundlename = toBundleName(baseName, locale);
      String resName = toResourceName(bundlename, "properties");
      InputStream stream = loader.getResourceAsStream(resName);
      return new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
    }
  }

}