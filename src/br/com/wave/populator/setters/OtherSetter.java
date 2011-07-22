package br.com.wave.populator.setters;

import java.lang.reflect.Field;
import java.util.List;

import br.com.brasilti.utils.reflection.ReflectionUtil;
import br.com.wave.populator.core.Filler;
import br.com.wave.populator.enums.ErrorEnum;
import br.com.wave.populator.exceptions.PopulatorException;

public class OtherSetter extends Setter {

	public OtherSetter(Filler filler) {
		super(filler);
	}

	@Override
	public <T> void set(T instance) throws PopulatorException {
		List<Field> fields = ReflectionUtil.getPersistentFields(instance.getClass());
		for (Field field : fields) {
			Class<?> klass = field.getType();
			boolean isNotPattern = !this.getManager().hasPattern(klass);
			boolean isNotCollection = !ReflectionUtil.isCollection(klass);

			if (isNotPattern && isNotCollection) {
				Object value = ReflectionUtil.get(field, instance);
				if (value == null) {
					try {
						value = klass.newInstance();
						ReflectionUtil.set(value, field, instance);
					} catch (InstantiationException e) {
						throw new PopulatorException(ErrorEnum.TYPE_UNEXPECTED, field.getName(), instance.getClass().getName());
					} catch (IllegalAccessException e) {
						throw new PopulatorException(e.getMessage());
					}
				}

				this.getFiller().fill(value);
			}
		}
	}

}
