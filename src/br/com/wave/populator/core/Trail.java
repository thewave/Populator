package br.com.wave.populator.core;

import java.util.ArrayList;
import java.util.List;

public class Trail {

	private List<Object> instances;

	public Trail() {
		this.instances = new ArrayList<Object>();
	}

	public void add(Object instance) {
		this.instances.add(instance);
	}

	public List<Object> getInstances() {
		return instances;
	}

}
