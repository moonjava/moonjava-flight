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
package br.com.moonjava.flight.action;

import java.util.List;

import br.com.moonjava.flight.jdbc.SqlStatement;
import br.com.moonjava.flight.jdbc.SqlStatementWrapper;
import br.com.moonjava.flight.util.RequestParam;

/**
 * @version 1.0, 25/07/2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class AeronaveAction implements Aeronave.Jdbc {

	private SqlStatement query() {
		return new SqlStatementWrapper()
			.prepare()

			.with("select *")
			.with("from FLIGHT.AERONAVE as AERONAVE")

			.load(new AeronaveLoader());
	}

	@Override
	public void criar(Aeronave aeronave) {
		new SqlStatementWrapper()
			.prepare()

			.with("insert into FLIGHT.AERONAVE")
			.with("(NOME, CODIGO, QTD_ASSENTO, MAPA)")

			.with("values (").with("?,", aeronave.getNome())
			.with("?,", aeronave.getCodigo())
			.with("?,", aeronave.getQtdDeAssento())
			.with("?)", aeronave.mapa())

			.andExecute();
	}
	
	@Override
	public List<Aeronave> listaTodasAeronaves(){
		
		return query()

				.with("where 1 = 1")
				.with("order by AERONAVE.CODIGO asc")

				.andList();
	}

	@Override
	public List<Aeronave> consultar(RequestParam request) {
		String nome = String.valueOf("%"+request.stringParam("nome")+"%");

		
		return query()

			.with("where 1 = 1")
			.with("and AERONAVE.NOME like ?", nome)

			.with("order by AERONAVE.CODIGO asc")

			.andList();
	}

	@Override
	public Aeronave consultarPorCodigo(int codigo) {

		return query()

		.with("where AERONAVE.CODIGO = ?", codigo)

		.andGet();
	}

	@Override
	public void atualizar(Aeronave aeronave) {
		new SqlStatementWrapper()
			.prepare()
			
			.with("update FLIGHT.AERONAVE AS AERONAVE SET")
			
			.with("AERONAVE.NOME = ?,", aeronave.getNome())
			.with("AERONAVE.CODIGO = ?", aeronave.getCodigo())
			
			.with("where AERONAVE.ID = ?", aeronave.getId())
			
			.andExecute();
	}

	@Override
	public void deletar(int id) {
	    new SqlStatementWrapper()   
        .prepare()                            //Esta operação a grandes possibilidades de lançar uma exception
        									  //pois pode haver um vôo relacionado com a aeronave que deseja deletar
        .with("delete from FLIGHT.AERONAVE")  //sugestão de solução: caso a exception seja lançada retornar a mesma para 
        .with("where AERONAVE.ID = ?", id)    //quem chamou o método e assim tratá-lo

        .andExecute();

	}

}
