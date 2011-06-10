package br.com.wave.populator.setters;

import java.lang.reflect.Field;
import java.util.List;

import br.com.wave.populator.core.Filler;
import br.com.wave.populator.enums.ErrorEnum;
import br.com.wave.populator.exceptions.PopulatorException;
import br.com.wave.populator.utils.ReflectionUtil;

public class FieldPatternSetter extends Setter {

	public FieldPatternSetter(Filler filler) {
		super(filler);
	}

	@Override
	public <T> void set(T instance) throws PopulatorException {
		List<Field> fields = ReflectionUtil.getPersistentFields(instance.getClass());
		if (fields.isEmpty()) {
			throw new PopulatorException(ErrorEnum.NOT_PERSISTENT_FIELDS.getMessage());
		}

		for (Field field : fields) {
			Class<?> klass = field.getType();
			boolean isPattern = this.getManager().isPattern(klass);
			boolean isNull = ReflectionUtil.get(field, instance) == null;

			if (isPattern && isNull) {
				ReflectionUtil.set(this.getManager().getValue(klass), field, instance);
			}
		}

		this.getSuccessor().set(instance);
	}

}
