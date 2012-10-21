package br.com.moonjava.flight.model.base;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Hours;

import br.com.moonjava.flight.dao.base.PassagemDAO;

public class PassagemModel implements Passagem {

  private int id;
  private Voo voo;
  private PessoaFisica pf;
  private String codigoBilhete;
  private String assento;
  private PassagemDAO dao;

  public PassagemModel(Builder builder) {
    this.voo = builder.getVoo();
    this.pf = builder.getPessoaFisica();
    this.codigoBilhete = builder.getCodigoBilhete();
    this.assento = builder.getAssento();
  }

  public PassagemModel() {
    dao = new PassagemDAO();
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public Voo getVoo() {
    return voo;
  }

  @Override
  public PessoaFisica getPessoaFisica() {
    return pf;
  }

  @Override
  public String getCodigoBilhete() {
    return codigoBilhete;
  }

  @Override
  public String getAssento() {
    return assento;
  }

  @Override
  public double getPreco(Passagem passagem) {
    Voo voo = passagem.getVoo();

    DateTime partida = voo.getDataDePartida();
    DateTime agora = new DateTime().toDateTime();
    int horas = Hours.hoursBetween(agora, partida).getHours();

    if (partida.isAfterNow()) {

      if (horas >= 24) {
        return voo.getPreco();
      } else if (horas >= 12) {
        return (voo.getPreco() * 0.5);
      } else if (horas >= 6) {
        return (voo.getPreco() * 0.25);
      } else {
        return 0.0;
      }

    } else {
      return -1;
    }
  }

  @Override
  public boolean transferir(Passagem pojo) {
    return dao.transferir(pojo);
  }

  @Override
  public boolean vender(Passagem pojo) {
    return dao.vender(pojo);
  }

  @Override
  public Passagem consultarPorCodigoBilhete(String bilhete) {
    return dao.consultarPorCodigoBilhete(bilhete);
  }

  @Override
  public List<Passagem> consultarPorVoo(Voo voo) {
    return dao.consultarPorVoo(voo);
  }

  @Override
  public boolean efetuarCheckin(Passagem pojo, String assento) {
    return dao.efetuarCheckin(pojo, assento);
  }

  @Override
  public boolean cancelarPorVoo(Voo pojo) {
    return dao.cancelarPorVoo(pojo);
  }

  @Override
  public boolean efetuarCancelamento(Passagem pojo) {
    return dao.efetuarCancelamento(pojo);
  }

}