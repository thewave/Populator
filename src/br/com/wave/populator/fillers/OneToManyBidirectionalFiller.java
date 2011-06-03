package br.com.wave.populator.fillers;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import br.com.wave.populator.core.Populator;
import br.com.wave.populator.exceptions.PopulatorException;
import br.com.wave.populator.utils.ReflectionUtil;

public class OneToManyBidirectionalFiller extends Filler {

	public OneToManyBidirectionalFiller(Populator populator) {
		super(populator);
	}

	@Override
	public <T> void fill(Field field, T instance) throws PopulatorException {
		Class<?> klass = field.getType();
		
		if(klass.equals(Collection.class)) {
			ParameterizedType type = (ParameterizedType) field.getGenericType();
			Class<?> typeArgument = (Class<?>) type.getActualTypeArguments()[0];
			
			if (ReflectionUtil.contains(typeArgument, instance.getClass())) {
				try {
					Object value = typeArgument.newInstance();
					List<Field> fields = this.getPopulator().getScanner().scan(value);
					for (Field f : fields) {
						if (f.getType().equals(instance.getClass())) {
							boolean accessible = f.isAccessible();
							f.setAccessible(true);
							
							f.set(value, instance);
							
							f.setAccessible(accessible);
						}
					}
//					this.getPopulator().populate(value);
					
					field.set(instance, Arrays.asList(value));
				} catch (InstantiationException e) {
					throw new PopulatorException(e.getMessage());
				} catch (IllegalAccessException e) {
					throw new PopulatorException(e.getMessage());
				}
			} else {
				this.getSuccessor().fill(field, instance);
			}
		} else {
			this.getSuccessor().fill(field, instance);
		}
	}

}
