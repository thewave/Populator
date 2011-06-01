package br.com.wave.populator.examples;

import java.io.Serializable;

public class E implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private F f;

	public F getF() {
		return f;
	}

	public void setF(F f) {
		this.f = f;
	}

}
