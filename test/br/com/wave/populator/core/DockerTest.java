package br.com.wave.populator.core;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.wave.populator.core.examples.ClasseComAtributoNaoPadrao;
import br.com.wave.populator.core.examples.ClasseComAtributosPadrao;
import br.com.wave.populator.core.examples.ClasseComColecaoDeAtributoNaoPadrao;

public class DockerTest {

	private Docker docker;

	private PatternManager manager;

	private ClasseComColecaoDeAtributoNaoPadrao colecao;

	private ClasseComAtributoNaoPadrao objetoComAtributoNaoPadrao;

	private ClasseComAtributosPadrao objetoComAtributosPadrao;

	@Before
	public void setUp() {
		this.manager = PatternManager.getInstance();
		this.manager.restore();

		this.colecao = new ClasseComColecaoDeAtributoNaoPadrao();
		this.objetoComAtributoNaoPadrao = new ClasseComAtributoNaoPadrao();
		this.objetoComAtributosPadrao = new ClasseComAtributosPadrao();

		this.manager.addPattern(ClasseComColecaoDeAtributoNaoPadrao.class, this.colecao);
		this.manager.addPattern(ClasseComAtributoNaoPadrao.class, this.objetoComAtributoNaoPadrao);
		this.manager.addPattern(ClasseComAtributosPadrao.class, this.objetoComAtributosPadrao);

		this.docker = new Docker();
	}

	@Test
	public void deveArmazenarAsInstanciasNaOrdemInversaEmQueForamAdicionadasComoPadrao() {
		this.docker.addInstances();

		List<Object> instances = this.docker.getInstances();

		assertEquals(3, instances.size());
		assertEquals(this.objetoComAtributosPadrao, instances.get(0));
		assertEquals(this.objetoComAtributoNaoPadrao, instances.get(1));
		assertEquals(this.colecao, instances.get(2));
	}

	@Test
	public void deveRetirarAsInstanciasNaMesmaOrdemEmQueForamAdicionadasComoPadrao() {
		this.docker.removeInstances();

		List<Object> instances = this.docker.getInstances();

		assertEquals(3, instances.size());
		assertEquals(this.colecao, instances.get(0));
		assertEquals(this.objetoComAtributoNaoPadrao, instances.get(1));
		assertEquals(this.objetoComAtributosPadrao, instances.get(2));
	}

	@After
	public void tearDown() {
		this.docker = null;
	}

}
