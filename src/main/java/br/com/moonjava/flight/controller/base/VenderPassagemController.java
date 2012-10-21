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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.joda.time.LocalDate;

import br.com.moonjava.flight.controller.financeiro.CartaoController;
import br.com.moonjava.flight.controller.financeiro.ChequeController;
import br.com.moonjava.flight.model.base.Passagem;
import br.com.moonjava.flight.model.base.PassagemModel;
import br.com.moonjava.flight.model.base.PessoaFisica;
import br.com.moonjava.flight.model.base.PessoaFisicaModel;
import br.com.moonjava.flight.model.base.Tipo;
import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.model.base.VooModel;
import br.com.moonjava.flight.util.CPF;
import br.com.moonjava.flight.util.CPFInvalidException;
import br.com.moonjava.flight.util.FlightFocusLostListener;
import br.com.moonjava.flight.util.FocusTextField;
import br.com.moonjava.flight.util.FormatDateTime;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.util.VerifierString;
import br.com.moonjava.flight.view.passagem.PrintFileToPrinter;
import br.com.moonjava.flight.view.passagem.VenderPassagemUI;

/**
 * @version 1.0 Aug 31, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class VenderPassagemController extends VenderPassagemUI {

  private Voo voo;
  private List<Voo> voos;
  private final List<PessoaFisica> pessoas;
  private final List<String> codigos;

  public VenderPassagemController(JPanel conteudo, ResourceBundle bundle) {
    super(conteudo, bundle);

    pessoas = new ArrayList<PessoaFisica>();
    codigos = new ArrayList<String>();
    // add listeners
    addFocusListener(new FocusTextField());
    addFocusTelResidencialListener(new FocusTelResidencialHandler());
    addFocusTelCelularListener(new FocusTelCelularHandler());
    addFocusCpfListener(new FocusCpfHandler());
    addFocusDataDeNascimentoListener(new FocusDataDeNascimentoHandler());
    addPagamentoChangeListener(new PagamentoChangeHandler());
    addChangeQuantidadeListener(new QuantidadeChangeHandler());
    addQuantidadeOKListener(new QuantidadeOKHandler());
    addSolicitarCompraListener(new SolicitarCompraHandler());
    addConcluirListener(new ConcluirHandler());
  }

  public VenderPassagemController(JPanel conteudo, ResourceBundle bundle, Voo voo) {
    this(conteudo, bundle);
    this.voo = voo;
  }

  public VenderPassagemController(JPanel conteudo, ResourceBundle bundle, List<Voo> voos) {
    this(conteudo, bundle);
    this.voos = voos;
  }

  private class FocusTelResidencialHandler extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      String tel = getParametersPF().stringParam("telResidencial");
      String defaultText = getDefaultTexts().stringParam("telResidencial");

      if (!tel.isEmpty() && !tel.equals(defaultText)) {

        try {
          int num = Integer.parseInt(tel);
          if (num <= 0) {
            throw new NumberFormatException();
          }
          removeImageTelResidencialParseException();
        } catch (NumberFormatException e2) {
          addImageTelResidencialParseException();
        }

      }
    }
  }

  private class FocusTelCelularHandler extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      String tel = getParametersPF().stringParam("telCelular");
      String defaultText = getDefaultTexts().stringParam("telCelular");

      if (!tel.isEmpty() && !tel.equals(defaultText)) {

        try {
          int num = Integer.parseInt(tel);
          if (num <= 0) {
            throw new NumberFormatException();
          }
          removeImageTelCelularParseException();
        } catch (NumberFormatException e2) {
          addImageTelCelularParseException();
        }

      }
    }
  }

  private class FocusCpfHandler extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      String cpf = getParametersPF().stringParam("cpf");
      if (!VerifierString.containsSpace(cpf)) {
        try {
          CPF.parse(cpf);
          addImageCpfValido();
        } catch (CPFInvalidException e1) {
          addImageCpfInvalido();
        }
      }
    }
  }

  private class FocusDataDeNascimentoHandler extends FlightFocusLostListener {
    @Override
    public void focusLost(FocusEvent e) {
      String dataDeNascimento = getParametersPF().stringParam("nascimento");
      if (!VerifierString.containsSpace(dataDeNascimento)) {
        if (VerifierString.isBirthDay(dataDeNascimento, bundle)) {
          addImageBirthDayValid();
        } else {
          addImageBirthDayInvalid();
        }
      }
    }
  }

  private class PagamentoChangeHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      RequestParamWrapper request = getParametersPF();
      int pagamento = request.intParam("pagamentoIndex");

      if (pagamento == 1) {
        ChequeController chequeController = new ChequeController(bundle, getValorTotal());
        if (chequeController.isParemeterValid()) {
          addConcluirButton();
        }
      } else {
        CartaoController cartaoController = new CartaoController(bundle, getValorTotal());
        if (cartaoController.isParameterValid()) {
          addConcluirButton();
        }
      }

    }
  }

  private class QuantidadeChangeHandler implements KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
      try {
        int qtd = Integer.parseInt(getQuantidade().getText());
        addComboBoxTipo(qtd);
      } catch (Exception e2) {
      }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
  }

  private class QuantidadeOKHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      int qtd = Integer.parseInt(getQuantidade().getText());
      int disponivel = voos.get(0).getAssentoLivre();
      if (qtd <= disponivel) {

        double valorTotal = 0;

        for (int j = 0; j < voos.size(); j++) {
          voo = voos.get(j);
          List<JComboBox> tipos = getTipos();
          for (int i = 0; i < tipos.size(); i++) {
            String item = (String) tipos.get(i).getSelectedItem();
            Tipo tipo = Tipo.fromString(item);

            if (tipo == Tipo.CRIANCA) {
              double taxa = 0.07 * voo.getPreco();
              valorTotal += 0.7 * voo.getPreco() + taxa;
            }
            if (tipo == Tipo.ADULTO) {
              double taxa = 0.07 * voo.getPreco();
              valorTotal += voo.getPreco() + taxa;
            }
          }
        }

        setValorTotal(valorTotal);
        addSolicitarCompraButton();

      } else {
        messageFailedQtd(disponivel);
      }
    }
  }

  private class SolicitarCompraHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      addForm(getTipos().get(getTipos().size() - 1));
    }
  }

  private class ConcluirHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      RequestParamWrapper request = getParametersPF();

      String nascimento = request.stringParam("nascimento");
      LocalDate date = FormatDateTime.parseToLocalDate(nascimento, bundle.getString("country"));
      long _cpf = CPF.parse(request.stringParam("cpf")).getDigito();
      int telResidencial = Integer.parseInt(request.stringParam("telResidencial"));
      int telCelular = Integer.parseInt(request.stringParam("telCelular"));
      request.set("nascimento", date);
      request.set("cpf", _cpf);
      request.set("telResidencial", telResidencial);
      request.set("telCelular", telCelular);

      if (!getTipos().isEmpty()) {
        PessoaFisica pojoPF = new PessoaFisicaCreate(request).createInstance();
        boolean created = new PessoaFisicaModel().criar(pojoPF);

        if (created) {
          PessoaFisica pf = new PessoaFisicaModel().consultarPorCPF(pojoPF.getCpf());
          pessoas.add(pf);
          codigos.add(request.stringParam("codigo"));

          removeForm();
        }

      }

      if (!getTipos().isEmpty()) {
        addForm(getTipos().get(getTipos().size() - 1));
      }

      if (getTipos().isEmpty()) {
        RequestParamWrapper req = new RequestParamWrapper();
        for (int i = 0; i < pessoas.size(); i++) {
          int vooId = voos.get(0).getId();
          req.set("voo", vooId);
          req.set("codBilhete", codigos.get(i));
          req.set("pessoaFisica", pessoas.get(i).getId());
          Passagem pojo = new PassagemCreate(req).createInstance();
          new PassagemModel().vender(pojo);
          new VooModel().decrementarAssento(vooId);
        }

        /** Location of a file to print **/
        String fileName = "abc.txt";

        /** Read the text content from this location **/
        String mText = PrintFileToPrinter.readContentFromFileToPrint(fileName);

        /** Create an AttributedString object from the text read */
        PrintFileToPrinter.myStyledText = new AttributedString(mText);

        PrintFileToPrinter.printToPrinter();
        refresh();
      }
    }
  }

}