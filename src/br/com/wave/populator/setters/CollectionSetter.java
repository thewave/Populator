package br.com.wave.populator.setters;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import br.com.wave.populator.core.Filler;
import br.com.wave.populator.exceptions.PopulatorException;
import br.com.wave.utils.reflection.ReflectionUtil;

public class CollectionSetter extends Setter {

	public CollectionSetter(Filler filler) {
		super(filler);
	}

	@Override
	public <T> void set(T instance) throws PopulatorException {
		List<Field> fields = ReflectionUtil.getPersistentFields(instance.getClass());
		for (Field field : fields) {
			Class<?> klass = field.getType();
			boolean isNotPattern = !this.getManager().isPattern(klass);
			boolean isCollection = ReflectionUtil.isCollection(klass);

			if (isNotPattern && isCollection) {
				Collection<?> collection = (Collection<?>) ReflectionUtil.get(field, instance);

				if (collection == null || collection.isEmpty()) {
					this.addElements(field, instance);
				} else {
					for (Object element : collection) {
						this.getFiller().fill(element);
					}
				}
			}
		}

		this.getSuccessor().set(instance);
	}

	private <T> void addElements(Field field, T instance) throws PopulatorException {
		Class<?> typeOfElements = ReflectionUtil.getTypeOfElements(field);

		try {
			Object value = null;
			if (this.getManager().isPattern(typeOfElements)) {
				value = this.getManager().getValue(typeOfElements);
			} else {
				value = typeOfElements.newInstance();
				this.getFiller().fill(value);
			}

			ReflectionUtil.set(Arrays.asList(value), field, instance);
		} catch (InstantiationException e) {
			throw new PopulatorException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new PopulatorException(e.getMessage());
		}
	}

}
