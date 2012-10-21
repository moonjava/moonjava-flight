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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import br.com.moonjava.flight.model.base.Aeronave;
import br.com.moonjava.flight.model.base.AeronaveModel;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.view.aeronave.AtualizarAeronaveUI;

/**
 * @version 1.0 Aug 29, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class AtualizarAeronaveController extends AtualizarAeronaveUI {

  // Singleton
  private static final AtualizarAeronaveController ui = new AtualizarAeronaveController();
  private boolean result;
  private List<Aeronave> list;
  private JTable tabela;

  private Aeronave pojo;

  private AtualizarAeronaveController() {
  }

  public static AtualizarAeronaveController getInstance() {
    return ui;
  }

  public void setAttributes(JTable tabela,
                            JPanel subConteudo,
                            ResourceBundle bundle,
                            JButton atualizar,
                            JButton deletar) {
    this.tabela = tabela;
    setAttributes(subConteudo, bundle, atualizar, deletar);
    addAtualizarListener(new AtualizarHandler());
    addEnviarListener(new EnviarHandler());
  }

  public void setResult(boolean result) {
    this.result = result;
  }

  public void setList(List<Aeronave> list) {
    this.list = list;
  }

  private class AtualizarHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      disableButtons();

      // busca aeronave selecionada
      if (!result) {
        result = true;
        int[] rows = tabela.getSelectedRows();

        if (rows.length == 1) {
          pojo = list.get(rows[0]);
          refresh();
          showAll();
        } else {
          messageFailed();
          refresh();
        }

      }
    }
  }

  private class EnviarHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      RequestParamWrapper request = getParameters();
      request.set("id", pojo.getId());

      if (!request.stringParam("nome").isEmpty()) {
        Aeronave aeronave = new AeronaveUpdate(request).createInstance();
        new AeronaveModel().atualizar(aeronave);

        // Renomeia arquivo do mapa de assento conforme o nome
        File oldFile = new File("airplanes/" + pojo.getNome() + ".jpg");
        File newFile = new File("airplanes/" + request.stringParam("nome") + ".jpg");
        oldFile.renameTo(newFile);

        messageOK();
        refresh();
      } else {
        addMessageFailed();
      }
    }
  }

}