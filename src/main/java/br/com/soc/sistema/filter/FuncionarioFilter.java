package br.com.soc.sistema.filter;

import br.com.soc.sistema.infra.OpcoesComboBuscarFuncionario;

public class FuncionarioFilter {
	private OpcoesComboBuscarFuncionario opcoesCombo;
	private String valorBusca;

	public String getValorBusca() {
		return valorBusca;
	}

	public FuncionarioFilter setValorBusca(String valorBusca) {
		this.valorBusca = valorBusca;
		return this;
	}

	public OpcoesComboBuscarFuncionario getOpcoesCombo() {
		return opcoesCombo;
	}

	public FuncionarioFilter setOpcoesCombo(String codigo) {
		this.opcoesCombo = OpcoesComboBuscarFuncionario.buscarPor(codigo);
		return this;
	}	
	
	public boolean isNullOpcoesCombo() {
		return this.getOpcoesCombo() == null;
	}
	
	public static FuncionarioFilter builder() {
		return new FuncionarioFilter();
	}
}
