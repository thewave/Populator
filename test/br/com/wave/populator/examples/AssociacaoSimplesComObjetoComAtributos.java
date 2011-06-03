package br.com.wave.populator.examples;

import java.io.Serializable;

public class AssociacaoSimplesComObjetoComAtributos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ObjetoSimplesComAtributos objetoSimplesComAtributos;
	
	public void setObjetoSimplesComAtributos(ObjetoSimplesComAtributos objetoSimplesComAtributos) {
		this.objetoSimplesComAtributos = objetoSimplesComAtributos;
	}
	
}
