package br.com.wave.populator.examples;

import java.io.Serializable;

public class I implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String atributo;
	
	private H h;
	
	private F f;

	public String getAtributo() {
		return atributo;
	}
	
	public H getH() {
		return h;
	}

	public F getF() {
		return f;
	}

}
