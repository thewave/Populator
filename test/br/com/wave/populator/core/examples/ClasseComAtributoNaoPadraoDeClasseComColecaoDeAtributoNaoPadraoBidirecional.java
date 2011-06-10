package br.com.wave.populator.core.examples;

import java.io.Serializable;

public class ClasseComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional implements Serializable {

	private static final long serialVersionUID = 1L;

	private ClasseComColecaoDeAtributoNaoPadraoBidirecional classeComColecaoDeAtributoNaoPadraoBidirecional;

	public ClasseComColecaoDeAtributoNaoPadraoBidirecional getClasseComColecaoDeAtributoNaoPadraoBidirecional() {
		return classeComColecaoDeAtributoNaoPadraoBidirecional;
	}

	public void setClasseComColecaoDeAtributoNaoPadraoBidirecional(ClasseComColecaoDeAtributoNaoPadraoBidirecional classeComColecaoDeAtributoNaoPadraoBidirecional) {
		this.classeComColecaoDeAtributoNaoPadraoBidirecional = classeComColecaoDeAtributoNaoPadraoBidirecional;
	}

}
