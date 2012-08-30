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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.moonjava.flight.dao.base.AeronaveDAO;
import br.com.moonjava.flight.model.base.Aeronave;
import br.com.moonjava.flight.util.CopyFile;
import br.com.moonjava.flight.util.RequestParamWrapper;
import br.com.moonjava.flight.view.aeronave.CriarAeronaveUI;

/**
 * @version 1.0 Aug 29, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class CriarAeronaveController extends CriarAeronaveUI {

  private RequestParamWrapper request;
  private String fileName;

  public CriarAeronaveController(JPanel conteudo,
                                 ResourceBundle bundle,
                                 JButton atualizar,
                                 JButton deletar) {
    super(conteudo, bundle, atualizar, deletar);

    addLoaderFileListener(new LoaderFileHandler());
    addCadastrarListener(new CadastrarHandler());
  }

  private class LoaderFileHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      request = getParameters();

      try {
        String qtdDeAssento = request.stringParam("qtdDeAssento");
        int assento = Integer.parseInt(qtdDeAssento);
        if (assento <= 0) {
          throw new NumberFormatException();
        }

        // Adiciona ao request o assento como tipo int
        request.set("qtdDeAssento", assento);

        // Carrega a imagem do mapa de assento
        JFileChooser caixa = new JFileChooser();
        caixa.setFileFilter(new FileNameExtensionFilter("*.jpg", "jpg"));
        int retorno = caixa.showOpenDialog(null);

        if (retorno == JFileChooser.APPROVE_OPTION) {
          File folder = new File("airplanes");

          File file = caixa.getSelectedFile();
          String absolutePath = file.getAbsolutePath();
          fileName = file.getName();

          if (!folder.exists()) {
            folder.mkdir();
            folder.setWritable(true, true);
          }

          // Adiciona o resultado ao request
          boolean executed = CopyFile.copyfile(absolutePath, folder.getName() + "/" + fileName);
          request.set("mapa", executed);

          // Adiciona imagem OK a view
          addImagem();
        }

      } catch (NumberFormatException ex) {
        // Falha no parseInt e/ou qtdDeAssento maior que zero
        messageNumberException();
      }
    }
  }

  private class CadastrarHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String res = request.stringParam("nome") + ".jpg";

      // Executa se nome de arquivo for igual a aeronave
      if (fileName.equals(res)) {
        Aeronave pojo = new AeronaveCreate(request).createInstance();
        new AeronaveDAO().criar(pojo);
        messageOK();
      } else {
        messageFailed();
      }
    }
  }

}