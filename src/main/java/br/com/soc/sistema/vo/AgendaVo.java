package br.com.soc.sistema.vo;

import br.com.soc.sistema.infra.OpcoesPeriodoDisponivel;

public class AgendaVo {

	private String rowid;
	private String nome;
	private OpcoesPeriodoDisponivel periodoDisponivel;
	
	public AgendaVo() {}

	public String getRowid() {
		return rowid;
	}

	public void setRowid(String rowid) {
		this.rowid = rowid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public OpcoesPeriodoDisponivel getPeriodoDisponivel() {
		return periodoDisponivel;
	}
	
	public void setPeriodoDisponivel(String codigo) {
		this.periodoDisponivel = OpcoesPeriodoDisponivel.buscarPorCodigo(codigo);
	}
	
	public void setPeriodoDisponivel(OpcoesPeriodoDisponivel periodoDisponivel) {
	    this.periodoDisponivel = periodoDisponivel;
	}
	
	@Override
	public String toString() {
		return "AgendaVo [rowid=" + rowid + ", nome=" + nome + ", periodoDisponivel=" + periodoDisponivel + "]";
	}
	
}
