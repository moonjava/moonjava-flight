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
import java.awt.event.FocusEvent;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import br.com.moonjava.flight.model.base.Passagem;
import br.com.moonjava.flight.model.base.PassagemModel;
import br.com.moonjava.flight.model.base.Reembolso;
import br.com.moonjava.flight.model.base.ReembolsoModel;
import br.com.moonjava.flight.model.base.VooModel;
import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.FlightFocusLostListener;
import br.com.moonjava.flight.util.FocusTextField;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.util.VerifierString;
import br.com.moonjava.flight.view.passagem.CancelarPassagemUI;

/**
 * @version 1.0 Aug 31, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class CancelarPassagemController extends CancelarPassagemUI {

  private Passagem passagem;

  public CancelarPassagemController(JPanel conteudo, ResourceBundle bundle) {
    super(conteudo, bundle);

    addFocusListener(new FocusTextField());
    addFocusBancoListener(new FocusBancoHandler());
    addFocusAgenciaListener(new FocusAgenciaHandler());
    addFocusContaListener(new FocusContaHandler());
    addFocusCpfListener(new FocusCpfHandler());
    addSolicitarCancelamentoListener(new SolicitarCancelamentoHandler());
    addEfetuarCancelamentoListener(new EfetuarCancelamentoHandler());
  }

  /*
   * As InnerClasses de focus mudam os textos nos campos quando necessário 
   * 
   */
  private class FocusBancoHandler extends FlightFocusLostListener {

    @Override
    public void focusLost(FocusEvent e) {
      String banco = getParametersReebolso().stringParam("banco");
      String defaultText = getDefaultTexts().stringParam("banco");

      if (!banco.isEmpty() && !banco.equals(defaultText)) {

        try {
          int num = Integer.parseInt(banco);
          if (num <= 0) {
            throw new NumberFormatException();
          }
          removeImageBancoParseException();
        } catch (NumberFormatException e2) {
          addImageBancoParseException();
        }

      }
    }
  }

  private class FocusAgenciaHandler extends FlightFocusLostListener {

    @Override
    public void focusLost(FocusEvent e) {
      String agencia = getParametersReebolso().stringParam("agencia");
      String defaultText = getDefaultTexts().stringParam("agencia");

      if (!agencia.isEmpty() && !agencia.equals(defaultText)) {

        try {
          int num = Integer.parseInt(agencia);
          if (num <= 0) {
            throw new NumberFormatException();
          }
          removeImageAgenciaParseException();
        } catch (NumberFormatException e2) {
          addImageAgenciaParseException();
        }

      }
    }
  }

  private class FocusContaHandler extends FlightFocusLostListener {

    @Override
    public void focusLost(FocusEvent e) {
      String conta = getParametersReebolso().stringParam("conta");
      String defaultText = getDefaultTexts().stringParam("conta");

      if (!conta.isEmpty() && !conta.equals(defaultText)) {

        try {
          int num = Integer.parseInt(conta);
          if (num <= 0) {
            throw new NumberFormatException();
          }
          removeImageContaParseException();
        } catch (NumberFormatException e2) {
          addImageContaParseException();
        }

      }
    }
  }

  private class FocusCpfHandler extends FlightFocusLostListener {

    @Override
    public void focusLost(FocusEvent e) {
      String cpf = getParametersReebolso().stringParam("cpf");
      if (!VerifierString.containsSpace(cpf)) {
        try {
          CPF.parse(cpf);
          addImageCpfValido();
        } catch (Exception e1) {
          addImageCpfInvalido();
        }
      }
    }
  }

  private class SolicitarCancelamentoHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      PassagemModel passagemModel = new PassagemModel();

      RequestParamWrapper request = getParametersPassagem();
      String codBilhete = request.stringParam("codBilhete");

      passagem = passagemModel.consultarPorCodigoBilhete(codBilhete);

      if (passagem == null) {
        messagePassagemOff();
        return;
      }

      String verifCancel = passagem.getVoo().getCodigo();

      if (verifCancel != null) {
        PassagemModel pasModel = new PassagemModel();
        double reembolso = pasModel.getPreco(passagem);

        if (reembolso > 0.0) {
          setValor(reembolso, passagem.getId());
          addCalcularPassagemButton();
        } else if (reembolso == 0.0) {
          messageReebolsoZero();
        } else {
          messageVooRealizado();
        }
      } else {
        messagemPasJaCancelada();
      }

    }
  }

  private class EfetuarCancelamentoHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      RequestParamWrapper request = getParametersReebolso();
      // Caso o usuario adicione virgula, o sistema atribuirá ponto
      // para cadastrar o dado no banco de dados
      ReembolsoModel model = new ReembolsoModel();
      PassagemModel modelPassagem = new PassagemModel();
      String valor = request.stringParam("valor").replace(",", ".");
      boolean status = false;

      if (!valor.equals("0.0")) {
        CPF _cpf = null;
        try {
          _cpf = CPF.parse(request.stringParam("cpf"));
          request.set("passagem", passagem.getId());
          request.set("banco", Integer.parseInt(request.stringParam("banco")));
          request.set("agencia", Integer.parseInt(request.stringParam("agencia")));
          request.set("conta", Integer.parseInt(request.stringParam("conta")));
          request.set("valor", Double.parseDouble(valor));
          request.set("cpf", _cpf.getDigito());
          Reembolso reembolso = new ReembolsoCreate(request).createInstance();

          status = model.criar(reembolso);
        } catch (Exception e2) {
          return;
        }
      }

      status = modelPassagem.efetuarCancelamento(passagem);

      if (status) {
        new VooModel().incrementarAssento(passagem.getVoo().getId());
        messageReembolso();
        messageOK();
      } else {
        messageDbOff();
      }
    }
  }

}