package br.com.wave.populator.scanners;

import java.lang.reflect.Field;

import br.com.wave.populator.core.Scanner;

public class NullChecker extends Checker{

	public NullChecker(Scanner scanner) {
		super(scanner);
	}

	@Override
	public <T> boolean isFilled(Field field, T instance) {
		try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);

			Object valor = field.get(instance);
			
			field.setAccessible(accessible);
			if(valor == null) {
				return false;
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return this.getSuccessor().isFilled(field, instance);
	}

}
