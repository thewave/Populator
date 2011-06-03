package br.com.wave.populator.enums;

public enum ErrorEnum {
	
	NOT_SERIALIZABLE("error.message.notSerializable"),
	NULL("error.message.null");
	
	private String message;

	private ErrorEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
