package br.com.wave.populator.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import br.com.wave.populator.utils.examples.ClasseComAtributosPadrao;
import br.com.wave.populator.utils.examples.NotSerializable;
import br.com.wave.populator.utils.examples.Serializable;

@SuppressWarnings("unused")
public class ReflectionUtilTest {

	private String atributo;

	private static String atributoEstatico;

	private String atributoNaoEstatico;

	private transient String atributoTransiente;

	private String atributoNaoTransiente;

	private static transient String atributoEstaticoETransiente;

	private String atributoDoTipoString;

	private Collection<String> colecao;

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
	public void deveRetornarVerdadeiroSeOAtributoForPersistente() {
		try {
			assertTrue(ReflectionUtil.isPersistent(this.getClass().getDeclaredField("atributo")));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deveRetornarFalsoSeOAtributoNaoForPersistente() {
		try {
			assertFalse(ReflectionUtil.isPersistent(this.getClass().getDeclaredField("atributoEstatico")));
			assertFalse(ReflectionUtil.isPersistent(this.getClass().getDeclaredField("atributoTransiente")));
			assertFalse(ReflectionUtil.isPersistent(this.getClass().getDeclaredField("atributoEstaticoETransiente")));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deveRetornarOsAtributosPersistentesDeUmaClasse() {
		List<Field> fields = ReflectionUtil.getPersistentFields(ReflectionUtilTest.class);

		assertEquals("atributo", fields.get(0).getName());
		assertEquals("atributoNaoEstatico", fields.get(1).getName());
		assertEquals("atributoNaoTransiente", fields.get(2).getName());
		assertEquals("atributoDoTipoString", fields.get(3).getName());
		assertEquals("colecao", fields.get(4).getName());

		assertEquals(5, fields.size());
	}

	@Test
	public void deveRetornarVerdadeiroSeAClasseForSerializable() {
		assertTrue(ReflectionUtil.implementz(Serializable.class, java.io.Serializable.class));
	}

	@Test
	public void deveRetornarFalsoSeAClasseNaoForSerializable() {
		assertFalse(ReflectionUtil.implementz(NotSerializable.class, java.io.Serializable.class));
	}

	@Test
	public void deveRetornarUmValorDeUmAtributoDeUmaClasse() throws SecurityException, NoSuchFieldException, InstantiationException, IllegalAccessException {
		String valor = "Teste";

		Field field = ReflectionUtilTest.class.getDeclaredField("atributo");
		ReflectionUtilTest instance = ReflectionUtilTest.class.newInstance();
		instance.atributo = valor;

		assertEquals(valor, ReflectionUtil.get(field, instance));
	}

	@Test
	public void deveAtribuirUmValorAUmAtributoDeUmaClasse() throws SecurityException, NoSuchFieldException, InstantiationException, IllegalAccessException {
		Field field = ReflectionUtilTest.class.getDeclaredField("atributo");
		ReflectionUtilTest instance = ReflectionUtilTest.class.newInstance();
		ReflectionUtil.set("Teste", field, instance);

		assertNotNull(field.get(instance));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveLancarUmaExcecaoSeAsInstanciasForemDeClassesDiferentes() {
		ClasseComAtributosPadrao origin = new ClasseComAtributosPadrao();
		String target = new String();

		ReflectionUtil.copy(origin, target);
	}

	@Test
	public void deveCopiarOValorDosAtributosDeUmaInstanciaParaOutraDaMesmaClasse() {
		String stringValue = "Origem";
		Integer integerValue = 1000;
		BigDecimal bigDecimalValue = BigDecimal.valueOf(1000);
		Boolean booleanValue = Boolean.TRUE;
		byte[] byteValue = new byte[integerValue];

		ClasseComAtributosPadrao origin = new ClasseComAtributosPadrao();
		origin.setStringField(stringValue);
		origin.setIntegerField(integerValue);
		origin.setBigDecimalField(bigDecimalValue);
		origin.setBooleanField(booleanValue);
		origin.setByteField(byteValue);

		ClasseComAtributosPadrao target = new ClasseComAtributosPadrao();
		target.setStringField("Destino");

		ReflectionUtil.copy(origin, target);

		assertEquals(origin.getStringField(), target.getStringField());
		assertEquals(origin.getIntegerField(), target.getIntegerField());
		assertEquals(origin.getLongField(), target.getLongField());
		assertEquals(origin.getBigDecimalField(), target.getBigDecimalField());
		assertEquals(origin.getBooleanField(), target.getBooleanField());
		assertEquals(origin.getCalendarField(), target.getCalendarField());
		assertEquals(origin.getByteField(), target.getByteField());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveLancarUmaExcecaoSeOAtributoNaoForUmaColecao() {
		Field field = this.getField("atributo", ReflectionUtilTest.class);

		ReflectionUtil.getTypeOfElements(field);
	}

	@Test
	public void deveRetornarOTipoDosElementosDeUmaColecao() {
		Field field = this.getField("colecao", ReflectionUtilTest.class);

		assertEquals(String.class, ReflectionUtil.getTypeOfElements(field));
	}

	@Test
	public void deveRetornarVerdadeiroSeAClasseForUmaColecao() {
		assertTrue(ReflectionUtil.isCollection(ArrayList.class));
		assertTrue(ReflectionUtil.isCollection(List.class));
		assertTrue(ReflectionUtil.isCollection(Collection.class));
	}

	@Test
	public void deveRetornarFalsoSeAClasseNaoForUmaColecao() {
		assertFalse(ReflectionUtil.isCollection(Set.class));
		assertFalse(ReflectionUtil.isCollection(Map.class));
	}

	private Field getField(String fieldName, Class<?> klass) {
		try {
			return klass.getDeclaredField(fieldName);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

		return null;
	}

}
