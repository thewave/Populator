package br.com.wave.populator.scanners;

import java.lang.reflect.Field;

import br.com.wave.populator.core.Scanner;
import br.com.wave.populator.enums.FixedPatternEnum;

public class FixedChecker extends Checker {

	public FixedChecker(Scanner scanner) {
		super(scanner);
	}

	@Override
	public <T> boolean isFilled(Field field, T instance) {
		boolean isFixedPattern = FixedPatternEnum.isFixedPattern(field.getType());
		if (isFixedPattern) {
			return true;
		}
		return this.getSuccessor().isFilled(field, instance);
	}

}
