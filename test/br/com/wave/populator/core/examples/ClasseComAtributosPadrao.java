package br.com.wave.populator.core.examples;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

public class ClasseComAtributosPadrao implements Serializable {

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

	public void setIntegerField(Integer integerField) {
		this.integerField = integerField;
	}

	public Long getLongField() {
		return longField;
	}

	public void setLongField(Long longField) {
		this.longField = longField;
	}

	public BigDecimal getBigDecimalField() {
		return bigDecimalField;
	}

	public void setBigDecimalField(BigDecimal bigDecimalField) {
		this.bigDecimalField = bigDecimalField;
	}

	public Boolean getBooleanField() {
		return booleanField;
	}

	public void setBooleanField(Boolean booleanField) {
		this.booleanField = booleanField;
	}

	public Calendar getCalendarField() {
		return calendarField;
	}

	public void setCalendarField(Calendar calendarField) {
		this.calendarField = calendarField;
	}

	public byte[] getByteField() {
		return byteField;
	}

	public void setByteField(byte[] byteField) {
		this.byteField = byteField;
	}

}
