package br.com.moonjava.flight.controller.base;

import java.util.List;
import java.util.ResourceBundle;

import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.model.base.VooModel;
import br.com.moonjava.flight.view.painel.PainelDeDecolagemUI;

public class PainelController extends PainelDeDecolagemUI {

  public PainelController(ResourceBundle bundle) {
    super(bundle);
    showAll();

    List<Voo> lista = new VooModel().consultaPainel();
    showList(lista);
  }

}