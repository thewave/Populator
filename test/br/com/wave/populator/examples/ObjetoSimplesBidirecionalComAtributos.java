package br.com.wave.populator.examples;

import java.io.Serializable;

public class ObjetoSimplesBidirecionalComAtributos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String naoPreenchido;
	
	private AssociacaoSimplesBidirecionalComObjetosComAtributos associacaoSimplesBidirecionalComObjetosComAtributos;
	
	public String getNaoPreenchido() {
		return naoPreenchido;
	}
	
	public void setAssociacaoSimplesBidirecionalComObjetosComAtributos(AssociacaoSimplesBidirecionalComObjetosComAtributos associacaoSimplesBidirecionalComObjetosComAtributos) {
		this.associacaoSimplesBidirecionalComObjetosComAtributos = associacaoSimplesBidirecionalComObjetosComAtributos;
	}

}
