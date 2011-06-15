package br.com.wave.populator.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.wave.populator.core.examples.ClasseComAtributosPadrao;
import br.com.wave.populator.enums.FixedPatternEnum;

public class PatternManagedTest {

	private PatternManager manager;

	@Before
	public void setUp() {
		this.manager = PatternManager.getInstance();
	}

	@Test
	public void deveAdicionarUmaClasseComoPadrao() {
		ClasseComAtributosPadrao instance = new ClasseComAtributosPadrao();

		this.manager.addPattern(ClasseComAtributosPadrao.class, instance);

		assertTrue(this.manager.hasPattern(ClasseComAtributosPadrao.class));
	}

	@Test
	public void deveRetornarVerdadeiroSeAClasseForPadrao() {
		assertTrue(this.manager.hasPattern(String.class));
	}

	@Test
	public void deveRetornarFalsoSeAClasseNaoForPadrao() {
		assertFalse(this.manager.hasPattern(ClasseComAtributosPadrao.class));
	}

	@Test
	public void deveRetornarOValorPadraoFixo() {
		assertEquals(FixedPatternEnum.STRING.getValue(), this.manager.getValue(String.class));
	}

	@Test
	public void deveRetornarOValorPadraoDeUmaClasseAdicionada() {
		ClasseComAtributosPadrao instance = new ClasseComAtributosPadrao();

		this.manager.addPattern(ClasseComAtributosPadrao.class, instance);

		assertEquals(instance, this.manager.getValue(ClasseComAtributosPadrao.class));
	}

	@Test
	public void deveRetornarNuloSeNaoHouverPadrao() {
		assertNull(this.manager.getValue(ClasseComAtributosPadrao.class));
	}

	@Test
	public void deveExcluirOsValoresPadroesAdicionados() {
		ClasseComAtributosPadrao instance = new ClasseComAtributosPadrao();

		this.manager.addPattern(ClasseComAtributosPadrao.class, instance);

		this.manager.restore();

		assertFalse(this.manager.hasPattern(ClasseComAtributosPadrao.class));
	}

	@After
	public void tearDown() {
		this.manager.restore();
	}

}
