package br.com.kanislupus.kashflow.model.enums;

public enum TipoPagamento {

	DINHEIRO(1, "Dinheiro"),
	CARTAOCREDITO(2, "Cartão de Crédito"), 
	CARTAODEBITO(3, "Cartão de Débito"), 
	PIX(4, "Pix"),
	BOLETO(5, "Boleto");

	private int cod;
	private String descricao;

	private TipoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoPagamento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TipoPagamento x : TipoPagamento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
  }

}
