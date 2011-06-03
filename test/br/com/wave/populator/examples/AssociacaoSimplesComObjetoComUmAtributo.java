package br.com.wave.populator.examples;

import java.io.Serializable;

public class AssociacaoSimplesComObjetoComUmAtributo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ObjetoSimplesComUmAtributo objetoSimplesComUmAtributo;
	
	public void setObjetoSimplesComUmAtributo(ObjetoSimplesComUmAtributo objetoSimplesComUmAtributo) {
		this.objetoSimplesComUmAtributo = objetoSimplesComUmAtributo;
	}

}
