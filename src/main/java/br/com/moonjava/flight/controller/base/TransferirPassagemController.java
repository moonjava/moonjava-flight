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

import br.com.moonjava.flight.model.base.Passagem;
import br.com.moonjava.flight.model.base.PassagemModel;
import br.com.moonjava.flight.model.base.Status;
import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.model.base.VooModel;
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
      habilitarBotao();
    }
  }

  private class ConsultarHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      VooModel vooModel = new VooModel();
      PassagemModel passagemModel = new PassagemModel();

      RequestParamWrapper request = getParametersPassagem();
      String codBilhete = request.stringParam("codBilhete");

      passagem = passagemModel.consultarPorCodigoBilhete(codBilhete);

      if (passagem == null) {
        messagePassagemOff();
        return;
      }

      String verifCancel = passagem.getVoo().getCodigo();

      if (verifCancel == null) {
        messagemPasJaCancelada();
        refresh();
        return;
      }

      Status status = Status.DISPONIVEL;
      request.set("status", status);
      request.set("assento", 0);

      List<Voo> voos = vooModel.consultar(request);

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
        Voo voo = list.get(rows[0]);

        if (voo.getAssentoLivre() == 0) {
          messageFailed();
          return;

        } else {
          PassagemModel model = new PassagemModel();
          RequestParamWrapper request = new RequestParamWrapper();

          request.set("id", passagem.getId());
          request.set("voo", voo.getId());

          Passagem pojo = new PassagemUpdate(request).createInstance();
          boolean updated = model.transferir(pojo);

          if (updated) {
            VooModel vooModel = new VooModel();
            vooModel.incrementarAssento(passagem.getVoo().getId());
            vooModel.decrementarAssento(voo.getId());
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