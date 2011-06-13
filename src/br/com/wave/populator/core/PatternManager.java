package br.com.wave.populator.core;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import br.com.wave.populator.enums.FixedPatternEnum;

public class PatternManager {

	private static PatternManager instance;

	private static Map<Class<?>, Object> fixedPatterns;

	private Map<Class<?>, Object> addedPatterns;

	static {
		fixedPatterns = new HashMap<Class<?>, Object>();

		FixedPatternEnum[] enumerations = FixedPatternEnum.values();
		for (FixedPatternEnum enumeration : enumerations) {
			fixedPatterns.put(enumeration.getType(), enumeration.getValue());
		}
	}

	private PatternManager() {
		this.addedPatterns = new LinkedHashMap<Class<?>, Object>();
	}

	public static PatternManager getInstance() {
		if (instance == null) {
			instance = new PatternManager();
		}

		return instance;
	}

	public void addPattern(Class<?> klass, Object instance) {
		this.addedPatterns.put(klass, instance);
	}

	public boolean isPattern(Class<?> klass) {
		return fixedPatterns.containsKey(klass) || this.addedPatterns.containsKey(klass);
	}

	public Object getValue(Class<?> klass) {
		if (fixedPatterns.get(klass) != null) {
			return fixedPatterns.get(klass);
		}

		return this.addedPatterns.get(klass);
	}

	public void restore() {
		this.addedPatterns = new LinkedHashMap<Class<?>, Object>();
	}

	public Map<Class<?>, Object> getAddedPatterns() {
		return addedPatterns;
	}

}
