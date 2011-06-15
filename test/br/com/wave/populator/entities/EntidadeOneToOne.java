package br.com.wave.populator.entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@javax.persistence.Entity
public class EntidadeOneToOne implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Version
	private Integer version;

	private Boolean active;

	private String stringField;

	@OneToOne
	private EntidadeBasic entidadeBasic;

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

	public EntidadeBasic getEntidadeBasic() {
		return entidadeBasic;
	}

}
