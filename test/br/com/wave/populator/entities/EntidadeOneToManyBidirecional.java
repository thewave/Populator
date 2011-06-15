package br.com.wave.populator.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@javax.persistence.Entity
public class EntidadeOneToManyBidirecional implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Version
	private Integer version;

	private Boolean active;

	private String stringField;

	@OneToMany(mappedBy = "entidadeOneToManyBidirecional")
	private Collection<EntidadeOneToManyBidirecionalInverse> colecao;

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

	public Collection<EntidadeOneToManyBidirecionalInverse> getColecao() {
		return colecao;
	}

}
