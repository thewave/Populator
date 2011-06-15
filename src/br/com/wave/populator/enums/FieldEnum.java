package br.com.wave.populator.enums;

public enum FieldEnum {

	ID("id"),
	VERSION("version");

	private String value;

	private FieldEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
