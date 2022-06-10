package br.com.kanislupus.kashflow.services.exceptions;

public class RegraNegocioException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public RegraNegocioException(String msg) {
		super(msg);
	}
}
