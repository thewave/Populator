package br.com.wave.populator.core;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import br.com.wave.populator.enums.FixedPatternEnum;

/**
 * Classe que implementa o padrao de projeto Singleton para gerenciar padroes de instancias. Padrao de instancia e a instancia que sera usada na
 * ocorrencia de sua classe.
 * 
 * @see br.com.wave.populator.enums.FixedPatternEnum
 * 
 * @author Benedito Barbosa
 * @author Christian Peixoto
 * 
 */
public class PatternManager {

	private static PatternManager instance;

	private static Map<Class<?>, Object> fixedPatterns;

	private Map<Class<?>, Object> addedPatterns;

	static {
		fixedPatterns = new HashMap<Class<?>, Object>();

		FixedPatternEnum[] enumerations = FixedPatternEnum.values();
		for (FixedPatternEnum enumeration : enumerations) {
			fixedPatterns.put(enumeration.getType(), enumeration.getValue());
		}
	}

	private PatternManager() {
		this.addedPatterns = new LinkedHashMap<Class<?>, Object>();
	}

	/**
	 * Metodo que garante a existencia de apenas uma instancia da classe PatternManager.
	 * 
	 * @return PatternManager
	 */
	public static PatternManager getInstance() {
		if (instance == null) {
			instance = new PatternManager();
		}

		return instance;
	}

	/**
	 * Define um padrao de instancia.
	 * 
	 * @param klass
	 * @param instance
	 */
	public void addPattern(Class<?> klass, Object instance) {
		this.addedPatterns.put(klass, instance);
	}

	/**
	 * Indica se uma determinada classe tem padrao.
	 * 
	 * @param klass
	 * @return true se a classe tem padrao.
	 */
	public boolean hasPattern(Class<?> klass) {
		return fixedPatterns.containsKey(klass) || this.addedPatterns.containsKey(klass);
	}

	/**
	 * Retorna o padrao definido para uma determinada classe.
	 * 
	 * @param klass
	 * @return null se nao houve um padrao definido para a classe.
	 */
	public Object getValue(Class<?> klass) {
		if (this.addedPatterns.get(klass) != null) {
			return this.addedPatterns.get(klass);
		}

		return fixedPatterns.get(klass);
	}

	/**
	 * Exclui todos os padroes definidos.
	 */
	public void restore() {
		this.addedPatterns = new LinkedHashMap<Class<?>, Object>();
	}

	/**
	 * Retorna todos os padroes definidos.
	 * 
	 * @return Mapa vazio se nao houver padroes.
	 */
	public Map<Class<?>, Object> getAddedPatterns() {
		return addedPatterns;
	}

}
