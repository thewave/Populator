package br.com.wave.populator.examples;

import java.io.Serializable;
import java.util.Collection;

public class J implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Collection<K> ks;
	
	public Collection<K> getKs() {
		return ks;
	}
	
}
