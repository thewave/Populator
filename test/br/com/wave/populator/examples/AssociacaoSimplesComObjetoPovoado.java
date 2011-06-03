package br.com.wave.populator.examples;

import java.io.Serializable;

public class AssociacaoSimplesComObjetoPovoado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ObjetoSimplesPovoado objetoSimplesPovoado;
	
	public void setObjetoSimplesPovoado(ObjetoSimplesPovoado objetoSimplesPovoado) {
		this.objetoSimplesPovoado = objetoSimplesPovoado;
	}

}
