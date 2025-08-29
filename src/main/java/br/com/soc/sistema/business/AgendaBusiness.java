package br.com.soc.sistema.business;

import java.util.List;

import br.com.soc.sistema.dao.AgendaDao;
import br.com.soc.sistema.vo.AgendaVo;

public class AgendaBusiness {

	private AgendaDao dao;
	
	public AgendaBusiness() {
		this.dao = new AgendaDao();
	}
	
	public List<AgendaVo> trazerTodasAsAgendas() {
		return dao.findAllAgendas();
	}
	
}
