package br.com.wave.populator.scanners;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

import br.com.wave.populator.core.Scanner;
import br.com.wave.populator.exceptions.PopulatorException;

public class ObjectChecker extends Checker{

	public ObjectChecker(Scanner scanner) {
		super(scanner);
	}

	@Override
	public <T> boolean isFilled(Field field, T instance) {
        boolean isNotCollection = !field.getType().equals(Collection.class);
        if(isNotCollection) {
        	try {
                boolean accessible = field.isAccessible();
                field.setAccessible(true);

                List<Field> fields = this.getScanner().scan(field.get(instance));
    			
    			field.setAccessible(accessible);

				return fields.isEmpty();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (PopulatorException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
        }		
		return this.getSuccessor().isFilled(field, instance);
	}

}
