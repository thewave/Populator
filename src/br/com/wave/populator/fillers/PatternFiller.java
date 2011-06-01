package br.com.wave.populator.fillers;

import java.lang.reflect.Field;

import br.com.wave.populator.core.Populator;
import br.com.wave.populator.exceptions.PopulatorException;

public class PatternFiller extends Filler {

	public PatternFiller(Populator populator) {
		super(populator);
	}

	@Override
	public <T> void fill(Field field, T instance) throws PopulatorException{
		
		try {
			Class<?> klass = field.getType();
			
			if (this.getPopulator().getManager().isPattern(klass)) {
					field.set(instance, this.getPopulator().getManager().getValue(klass));
			}else {
				this.getSuccessor().fill(field, instance);
			}
		} catch (IllegalArgumentException e) {
			throw new PopulatorException(e.getMessage());			
		} catch (IllegalAccessException e) {
			throw new PopulatorException(e.getMessage());
		}
	}

}
