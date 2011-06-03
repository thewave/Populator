package br.com.wave.populator.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.wave.populator.enums.FixedPatternEnum;
import br.com.wave.populator.examples.AtributosFixos;

public class PatternManagedTest {
	
	private PatternManager manager;
	
	@Before
	public void setUp() {
		this.manager = new PatternManager();
	}
	
	@Test
	public void deveRetornarVerdadeiroSeAClasseEstiverAssociada() {
		assertTrue(this.manager.isPattern(String.class));
	}
	
	@Test
	public void deveRetornarFalsoSeAClasseNaoEstiverAssociada() {
		assertFalse(this.manager.isPattern(AtributosFixos.class));
	}
	
	@Test
	public void deveRetornarOValorPadrao() {
		assertEquals(FixedPatternEnum.STRING.getValue(), this.manager.getValue(String.class));
	}
	
	@Test
	public void deveRetornarVerdadeiroSeAClasseAForAssociada() {
		this.manager.addPattern(AtributosFixos.class, new AtributosFixos());
		
		assertTrue(this.manager.isPattern(AtributosFixos.class));
	}
	
	@Test
	public void deveRetornarOValorDoObjeto() {
		AtributosFixos a = new AtributosFixos();
		
		this.manager.addPattern(AtributosFixos.class, a);
		assertEquals(a, this.manager.getValue(AtributosFixos.class));
	}
	
	@After
	public void tearDown() {
		this.manager = null;
	}

}
