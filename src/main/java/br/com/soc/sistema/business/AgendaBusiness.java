package br.com.soc.sistema.business;

import java.time.LocalTime;
import java.util.List;

import br.com.soc.sistema.dao.AgendaDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.infra.OpcoesPeriodoDisponivel;
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
	
	public void excluirAgenda(AgendaVo agendaVo) {
		
		try {
			dao.deleteAgenda(agendaVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a exclusão do registro");
		}
		
	}
	
	public boolean verificarHorarioPermitidoAgenda(String codigoAgenda, String horarioStr) {
		AgendaVo vo = buscarAgendaPor(codigoAgenda);
		
		try {
			LocalTime horario = LocalTime.parse(horarioStr);
			
			if(vo.getPeriodoDisponivel().equals(OpcoesPeriodoDisponivel.MANHA)) {
				return !horario.isBefore(LocalTime.of(8, 0)) && horario.isBefore(LocalTime.of(12, 0));
			} else if(vo.getPeriodoDisponivel().equals(OpcoesPeriodoDisponivel.TARDE)) {
				return !horario.isBefore(LocalTime.of(12, 0)) && horario.isBefore(LocalTime.of(18, 0));
			} else if(vo.getPeriodoDisponivel().equals(OpcoesPeriodoDisponivel.AMBOS)) {
				return !horario.isBefore(LocalTime.of(8, 0)) && horario.isBefore(LocalTime.of(18, 0));
			}
			
		} catch(Exception e) {
			throw new BusinessException("Nao foi possivel utilizar o horario");
		}
		return false;
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
