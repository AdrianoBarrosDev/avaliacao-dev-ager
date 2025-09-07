package br.com.soc.sistema.business;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.AgendaDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.AgendaFilter;
import br.com.soc.sistema.infra.OpcoesPeriodoDisponivel;
import br.com.soc.sistema.vo.AgendaVo;

public class AgendaBusiness {
	
	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caractere no lugar de um número";
	private AgendaDao dao;
	
	public AgendaBusiness() {
		this.dao = new AgendaDao();
	}
	
	public List<AgendaVo> trazerTodasAsAgendas() {
		return dao.findAllAgendas();
	}
	
	public void salvarAgenda(AgendaVo agendaVo) {
		if(agendaVo.getRowid() != null && !agendaVo.getRowid().isEmpty()) {
			editarAgenda(agendaVo);
		} else {
			criarAgenda(agendaVo);
		}
	}
	
	public void criarAgenda(AgendaVo agendaVo) {
		try {
	    	
	    	validarAgenda(agendaVo);
	    
	    	dao.insertAgenda(agendaVo);
	        
	    } catch (IllegalArgumentException | BusinessException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new BusinessException("Erro ao inserir agenda");
	    }
	}
	
	public void editarAgenda(AgendaVo agendaVo) {
	    try {
	    	
	    	if (agendaVo.getRowid() == null || agendaVo.getRowid().isEmpty()) 
		        throw new IllegalArgumentException("ID da agenda obrigatório para atualização");
	    	
	    	validarAgenda(agendaVo);
		    
		    if(buscarAgendaPor(agendaVo.getRowid()) == null)
	    		throw new IllegalArgumentException("Esse ID de agenda não existe");
	    	
	    	dao.updateAgenda(agendaVo);
	    	
	    } catch (IllegalArgumentException | BusinessException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new BusinessException("Erro ao atualizar agenda");
	    }

	}
	
	public void excluirAgenda(String codAgenda) {
		try {
			
			if(codAgenda == null || codAgenda.isEmpty())
				throw new IllegalArgumentException("ID da agenda obrigatório para exclusão");
			
			if(buscarAgendaPor(codAgenda) == null)
	    		throw new IllegalArgumentException("Esse ID de agenda não existe");
			
			dao.deleteAgenda(codAgenda);
			
		} catch (IllegalArgumentException e) {
	        throw e;
	    } catch (SQLIntegrityConstraintViolationException e) {
	        throw new BusinessException("Não é possível excluir uma agenda que tem compromissos cadastrados");
	    } catch (Exception e) {
			throw new BusinessException("Não foi possível realizar a exclusão do registro");
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
			throw new BusinessException("Não foi possível utilizar o horário");
		}
		return false;
	}
	
	public List<AgendaVo> filtrarAgendas(AgendaFilter filter) {
		
		List<AgendaVo> agendas = new ArrayList<>();
		
		switch(filter.getOpcoesCombo()) {
			case ID:
				
				if(filter.getValorBusca().isEmpty()) {
					return agendas;
				}
				
				agendas.add(buscarAgendaPor(filter.getValorBusca()));
				
			break;
			
			case NOME:
				agendas.addAll(dao.findAllByNome(filter.getValorBusca()));
			break;
			
			case PERIODO:
				
				if(filter.getValorBusca().isEmpty()) {
					return dao.findAllAgendas();
				}
				
				agendas.addAll(dao.findByPeriodo(filter.getValorBusca()));
				
			break;
		
		}
		
		return agendas;
		
	}
	
	public AgendaVo buscarAgendaPor(String codigo) {
		try {
			
			Long cod = Long.parseLong(codigo);
			AgendaVo agenda = dao.findByCodigo(cod);
			
			if(agenda == null)
				throw new BusinessException("Agenda não encontrada para o ID informado");
			
			return agenda;
			
		} catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		} catch (IllegalArgumentException e) {
	        throw e;
	    }
	}
	
	public void validarAgenda(AgendaVo vo) {
		
		if(vo == null)
			throw new IllegalArgumentException("Agenda inválida");
		
		if(vo.getNome() == null || vo.getNome().isEmpty())
			throw new IllegalArgumentException("Nome não pode ser em branco");
		
		if(vo.getPeriodoDisponivel() == null)
			throw new IllegalArgumentException("Período disponível não pode ser em branco");
		
	}
	
}
