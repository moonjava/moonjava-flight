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

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @version 1.0 Sep 7, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
// Este classe deixa as view dinâmicas,
// inserindo imagens e removendo-as a cada focusLost
public class FlightImageUI {

  public static void add(JLabel imagem,
                         JLabel alerta,
                         String mensagem,
                         ResourceBundle bundle,
                         JPanel conteudo) {
    try {
      InputStream stream = FlightImageUI.class.getResourceAsStream("/img/icon_disponivel.png");
      Image image = ImageIO.read(stream);
      ImageIcon icon = new ImageIcon(image);
      imagem.setIcon(icon);

      alerta.setFont(new Font("Arial", Font.BOLD, 13));
      alerta.setForeground(Color.BLACK);
      alerta.setText(mensagem);

      conteudo.add(imagem);
      conteudo.add(alerta);

    } catch (IOException e) {
      ErrorSystem.addException(e, bundle);
    }
  }

  public static void addError(JLabel imagem,
                              JLabel alerta,
                              String mensagem,
                              ResourceBundle bundle,
                              JPanel conteudo) {
    try {
      InputStream stream = FlightImageUI.class.getResourceAsStream("/img/icon_indisponivel.png");
      Image image = ImageIO.read(stream);
      ImageIcon icon = new ImageIcon(image);
      imagem.setIcon(icon);

      alerta.setFont(new Font("Arial", Font.BOLD, 13));
      alerta.setForeground(Color.RED);
      alerta.setText(mensagem);

      conteudo.add(imagem);
      conteudo.add(alerta);

    } catch (IOException e) {
      ErrorSystem.addException(e, bundle);
    }
  }

}