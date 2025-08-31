package br.com.soc.sistema.business;

import java.util.List;

import br.com.soc.sistema.dao.CompromissoDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.CompromissoVo;

public class CompromissoBusiness {

	private CompromissoDao dao;
	
	public CompromissoBusiness() {
		this.dao = new CompromissoDao();
	}
	
	public List<CompromissoVo> trazerTodosOsCompromissos() {
		return dao.findAllCompromissos();
	}
	
	public void salvarCompromisso(CompromissoVo compromissoVo) {
		try {
			if(compromissoVo.getCodigoAgenda().isEmpty() || compromissoVo.getCodigoFuncionario().isEmpty()) 
				throw new IllegalArgumentException("Codigo da agenda e do funcionario nao pode ser em branco");
			
			if(compromissoVo.getRowid().isEmpty()) {
				dao.insertCompromisso(compromissoVo);
			} else {
				dao.updateCompromisso(compromissoVo);
			}
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}
	}
	
}
