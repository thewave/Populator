package br.com.wave.populator.examples;

import java.io.Serializable;
import java.util.Collection;

public class AssociacaoComposta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Collection<ObjetoSimples> objetosSimples;
	
	public void setObjetosSimples(Collection<ObjetoSimples> objetosSimples) {
		this.objetosSimples = objetosSimples;
	}

}
