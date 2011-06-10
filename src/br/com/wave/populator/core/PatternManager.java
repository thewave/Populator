package br.com.wave.populator.core;

import java.util.HashMap;
import java.util.Map;

import br.com.wave.populator.enums.FixedPatternEnum;

public class PatternManager {

	private static PatternManager instance;

	private static Map<Class<?>, Object> fixedMap;

	private Map<Class<?>, Object> addedMap;

	static {
		fixedMap = new HashMap<Class<?>, Object>();

		FixedPatternEnum[] enumerations = FixedPatternEnum.values();
		for (FixedPatternEnum enumeration : enumerations) {
			fixedMap.put(enumeration.getType(), enumeration.getValue());
		}
	}

	private PatternManager() {
		this.addedMap = new HashMap<Class<?>, Object>();
	}

	public static PatternManager getInstance() {
		if (instance == null) {
			instance = new PatternManager();
		}

		return instance;
	}

	public void addPattern(Class<?> klass, Object instance) {
		this.addedMap.put(klass, instance);
	}

	public boolean isPattern(Class<?> klass) {
		return fixedMap.containsKey(klass) || this.addedMap.containsKey(klass);
	}

	public Object getValue(Class<?> klass) {
		if (fixedMap.get(klass) != null) {
			return fixedMap.get(klass);
		}

		return this.addedMap.get(klass);
	}

	public void restore() {
		this.addedMap = new HashMap<Class<?>, Object>();
	}

}
