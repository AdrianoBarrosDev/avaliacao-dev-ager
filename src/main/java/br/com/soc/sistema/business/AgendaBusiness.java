package br.com.soc.sistema.business;

import java.util.List;

import br.com.soc.sistema.dao.AgendaDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.AgendaVo;

public class AgendaBusiness {
	
	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private AgendaDao dao;
	
	public AgendaBusiness() {
		this.dao = new AgendaDao();
	}
	
	public List<AgendaVo> trazerTodasAsAgendas() {
		return dao.findAllAgendas();
	}
	
	public void salvarAgenda(AgendaVo agendaVo) {
		
		try {
			
			if(agendaVo.getNome().isEmpty())
				throw new IllegalArgumentException("Nome nao pode ser em branco");
			
			if(agendaVo.getRowid().isEmpty()) {
				dao.insertAgenda(agendaVo);
			} else {
				dao.updateAgenda(agendaVo);
			}
			
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}

	}
	
	public AgendaVo buscarAgendaPor(String codigo) {
		
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		} catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
		
	}
	
}
