package br.com.soc.sistema.filter;

import br.com.soc.sistema.infra.OpcoesComboBuscarCompromisso;

public class CompromissoFilter {
	private OpcoesComboBuscarCompromisso opcoesCombo;
	private String valorBusca;

	public String getValorBusca() {
		return valorBusca;
	}

	public CompromissoFilter setValorBusca(String valorBusca) {
		this.valorBusca = valorBusca;
		return this;
	}

	public OpcoesComboBuscarCompromisso getOpcoesCombo() {
		return opcoesCombo;
	}

	public CompromissoFilter setOpcoesCombo(String codigo) {
		this.opcoesCombo = OpcoesComboBuscarCompromisso.buscarPor(codigo);
		return this;
	}
	
	public boolean isNullOpcoesCombo() {
		return this.getOpcoesCombo() == null;
	}
	
	public static CompromissoFilter builder() {
		return new CompromissoFilter();
	}
}
