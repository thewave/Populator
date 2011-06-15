package br.com.wave.populator.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@javax.persistence.Entity
public class EntidadeOneToMany implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Version
	private Integer version;

	private Boolean active;

	private String stringField;

	@OneToMany
	@JoinTable(
			name = "TabelaOneToMany",
			joinColumns = @JoinColumn(name = "this_id"),
			inverseJoinColumns = @JoinColumn(name = "colecao_id")
	)
	private Collection<EntidadeOneToOne> colecao;

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

	public Collection<EntidadeOneToOne> getColecao() {
		return colecao;
	}

}
