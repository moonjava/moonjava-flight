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
package br.com.moonjava.flight.dao.base;

import br.com.moonjava.flight.jdbc.SqlStatementWrapper;
import br.com.moonjava.flight.model.base.Passagem;
import br.com.moonjava.flight.model.base.Reembolso;

/**
 * @version 1.0, Aug 13, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class ReembolsoDAO implements Reembolso.Jdbc {

  @Override
  public boolean criar(Reembolso reembolso) {
    Passagem passagem = reembolso.getPassagem();
    boolean executed = new SqlStatementWrapper()
        .prepare()

        .with("insert into FLIGHT.REEMBOLSO")
        .with("(ID, PASSAGEM_ID, TITULAR, CPF, BANCO, AGENCIA, CONTA, VALOR)")

        .with("values (")
        .with("?,", reembolso.getId())
        .with("?,", passagem.getId())
        .with("?,", reembolso.getTitular())
        .with("?,", reembolso.getCpf().getDigito())
        .with("?,", reembolso.getBanco())
        .with("?,", reembolso.getAgencia())
        .with("?,", reembolso.getConta())
        .with("?)", reembolso.getValor())

        .andExecute();
    return executed;
  }

}