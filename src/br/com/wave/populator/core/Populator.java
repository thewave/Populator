package br.com.wave.populator.core;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.wave.populator.enums.ErrorEnum;
import br.com.wave.populator.exceptions.PopulatorException;
import br.com.wave.utils.reflection.ReflectionUtil;

/**
 * Delega a responsabilidade de povoar instancias, alem da responsabilidade de armazena-las em um repositorio.
 * 
 * @see br.com.wave.populator.core.Filler
 * @see br.com.wave.populator.core.Docker
 * 
 * @author Benedito Barbosa
 * @author Christian Peixoto
 * 
 */
public class Populator {

	@Inject
	private Filler filler;

	@Inject
	private Docker docker;

	/**
	 * Cria e povoa uma instancia de uma determinada classe. Em seguida, essa instancia e armazenada em um repositorio.
	 * 
	 * @param klass
	 * @return Instancia de uma determinada classe.
	 * @throws PopulatorException
	 *             quando a classe for nula.
	 */
	public <T> T populate(Class<T> klass) throws PopulatorException {
		if (klass == null) {
			throw new PopulatorException(ErrorEnum.NULL);
		}

		try {
			T instance = klass.newInstance();
			this.populate(instance);

			return instance;
		} catch (InstantiationException e) {
			throw new PopulatorException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new PopulatorException(e.getMessage());
		}
	}

	/**
	 * Povoa uma determinada instancia. Em seguida, essa instancia e armazenada em um repositorio.
	 * 
	 * @param instance
	 * @throws PopulatorException
	 *             quando a instancia for nula ou nao for serializavel.
	 */
	public <T> void populate(T instance) throws PopulatorException {
		if (instance == null) {
			throw new PopulatorException(ErrorEnum.NULL);
		}

		if (!ReflectionUtil.implementz(instance.getClass(), Serializable.class)) {
			throw new PopulatorException(ErrorEnum.NOT_SERIALIZABLE, instance.getClass().getName());
		}

		this.filler.fill(instance);

		this.docker.addInstances();
	}

	/**
	 * Define que a instancia de uma classe sera usada em cada ocorrencia da classe.
	 * 
	 * @see br.com.wave.populator.core.PatternManager
	 * 
	 * @param klass
	 * @param instance
	 * @throws PopulatorException
	 */
	public <T> void addPattern(Class<?> klass, T instance) throws PopulatorException {
		this.populate(instance);

		PatternManager.getInstance().addPattern(klass, instance);
	}

	/**
	 * Retira as instancias armazenadas em um repositorio.
	 * 
	 * @throws PopulatorException
	 */
	public void clear() throws PopulatorException {
		this.docker.removeInstances();
	}

}
