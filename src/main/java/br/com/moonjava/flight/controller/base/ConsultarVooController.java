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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;

import org.joda.time.DateTime;

import br.com.moonjava.flight.model.base.Status;
import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.model.base.VooModel;
import br.com.moonjava.flight.util.FormatDateTime;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.view.voo.ConsultarVooUI;

/**
 * @version 1.0 Aug 29, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class ConsultarVooController extends ConsultarVooUI {

  private final JPanel conteudo;
  private final ResourceBundle bundle;
  private JButton atualizar;
  private JButton deletar;
  private JButton status;

  private List<Voo> list;
  private final List<Voo> voos = new ArrayList<Voo>();

  public ConsultarVooController(JPanel conteudo,
                                ResourceBundle bundle,
                                JButton atualizar,
                                JButton deletar,
                                JButton status) {
    super(conteudo, bundle, atualizar, deletar, status);
    this.conteudo = conteudo;
    this.bundle = bundle;
    this.atualizar = atualizar;
    this.deletar = deletar;
    this.status = status;

    addConsultarListener(new ConsultarHandler());
    addItemTableSelectedListener(new ItemTableSelectedHandler());
    addVenderPassagemListener(new VenderPassagemHandler());
  }

  /*
   * Construtor apenas para consultar voo vinda de vender passagem
   */
  public ConsultarVooController(JPanel conteudo, ResourceBundle bundle) {
    super(conteudo, bundle, true);

    this.conteudo = conteudo;
    this.bundle = bundle;
    addConsultarListener(new ConsultarHandler());
    addItemTableSelectedListener(new ItemTableSelectedPassagemHandler());
    addVenderPassagemListener(new VenderPassagemHandler());
  }

  private class ConsultarHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      RequestParamWrapper request = getParameters();
      String partida = request.stringParam("partida");
      String chegada = request.stringParam("chegada");

      String country = getCountry();
      String _partida = null;
      String _chegada = null;

      // Teste para atribuir o padrão AM e PM
      if (country.equals("US")) {
        String timePartida = request.stringParam("timePartida");
        String timeChegada = request.stringParam("timeChegada");

        _partida = String.format("%s %s", partida, timePartida);
        _chegada = String.format("%s %s", chegada, timeChegada);
      } else {
        _partida = partida;
        _chegada = chegada;
      }

      int index = request.intParam("status");
      Status[] values = Status.values();
      Status _status = values[index];

      // Mascara padrão para a data
      String maskEmpty = "  /  /       :  ";
      DateTime dataPartida = null;
      DateTime dataChegada = null;

      if (!_partida.startsWith(maskEmpty)) {
        dataPartida = FormatDateTime.parseToDateTime(_partida, country);
      }

      if (!_chegada.startsWith(maskEmpty)) {
        dataChegada = FormatDateTime.parseToDateTime(_chegada, country);
      }

      request.set("partida", dataPartida);
      request.set("chegada", dataChegada);
      request.set("status", _status);

      // Exibe os vôos
      list = new VooModel().consultar(request);
      boolean isEmpty = showList(list);

      if (isEmpty) {
        messageFailed();
      } else {
        repaint();
      }
    }
  }

  private class ItemTableSelectedHandler extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
      enableButtons();
      JTable tabela = getTable();

      // Os controladores de Deletar, Atualizar e Controlar Status
      // são objetos Singleton pois a cada evento (mouseClicked)
      // um objeto seria instanciado, ocasionando a ação
      // dos JOptionPane (definidos nestas classes) mais de uma vez.
      // Ex.: Deletar mais de um item sem um Singleton executará a JOptionPane
      // em questão a mesma quantidade de clicks (isso porque o HashCode de cada
      // objeto é diferente). Com Singleton, isso não ocorrerá porque há somente
      // um HashCode, logo, há somente um JOptionPane.
      DeletarVooController delete = DeletarVooController.getInstance();
      delete.setAttributes(tabela, conteudo, bundle, atualizar, deletar, status);
      delete.setResult(false);
      delete.setList(list);

      AtualizarVooController atualiza = AtualizarVooController.getInstance();
      atualiza.setAttributes(tabela, conteudo, bundle, atualizar, deletar, status);
      atualiza.setResult(false);
      atualiza.setList(list);

      ControlarStatusController ctrlStatus = ControlarStatusController.getInstance();
      ctrlStatus.setAttributes(tabela, list, conteudo, bundle);
      ctrlStatus.setResult(false);
      status.addActionListener(ctrlStatus);
    }
  }

  /*
   * Usado somente na tela de vender passagem 
   * 
   */
  private class ItemTableSelectedPassagemHandler extends MouseAdapter {

    private boolean flag;

    @Override
    public void mouseClicked(MouseEvent e) {
      if (e.getClickCount() == 2) {
        int[] rows = getTable().getSelectedRows();
        if (rows.length == 1) {
          voos.add(list.get(rows[0]));
          // Verifica se o voo é de ida e volta
          int res = 0;
          if (!flag) {
            res = messagePassagemIdaVolta();
          }
          if (res == 2) {
            addVoo();
          }
          if (res != 2 && flag) {
            addVoo();
          }
          flag = true;
        } else {
          messageSelectFailed();
        }
      }
    }

    // Substitui os nomes padrões dos OptionPane
    private void addVoo() {
      new VenderPassagemController(conteudo, bundle, voos);
      UIManager.put("OptionPane.okButtonText", "Ok");
      UIManager.put("OptionPane.cancelButtonText", "Cancel");
    }

  }

  private class VenderPassagemHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      // busca voo selecionada
      int[] rows = getTable().getSelectedRows();
      if (rows.length == 1) {
        voos.add(list.get(rows[0]));
        new VenderPassagemController(conteudo, bundle, voos);
      } else {
        messageSelectFailed();
      }
    }
  }

}