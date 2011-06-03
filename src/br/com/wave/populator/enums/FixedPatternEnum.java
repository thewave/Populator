package br.com.wave.populator.enums;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

public enum FixedPatternEnum {
	
	STRING(String.class, String.valueOf("")),
	INTEGER(Integer.class, Integer.valueOf(0)),
	LONG(Long.class, Long.valueOf(0)),
	BIG_DECIMAL(BigDecimal.class, BigDecimal.ZERO),
	BOOLEAN(Boolean.class, Boolean.FALSE),
	CALENDAR(Calendar.class, Calendar.getInstance()),
	BYTE_ARRAY(byte[].class, new byte[0]);
	
	private Class<?> type;
	
	private Serializable value;

	private FixedPatternEnum(Class<?> type, Serializable value) {
		this.type = type;
		this.value = value;
	}
	
	public static boolean isFixedPattern(Class<?> klass) {
		FixedPatternEnum[] enumerations = FixedPatternEnum.values();
		for (FixedPatternEnum enumeration : enumerations) {
			if (enumeration.getType().equals(klass)) {
				return true;
			}
		}
		
		return false;
	}
	
	public Class<?> getType() {
		return type;
	}

	public Serializable getValue() {
		return value;
	}

}
