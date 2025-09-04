package br.com.soc.sistema.filter;

import br.com.soc.sistema.infra.OpcoesComboBuscarAgenda;

public class AgendaFilter {
	private OpcoesComboBuscarAgenda opcoesCombo;
	private String valorBusca;

	public String getValorBusca() {
		return valorBusca;
	}

	public AgendaFilter setValorBusca(String valorBusca) {
		this.valorBusca = valorBusca;
		return this;
	}

	public OpcoesComboBuscarAgenda getOpcoesCombo() {
		return opcoesCombo;
	}

	public AgendaFilter setOpcoesCombo(String codigo) {
		this.opcoesCombo = OpcoesComboBuscarAgenda.buscarPor(codigo);
		return this;
	}
	
	public boolean isNullOpcoesCombo() {
		return this.getOpcoesCombo() == null;
	}
	
	public static AgendaFilter builder() {
		return new AgendaFilter();
	}
}
