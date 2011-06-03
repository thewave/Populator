package br.com.wave.populator.examples;

import java.io.Serializable;

public class ObjetoSimplesComAtributos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String preenchido;
	
	private String naoPreenchido;
	
	public void setPreenchido(String preenchido) {
		this.preenchido = preenchido;
	}

}
