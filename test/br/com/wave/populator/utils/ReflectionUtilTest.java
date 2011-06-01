package br.com.wave.populator.utils;

import br.com.wave.populator.utils.ReflectionUtil;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.wave.populator.examples.E;
import br.com.wave.populator.examples.H;
import br.com.wave.populator.examples.NotSerializable;
import br.com.wave.populator.examples.Serializable;

@SuppressWarnings("unused")
public class ReflectionUtilTest {
	
	private static String atributoEstatico;
	
	private String atributoNaoEstatico;
	
	private transient String atributoTransiente;
	
	private String atributoNaoTransiente;
	
	private String atributoDoTipoString;
	
	@Test
	public void deveRetornarVerdadeiroSeOAtributoForEstatico() {
		try {
			assertTrue(ReflectionUtil.isStatic(this.getClass().getDeclaredField("atributoEstatico")));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deveRetornarFalsoSeOAtributoNaoForEstatico() {
		try {
			assertFalse(ReflectionUtil.isStatic(this.getClass().getDeclaredField("atributoNaoEstatico")));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deveRetornarVerdadeiroSeOAtributoForTransiente() {
		try {
			assertTrue(ReflectionUtil.isTransient(this.getClass().getDeclaredField("atributoTransiente")));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deveRetornarFalsoSeOAtributoNaoForTransiente() {
		try {
			assertFalse(ReflectionUtil.isTransient(this.getClass().getDeclaredField("atributoNaoTransiente")));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deveRetornarVerdadeiroSeAClasseForSerializable() {
		assertTrue(ReflectionUtil.isSerializable(Serializable.class));
	}
	
	@Test
	public void deveRetornarFalsoSeAClasseNaoForSerializable() {
		assertFalse(ReflectionUtil.isSerializable(NotSerializable.class));
	}
	
	@Test
	public void deveRetornarVerdadeiroSeOAtributoTiverUmAtributoDaMesmaClasseDaInstancia() throws SecurityException, NoSuchFieldException {
		H h = new H();
		
		assertTrue(ReflectionUtil.contains(h.getClass().getDeclaredField("i"), h.getClass()));
	}
	
	@Test
	public void deveRetornarFalsoSeOAtributoNaoTiverUmAtributoDaMesmaClasseDaInstancia() throws SecurityException, NoSuchFieldException {
		E e = new E();
		
		assertFalse(ReflectionUtil.contains(e.getClass().getDeclaredField("f"), e.getClass()));
	}

}
