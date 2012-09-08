package br.com.moonjava.flight.view.painel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import br.com.moonjava.flight.model.base.Voo;

public class PainelDeDecolagemUI {

  private final ResourceBundle bundle;
  private JTable tabela;
  private JFrame conteudo;
  private JPanel painel;

  public PainelDeDecolagemUI(ResourceBundle bundle) {
    this.bundle = bundle;
    window();
  }

  public void window() {
    conteudo = new JFrame("Flight");
    conteudo.getContentPane().setBackground(Color.DARK_GRAY);

    painel = new JPanel(null);
    painel.setBounds(30, 30, 1130, 600);

    tabela = new JTable();
    tabela.setBorder(new LineBorder(Color.black));
    tabela.setGridColor(Color.black);
    tabela.setShowGrid(true);

    JScrollPane scroll = new JScrollPane();
    scroll.getViewport().setBorder(null);
    scroll.getViewport().add(tabela);
    scroll.setBounds(30, 45, 750, 400);
    scroll.setSize(750, 400);

    Font font = new Font("Arial", Font.BOLD, 13);

    JLabel titulo = new JLabel(bundle.getString("painel.titulo"));
    titulo.setFont(font);
    titulo.setBounds(340, 12, 180, 30);

    painel.add(titulo);
    painel.add(scroll);

    conteudo.add(painel);
  }
  public JTable getTable() {
    return tabela;
  }

  public boolean showList(List<Voo> lista) {
    PainelDeDecolagemTableModel voos = new PainelDeDecolagemTableModel(lista, bundle);
    tabela.setModel(voos);
    return tabela.getRowCount() == 0 ? true : false;
  }

  public void showAll() {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int width = dimension.width;
    int height = dimension.height;
    int frameWidth = 826;
    int frameHeight = 512;

    conteudo.setLocation((width / 2) - (frameWidth / 2), (height / 2)
        - (frameHeight / 2));
    conteudo.setSize(frameWidth, frameHeight);
    conteudo.setResizable(true);
    conteudo.setVisible(true);
    conteudo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
