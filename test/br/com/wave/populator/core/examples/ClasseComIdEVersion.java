package br.com.wave.populator.core.examples;

import java.io.Serializable;

public class ClasseComIdEVersion implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String version;

	private String atributo;

	public String getId() {
		return id;
	}

	public String getVersion() {
		return version;
	}

	public String getAtributo() {
		return atributo;
	}

}
