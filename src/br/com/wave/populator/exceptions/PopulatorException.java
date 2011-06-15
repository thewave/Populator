package br.com.wave.populator.exceptions;

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

	public PopulatorException(String message) {
		super(message);
	}

}
