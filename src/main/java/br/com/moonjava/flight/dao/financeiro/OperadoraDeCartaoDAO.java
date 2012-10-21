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
package br.com.moonjava.flight.dao.financeiro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.joda.time.LocalDate;

import br.com.moonjava.flight.controller.financeiro.CartaoCreate;
import br.com.moonjava.flight.model.financeiro.Bandeira;
import br.com.moonjava.flight.model.financeiro.Cartao;
import br.com.moonjava.flight.util.ReaderFile;
import br.com.moonjava.flight.util.RequestParamWrapper;

/**
 * @version 1.0 Jul 25, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class OperadoraDeCartaoDAO implements ReaderFile<Cartao> {

  private final List<Cartao> list;

  public OperadoraDeCartaoDAO() {
    list = new ArrayList<Cartao>();
  }

  private Scanner input;

  @Override
  public void openFile() {
    try {
      input = new Scanner(new File("operadora_cartao.txt"));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Cartao> readFile() {
    try {
      while (input.hasNext()) {
        RequestParamWrapper request = new RequestParamWrapper();

        String titular = input.next();
        long numero = input.nextLong();
        String data = input.next();
        String cartao = input.next();
        double valor = 0;

        request.set("titular", titular);
        request.set("numero", numero);

        LocalDate validade = LocalDate.parse(data);
        request.set("validade", validade);

        Bandeira bandeira = Bandeira.valueOf(Bandeira.class, cartao);
        request.set("bandeira", bandeira);

        request.set("valor", valor);

        Cartao pojo = new CartaoCreate(request).createInstance();
        list.add(pojo);
      }
    } catch (NoSuchElementException e) {
      input.close();
    } catch (IllegalStateException e) {
      throw new RuntimeException(e);
    }
    return list;
  }

  @Override
  public void closeFile() {
    if (input != null) {
      input.close();
    }
  }

}