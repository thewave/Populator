package br.com.wave.populator.core;

import java.io.Serializable;

import br.com.wave.populator.enums.ErrorEnum;
import br.com.wave.populator.exceptions.PopulatorException;
import br.com.wave.utils.reflection.ReflectionUtil;

/**
 * @author Benedito Barbosa
 * @author Christian Peixoto
 * 
 */
public class Populator {

	private Filler filler;

	private Docker docker;

	public Populator() {
		this.filler = new Filler();

		this.docker = new Docker();
	}

	public <T> T populate(Class<T> klass) throws PopulatorException {
		if (klass == null) {
			throw new PopulatorException(ErrorEnum.NULL.getMessage());
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

	public <T> void populate(T instance) throws PopulatorException {
		if (instance == null) {
			throw new PopulatorException(ErrorEnum.NULL.getMessage());
		}

		if (!ReflectionUtil.implementz(instance.getClass(), Serializable.class)) {
			throw new PopulatorException(ErrorEnum.NOT_SERIALIZABLE.getMessage());
		}

		this.filler.fill(instance);

		this.docker.addInstances();
	}

	public <T> void addPattern(Class<?> klass, T instance) throws PopulatorException {
		this.populate(instance);

		PatternManager.getInstance().addPattern(klass, instance);
	}

	public void clear() {
		this.docker.removeInstances();
	}

}
