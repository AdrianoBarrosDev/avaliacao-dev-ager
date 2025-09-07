package br.com.soc.sistema.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.CompromissoDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.CompromissoFilter;
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
			
			if(compromissoVo.getCodigoAgenda() == null || compromissoVo.getCodigoAgenda().isEmpty())
				throw new IllegalArgumentException("Codigo da agenda nao pode ser em branco");
			
			if(compromissoVo.getCodigoFuncionario() == null || compromissoVo.getCodigoFuncionario().isEmpty()) 
				throw new IllegalArgumentException("Codigo do funcionario nao pode ser em branco");
			
			if(compromissoVo.getData() == null || compromissoVo.getData().isEmpty()) 
				throw new IllegalArgumentException("Data nao pode ser em branco");
			
			if(compromissoVo.getHorario() == null || compromissoVo.getHorario().isEmpty()) 
				throw new IllegalArgumentException("Horario nao pode ser em branco");
			
			AgendaBusiness agendaBusiness = new AgendaBusiness();
			if(!agendaBusiness.verificarHorarioPermitidoAgenda(compromissoVo.getCodigoAgenda(), compromissoVo.getHorario())) {
				throw new BusinessException("Horario invalido para essa agenda");
			}
			
			if(compromissoVo.getRowid() != null && !compromissoVo.getRowid().isEmpty()) {
				editarCompromisso(compromissoVo);
			} else {
				criarCompromisso(compromissoVo);
			}
			
		} catch (IllegalArgumentException e) {
	        throw e;
	    } catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}
		
	}
	
	public void criarCompromisso(CompromissoVo compromissoVo) {
		dao.insertCompromisso(compromissoVo);
	}
	
	public void editarCompromisso(CompromissoVo compromissoVo) {
	    if (compromissoVo.getRowid() == null || compromissoVo.getRowid().isEmpty()) 
	        throw new IllegalArgumentException("ID do compromisso obrigatorio para atualizacao");
	    
	    if(buscarCompromissoPor(compromissoVo.getRowid()) == null)
    		throw new IllegalArgumentException("Esse ID de compromisso nao existe");
	    
	    try {
	    	dao.updateCompromisso(compromissoVo);
	    } catch (Exception e) {
	        throw new BusinessException("Erro ao atualizar compromisso");
	    }
	    
	}
	
	public void excluirCompromisso(String codCompromisso) {
		try {
			
			if(codCompromisso == null || codCompromisso.isEmpty())
				throw new IllegalArgumentException("ID do compromisso obrigatorio para exclusao");
			
			if(buscarCompromissoPor(codCompromisso) == null)
	    		throw new IllegalArgumentException("Esse ID de compromisso nao existe");
			
			dao.deleteCompromisso(codCompromisso);
			
		} catch (IllegalArgumentException e) {
	        throw e;
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
	
	public List<CompromissoVo> filtrarCompromissos(CompromissoFilter filter) {
		
		List<CompromissoVo> compromissos = new ArrayList<>();
		
		switch(filter.getOpcoesCombo()) {
			case ID:
				
				if(filter.getValorBusca().isEmpty()) {
					return compromissos;
				}
				
				try {
					Long cod = Long.parseLong(filter.getValorBusca());
					compromissos.add(dao.findByCodigo(cod));
				} catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
				
			break;
			
			case CODFUNCIONARIO:
				
				if(filter.getValorBusca().isEmpty()) {
					return compromissos;
				}
				
				try {
					Long cod = Long.parseLong(filter.getValorBusca());
					compromissos.addAll(dao.findAllByCodigoFuncionario(cod));
				} catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
				
			break;
			
			case NOMEFUNCIONARIO:
				compromissos.addAll(dao.findAllByNomeFuncionario(filter.getValorBusca()));
			break;
			
			case CODAGENDA:
				
				if(filter.getValorBusca().isEmpty()) {
					return compromissos;
				}
				
				try {
					Long cod = Long.parseLong(filter.getValorBusca());
					compromissos.addAll(dao.findAllByCodigoAgenda(cod));
				} catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}

			break;
			
			case NOMEAGENDA:
				compromissos.addAll(dao.findAllByNomeAgenda(filter.getValorBusca()));
			break;
			
			case DATA:
				
				if(filter.getValorBusca().isEmpty()) {
					return compromissos;
				}
				
				compromissos.addAll(dao.findAllByData(filter.getValorBusca()));
				
			break;
			
			case HORARIO:
				
				if(filter.getValorBusca().isEmpty()) {
					return compromissos;
				}
				
				if(filter.getValorBusca().length() == 5) {
					filter.setValorBusca(filter.getValorBusca() + ":00");
				}
				
				compromissos.addAll(dao.findAllByHorario(filter.getValorBusca()));
				
			break;
		
		}
		
		return compromissos;
		
	}
	
}
