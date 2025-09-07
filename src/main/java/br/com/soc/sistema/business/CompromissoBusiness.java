package br.com.soc.sistema.business;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
			
			FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
			if(funcionarioBusiness.buscarFuncionarioPor(compromissoVo.getCodigoFuncionario()) == null) {
				throw new IllegalArgumentException("Nao foi encontrado nenhum funcionario com esse ID");
			}
			
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
		try {
	    	
	    	validarCompromisso(compromissoVo);
	    
	    	dao.insertCompromisso(compromissoVo);
	        
	    } catch (IllegalArgumentException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new BusinessException("Erro ao inserir compromisso");
	    }
	}
	
	public void editarCompromisso(CompromissoVo compromissoVo) {
	    try {
	    	
	    	if (compromissoVo.getRowid() == null || compromissoVo.getRowid().isEmpty()) 
		        throw new IllegalArgumentException("ID do compromisso obrigatorio para atualizacao");
	    	
	    	validarCompromisso(compromissoVo);
		    
		    if(buscarCompromissoPor(compromissoVo.getRowid()) == null)
	    		throw new IllegalArgumentException("Esse ID de compromisso nao existe");
	    	
	    	dao.updateCompromisso(compromissoVo);
	    	
	    } catch (IllegalArgumentException e) {
	        throw e;
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
			CompromissoVo compromisso = dao.findByCodigo(cod);
			
			if(compromisso == null)
				throw new BusinessException("Compromisso nao encontrado para o ID informado");
			
			return compromisso;
			
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
	
	public void validarCompromisso(CompromissoVo vo) {
		if(vo.getCodigoAgenda() == null || vo.getCodigoAgenda().isEmpty())
			throw new IllegalArgumentException("Codigo da agenda nao pode ser em branco");
		
		if(vo.getCodigoFuncionario() == null || vo.getCodigoFuncionario().isEmpty()) 
			throw new IllegalArgumentException("Codigo do funcionario nao pode ser em branco");
		
		if(vo.getData() == null || vo.getData().isEmpty()) 
			throw new IllegalArgumentException("Data nao pode ser em branco");
		
		if(vo.getHorario() == null || vo.getHorario().isEmpty()) 
			throw new IllegalArgumentException("Horario nao pode ser em branco");
		
		validarDataCompromisso(vo.getData());
		validarHorarioCompromisso(vo.getHorario());
		
	}
	
	public void validarDataCompromisso(String data) {
		
	    DateTimeFormatter formatoPermitido = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
	    try {
	        LocalDate.parse(data, formatoPermitido);
	    } catch (DateTimeParseException e) {
	    	throw new IllegalArgumentException("Formato de data invalido. Use yyyy-MM-dd");
	    }
	    
	}
	
	public void validarHorarioCompromisso(String horario) {
		
		DateTimeFormatter formatoPermitido = DateTimeFormatter.ofPattern("HH:mm");
		
		try {
	        LocalTime.parse(horario, formatoPermitido);
	    } catch (DateTimeParseException e) {
	    	throw new IllegalArgumentException("Formato de horario invalido. Use HH:mm");
	    }
		
	}
	
}
