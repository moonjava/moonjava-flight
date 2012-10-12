package br.com.moonjava.flight.model.base;

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

}