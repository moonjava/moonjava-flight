package br.com.moonjava.flight.action;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.com.moonjava.flight.jdbc.DbUnit;
import br.com.moonjava.flight.jdbc.DbUnitFlightXml;
import br.com.moonjava.flight.util.RequestParamWrapper;

@Test
public class TesteDeAtualizarAeronave {

  @BeforeClass
  public void limpaTabela() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void atualizar_aeronave() {
    AeronaveAction action = new AeronaveAction();
    RequestParamWrapper request = new RequestParamWrapper();

    Aeronave antes = action.consultarPorCodigo(1);
    assertThat(antes.getCodigo(), equalTo(1));
    assertThat(antes.getNome(), equalTo("nave A"));

    int id = 1;
    int codigo = 1;
    String nome = "nave A";

    request.set("id", id);
    request.set("codigo", codigo);
    request.set("nome", nome);

    Aeronave aeronave = new AeronaveUpdate(request).createInstance();

    action.atualizar(aeronave);

    Aeronave res = action.consultarPorCodigo(id);
    assertThat(res.getCodigo(), equalTo(codigo));
    assertThat(res.getNome(), equalTo(nome));

  }
}
