package br.com.wave.populator.examples;

import java.io.Serializable;

public class AssociacaoSimples implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ObjetoSimples objetoSimples;
	
	public void setObjetoSimples(ObjetoSimples objetoSimples) {
		this.objetoSimples = objetoSimples;
	}
	
}
