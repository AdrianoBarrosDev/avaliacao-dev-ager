package br.com.soc.sistema.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.AgendaBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesPeriodoDisponivel;
import br.com.soc.sistema.vo.AgendaVo;

public class AgendaAction extends Action {

	private List<AgendaVo> agendas = new ArrayList<>();
	private AgendaBusiness business = new AgendaBusiness();
	private AgendaVo agendaVo = new AgendaVo();
	private List<OpcoesPeriodoDisponivel> opcoesPeriodo = Arrays.asList(OpcoesPeriodoDisponivel.values());
	
	public String todos() {
		agendas.addAll(business.trazerTodasAsAgendas());
		return SUCCESS;
	}
	
	public String novo() {
		
		if(agendaVo.getNome() == null || agendaVo.getPeriodoDisponivel() == null)
			return INPUT;
		
		business.salvarAgenda(agendaVo);
		
		return REDIRECT;
	}
	
	public String editar() {
		
		if(agendaVo.getRowid() == null)
			return REDIRECT;
		
		agendaVo = business.buscarAgendaPor(agendaVo.getRowid());
		
		return INPUT;
	}
	
	public List<AgendaVo> getAgendas() {
		return agendas;
	}
	
	public void setAgendas(List<AgendaVo> agendas) {
		this.agendas = agendas;
	}
	
	public AgendaBusiness getBusiness() {
		return business;
	}
	
	public void setBusiness(AgendaBusiness business) {
		this.business = business;
	}
	
	public AgendaVo getAgendaVo() {
		return agendaVo;
	}
	
	public void setAgendaVo(AgendaVo agendaVo) {
		this.agendaVo = agendaVo;
	}

	public List<OpcoesPeriodoDisponivel> getOpcoesPeriodo() {
		return opcoesPeriodo;
	}

	public void setOpcoesPeriodo(List<OpcoesPeriodoDisponivel> opcoesPeriodo) {
		this.opcoesPeriodo = opcoesPeriodo;
	}
	
}
