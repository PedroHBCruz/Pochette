package br.com.kanislupus.kashflow.model.enums;


public enum StatusLancamento {
	
	PENDENTE(1, "Pendente"), 
	EFETIVADO(2, "Efetivado");

	private int cod;
	private String descricao;

	private StatusLancamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static StatusLancamento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (StatusLancamento x : StatusLancamento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
  }

}
