package br.com.wave.populator.scanners;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.wave.populator.core.Scanner;
import br.com.wave.populator.examples.checker.ClasseComAtributoFixo;
import br.com.wave.populator.utils.ReflectionUtil;

public class NullCheckerTest {
	
	private Checker checker;
	
	@Before
	public void setUp() {
		Scanner scanner = mock(Scanner.class);
		this.checker = new NullChecker(scanner);
	}
	
	@Test
	public void deveRetornarFalsoSeOAtributoEstiverNulo() {
		ClasseComAtributoFixo instance = new ClasseComAtributoFixo();
		
		assertFalse(this.checker.isFilled(ReflectionUtil.getField("atributo", instance.getClass()), instance));
	}
	
	@Test
	public void deveRetornarVerdadeiroSeOVerificadorChamarSeuSucessor() {
		ClasseComAtributoFixo instance = new ClasseComAtributoFixo();
		instance.setAtributo("Teste");
		
		Field field = ReflectionUtil.getField("atributo", instance.getClass());
		
		Checker sucessor = mock(Checker.class);
		when(sucessor.isFilled(field, instance)).thenReturn(Boolean.TRUE);
		
		this.checker.setSuccessor(sucessor);
		
		assertTrue(this.checker.isFilled(field, instance));
	}
	
	@After
	public void tearDown() {
		this.checker = null;
	}

}
