package br.com.kanislupus.kashflow.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.kanislupus.kashflow.model.Lancamento;

public class LancamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricao;
	private BigDecimal valor;
	private Long usuario;
	private String tipo;
	private String status;
	private String tipoPagamento;
	
	public LancamentoDTO() {
		
	}

	public LancamentoDTO(Lancamento obj) {
		super();
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.valor = obj.getValor();
		this.usuario = obj.getUsuario().getId();
		this.tipo = obj.getTipo().getDescricao();
		this.status = obj.getStatus().getDescricao();
		this.tipoPagamento = obj.getTipoPagamento().getDescricao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

}
