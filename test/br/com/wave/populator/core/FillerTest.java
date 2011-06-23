package br.com.wave.populator.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.wave.populator.core.examples.ClasseComAtributoNaoPadrao;
import br.com.wave.populator.core.examples.ClasseComAtributoNaoPadraoBidirecional;
import br.com.wave.populator.core.examples.ClasseComAtributoNaoPadraoComAtributoNaoPadrao;
import br.com.wave.populator.core.examples.ClasseComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional;
import br.com.wave.populator.core.examples.ClasseComAtributosPadrao;
import br.com.wave.populator.core.examples.ClasseComColecaoDeAtributoNaoPadrao;
import br.com.wave.populator.core.examples.ClasseComColecaoDeAtributoNaoPadraoBidirecional;
import br.com.wave.populator.core.examples.ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional;
import br.com.wave.populator.core.examples.ClasseComColecaoDeAtributoPadrao;
import br.com.wave.populator.core.examples.ClasseComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional;
import br.com.wave.populator.core.examples.ClasseComIdEVersion;
import br.com.wave.populator.core.examples.ClasseComMapa;
import br.com.wave.populator.core.examples.ClasseSemAtributos;
import br.com.wave.populator.enums.ErrorEnum;
import br.com.wave.populator.enums.FixedPatternEnum;
import br.com.wave.populator.exceptions.PopulatorException;

public class FillerTest {

	private Filler filler;

	@Before
	public void setUp() {
		this.filler = new Filler();
	}

	@Test
	public void devePreencherUmaInstanciaComOValorPadraoFixo() throws PopulatorException {
		Serializable instance = new String();

		this.filler.fill(instance);

		assertEquals(FixedPatternEnum.STRING.getValue(), instance);
	}

	@Test
	public void devePreencherUmaInstanciaComOValorPadraoAdicionado() throws PopulatorException {
		ClasseComAtributosPadrao valorPadrao = new ClasseComAtributosPadrao();
		valorPadrao.setStringField("Teste");

		PatternManager.getInstance().addPattern(ClasseComAtributosPadrao.class, valorPadrao);

		ClasseComAtributosPadrao instance = new ClasseComAtributosPadrao();
		this.filler.fill(instance);

		assertEquals(valorPadrao.getStringField(), instance.getStringField());
	}

	@Test(expected = PopulatorException.class)
	public void deveLancarUmaExcecaoSeAInstanciaNaoTiverAtributosPersistentesException() throws PopulatorException {
		ClasseSemAtributos instance = new ClasseSemAtributos();

		this.filler.fill(instance);
	}

	@Test
	public void deveLancarUmaExcecaoSeAInstanciaNaoTiverAtributosPersistentes() {
		ClasseSemAtributos instance = new ClasseSemAtributos();

		try {
			this.filler.fill(instance);
		} catch (PopulatorException e) {
			assertEquals(ErrorEnum.NOT_PERSISTENT_FIELDS.getMessage(instance.getClass().getName()), e.getMessage());
		}
	}

	@Test
	public void devePreencherUmaInstanciaDeClasseComAtributosPadrao() throws PopulatorException {
		ClasseComAtributosPadrao instance = new ClasseComAtributosPadrao();

		this.filler.fill(instance);

		assertEquals(FixedPatternEnum.STRING.getValue(), instance.getStringField());
		assertEquals(FixedPatternEnum.INTEGER.getValue(), instance.getIntegerField());
		assertEquals(FixedPatternEnum.LONG.getValue(), instance.getLongField());
		assertEquals(FixedPatternEnum.BIG_DECIMAL.getValue(), instance.getBigDecimalField());
		assertEquals(FixedPatternEnum.BOOLEAN.getValue(), instance.getBooleanField());
		assertEquals(FixedPatternEnum.CALENDAR.getValue(), instance.getCalendarField());
		assertEquals(FixedPatternEnum.BYTE_ARRAY.getValue(), instance.getByteField());
	}

	@Test
	public void devePreencherApenasOsAtributosPovoaveisDeUmaInstanciaDeClasseComAtributosPadrao() throws PopulatorException {
		String valor = "Teste";

		ClasseComAtributosPadrao instance = new ClasseComAtributosPadrao();
		instance.setStringField(valor);

		this.filler.fill(instance);

		assertEquals(valor, instance.getStringField());
		assertEquals(FixedPatternEnum.INTEGER.getValue(), instance.getIntegerField());
		assertEquals(FixedPatternEnum.LONG.getValue(), instance.getLongField());
		assertEquals(FixedPatternEnum.BIG_DECIMAL.getValue(), instance.getBigDecimalField());
		assertEquals(FixedPatternEnum.BOOLEAN.getValue(), instance.getBooleanField());
		assertEquals(FixedPatternEnum.CALENDAR.getValue(), instance.getCalendarField());
		assertEquals(FixedPatternEnum.BYTE_ARRAY.getValue(), instance.getByteField());
	}

	@Test
	public void naoDevePreencherOsAtributosPovoadosDeUmaInstanciaDeClasseComAtributosPadrao() throws PopulatorException {
		String stringValue = "Teste";
		Integer integerValue = 1000;
		Long longValue = 1000L;
		BigDecimal bigDecimalValue = BigDecimal.valueOf(longValue);
		Boolean booleanValue = Boolean.TRUE;
		Calendar calendarValue = Calendar.getInstance();
		calendarValue.add(Calendar.YEAR, 1);
		byte[] byteValue = new byte[integerValue];

		ClasseComAtributosPadrao instance = new ClasseComAtributosPadrao();
		instance.setStringField(stringValue);
		instance.setIntegerField(integerValue);
		instance.setLongField(longValue);
		instance.setBigDecimalField(bigDecimalValue);
		instance.setBooleanField(booleanValue);
		instance.setCalendarField(calendarValue);
		instance.setByteField(byteValue);

		this.filler.fill(instance);

		assertEquals(stringValue, instance.getStringField());
		assertEquals(integerValue, instance.getIntegerField());
		assertEquals(longValue, instance.getLongField());
		assertEquals(bigDecimalValue, instance.getBigDecimalField());
		assertEquals(booleanValue, instance.getBooleanField());
		assertEquals(calendarValue, instance.getCalendarField());
		assertEquals(byteValue, instance.getByteField());
	}

	@Test
	public void devePreencherUmaInstanciaDeClasseComAtributosNaoPadrao() throws PopulatorException {
		ClasseComAtributoNaoPadrao instance = new ClasseComAtributoNaoPadrao();

		this.filler.fill(instance);

		ClasseComAtributosPadrao objetoComAtributosPadrao = instance.getClasseComAtributosPadrao();
		assertNotNull(objetoComAtributosPadrao);

		assertEquals(FixedPatternEnum.STRING.getValue(), objetoComAtributosPadrao.getStringField());
		assertEquals(FixedPatternEnum.INTEGER.getValue(), objetoComAtributosPadrao.getIntegerField());
		assertEquals(FixedPatternEnum.LONG.getValue(), objetoComAtributosPadrao.getLongField());
		assertEquals(FixedPatternEnum.BIG_DECIMAL.getValue(), objetoComAtributosPadrao.getBigDecimalField());
		assertEquals(FixedPatternEnum.BOOLEAN.getValue(), objetoComAtributosPadrao.getBooleanField());
		assertEquals(FixedPatternEnum.CALENDAR.getValue(), objetoComAtributosPadrao.getCalendarField());
		assertEquals(FixedPatternEnum.BYTE_ARRAY.getValue(), objetoComAtributosPadrao.getByteField());
	}

	@Test
	public void devePreencherApenasOsAtributosPovoaveisDeUmaInstanciaDeClasseComAtributosNaoPadrao() throws PopulatorException {
		ClasseComAtributoNaoPadrao instance = new ClasseComAtributoNaoPadrao();

		String stringValue = "Teste";
		ClasseComAtributosPadrao objetoComAtributosPadrao = new ClasseComAtributosPadrao();
		objetoComAtributosPadrao.setStringField(stringValue);
		instance.setClasseComAtributosPadrao(objetoComAtributosPadrao);

		this.filler.fill(instance);

		assertEquals(stringValue, objetoComAtributosPadrao.getStringField());
		assertEquals(FixedPatternEnum.INTEGER.getValue(), objetoComAtributosPadrao.getIntegerField());
		assertEquals(FixedPatternEnum.LONG.getValue(), objetoComAtributosPadrao.getLongField());
		assertEquals(FixedPatternEnum.BIG_DECIMAL.getValue(), objetoComAtributosPadrao.getBigDecimalField());
		assertEquals(FixedPatternEnum.BOOLEAN.getValue(), objetoComAtributosPadrao.getBooleanField());
		assertEquals(FixedPatternEnum.CALENDAR.getValue(), objetoComAtributosPadrao.getCalendarField());
		assertEquals(FixedPatternEnum.BYTE_ARRAY.getValue(), objetoComAtributosPadrao.getByteField());
	}

	@Test
	public void devePreencherUmaInstanciaDeClasseComAtributoNaoPadraoQueTemUmaClasseComAtributosNaoPadrao() throws PopulatorException {
		ClasseComAtributoNaoPadraoComAtributoNaoPadrao instance = new ClasseComAtributoNaoPadraoComAtributoNaoPadrao();

		this.filler.fill(instance);

		ClasseComAtributoNaoPadrao objetoComAtributoNaoPadrao = instance.getClasseComAtributoNaoPadrao();
		assertNotNull(objetoComAtributoNaoPadrao);
		ClasseComAtributosPadrao objetoComAtributosPadrao = objetoComAtributoNaoPadrao.getClasseComAtributosPadrao();
		assertNotNull(objetoComAtributosPadrao);

		assertEquals(FixedPatternEnum.STRING.getValue(), objetoComAtributosPadrao.getStringField());
		assertEquals(FixedPatternEnum.INTEGER.getValue(), objetoComAtributosPadrao.getIntegerField());
		assertEquals(FixedPatternEnum.LONG.getValue(), objetoComAtributosPadrao.getLongField());
		assertEquals(FixedPatternEnum.BIG_DECIMAL.getValue(), objetoComAtributosPadrao.getBigDecimalField());
		assertEquals(FixedPatternEnum.BOOLEAN.getValue(), objetoComAtributosPadrao.getBooleanField());
		assertEquals(FixedPatternEnum.CALENDAR.getValue(), objetoComAtributosPadrao.getCalendarField());
		assertEquals(FixedPatternEnum.BYTE_ARRAY.getValue(), objetoComAtributosPadrao.getByteField());
	}

	@Test
	public void devePreencherUmaInstanciaDeClasseComAtributoNaoPadraoBidirecional() throws PopulatorException {
		ClasseComAtributoNaoPadraoBidirecional instance = new ClasseComAtributoNaoPadraoBidirecional();

		this.filler.fill(instance);

		assertEquals(instance, instance.getClasseComAtributoNaoPadraoBidirecional().getClasseComAtributoNaoPadraoBidirecional());
	}

	@Test
	public void devePreencherUmaInstanciaDeClasseComColecaoNulaDeAtributoPadrao() throws PopulatorException {
		ClasseComColecaoDeAtributoPadrao instance = new ClasseComColecaoDeAtributoPadrao();

		this.filler.fill(instance);

		Collection<String> itens = instance.getColecao();
		assertFalse(itens.isEmpty());
		assertEquals(1, itens.size());

		for (String item : itens) {
			assertEquals(FixedPatternEnum.STRING.getValue(), item);
		}
	}

	@Test
	public void devePreencherUmaInstanciaDeClasseComColecaoVaziaDeAtributoPadrao() throws PopulatorException {
		ClasseComColecaoDeAtributoPadrao instance = new ClasseComColecaoDeAtributoPadrao();
		instance.setColecao(new ArrayList<String>());

		this.filler.fill(instance);

		Collection<String> itens = instance.getColecao();
		assertFalse(itens.isEmpty());
		assertEquals(1, itens.size());

		for (String item : itens) {
			assertEquals(FixedPatternEnum.STRING.getValue(), item);
		}
	}

	@Test
	public void devePreencherUmaInstanciaDeClasseComColecaoVaziaDeAtributoNaoPadrao() throws PopulatorException {
		ClasseComColecaoDeAtributoNaoPadrao instance = new ClasseComColecaoDeAtributoNaoPadrao();
		instance.setColecao(new ArrayList<ClasseComAtributoNaoPadrao>());

		this.filler.fill(instance);

		Collection<ClasseComAtributoNaoPadrao> itens = instance.getColecao();
		assertFalse(itens.isEmpty());
		assertEquals(1, itens.size());

		for (ClasseComAtributoNaoPadrao item : itens) {
			ClasseComAtributosPadrao objetoComAtributosPadrao = item.getClasseComAtributosPadrao();
			assertNotNull(objetoComAtributosPadrao);

			assertEquals(FixedPatternEnum.STRING.getValue(), objetoComAtributosPadrao.getStringField());
			assertEquals(FixedPatternEnum.INTEGER.getValue(), objetoComAtributosPadrao.getIntegerField());
			assertEquals(FixedPatternEnum.LONG.getValue(), objetoComAtributosPadrao.getLongField());
			assertEquals(FixedPatternEnum.BIG_DECIMAL.getValue(), objetoComAtributosPadrao.getBigDecimalField());
			assertEquals(FixedPatternEnum.BOOLEAN.getValue(), objetoComAtributosPadrao.getBooleanField());
			assertEquals(FixedPatternEnum.CALENDAR.getValue(), objetoComAtributosPadrao.getCalendarField());
			assertEquals(FixedPatternEnum.BYTE_ARRAY.getValue(), objetoComAtributosPadrao.getByteField());
		}
	}

	@Test
	public void devePreencherUmaInstanciaDeClasseComColecaoDeAtributoNaoPadraoNulo() throws PopulatorException {
		ClasseComAtributoNaoPadrao objetoComAtributoNaoPadrao = new ClasseComAtributoNaoPadrao();

		ClasseComColecaoDeAtributoNaoPadrao instance = new ClasseComColecaoDeAtributoNaoPadrao();
		instance.setColecao(Arrays.asList(objetoComAtributoNaoPadrao));

		this.filler.fill(instance);

		Collection<ClasseComAtributoNaoPadrao> itens = instance.getColecao();
		for (ClasseComAtributoNaoPadrao item : itens) {
			assertEquals(objetoComAtributoNaoPadrao, item);
			ClasseComAtributosPadrao objetoComAtributosPadrao = item.getClasseComAtributosPadrao();

			assertEquals(FixedPatternEnum.STRING.getValue(), objetoComAtributosPadrao.getStringField());
			assertEquals(FixedPatternEnum.INTEGER.getValue(), objetoComAtributosPadrao.getIntegerField());
			assertEquals(FixedPatternEnum.LONG.getValue(), objetoComAtributosPadrao.getLongField());
			assertEquals(FixedPatternEnum.BIG_DECIMAL.getValue(), objetoComAtributosPadrao.getBigDecimalField());
			assertEquals(FixedPatternEnum.BOOLEAN.getValue(), objetoComAtributosPadrao.getBooleanField());
			assertEquals(FixedPatternEnum.CALENDAR.getValue(), objetoComAtributosPadrao.getCalendarField());
			assertEquals(FixedPatternEnum.BYTE_ARRAY.getValue(), objetoComAtributosPadrao.getByteField());
		}
	}

	@Test
	public void naoDevePreencherUmaInstanciaDeClasseComColecaoPovoada() throws PopulatorException {
		String stringValue = "Teste";

		ClasseComColecaoDeAtributoPadrao instance = new ClasseComColecaoDeAtributoPadrao();
		instance.setColecao(Arrays.asList(stringValue));

		this.filler.fill(instance);

		Collection<String> itens = instance.getColecao();
		for (String item : itens) {
			assertEquals(stringValue, item);
		}
	}

	@Test(expected = PopulatorException.class)
	public void deveLancarUmaExcecaoParaInstanciasDeClassesDesconhecidasException() throws PopulatorException {
		ClasseComMapa instance = new ClasseComMapa();

		this.filler.fill(instance);
	}

	@Test
	public void deveLancarUmaExcecaoParaInstanciasDeClassesDesconhecidas() {
		ClasseComMapa instance = new ClasseComMapa();

		try {
			this.filler.fill(instance);
		} catch (PopulatorException e) {
			assertEquals(ErrorEnum.TYPE_UNEXPECTED.getMessage("mapa", instance.getClass().getName()), e.getMessage());
		}
	}

	@Test
	public void devePreencherUmaInstanciaDeClasseComColecaoNulaDeAtributoNaoPadraoBidirecional() throws PopulatorException {
		ClasseComColecaoDeAtributoNaoPadraoBidirecional instance = new ClasseComColecaoDeAtributoNaoPadraoBidirecional();

		this.filler.fill(instance);

		Collection<ClasseComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional> itens = instance.getColecao();
		assertFalse(itens.isEmpty());
		assertEquals(1, itens.size());

		for (ClasseComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional item : itens) {
			assertEquals(instance, item.getClasseComColecaoDeAtributoNaoPadraoBidirecional());
		}
	}

	@Test
	public void devePreencherUmaInstanciaDeClasseComColecaoVaziaDeAtributoNaoPadraoBidirecional() throws PopulatorException {
		ClasseComColecaoDeAtributoNaoPadraoBidirecional instance = new ClasseComColecaoDeAtributoNaoPadraoBidirecional();
		instance.setColecao(new ArrayList<ClasseComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional>());

		this.filler.fill(instance);

		Collection<ClasseComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional> itens = instance.getColecao();
		assertFalse(itens.isEmpty());
		assertEquals(1, itens.size());

		for (ClasseComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional item : itens) {
			assertEquals(instance, item.getClasseComColecaoDeAtributoNaoPadraoBidirecional());
		}
	}

	@Test
	public void devePreencherUmaInstanciaDeClasseComColecaoDeAtributoNaoPadraoBidirecionalNulo() throws PopulatorException {
		ClasseComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional objetoComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional = new ClasseComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional();

		ClasseComColecaoDeAtributoNaoPadraoBidirecional instance = new ClasseComColecaoDeAtributoNaoPadraoBidirecional();
		instance.setColecao(Arrays.asList(objetoComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional));

		this.filler.fill(instance);

		Collection<ClasseComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional> itens = instance.getColecao();
		for (ClasseComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional item : itens) {
			assertEquals(objetoComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional, item);
			assertEquals(instance, item.getClasseComColecaoDeAtributoNaoPadraoBidirecional());
		}
	}

	@Test
	public void naoDevePreencherUmaInstanciaDeClasseComColecaoDeAtributoNaoPadraoBidirecionalPovoada() throws PopulatorException {
		ClasseComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional objetoComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional = new ClasseComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional();

		ClasseComColecaoDeAtributoNaoPadraoBidirecional instance = new ClasseComColecaoDeAtributoNaoPadraoBidirecional();
		instance.setColecao(Arrays.asList(objetoComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional));
		objetoComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional.setClasseComColecaoDeAtributoNaoPadraoBidirecional(instance);

		this.filler.fill(instance);

		Collection<ClasseComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional> itens = instance.getColecao();
		for (ClasseComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional item : itens) {
			assertEquals(objetoComAtributoNaoPadraoDeClasseComColecaoDeAtributoNaoPadraoBidirecional, item);
			assertEquals(instance, item.getClasseComColecaoDeAtributoNaoPadraoBidirecional());
		}
	}

	@Test
	public void devePreencherUmaInstanciaDeClasseComColecaoNulaDeAtributoNaoPadraoComColecaoBidirecional() throws PopulatorException {
		ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional instance = new ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional();

		this.filler.fill(instance);

		Collection<ClasseComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional> itens = instance.getColecao();
		assertFalse(itens.isEmpty());
		assertEquals(1, itens.size());

		for (ClasseComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional item : itens) {
			Collection<ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional> colecao = item.getColecao();
			assertFalse(colecao.isEmpty());
			assertEquals(1, colecao.size());

			for (ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional elemento : colecao) {
				assertEquals(instance, elemento);
			}
		}
	}

	@Test
	public void devePreencherUmaInstanciaDeClasseComColecaoVaziaDeAtributoNaoPadraoComColecaoBidirecional() throws PopulatorException {
		ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional instance = new ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional();
		instance.setColecao(new ArrayList<ClasseComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional>());

		this.filler.fill(instance);

		Collection<ClasseComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional> itens = instance.getColecao();
		assertFalse(itens.isEmpty());
		assertEquals(1, itens.size());

		for (ClasseComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional item : itens) {
			Collection<ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional> colecao = item.getColecao();
			assertFalse(colecao.isEmpty());
			assertEquals(1, colecao.size());

			for (ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional elemento : colecao) {
				assertEquals(instance, elemento);
			}
		}
	}

	@Test
	public void devePreencherAColecaoBidirecionalNulaDeUmaInstanciaDeClasseComColecaoDeAtributoNaoPadraoPovoada() throws PopulatorException {
		ClasseComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional objetoComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional = new ClasseComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional();

		ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional instance = new ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional();
		instance.setColecao(Arrays.asList(objetoComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional));

		this.filler.fill(instance);

		Collection<ClasseComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional> itens = instance.getColecao();
		for (ClasseComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional item : itens) {
			assertEquals(objetoComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional, item);

			Collection<ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional> colecao = item.getColecao();
			assertFalse(colecao.isEmpty());
			assertEquals(1, colecao.size());

			for (ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional elemento : colecao) {
				assertEquals(instance, elemento);
			}
		}
	}

	@Test
	public void devePreencherAColecaoBidirecionalVaziaDeUmaInstanciaDeClasseComColecaoDeAtributoNaoPadraoPovoada() throws PopulatorException {
		ClasseComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional objetoComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional = new ClasseComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional();
		objetoComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional.setColecao(new ArrayList<ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional>());

		ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional instance = new ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional();
		instance.setColecao(Arrays.asList(objetoComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional));

		this.filler.fill(instance);

		Collection<ClasseComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional> itens = instance.getColecao();
		for (ClasseComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional item : itens) {
			assertEquals(objetoComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional, item);

			Collection<ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional> colecao = item.getColecao();
			assertFalse(colecao.isEmpty());
			assertEquals(1, colecao.size());

			for (ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional elemento : colecao) {
				assertEquals(instance, elemento);
			}
		}
	}

	@Test
	public void naoDevePreencherAColecaoBidirecionalPovoadaDeUmaInstanciaDeClasseComColecaoDeAtributoNaoPadraoPovoada() throws PopulatorException {
		ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional instance = new ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional();

		ClasseComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional objetoComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional = new ClasseComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional();
		objetoComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional.setColecao(Arrays.asList(instance));

		instance.setColecao(Arrays.asList(objetoComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional));

		this.filler.fill(instance);

		Collection<ClasseComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional> itens = instance.getColecao();
		for (ClasseComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional item : itens) {
			assertEquals(objetoComColecaoDeClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional, item);

			Collection<ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional> colecao = item.getColecao();
			for (ClasseComColecaoDeAtributoNaoPadraoComColecaoBidirecional elemento : colecao) {
				assertEquals(instance, elemento);
			}
		}
	}

	@Test
	public void naoDevePreencherAtributosIdEVersion() throws PopulatorException {
		ClasseComIdEVersion instance = new ClasseComIdEVersion();

		this.filler.fill(instance);

		assertNull(instance.getId());
		assertNull(instance.getVersion());
		assertEquals(FixedPatternEnum.STRING.getValue(), instance.getAtributo());
	}

	@After
	public void tearDown() {
		PatternManager.getInstance().restore();
		this.filler = null;
	}

}
