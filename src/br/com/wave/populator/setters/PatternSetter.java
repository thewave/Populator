package br.com.wave.populator.setters;

import br.com.brasilti.utils.reflection.ReflectionUtil;
import br.com.wave.populator.core.Filler;
import br.com.wave.populator.exceptions.PopulatorException;

public class PatternSetter extends Setter {

	public PatternSetter(Filler filler) {
		super(filler);
	}

	@Override
	public <T> void set(T instance) throws PopulatorException {
		Class<?> klass = instance.getClass();
		if (this.getManager().hasPattern(klass)) {
			Object value = this.getManager().getValue(klass);

			ReflectionUtil.copy(value, instance);
		} else {
			this.getManager().addPattern(klass, instance);

			this.getSuccessor().set(instance);
		}
	}

}
