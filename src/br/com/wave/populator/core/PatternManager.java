package br.com.wave.populator.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import br.com.wave.populator.enums.FixedPatternEnum;

public class PatternManager {
	
	private static Map<Class<?>, Serializable> fixedMap;
	
	private Map<Class<?>, Serializable> addedMap;
	
	static {
		fixedMap = new HashMap<Class<?>, Serializable>();
		
		FixedPatternEnum[] enumerations = FixedPatternEnum.values();
		for (FixedPatternEnum enumeration : enumerations) {
			fixedMap.put(enumeration.getType(), enumeration.getValue());
		}
	}
	
	public PatternManager() {
		this.addedMap = new HashMap<Class<?>, Serializable>();
	}
	
	public <T extends Serializable> void addPattern(Class<?> klass, T instance) {
		this.addedMap.put(klass, instance);
	}
	
	public boolean isFixedPattern(Class<?> klass) {
		return fixedMap.containsKey(klass);
	}

	public boolean isPattern(Class<?> klass) {
		return fixedMap.containsKey(klass) || this.addedMap.containsKey(klass);
	}

	public Serializable getValue(Class<?> klass) {
		if (fixedMap.get(klass) != null) {
			return fixedMap.get(klass);
		}
		
		return this.addedMap.get(klass);
	}

}
