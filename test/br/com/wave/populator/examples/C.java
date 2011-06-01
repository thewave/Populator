package br.com.wave.populator.examples;

import java.io.Serializable;

public class C implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String atributoNaoNulo;
	
	private String atributoNulo;

	public String getAtributoNaoNulo() {
		return atributoNaoNulo;
	}

	public void setAtributoNaoNulo(String atributo) {
		this.atributoNaoNulo = atributo;
	}

	public String getAtributoNulo() {
		return atributoNulo;
	}

	public void setAtributoNulo(String atributoNulo) {
		this.atributoNulo = atributoNulo;
	}

}
