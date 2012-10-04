package br.com.moonjava.flight.model.base;

public class PassagemModel implements Passagem {

	private int id;
	private Voo voo;
	private PessoaFisica pf;
	private String codigoBilhete;
	private String assento;

	public PassagemModel(Builder builder) {
		this.voo = builder.getVoo();
		this.pf = builder.getPessoaFisica();
		this.codigoBilhete = builder.getCodigoBilhete();
		this.assento = builder.getAssento();
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
}
