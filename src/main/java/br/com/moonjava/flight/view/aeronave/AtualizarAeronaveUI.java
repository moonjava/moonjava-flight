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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.moonjava.flight.util.JTextFieldLimit;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.1 Aug 16, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class AtualizarAeronaveUI {

  private JPanel conteudo;
  private ResourceBundle bundle;
  private JButton atualizar;
  private JButton deletar;
  private JLabel tituloNome;
  private JTextField nome;
  private JButton enviar;

  public void setAttributes(JPanel conteudo,
                            ResourceBundle bundle,
                            JButton atualizar,
                            JButton deletar) {
    this.conteudo = conteudo;
    this.bundle = bundle;
    this.atualizar = atualizar;
    this.deletar = deletar;

    mainMenu();
  }

  private void mainMenu() {
    tituloNome = new JLabel(bundle.getString("criar.aeronave.titulo.nome"));
    enviar = new JButton(bundle.getString("atualizar.aeronave.botao.atualizar"));
    nome = new JTextField();
    nome.setDocument(new JTextFieldLimit(40));

    tituloNome.setBounds(100, 70, 200, 30);
    enviar.setBounds(180, 110, 150, 30);
    nome.setBounds(180, 70, 300, 30);
  }

  public void addAtualizarListener(ActionListener a) {
    atualizar.addActionListener(a);
  }

  public void addEnviarListener(ActionListener a) {
    enviar.addActionListener(a);
  }

  public RequestParamWrapper getParameters() {
    RequestParamWrapper request = new RequestParamWrapper();
    request.set("nome", nome.getText());
    return request;
  }

  public void showAll() {
    conteudo.add(tituloNome);
    conteudo.add(nome);
    conteudo.add(enviar);

    conteudo.repaint();
    conteudo.validate();
  }

  protected void addMessageFailed() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("dadoincorreto"),
        "flight",
        JOptionPane.ERROR_MESSAGE);
  }
  
  public void messageFailed() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("atualizar.joption.err"),
        bundle.getString("atualizar.joption.titulo"),
        JOptionPane.ERROR_MESSAGE);
  }

  public void messageOK() {
    JOptionPane.showMessageDialog(null,
        bundle.getString("atualizar.joption.ok"),
        bundle.getString("atualizar.joption.titulo"),
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