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


  @Test
  public void criar_aeronave() {
    
	  String nome = "Teste 1";
	  int codigo = 3;
	  int qtdAssentos = 50;
	  int mapa = 1;
	  
	  AeronaveAction action = new AeronaveAction();
	  RequestParamWrapper request = new RequestParamWrapper();
	  
	  List<Aeronave> test1 = action.listaTodasAeronaves();
	  assertThat(test1.size(), equalTo(2));
	  
	  request.set("nome", nome);
	  request.set("codigo", codigo);
	  request.set("qtdDeAssento", qtdAssentos);
	  request.set("mapa", mapa);
	  
	  Aeronave aeronave = new AeronaveCreate(request).createInstance();
	  action.criar(aeronave);

	  List<Aeronave> test2 = action.listaTodasAeronaves();
	  assertThat(test2.size(), equalTo(3));
	  
	  Aeronave test3 = action.consultarPorCodigo(codigo);
	  assertThat(test3.getCodigo(), equalTo(codigo));
	  assertThat(test3.getNome(), equalTo(nome));
	  assertThat(test3.getQtdDeAssento(), equalTo(qtdAssentos));
	  assertThat(test3.mapa(), equalTo(1));	  
	  
  }
}
