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
public class TesteDeConsultarAeronave {
  @BeforeClass
  public void limpaTabela() {
	    DbUnit dbUnit = new DbUnit();
	    dbUnit.load(new DbUnitFlightXml());
  }


  @Test
  public void consultar_todas_aeronaves() {
    
	  AeronaveAction action = new AeronaveAction();
	  
	  List<Aeronave> test1 = action.listaTodasAeronaves();
	  assertThat(test1.size(), equalTo(2));
	  
	  Aeronave aero1 = test1.get(0);
	  Aeronave aero2 = test1.get(1);
	  
	  assertThat(aero1.getCodigo(), equalTo(1));
	  assertThat(aero1.getNome(), equalTo("nave A"));
	  assertThat(aero1.getQtdDeAssento(), equalTo(100));
	  
	  assertThat(aero2.getCodigo(), equalTo(2));
	  assertThat(aero2.getNome(), equalTo("nave B"));
	  assertThat(aero2.getQtdDeAssento(), equalTo(200));
  }
  
  @Test
  public void consultar_por_filtro_nome(){
	  
	  AeronaveAction action = new AeronaveAction();
	  RequestParamWrapper request = new RequestParamWrapper();
	  
	  String nome1 = "nave A";
	  request.set("nome", nome1);
	  
	  List<Aeronave> test1 = action.consultar(request);
	  assertThat(test1.size(), equalTo(1));
	  
	  Aeronave aero1 = test1.get(0);

	  assertThat(aero1.getCodigo(), equalTo(1));
	  assertThat(aero1.getNome(), equalTo("nave A"));
	  assertThat(aero1.getQtdDeAssento(), equalTo(100));
	  
	  String nome2 = "nave B";
	  request.set("nome", nome2);
	  
	  List<Aeronave> test2 = action.consultar(request);
	  assertThat(test2.size(), equalTo(1));
	  
	  Aeronave aero2 = test2.get(0);

	  assertThat(aero2.getCodigo(), equalTo(2));
	  assertThat(aero2.getNome(), equalTo("nave B"));
	  assertThat(aero2.getQtdDeAssento(), equalTo(200));
	  
	  String nome3 = "nave C";
	  request.set("nome", nome3);
	  
	  List<Aeronave> test3 = action.consultar(request);
	  assertThat(test3.size(), equalTo(0));
	  
  }
}
