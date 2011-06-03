package br.com.wave.populator.core;

public class Repository {
	
	public <T> void persist(T instance) {
		System.out.println("Persist: " + instance.getClass());
	}
	
	public <T> void remove(T instance) {
		System.out.println("Remove: " + instance.getClass());
	}

}
