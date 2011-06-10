package br.com.wave.populator.core;

import java.io.Serializable;

import br.com.wave.populator.enums.ErrorEnum;
import br.com.wave.populator.exceptions.PopulatorException;
import br.com.wave.populator.utils.ReflectionUtil;

public class Populator {

	private Filler filler;

	public Populator() {
		this.filler = new Filler();
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
	}

	public <T extends Serializable> void addPattern(Class<?> klass, T instance) throws PopulatorException {
		this.populate(instance);

		PatternManager.getInstance().addPattern(klass, instance);
	}

}
