package br.com.soc.sistema.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.CompromissoBusiness;
import br.com.soc.sistema.filter.CompromissoFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarAgenda;
import br.com.soc.sistema.infra.OpcoesComboBuscarCompromisso;
import br.com.soc.sistema.vo.CompromissoVo;

public class CompromissoAction extends Action {

	private List<CompromissoVo> compromissos = new ArrayList<>();
	private CompromissoVo compromissoVo = new CompromissoVo();
	private CompromissoBusiness business = new CompromissoBusiness();
	private CompromissoFilter filtrar = new CompromissoFilter();
	
	public String todos() {
		compromissos.addAll(business.trazerTodosOsCompromissos());
		return SUCCESS;
	}
	
	public String filtrar() {
		if(filtrar.isNullOpcoesCombo())
			return REDIRECT;
		
		compromissos = business.filtrarCompromissos(filtrar);
		
		return SUCCESS;
	}
	
	public String salvar() {
		if(compromissoVo.getCodigoAgenda() == null || compromissoVo.getCodigoFuncionario() == null || compromissoVo.getData() == null || compromissoVo.getHorario() == null)
			return INPUT;
		
		business.salvarCompromisso(compromissoVo);
		
		return REDIRECT;
	}
	
	public String editar() {
		if(compromissoVo.getRowid() == null)
			return REDIRECT;
		
		compromissoVo = business.buscarCompromissoPor(compromissoVo.getRowid());
		
		return INPUT;
	}
	
	public String excluir() {
		if(compromissoVo.getRowid() == null)
			return REDIRECT;
		
		business.excluirCompromisso(compromissoVo.getRowid());
		
		return REDIRECT;
	}
	
	public List<OpcoesComboBuscarCompromisso> getListaOpcoesCombo(){
		return Arrays.asList(OpcoesComboBuscarCompromisso.values());
	}
	
	public List<CompromissoVo> getCompromissos() {
		return compromissos;
	}
	
	public void setCompromissos(List<CompromissoVo> compromissos) {
		this.compromissos = compromissos;
	}
	
	public CompromissoVo getCompromissoVo() {
		return compromissoVo;
	}
	
	public void setCompromissoVo(CompromissoVo compromissoVo) {
		this.compromissoVo = compromissoVo;
	}

	public CompromissoFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(CompromissoFilter filtrar) {
		this.filtrar = filtrar;
	}
	
}
