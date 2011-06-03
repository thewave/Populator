package br.com.wave.populator.examples;

import java.io.Serializable;

public class AssociacaoSimplesBidirecionalComObjetosComAtributos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ObjetoSimplesBidirecionalComAtributos objetoSimplesBidirecionalComAtributos;
	
	public void setObjetoSimplesBidirecionalComAtributos(ObjetoSimplesBidirecionalComAtributos objetoSimplesBidirecionalComAtributos) {
		this.objetoSimplesBidirecionalComAtributos = objetoSimplesBidirecionalComAtributos;
	}

}
