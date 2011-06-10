package br.com.wave.populator.core.examples;

import java.io.Serializable;
import java.util.Collection;

public class ClasseComColecaoDeAtributoNaoPadraoBidirecional implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Collection<ClasseComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional> colecao;

	public Collection<ClasseComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional> getColecao() {
		return colecao;
	}
	
	public void setColecao(Collection<ClasseComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional> colecao) {
		this.colecao = colecao;
	}

}
