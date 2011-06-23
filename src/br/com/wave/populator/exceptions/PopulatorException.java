package br.com.wave.populator.exceptions;

import br.com.wave.populator.enums.ErrorEnum;

/**
 * Exception utilizada para apresentar as mensagens de erro.
 * 
 * @see br.com.wave.populator.enums.ErrorEnum
 * 
 * @author Benedito Barbosa
 * @author Christian Peixoto
 * 
 */
public class PopulatorException extends Exception {

	private static final long serialVersionUID = 1L;

	private ErrorEnum errorEnum;

	private Object[] params;

	public PopulatorException(String message) {
		super(message);
	}

	public PopulatorException(ErrorEnum errorEnum, Object... params) {
		this.errorEnum = errorEnum;
		this.params = params;
	}

	@Override
	public String getMessage() {
		if (this.errorEnum == null) {
			return super.getMessage();
		}

		return this.errorEnum.getMessage(this.params);
	}

}
