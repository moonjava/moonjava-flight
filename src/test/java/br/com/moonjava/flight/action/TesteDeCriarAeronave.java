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
public class TesteDeCriarAeronave {

  @BeforeClass
  public void beforeClass() {
    DbUnit dbUnit = new DbUnit();
    dbUnit.load(new DbUnitFlightXml());
  }

  public void criar_aeronave() {
    String nome = "Teste 1";
    int codigo = 3;
    int qtdAssentos = 50;
    boolean mapa = true;

    AeronaveAction action = new AeronaveAction();
    RequestParamWrapper request = new RequestParamWrapper();
    RequestParamWrapper req = new RequestParamWrapper();

    String nome1 = "";
    req.set("nome", nome1);

    List<Aeronave> antes = action.consultar(req);
    assertThat(antes.size(), equalTo(2));

    request.set("nome", nome);
    request.set("codigo", codigo);
    request.set("qtdDeAssento", qtdAssentos);
    request.set("mapa", mapa);

    Aeronave aeronave = new AeronaveCreate(request).createInstance();
    action.criar(aeronave);

    List<Aeronave> res = action.consultar(req);
    assertThat(res.size(), equalTo(3));

    Aeronave res2 = action.consultarPorCodigo(codigo);
    assertThat(res2.getCodigo(), equalTo(codigo));
    assertThat(res2.getNome(), equalTo(nome));
    assertThat(res2.getQtdDeAssento(), equalTo(qtdAssentos));
    assertThat(res2.isMapa(), equalTo(true));

  }
}
