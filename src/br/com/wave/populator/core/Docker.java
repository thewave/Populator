package br.com.wave.populator.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import br.com.wave.repository.core.Repository;

public class Docker {

	private PatternManager manager;

	private Repository repository;

	private List<Object> instances;

	public Docker() {
		this.manager = PatternManager.getInstance();
		this.repository = new Repository();
		this.instances = new ArrayList<Object>();
	}

	// TODO Colocar @Transaction
	public void addInstances() {
		this.loadInstances();
		Collections.reverse(this.instances);

		for (Object instance : this.instances) {
			this.repository.persist(instance);
		}
	}

	// TODO Colocar @Transaction
	public void removeInstances() {
		this.loadInstances();

		for (Object instance : this.instances) {
			this.repository.remove(instance);
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
