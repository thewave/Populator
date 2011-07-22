package br.com.wave.populator.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.com.brasilti.repository.annotations.Transactional;
import br.com.brasilti.repository.core.Keeper;
import br.com.brasilti.repository.enums.RemoveEnum;
import br.com.brasilti.repository.exceptions.RepositoryException;
import br.com.wave.populator.exceptions.PopulatorException;

/**
 * Responsavel por armazenar instancias em um repositorio.
 * 
 * @author Benedito Barbosa
 * @author Christian Peixoto
 * 
 */
public class Docker {

	private PatternManager manager;

	private Keeper keeper;

	private List<Object> instances;

	@Inject
	public Docker(Keeper keeper) {
		this.manager = PatternManager.getInstance();
		this.keeper = keeper;
		this.instances = new ArrayList<Object>();
	}

	/**
	 * Armazena instancias em um repositorio.
	 * 
	 * @throws PopulatorException
	 */
	@Transactional
	public void addInstances() throws PopulatorException {
		this.loadInstances();
		Collections.reverse(this.instances);

		try {
			for (Object instance : this.instances) {
				this.keeper.persist(instance);
			}
		} catch (RepositoryException e) {
			throw new PopulatorException(e.getMessage());
		}
	}

	/**
	 * Retira instancias armazenadas em um repositorio.
	 * 
	 * @throws PopulatorException
	 */
	@Transactional
	public void removeInstances() throws PopulatorException {
		this.loadInstances();

		try {
			for (Object instance : this.instances) {
				this.keeper.remove(instance, RemoveEnum.PHYSICAL);
			}
		} catch (RepositoryException e) {
			throw new PopulatorException(e.getMessage());
		}
	}

	private void loadInstances() {
		this.instances.clear();

		Map<Class<?>, Object> addedPatterns = this.manager.getAddedPatterns();
		for (Class<?> key : addedPatterns.keySet()) {
			this.instances.add(addedPatterns.get(key));
		}
	}

	public List<Object> getInstances() {
		return instances;
	}

}
