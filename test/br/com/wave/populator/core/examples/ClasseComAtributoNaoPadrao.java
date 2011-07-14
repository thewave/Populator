package br.com.wave.populator.core.examples;

import java.io.Serializable;

public class ClasseComAtributoNaoPadrao implements Serializable {

	private static final long serialVersionUID = 1L;

	private ClasseComAtributosPadrao classeComAtributosPadrao;

	public ClasseComAtributosPadrao getClasseComAtributosPadrao() {
		return classeComAtributosPadrao;
	}

	public void setClasseComAtributosPadrao(ClasseComAtributosPadrao classeComAtributosPadrao) {
		this.classeComAtributosPadrao = classeComAtributosPadrao;
	}

}
