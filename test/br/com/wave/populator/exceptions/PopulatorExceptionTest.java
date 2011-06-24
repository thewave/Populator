package br.com.wave.populator.exceptions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.wave.populator.enums.ErrorEnum;

public class PopulatorExceptionTest {

	@Test
	public void deveExibirMensagemDoErrorEnum() {
		try {
			throw new PopulatorException(ErrorEnum.NULL);
		} catch (PopulatorException e) {
			assertEquals(ErrorEnum.NULL.getMessage(),e.getMessage());
		}
	}

	@Test
	public void deveExibirMensagemGeral() {
		String message = "Mensagem de erro capturada";
		try {
			throw new PopulatorException(message);
		} catch (PopulatorException e) {
			assertEquals(message,e.getMessage());
		}
	}

}
