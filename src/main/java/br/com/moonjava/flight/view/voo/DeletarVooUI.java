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
package br.com.moonjava.flight.view.voo;

import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 * @version 1.0 Aug 21, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class DeletarVooUI {

  private JPanel conteudo;
  protected ResourceBundle bundle;
  private JButton atualizar;
  private JButton deletar;
  private JButton status;

  public void setAttributes(JTable tabela, JPanel conteudo,
                            ResourceBundle bundle,
                            JButton atualizar,
                            JButton deletar,
                            JButton status) {
    this.conteudo = conteudo;
    this.bundle = bundle;
    this.atualizar = atualizar;
    this.deletar = deletar;
    this.status = status;
  }

  public void addDeletarListener(ActionListener a) {
    deletar.addActionListener(a);
  }

  public void messageDeleteOK() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("deletar.voo.joption.ok"),
        bundle.getString("criar.voo.joption.titulo"),
        JOptionPane.INFORMATION_MESSAGE);
  }

  public void disableButtons() {
    atualizar.setEnabled(false);
    deletar.setEnabled(false);
    status.setEnabled(false);
  }

  public void refresh() {
    conteudo.removeAll();
    conteudo.repaint();
    conteudo.validate();
  }

}