package br.com.moonjava.flight.view.painel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import br.com.moonjava.flight.model.base.Voo;
import br.com.moonjava.flight.model.base.VooModel;

public class PainelDeDecolagemUI {

  private final ResourceBundle bundle;
  private JTable tabela;
  private JFrame conteudo;
  private JPanel painel;

  public PainelDeDecolagemUI(ResourceBundle bundle) {
    this.bundle = bundle;
    window();
  }

  private void window() {
    conteudo = new JFrame("Flight - " + bundle.getString("painel.titulo"));
    conteudo.getContentPane().setBackground(Color.DARK_GRAY);

    painel = new JPanel(null);
    painel.setBounds(30, 30, 1130, 600);

    tabela = new JTable();
    tabela.setBorder(new LineBorder(Color.black));
    tabela.setGridColor(Color.black);
    tabela.setShowGrid(true);
    tabela.setFont(new Font("Century Gothic", Font.ITALIC, 13));

    // Atualiza automaticamente o painel de decolagem a cada 1 minuto
    Timer timer = new Timer(1000 * 60, new Clock());
    timer.start();

    JScrollPane scroll = new JScrollPane();
    scroll.getViewport().setBorder(null);
    scroll.getViewport().add(tabela);
    scroll.setBounds(30, 45, 1050, 400);
    scroll.setSize(1050, 400);

    painel.add(scroll);
    conteudo.add(painel);
  }

  // Adiciona o dados da tabela (DB) através do método setModel(voos);
  protected boolean showList(List<Voo> lista) {
    PainelDeDecolagemTableModel voos = new PainelDeDecolagemTableModel(lista, bundle);
    tabela.setModel(voos);
    return tabela.getRowCount() == 0 ? true : false;
  }

  protected void showAll() {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int width = dimension.width;
    int height = dimension.height;
    int frameWidth = 1126;
    int frameHeight = 512;

    // Centraliza a frame
    conteudo.setLocation((width / 2) - (frameWidth / 2), (height / 2) - (frameHeight / 2));
    conteudo.setSize(frameWidth, frameHeight);
    conteudo.setResizable(false);
    conteudo.setVisible(true);
    conteudo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /*
   * Esta classe inicia o evento de acordo com o tempo definido anteriormente
   */
  private class Clock implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      List<Voo> lista = new VooModel().consultaPainel();
      showList(lista);

      conteudo.repaint();
      conteudo.validate();
    }
  }

}