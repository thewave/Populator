package br.com.wave.populator.enums;

public enum ErrorEnum {

	NULL("error.message.null"),
	NOT_SERIALIZABLE("error.message.notSerializable"),
	NOT_PERSISTENT_FIELDS("error.message.notPersistentFields"),
	TYPE_UNEXPECTED("error.message.typeUnexpected");

	private String message;

	private ErrorEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
