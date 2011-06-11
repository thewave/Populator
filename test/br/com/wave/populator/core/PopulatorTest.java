package br.com.wave.populator.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.wave.populator.core.examples.ClasseComAtributosPadrao;
import br.com.wave.populator.core.examples.ClasseNaoSerializavel;
import br.com.wave.populator.enums.ErrorEnum;
import br.com.wave.populator.enums.FixedPatternEnum;
import br.com.wave.populator.exceptions.PopulatorException;

public class PopulatorTest {

	private Populator populator;

	@Before
	public void setUp() {
		this.populator = new Populator();
	}

	@Test(expected = PopulatorException.class)
	public void deveLancarUmaExcecaoSeAInstanciaForNulaException() throws PopulatorException {
		this.populator.populate(null);
	}

	@Test
	public void deveLancarUmaExcecaoSeAInstanciaForNula() {
		try {
			this.populator.populate(null);
		} catch (PopulatorException e) {
			assertEquals(ErrorEnum.NULL.getMessage(), e.getMessage());
		}
	}

	@Test(expected = PopulatorException.class)
	public void deveLancarExcecaoSeAInstanciaNaoForSerializavelException() throws PopulatorException {
		this.populator.populate(ClasseNaoSerializavel.class);
	}

	@Test
	public void deveLancarExcecaoSeAInstanciaNaoForSerializavel() {
		try {
			this.populator.populate(ClasseNaoSerializavel.class);
		} catch (PopulatorException e) {
			assertEquals(ErrorEnum.NOT_SERIALIZABLE.getMessage(), e.getMessage());
		}
	}

	@Test
	public void deveDelegarAoFillerAResponsabilidadeDePreencherUmaInstancia() throws PopulatorException {
		ClasseComAtributosPadrao instance = this.populator.populate(ClasseComAtributosPadrao.class);

		assertEquals(FixedPatternEnum.STRING.getValue(), instance.getStringField());
		assertEquals(FixedPatternEnum.INTEGER.getValue(), instance.getIntegerField());
		assertEquals(FixedPatternEnum.LONG.getValue(), instance.getLongField());
		assertEquals(FixedPatternEnum.BIG_DECIMAL.getValue(), instance.getBigDecimalField());
		assertEquals(FixedPatternEnum.BOOLEAN.getValue(), instance.getBooleanField());
		assertEquals(FixedPatternEnum.CALENDAR.getValue(), instance.getCalendarField());
		assertEquals(FixedPatternEnum.BYTE_ARRAY.getValue(), instance.getByteField());
	}

	@Test
	public void deveDelegarAoPatternManagerAResponsabilidadeDeGerenciarOsPadroesAdicionados() throws PopulatorException {
		ClasseComAtributosPadrao instance = new ClasseComAtributosPadrao();

		this.populator.addPattern(ClasseComAtributosPadrao.class, instance);

		assertTrue(PatternManager.getInstance().isPattern(ClasseComAtributosPadrao.class));
		assertEquals(instance, PatternManager.getInstance().getValue(ClasseComAtributosPadrao.class));
	}

	@After
	public void tearDown() {
		this.populator = null;
	}

}
