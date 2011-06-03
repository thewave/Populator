package br.com.wave.populator.scanners;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

import br.com.wave.populator.core.Scanner;
import br.com.wave.populator.exceptions.PopulatorException;

public class CollectionChecker extends Checker{

	public CollectionChecker(Scanner scanner) {
		super(scanner);
	}

	@Override
	public <T> boolean isFilled(Field field, T instance) {
        boolean isCollection = field.getType().equals(Collection.class);
        if(isCollection) {
        	try {
                boolean accessible = field.isAccessible();
                field.setAccessible(true);

        		Collection<?> elements = (Collection<?>) field.get(instance);
        		
        		field.setAccessible(accessible);
				for (Object element : elements) {
					List<Field> fields = this.getScanner().scan(element);
					if(!fields.isEmpty()) {
						return false; 
					}
				}
				return true;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (PopulatorException e) {
				e.printStackTrace();
			}
        }
		return false;
	}

}
