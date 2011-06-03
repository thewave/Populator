package br.com.wave.populator.examples;

import java.io.Serializable;

public class ObjetoSimplesBidirecional implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private AssociacaoSimplesBidirecional associacaoSimplesBidirecional;
	
	public AssociacaoSimplesBidirecional getAssociacaoSimplesBidirecional() {
		return associacaoSimplesBidirecional;
	}
	
	public void setAssociacaoSimplesBidirecional(AssociacaoSimplesBidirecional associacaoSimplesBidirecional) {
		this.associacaoSimplesBidirecional = associacaoSimplesBidirecional;
	}

}
