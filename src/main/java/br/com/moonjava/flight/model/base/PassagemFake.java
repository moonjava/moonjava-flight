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

}
