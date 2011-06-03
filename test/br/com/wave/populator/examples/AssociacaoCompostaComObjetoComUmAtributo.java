package br.com.wave.populator.examples;

import java.io.Serializable;
import java.util.Collection;

public class AssociacaoCompostaComObjetoComUmAtributo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Collection<ObjetoSimplesComUmAtributo> objetosSimplesComUmAtributo;
	
	public void setObjetosSimplesComUmAtributo(Collection<ObjetoSimplesComUmAtributo> objetosSimplesComUmAtributo) {
		this.objetosSimplesComUmAtributo = objetosSimplesComUmAtributo;
	}

}
