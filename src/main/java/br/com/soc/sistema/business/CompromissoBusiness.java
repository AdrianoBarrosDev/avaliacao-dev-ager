package br.com.soc.sistema.business;

import java.time.LocalDate;
import java.util.List;

import br.com.soc.sistema.dao.CompromissoDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.CompromissoVo;

public class CompromissoBusiness {
	
	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
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
			
			AgendaBusiness agendaBusiness = new AgendaBusiness();
			if(!agendaBusiness.verificarHorarioPermitidoAgenda(compromissoVo.getCodigoAgenda(), compromissoVo.getHorario())) {
				throw new BusinessException("Horario invalido para essa agenda");
			}
			
			if(compromissoVo.getRowid().isEmpty()) {
				dao.insertCompromisso(compromissoVo);
			} else {
				dao.updateCompromisso(compromissoVo);
			}
			
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}
	}
	
	public void excluirCompromisso(CompromissoVo compromissoVo) {
		try {
			dao.deleteCompromisso(compromissoVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a exclusao do registro");
		}
	}
	
	public CompromissoVo buscarCompromissoPor(String codigo) {
		try {
			Long cod = Long.parseLong(codigo);
			return dao.findByCodigo(cod);
		} catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
	
	public List<CompromissoVo> filtrarCompromissos(String dataInicialStr, String dataFinalStr) {
		try {
			if(dataInicialStr.isEmpty() || dataFinalStr.isEmpty())
				throw new IllegalArgumentException("As datas nao podem ser em branco");
			
			LocalDate dataInicial = LocalDate.parse(dataInicialStr);
			LocalDate dataFinal = LocalDate.parse(dataFinalStr);
			return dao.buscarPorPeriodo(dataInicial, dataFinal);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
}
