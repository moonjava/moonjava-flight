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
package br.com.moonjava.flight.controller.base;

import java.util.List;
import java.util.ResourceBundle;

import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.model.base.VooModel;
import br.com.moonjava.flight.view.painel.PainelDeDecolagemUI;

/**
 * @version 1.0 Sep 8, 2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class PainelController extends PainelDeDecolagemUI {

  public PainelController(ResourceBundle bundle) {
    super(bundle);
    showAll();

    List<Voo> lista = new VooModel().consultaPainel();
    showList(lista);
  }

}