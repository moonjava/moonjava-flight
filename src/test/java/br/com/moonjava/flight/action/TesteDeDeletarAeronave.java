package br.com.moonjava.flight.action;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.util.RequestParamWrapper;

@Test
public class TesteDeDeletarAeronave {

  @BeforeClass
  public void beforeClass() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void deletar() {
    VooAction action = new VooAction();
    AeronaveAction actionAero = new AeronaveAction();
    RequestParamWrapper request = new RequestParamWrapper();

    request.set("status", Status.DISPONIVEL);

    int id = 4;

    List<Voo> antes = action.consultar(request);
    assertThat(antes.size(), equalTo(3));

    action.deletar(id);

    List<Voo> res = action.consultar(request);
    assertThat(res.size(), equalTo(2));

    int id2 = 2;

    String nome = "";
    RequestParamWrapper req = new RequestParamWrapper();
    req.set("nome", nome);

    List<Aeronave> res2 = actionAero.consultar(req);
    assertThat(res2.size(), equalTo(2));

    actionAero.deletar(id2);

    List<Aeronave> res3 = actionAero.consultar(req);
    assertThat(res3.size(), equalTo(1));

  }
}
