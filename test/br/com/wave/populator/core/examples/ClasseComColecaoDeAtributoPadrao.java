package br.com.wave.populator.core.examples;

import java.io.Serializable;
import java.util.Collection;

public class ClasseComColecaoDeAtributoPadrao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Collection<String> colecao;

	public Collection<String> getColecao() {
		return colecao;
	}

	public void setColecao(Collection<String> colecao) {
		this.colecao = colecao;
	}

}
