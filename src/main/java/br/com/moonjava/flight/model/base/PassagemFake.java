package br.com.moonjava.flight.model.base;

import java.util.List;

public class PassagemFake implements Passagem {

  @Override
  public int getId() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Voo getVoo() {
    throw new UnsupportedOperationException();
  }

  @Override
  public PessoaFisica getPessoaFisica() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getCodigoBilhete() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getAssento() {
    throw new UnsupportedOperationException();
  }

  @Override
  public double cancelarPassagem(Passagem passagem) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean transferirPassagem(Passagem passagem, Voo voo) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void venderPassagem(Passagem pojo) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Passagem consultarPorCodigoBilhete(String bilhete) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Passagem> consultarPorVoo(Voo voo) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean efetuarCheckin(Passagem pojo, String assento) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean cancelarPorVoo(Voo voo) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean efetuarCancelamento(Passagem pojo) {
    throw new UnsupportedOperationException();
  }

}