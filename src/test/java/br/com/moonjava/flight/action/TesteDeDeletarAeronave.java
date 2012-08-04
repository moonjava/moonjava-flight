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
    VooAction actionVoo = new VooAction();
    AeronaveAction actionAeronave = new AeronaveAction();
    RequestParamWrapper request = new RequestParamWrapper();

    request.set("status", Status.DISPONIVEL);

    int id = 1;

    List<Voo> antesVoo = actionVoo.consultar(request);
    assertThat(antesVoo.size(), equalTo(3));

    List<Aeronave> antesAeronave = actionAeronave.consultar(request);
    assertThat(antesAeronave.size(), equalTo(2));

    actionVoo.deletarPorAeronaveId(id);
    actionAeronave.deletar(id);

    List<Voo> resVoo = actionVoo.consultar(request);
    assertThat(resVoo.size(), equalTo(1));

    List<Aeronave> resAeronave = actionAeronave.consultar(request);
    assertThat(resAeronave.size(), equalTo(1));
  }

}