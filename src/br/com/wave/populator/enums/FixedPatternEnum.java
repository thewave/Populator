package br.com.wave.populator.enums;

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

	private Object value;

	private FixedPatternEnum(Class<?> type, Object value) {
		this.type = type;
		this.value = value;
	}

	public Class<?> getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}

}
