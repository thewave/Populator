package br.com.wave.populator.examples;

import java.io.Serializable;

public class AssociacaoSimplesBidirecional implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ObjetoSimplesBidirecional objetoSimplesBidirecional;
	
	public ObjetoSimplesBidirecional getObjetoSimplesBidirecional() {
		return objetoSimplesBidirecional;
	}
	
	public void setObjetoSimplesBidirecional(ObjetoSimplesBidirecional objetoSimplesBidirecional) {
		this.objetoSimplesBidirecional = objetoSimplesBidirecional;
	}

}
