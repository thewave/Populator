package br.com.wave.populator.scanners;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.wave.populator.core.Scanner;
import br.com.wave.populator.examples.checker.ClasseComAtributoFixo;
import br.com.wave.populator.examples.checker.ClasseComColecao;
import br.com.wave.populator.examples.checker.ClasseComObjeto;
import br.com.wave.populator.exceptions.PopulatorException;
import br.com.wave.populator.utils.ReflectionUtil;

public class ObjectCheckerTest {
	
	private Scanner scanner;
	
	private Checker checker;
	
	@Before
	public void setUp() {
		this.scanner = mock(Scanner.class);
		this.checker = new ObjectChecker(this.scanner);
	}
	
	@Test
	public void deveRetornarVerdadeiroSeOsAtributosDoObjetoDaInstanciaEstiveremPreenchidos() throws PopulatorException {
		ClasseComObjeto instance = new ClasseComObjeto();
		instance.setClasseComAtributo(new ClasseComAtributoFixo());
		
		Field field = ReflectionUtil.getField("classeComAtributo", instance.getClass());
		
		when(this.scanner.scan(instance)).thenReturn(new ArrayList<Field>());
		
		assertTrue(this.checker.isFilled(field, instance));
	}
	
	@Test
	public void deveRetornarFalsoSeOVerificadorChamarSeuSucessor() {
		ClasseComColecao instance = new ClasseComColecao();
		instance.setColecao(new ArrayList<String>());
		
		Field field = ReflectionUtil.getField("colecao", instance.getClass());
		
		Checker sucessor = mock(Checker.class);
		when(sucessor.isFilled(field, instance)).thenReturn(Boolean.FALSE);
		
		this.checker.setSuccessor(sucessor);
		
		assertFalse(this.checker.isFilled(field, instance));
	}
	
	@After
	public void tearDown() {
		this.checker = null;
		this.scanner = null;
	}

}
