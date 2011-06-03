package br.com.wave.populator.scanners;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.wave.populator.core.Scanner;
import br.com.wave.populator.examples.checker.ClasseComAtributoFixo;
import br.com.wave.populator.examples.checker.ClasseComColecao;
import br.com.wave.populator.examples.checker.ClasseComColecaoDeObjetos;
import br.com.wave.populator.exceptions.PopulatorException;
import br.com.wave.populator.utils.ReflectionUtil;

public class CollectionCheckerTest {
	
	private Scanner scanner;
	
	private Checker checker;
	
	@Before
	public void setUp() {
		this.scanner = mock(Scanner.class);
		this.checker = new CollectionChecker(this.scanner);
	}
	
	@Test
	public void deveRetornarVerdadeiroSeOsAtributosDosObjetosDaColecaoDaInstanciaEstiveremPreenchidos() throws PopulatorException {
		ClasseComColecao instance = new ClasseComColecao();
		
		String valor = "Teste";
		instance.setColecao(Arrays.asList(valor));
		
		Field field = ReflectionUtil.getField("colecao", instance.getClass());
		
		when(this.scanner.scan(valor)).thenReturn(new ArrayList<Field>());
		
		assertTrue(this.checker.isFilled(field, instance));
	}
	
	@Test
	public void deveRetornarFalsoSeQualquerAtributoDosObjetosDaColecaoDaInstanciaNaoEstiveremPreenchidos() throws PopulatorException {
		ClasseComColecaoDeObjetos instance = new ClasseComColecaoDeObjetos();
		
		ClasseComAtributoFixo elemento = new ClasseComAtributoFixo();
		instance.setClassesComAtributoFixos(Arrays.asList(elemento));
		
		Field field = ReflectionUtil.getField("classesComAtributoFixos", instance.getClass());
		
		when(this.scanner.scan(elemento)).thenReturn(Arrays.asList(field));
		
		assertFalse(this.checker.isFilled(field, instance));
	}
	
	@Test
	public void deveRetornarFalsoSeOObjetoDaInstanciaNaoForUmaColecao() throws PopulatorException {
		ClasseComAtributoFixo instance = new ClasseComAtributoFixo();
		
		Field field = ReflectionUtil.getField("atributo", instance.getClass());
		
		assertFalse(this.checker.isFilled(field, instance));
	}
	
	@After
	public void tearDown() {
		this.checker = null;
		this.scanner = null;
	}


}
