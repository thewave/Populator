package br.com.wave.populator.fillers;

import java.lang.reflect.Field;

import br.com.wave.populator.core.Populator;
import br.com.wave.populator.exceptions.PopulatorException;

public abstract class Filler {

	private Populator populator;
	
	private Filler successor;
	
	public Filler(Populator populator) {
		this.populator = populator;
	}
	
	public abstract <T> void fill(Field field, T instance) throws PopulatorException;
	
	public Populator getPopulator() {
		return populator;
	}
	
	public Filler getSuccessor() {
		return successor;
	}
	
	public void setSuccessor(Filler successor) {
		this.successor = successor;
	}
	
}
