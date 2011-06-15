package br.com.wave.populator.entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@javax.persistence.Entity
public class EntidadeOneToManyBidirecionalInverse implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Version
	private Integer version;

	private Boolean active;

	private String stringField;

	@ManyToOne
	private EntidadeOneToManyBidirecional entidadeOneToManyBidirecional;

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

	public EntidadeOneToManyBidirecional getEntidadeOneToManyBidirecional() {
		return entidadeOneToManyBidirecional;
	}

}
