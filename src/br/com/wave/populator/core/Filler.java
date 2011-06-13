package br.com.wave.populator.core;

import br.com.wave.populator.exceptions.PopulatorException;
import br.com.wave.populator.setters.CollectionSetter;
import br.com.wave.populator.setters.FieldPatternSetter;
import br.com.wave.populator.setters.OtherSetter;
import br.com.wave.populator.setters.PatternSetter;
import br.com.wave.populator.setters.Setter;

public class Filler {

	private Setter patternSetter;

	private Setter fieldPatternSetter;

	private Setter collectionSetter;

	private Setter otherSetter;

	public Filler() {
		this.patternSetter = new PatternSetter(this);
		this.fieldPatternSetter = new FieldPatternSetter(this);
		this.collectionSetter = new CollectionSetter(this);
		this.otherSetter = new OtherSetter(this);

		this.patternSetter.setSuccessor(this.fieldPatternSetter);
		this.fieldPatternSetter.setSuccessor(this.collectionSetter);
		this.collectionSetter.setSuccessor(this.otherSetter);
	}

	public <T> void fill(T instance) throws PopulatorException {
		this.patternSetter.set(instance);
	}

}
