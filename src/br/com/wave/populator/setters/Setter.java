package br.com.wave.populator.setters;

import br.com.wave.populator.core.Filler;
import br.com.wave.populator.core.PatternManager;
import br.com.wave.populator.exceptions.PopulatorException;

public abstract class Setter {

	private Filler filler;

	private PatternManager manager;

	private Setter successor;

	public Setter(Filler filler) {
		this.filler = filler;

		this.manager = PatternManager.getInstance();
	}

	public abstract <T> void set(T instance) throws PopulatorException;

	public Filler getFiller() {
		return filler;
	}

	public PatternManager getManager() {
		return manager;
	}

	public Setter getSuccessor() {
		return successor;
	}

	public void setSuccessor(Setter successor) {
		this.successor = successor;
	}

}
