package br.com.kanislupus.kashflow.model.enums;


public enum TipoLancamento {
	
	DESPESA(1, "Despesa"), 
	RECEITA(2, "Receita");

	private int cod;
	private String descricao;

	private TipoLancamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoLancamento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TipoLancamento x : TipoLancamento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
  }

}
