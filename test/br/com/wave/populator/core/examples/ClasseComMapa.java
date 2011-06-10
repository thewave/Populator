package br.com.wave.populator.core.examples;

import java.io.Serializable;
import java.util.Map;

public class ClasseComMapa implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, String> mapa;

	public Map<String, String> getMapa() {
		return mapa;
	}

}
