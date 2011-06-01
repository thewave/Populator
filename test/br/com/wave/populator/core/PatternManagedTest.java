package br.com.wave.populator.core;

import br.com.wave.populator.core.PatternManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.wave.populator.enums.FixedPatternEnum;
import br.com.wave.populator.examples.A;

public class PatternManagedTest {
	
	private PatternManager manager;
	
	@Before
	public void setUp() {
		this.manager = new PatternManager();
	}
	
	@Test
	public void deveRetornarVerdadeiroSeAClasseForDefault() {
		assertTrue(this.manager.isFixedPattern(String.class));
	}
	
	@Test
	public void deveRetornarFalsoSeAClasseNaoForDefault() {
		assertFalse(this.manager.isFixedPattern(A.class));
	}
	
	@Test
	public void deveRetornarVerdadeiroSeAClasseEstiverAssociada() {
		assertTrue(this.manager.isPattern(String.class));
	}
	
	@Test
	public void deveRetornarFalsoSeAClasseNaoEstiverAssociada() {
		assertFalse(this.manager.isPattern(A.class));
	}
	
	@Test
	public void deveRetornarOValorPadrao() {
		assertEquals(FixedPatternEnum.STRING.getValue(), this.manager.getValue(String.class));
	}
	
	@Test
	public void deveRetornarFalsoSeAClasseANaoForDefault() {
		this.manager.addPattern(A.class, new A());
		
		assertFalse(this.manager.isFixedPattern(A.class));
	}
	
	@Test
	public void deveRetornarVerdadeiroSeAClasseAForAssociada() {
		this.manager.addPattern(A.class, new A());
		
		assertTrue(this.manager.isPattern(A.class));
	}
	
	@Test
	public void deveRetornarOValorDoObjeto() {
		A a = new A();
		
		this.manager.addPattern(A.class, a);
		assertEquals(a, this.manager.getValue(A.class));
	}
	
	@After
	public void tearDown() {
		this.manager = null;
	}

}
