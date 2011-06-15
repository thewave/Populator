package br.com.wave.populator.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

@javax.persistence.Entity
public class EntidadeManyToManyInverse implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Version
	private Integer version;

	private Boolean active;

	private String stringField;

	@ManyToMany(mappedBy = "colecao")
	private Collection<EntidadeManyToMany> colecao;

	public Long getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	public Boolean getActive() {
		return active;
	}

	public String getStringField() {
		return stringField;
	}

	public Collection<EntidadeManyToMany> getColecao() {
		return colecao;
	}

}
