package br.com.moonjava.flight.controller.base;

import java.util.List;
import java.util.ResourceBundle;

import br.com.moonjava.flight.dao.base.VooDAO;
import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.view.painel.PainelDeDecolagemUI;

public class PainelController extends PainelDeDecolagemUI {

  public PainelController(ResourceBundle bundle) {
    super(bundle);
    painelHandler();
    showAll();
  }

  public void painelHandler() {
    VooDAO dao = new VooDAO();
    List<Voo> lista = dao.consultaPainel();
    showList(lista);
  }

}