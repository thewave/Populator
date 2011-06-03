package br.com.wave.populator.examples;

import java.io.Serializable;
import java.util.Collection;

public class G implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Collection<F> fs;

	public Collection<F> getFs() {
		return fs;
	}
	
}
