package br.com.soc.sistema.action;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.business.CompromissoBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.CompromissoVo;

public class CompromissoAction extends Action {

	private List<CompromissoVo> compromissos = new ArrayList<>();
	private CompromissoVo compromissoVo = new CompromissoVo();
	private CompromissoBusiness business = new CompromissoBusiness();
	
	public String todos() {
		compromissos.addAll(business.trazerTodosOsCompromissos());
		return SUCCESS;
	}
	
	public String novo() {
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
	
}
