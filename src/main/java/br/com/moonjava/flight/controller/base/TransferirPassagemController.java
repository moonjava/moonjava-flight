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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import br.com.moonjava.flight.dao.base.PassagemDAO;
import br.com.moonjava.flight.dao.base.ReembolsoDAO;
import br.com.moonjava.flight.dao.base.VooDAO;
import br.com.moonjava.flight.model.base.Passagem;
import br.com.moonjava.flight.model.base.PassagemModel;
import br.com.moonjava.flight.model.base.Reembolso;
import br.com.moonjava.flight.model.base.Status;
import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.view.passagem.TransferirPassagemUI;

/**
 * @version 1.0 Aug 31, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class TransferirPassagemController extends TransferirPassagemUI {

  private List<Voo> list;
  private Passagem passagem;

  public TransferirPassagemController(JPanel conteudo, ResourceBundle bundle) {
    super(conteudo, bundle);

    addConsultarListener(new ConsultarHandler());
    addTransferirListener(new TransferirHandler());
    addItemTableSelectedListener(new ItemTableSelectedHandler());
  }

  public void setList(List<Voo> list) {
    this.list = list;
  }

  private class ItemTableSelectedHandler extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
      abilitarBotao();
    }

  }

  private class ConsultarHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      VooDAO vDao = new VooDAO();
      PassagemDAO pDao = new PassagemDAO();
      ReembolsoDAO rDao = new ReembolsoDAO();

      RequestParamWrapper request = getParametersPassagem();
      String codBilhete = request.stringParam("codBilhete");

      passagem = pDao.consultarPorCodigoBilhete(codBilhete);

      if (passagem == null) {
        messagePassagemOff();
        return;
      }

      Reembolso verifCancel = rDao.consultarPorPassagemId(passagem.getId());

      if (verifCancel != null) {
        messagemPasJaCancelada();
        return;
      }

      Status status = Status.DISPONIVEL;
      // DateTime agora = new DateTime().toDateTime();
      request.set("status", status);
      // request.set("partida", agora);

      List<Voo> voos = vDao.consultar(request);

      setList(voos);
      showList(voos);
      addVooTable();
    }
  }

  private class TransferirHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      int[] rows = getTable().getSelectedRows();

      if (rows.length == 1) {
        Voo pojo = list.get(rows[0]);

        if (pojo.getAssentoLivre() == 0) {
          messageFailed();
          return;

        } else {
          PassagemModel model = new PassagemModel();

          if (model.transferirPassagem(passagem, pojo)) {
            messageOK();
            return;
          } else {
            messageDbOff();
            return;
          }

        }
      } else {
        messageSelectFailed();
        return;
      }
    }
  }

}