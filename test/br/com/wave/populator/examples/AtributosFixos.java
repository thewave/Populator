package br.com.wave.populator.examples;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

public class AtributosFixos implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static String staticField;
	
	private transient String transientField;
	
	private static transient String staticAndTransientField;

	private String stringField;
	
	private Integer integerField;
	
	private Long longField;
	
	private BigDecimal bigDecimalField;
	
	private Boolean booleanField;
	
	private Calendar calendarField;
	
	private byte[] byteField;

	public static String getStaticField() {
		return staticField;
	}

	public String getTransientField() {
		return transientField;
	}

	public static String getStaticAndTransientField() {
		return staticAndTransientField;
	}

	public String getStringField() {
		return stringField;
	}

	public void setStringField(String stringField) {
		this.stringField = stringField;
	}

	public Integer getIntegerField() {
		return integerField;
	}

	public Long getLongField() {
		return longField;
	}

	public BigDecimal getBigDecimalField() {
		return bigDecimalField;
	}

	public Boolean getBooleanField() {
		return booleanField;
	}

	public Calendar getCalendarField() {
		return calendarField;
	}

	public byte[] getByteField() {
		return byteField;
	}
	
}
