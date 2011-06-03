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
import br.com.wave.populator.examples.checker.ClasseComObjeto;
import br.com.wave.populator.utils.ReflectionUtil;

public class FixedCheckerTest {
	
	private Checker checker;
	
	@Before
	public void setUp() {
		Scanner scanner = mock(Scanner.class);
		this.checker = new FixedChecker(scanner);
	}
	
	@Test
	public void deveRetornarVerdadeiroSeOAtributoForFixo() {
		ClasseComAtributoFixo instance = new ClasseComAtributoFixo();
		instance.setAtributo("Teste");
		
		assertTrue(this.checker.isFilled(ReflectionUtil.getField("atributo", instance.getClass()), instance));
	}
	
	@Test
	public void deveRetornarFalsoSeOVerificadorChamarSeuSucessor() {
		ClasseComObjeto instance = new ClasseComObjeto();
		
		Field field = ReflectionUtil.getField("classeComAtributo", instance.getClass());
		
		Checker sucessor = mock(Checker.class);
		when(sucessor.isFilled(field, instance)).thenReturn(Boolean.FALSE);
		
		this.checker.setSuccessor(sucessor);
		
		assertFalse(this.checker.isFilled(field, instance));
	}
	
	@After
	public void tearDown() {
		this.checker = null;
	}

}
