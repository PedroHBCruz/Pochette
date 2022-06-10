package br.com.kanislupus.kashflow.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.kanislupus.kashflow.model.Lancamento;

public class LancamentoNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 40, message = "O tamanho deve ser entre 5 e 40 caracteres")
	private String descricao;
	
	@NotNull(message = "Preenchimento obrigatório")
	private BigDecimal valor;
	
	private Long usuario;
	
	@NotNull(message = "Preenchimento obrigatório")
	private Integer tipo;
	
	private Integer status;
	
	@NotNull(message = "Preenchimento obrigatório")
	private Integer tipoPagamento;

	public LancamentoNewDTO() {

	}

	public LancamentoNewDTO(Lancamento obj) {
		super();
		this.descricao = obj.getDescricao();
		this.valor = obj.getValor();
		this.usuario = obj.getUsuario().getId();
		this.tipo = obj.getTipo().getCod();
		this.tipoPagamento = obj.getTipoPagamento().getCod();
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

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(Integer tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
}
