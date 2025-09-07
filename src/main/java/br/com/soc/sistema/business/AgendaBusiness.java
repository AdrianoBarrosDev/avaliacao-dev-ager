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
			
			if(agendaVo.getRowid() != null && !agendaVo.getRowid().isEmpty()) {
				editarAgenda(agendaVo);
			} else {
				criarAgenda(agendaVo);
			}
			
		} catch (IllegalArgumentException e) {
	        throw e;
	    } catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}
		
	}
	
	public void criarAgenda(AgendaVo agendaVo) {
		try {
	    	
	    	validarAgenda(agendaVo);
	    
	    	dao.insertAgenda(agendaVo);
	        
	    } catch (IllegalArgumentException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new BusinessException("Erro ao inserir agenda");
	    }
	}
	
	public void editarAgenda(AgendaVo agendaVo) {
	    try {
	    	
	    	if (agendaVo.getRowid() == null || agendaVo.getRowid().isEmpty()) 
		        throw new IllegalArgumentException("ID da agenda obrigatorio para atualizacao");
	    	
	    	validarAgenda(agendaVo);
		    
		    if(buscarAgendaPor(agendaVo.getRowid()) == null)
	    		throw new IllegalArgumentException("Esse ID de agenda nao existe");
	    	
	    	dao.updateAgenda(agendaVo);
	    	
	    } catch (IllegalArgumentException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new BusinessException("Erro ao atualizar agenda");
	    }

	}
	
	public void excluirAgenda(String codAgenda) {
		try {
			
			if(codAgenda == null || codAgenda.isEmpty())
				throw new IllegalArgumentException("ID da agenda obrigatorio para exclusao");
			
			if(buscarAgendaPor(codAgenda) == null)
	    		throw new IllegalArgumentException("Esse ID de agenda nao existe");
			
			dao.deleteAgenda(codAgenda);
			
		} catch (IllegalArgumentException e) {
	        throw e;
	    } catch (SQLIntegrityConstraintViolationException e) {
	        throw new BusinessException("Não é possível excluir uma agenda que tem compromissos cadastrados");
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
	
	public List<AgendaVo> filtrarAgendas(AgendaFilter filter) {
		
		List<AgendaVo> agendas = new ArrayList<>();
		
		switch(filter.getOpcoesCombo()) {
			case ID:
				
				if(filter.getValorBusca().isEmpty()) {
					return agendas;
				}
				
				try {
					Long cod = Long.parseLong(filter.getValorBusca());
					agendas.add(dao.findByCodigo(cod));
				} catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
				
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
				throw new BusinessException("Agenda nao encontrada para o ID informado");
			
			return agenda;
			
		} catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		} catch (IllegalArgumentException e) {
	        throw e;
	    }
	}
	
	public void validarAgenda(AgendaVo vo) {
		
		if(vo == null)
			throw new IllegalArgumentException("Agenda invalida");
		
		if(vo.getNome() == null || vo.getNome().isEmpty())
			throw new IllegalArgumentException("Nome nao pode ser em branco");
		
		if(vo.getPeriodoDisponivel() == null)
			throw new IllegalArgumentException("Periodo disponivel nao pode ser em branco");
		
	}
	
}
