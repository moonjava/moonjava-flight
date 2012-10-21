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
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import br.com.moonjava.flight.model.base.Passagem;
import br.com.moonjava.flight.model.base.PassagemModel;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.view.checkin.EfetuarCheckinUI;

/**
 * @version 1.0 Sep 8, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class EfetuarCheckinController extends EfetuarCheckinUI {

  private List<Passagem> passagens;
  private Passagem pojo;

  public EfetuarCheckinController(JPanel conteudo, ResourceBundle bundle) {
    super(conteudo, bundle);

    addConsultarListener(new ConsultarHandler());
    addAlocarAssentoListener(new AlocarAssentoHandler());
    addFinalizarCheckinListener(new FinalizarCheckinHandler());
  }

  private class ConsultarHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      RequestParamWrapper request = getParameters();
      String bilhete = request.stringParam("bilhete");
      // Carrega imagem do mapa de assento de acordo
      // com o passageiro
      pojo = new PassagemModel().consultarPorCodigoBilhete(bilhete);
      if (pojo != null) {
        String pathFile = String.format("airplanes/%s.jpg", pojo.getVoo().getAeronave().getNome());
        showSeatMap(pathFile);
        passagens = new PassagemModel().consultarPorVoo(pojo.getVoo());
        showList(passagens);
      } else {
        messageSolicitacaoErro();
      }
    }
  }

  private class AlocarAssentoHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String assento = getParameters().stringParam("assento").toUpperCase();
      // Verifica se o assento já existe
      boolean assentoExist = false;
      for (Passagem passagem : passagens) {
        if (passagem.getAssento() != null) {
          if (passagem.getAssento().toUpperCase().equals(assento)) {
            assentoExist = true;
          }
        }
      }
      if (!assentoExist) {
        messageAssentoOK();
      } else {
        messageAssentoFailed();
      }
    }
  }

  private class FinalizarCheckinHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String assento = getParameters().stringParam("assento").toUpperCase();
      new PassagemModel().efetuarCheckin(pojo, assento);
      messageOK();
    }
  }

}