package br.com.wave.populator.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.wave.populator.core.examples.ClasseNaoSerializavel;
import br.com.wave.populator.entities.EntidadeBasic;
import br.com.wave.populator.entities.EntidadeManyToMany;
import br.com.wave.populator.entities.EntidadeManyToManyInverse;
import br.com.wave.populator.entities.EntidadeOneToMany;
import br.com.wave.populator.entities.EntidadeOneToManyBidirecional;
import br.com.wave.populator.entities.EntidadeOneToManyBidirecionalInverse;
import br.com.wave.populator.entities.EntidadeOneToOne;
import br.com.wave.populator.entities.EntidadeOneToOneBidirecional;
import br.com.wave.populator.entities.EntidadeOneToOneBidirecionalInverse;
import br.com.wave.populator.enums.ErrorEnum;
import br.com.wave.populator.enums.FixedPatternEnum;
import br.com.wave.populator.exceptions.PopulatorException;

public class PopulatorTest {

	private Populator populator;

	@Before
	public void setUp() {
		this.populator = PopulatorFactory.createPopulator();
	}

	@Test(expected = PopulatorException.class)
	public void deveLancarUmaExcecaoSeAInstanciaForNulaException() throws PopulatorException {
		this.populator.populate(null);
	}

	@Test
	public void deveLancarUmaExcecaoSeAInstanciaForNula() {
		try {
			this.populator.populate(null);
		} catch (PopulatorException e) {
			assertEquals(ErrorEnum.NULL.getMessage(), e.getMessage());
		}
	}

	@Test(expected = PopulatorException.class)
	public void deveLancarExcecaoSeAInstanciaNaoForSerializavelException() throws PopulatorException {
		this.populator.populate(ClasseNaoSerializavel.class);
	}

	@Test
	public void deveLancarExcecaoSeAInstanciaNaoForSerializavel() {
		try {
			this.populator.populate(ClasseNaoSerializavel.class);
		} catch (PopulatorException e) {
			assertEquals(ErrorEnum.NOT_SERIALIZABLE.getMessage(ClasseNaoSerializavel.class.getName()), e.getMessage());
		}
	}

	@Test
	public void devePersistirUmaEntidadeBasic() throws PopulatorException {
		EntidadeBasic instance = this.populator.populate(EntidadeBasic.class);

		assertNotNull(instance.getId());
		assertNotNull(instance.getVersion());
	}

	@Test
	public void devePersistirUmaEntidadeOneToOne() throws PopulatorException {
		EntidadeOneToOne instance = this.populator.populate(EntidadeOneToOne.class);

		assertNotNull(instance.getId());
		assertNotNull(instance.getVersion());
		assertEquals(FixedPatternEnum.STRING.getValue(), instance.getStringField());

		assertNotNull(instance.getEntidadeBasic().getId());
		assertNotNull(instance.getEntidadeBasic().getVersion());
	}

	@Test
	public void devePersistirUmaEntidadeOneToMany() throws PopulatorException {
		EntidadeOneToMany instance = this.populator.populate(EntidadeOneToMany.class);

		assertNotNull(instance.getId());
		assertNotNull(instance.getVersion());
		assertEquals(FixedPatternEnum.STRING.getValue(), instance.getStringField());

		Collection<EntidadeOneToOne> itens = instance.getColecao();
		for (EntidadeOneToOne item : itens) {
			assertNotNull(item.getId());
			assertNotNull(item.getVersion());
		}
	}

	@Test
	public void devePersistirUmaEntidadeOneToOneBidirecional() throws PopulatorException {
		EntidadeOneToOneBidirecional instance = this.populator.populate(EntidadeOneToOneBidirecional.class);

		assertNotNull(instance.getId());
		assertNotNull(instance.getVersion());
		assertEquals(FixedPatternEnum.STRING.getValue(), instance.getStringField());

		EntidadeOneToOneBidirecionalInverse objetoOneToOneBidirecionalInverse = instance.getEntidadeOneToOneBidirecionalInverse();
		assertNotNull(objetoOneToOneBidirecionalInverse.getId());
		assertNotNull(objetoOneToOneBidirecionalInverse.getVersion());
		assertNotNull(objetoOneToOneBidirecionalInverse.getEntidadeOneToOneBidirecional());
	}

	@Test
	public void devePersistirUmaEntidadeOneToManyBidirecional() throws PopulatorException {
		EntidadeOneToManyBidirecional instance = this.populator.populate(EntidadeOneToManyBidirecional.class);

		assertNotNull(instance.getId());
		assertNotNull(instance.getVersion());
		assertEquals(FixedPatternEnum.STRING.getValue(), instance.getStringField());

		Collection<EntidadeOneToManyBidirecionalInverse> itens = instance.getColecao();
		for (EntidadeOneToManyBidirecionalInverse item : itens) {
			assertNotNull(item.getId());
			assertNotNull(item.getVersion());
			assertNotNull(item.getEntidadeOneToManyBidirecional());
		}
	}

	@Test
	public void devePersistirUmaEntidadeManyToMany() throws PopulatorException {
		EntidadeManyToMany instance = this.populator.populate(EntidadeManyToMany.class);

		assertNotNull(instance.getId());
		assertNotNull(instance.getVersion());
		assertEquals(FixedPatternEnum.STRING.getValue(), instance.getStringField());

		Collection<EntidadeManyToManyInverse> itens = instance.getColecao();
		for (EntidadeManyToManyInverse item : itens) {
			assertNotNull(item.getId());
			assertNotNull(item.getVersion());

			Collection<EntidadeManyToMany> elementos = item.getColecao();
			for (EntidadeManyToMany elemento : elementos) {
				assertEquals(instance, elemento);
			}
		}
	}

	@Test
	public void devePersistirUmaEntidadeUsandoPadraoAdicionado() throws PopulatorException {
		EntidadeBasic basic = new EntidadeBasic();
		basic.setStringField("Teste");
		basic.setLongField(1000L);

		this.populator.addPattern(EntidadeBasic.class, basic);

		EntidadeOneToOne instance = this.populator.populate(EntidadeOneToOne.class);
		assertNotNull(instance.getId());
		assertNotNull(instance.getVersion());

		assertEquals(basic.getStringField(), instance.getEntidadeBasic().getStringField());
		assertEquals(basic.getLongField(), instance.getEntidadeBasic().getLongField());
	}

	@After
	public void tearDown() throws PopulatorException {
		this.populator.clear();
		PatternManager.getInstance().restore();

		this.populator = null;
	}

}
