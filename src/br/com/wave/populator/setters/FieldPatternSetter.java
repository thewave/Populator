package br.com.wave.populator.setters;

import java.lang.reflect.Field;
import java.util.List;

import br.com.wave.populator.core.Filler;
import br.com.wave.populator.enums.ErrorEnum;
import br.com.wave.populator.enums.FieldEnum;
import br.com.wave.populator.exceptions.PopulatorException;
import br.com.wave.utils.reflection.ReflectionUtil;

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
			boolean isPattern = this.getManager().hasPattern(klass);
			boolean isNull = ReflectionUtil.get(field, instance) == null;

			if (isPattern && isNull && isFillable(field)) {
				ReflectionUtil.set(this.getManager().getValue(klass), field, instance);
			}
		}

		this.getSuccessor().set(instance);
	}

	private boolean isFillable(Field field) {
		String fieldName = field.getName();
		if (fieldName.equals(FieldEnum.ID.getValue()) || fieldName.equals(FieldEnum.VERSION.getValue())) {
			return false;
		}

		return true;
	}

}
