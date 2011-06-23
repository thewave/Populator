package br.com.wave.populator.enums;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public enum ErrorEnum {

	NULL("error.message.null"),
	NOT_SERIALIZABLE("error.message.notSerializable"),
	NOT_PERSISTENT_FIELDS("error.message.notPersistentFields"),
	TYPE_UNEXPECTED("error.message.typeUnexpected");

	private String key;

	private ErrorEnum(String key) {
		this.key = key;
	}
	
	public String getMessage(Object... params) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

		String value = bundle.getString(this.key);
		
		return new MessageFormat(value).format(params);
	}

}
