package br.com.wave.populator.setters;

import br.com.wave.populator.core.Filler;
import br.com.wave.populator.exceptions.PopulatorException;
import br.com.wave.populator.utils.ReflectionUtil;

public class PatternSetter extends Setter {

	public PatternSetter(Filler filler) {
		super(filler);
	}

	@Override
	public <T> void set(T instance) throws PopulatorException {
		Class<?> klass = instance.getClass();
		if (this.getManager().isPattern(klass)) {
			Object value = this.getManager().getValue(klass);

			ReflectionUtil.copy(value, instance);
		} else {
			this.getManager().addPattern(klass, instance);

			this.getSuccessor().set(instance);
		}
	}

}
