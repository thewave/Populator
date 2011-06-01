package br.com.wave.populator.examples;

import java.io.Serializable;

public class B implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String atributoNaoNulo;
	
	private String atributoNulo;
	
	private C c;

	public String getAtributoNaoNulo() {
		return atributoNaoNulo;
	}

	public void setAtributoNaoNulo(String atributoNaoNulo) {
		this.atributoNaoNulo = atributoNaoNulo;
	}

	public String getAtributoNulo() {
		return atributoNulo;
	}

	public void setAtributoNulo(String atributoNulo) {
		this.atributoNulo = atributoNulo;
	}

	public C getC() {
		return c;
	}

	public void setC(C c) {
		this.c = c;
	}

}
