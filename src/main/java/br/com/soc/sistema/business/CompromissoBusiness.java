package br.com.soc.sistema.business;

import java.util.List;

import br.com.soc.sistema.dao.CompromissoDao;
import br.com.soc.sistema.vo.CompromissoVo;

public class CompromissoBusiness {

	private CompromissoDao dao;
	
	public CompromissoBusiness() {
		this.dao = new CompromissoDao();
	}
	
	public List<CompromissoVo> trazerTodosOsCompromissos() {
		return dao.findAllCompromissos();
	}
	
}
