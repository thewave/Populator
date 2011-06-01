package br.com.wave.populator.examples;

import java.io.Serializable;

public class D implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private E e;

	public E getE() {
		return e;
	}

	public void setE(E e) {
		this.e = e;
	}
	
}
