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
package br.com.moonjava.flight.view.aeronave;

import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * @version 1.0 Aug 29, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class DeletarAeronaveUI {

  private JPanel conteudo;
  private ResourceBundle bundle;
  private JButton atualizar;
  private JButton deletar;

  public void setAttributes(JPanel conteudo,
                            ResourceBundle bundle,
                            JButton atualizar,
                            JButton deletar) {
    this.conteudo = conteudo;
    this.bundle = bundle;
    this.atualizar = atualizar;
    this.deletar = deletar;
  }

  public void addDeletarListener(ActionListener a) {
    deletar.addActionListener(a);
  }

  public int messageDeleteAll() {
    UIManager.put("OptionPane.cancelButtonText", bundle.getString("cancelar"));
    return JOptionPane.showConfirmDialog(null,
        bundle.getString("deletar.joption.msg"),
        bundle.getString("deletar.joption.titulo"),
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.INFORMATION_MESSAGE);
  }

  public void messageDeleteOK() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("deletar.joption.ok"),
        bundle.getString("deletar.joption.titulo"),
        JOptionPane.INFORMATION_MESSAGE);
  }

  public void disableButtons() {
    atualizar.setEnabled(false);
    deletar.setEnabled(false);
  }

  public void refresh() {
    conteudo.removeAll();
    conteudo.repaint();
    conteudo.validate();
  }

}