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
import br.com.soc.sistema.vo.AgendaVo;
import br.com.soc.sistema.vo.CompromissoVo;

public class CompromissoBusiness {
	
	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caractere no lugar de um número";
	private CompromissoDao dao;
	
	public CompromissoBusiness() {
		this.dao = new CompromissoDao();
	}
	
	public List<CompromissoVo> trazerTodosOsCompromissos() {
		return dao.findAllCompromissos();
	}
	
	public void salvarCompromisso(CompromissoVo compromissoVo) {
		if(compromissoVo.getRowid() != null && !compromissoVo.getRowid().isEmpty()) {
			editarCompromisso(compromissoVo);
		} else {
			criarCompromisso(compromissoVo);
		}
	}
	
	public void criarCompromisso(CompromissoVo compromissoVo) {
		try {
	    	
	    	validarCompromisso(compromissoVo);
	    
	    	dao.insertCompromisso(compromissoVo);
	        
		} catch (IllegalArgumentException | BusinessException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new BusinessException("Erro ao inserir compromisso");
	    }
	}
	
	public void editarCompromisso(CompromissoVo compromissoVo) {
	    try {
	    	
	    	if (compromissoVo.getRowid() == null || compromissoVo.getRowid().isEmpty()) 
		        throw new IllegalArgumentException("ID do compromisso obrigatório para atualização");
	    	
	    	validarCompromisso(compromissoVo);
		    
		    if(buscarCompromissoPor(compromissoVo.getRowid()) == null)
	    		throw new IllegalArgumentException("Esse ID de compromisso não existe");
	    	
	    	dao.updateCompromisso(compromissoVo);
	    	
	    } catch (IllegalArgumentException | BusinessException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new BusinessException("Erro ao atualizar compromisso");
	    }
	}
	
	public void excluirCompromisso(String codCompromisso) {
		try {
			
			if(codCompromisso == null || codCompromisso.isEmpty())
				throw new IllegalArgumentException("ID do compromisso obrigatório para exclusão");
			
			if(buscarCompromissoPor(codCompromisso) == null)
	    		throw new IllegalArgumentException("Esse ID de compromisso não existe");
			
			dao.deleteCompromisso(codCompromisso);
			
		} catch (IllegalArgumentException e) {
	        throw e;
	    } catch (Exception e) {
			throw new BusinessException("Não foi possível realizar a exclusão do registro");
		}
	}
	
	public CompromissoVo buscarCompromissoPor(String codigo) {
		try {
			
			Long cod = Long.parseLong(codigo);
			CompromissoVo compromisso = dao.findByCodigo(cod);
			
			if(compromisso == null)
				throw new BusinessException("Compromisso não encontrado para o ID informado");
			
			return compromisso;
			
		} catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
	
	public List<CompromissoVo> filtrarCompromissos(String dataInicialStr, String dataFinalStr) {
		try {
			if(dataInicialStr.isEmpty() || dataFinalStr.isEmpty())
				throw new IllegalArgumentException("As datas não podem ser em branco");
			
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
				
				compromissos.add(buscarCompromissoPor(filter.getValorBusca()));
				
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
			throw new IllegalArgumentException("Código da agenda não pode ser em branco");
		
		if(vo.getCodigoFuncionario() == null || vo.getCodigoFuncionario().isEmpty()) 
			throw new IllegalArgumentException("Código do funcionário não pode ser em branco");
		
		if(vo.getData() == null || vo.getData().isEmpty()) 
			throw new IllegalArgumentException("Data não pode ser em branco");
		
		if(vo.getHorario() == null || vo.getHorario().isEmpty()) 
			throw new IllegalArgumentException("Horário não pode ser em branco");
		
		validarDataCompromisso(vo.getData());
		validarHorarioCompromisso(vo.getHorario());
		
		FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
		funcionarioBusiness.buscarFuncionarioPor(vo.getCodigoFuncionario()); // Lança uma exceção se não encontrar
		
		AgendaBusiness agendaBusiness = new AgendaBusiness();
		if(!agendaBusiness.verificarHorarioPermitidoAgenda(vo.getCodigoAgenda(), vo.getHorario())) {
			AgendaVo agendaVo = agendaBusiness.buscarAgendaPor(vo.getCodigoAgenda());
			throw new BusinessException("Horário inválido para essa agenda. Essa agenda só permite compromissos no período: " + agendaVo.getPeriodoDisponivel());
		}
		
	}
	
	public void validarDataCompromisso(String data) {
		
	    DateTimeFormatter formatoPermitido = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
	    try {
	        LocalDate.parse(data, formatoPermitido);
	    } catch (DateTimeParseException e) {
	    	throw new IllegalArgumentException("Formato de data inválido. Use yyyy-MM-dd");
	    }
	    
	}
	
	public void validarHorarioCompromisso(String horario) {

		if(horario.length() == 5)
			horario += ":00";
		
		DateTimeFormatter formatoPermitido = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		try {
	        LocalTime.parse(horario, formatoPermitido);
	    } catch (DateTimeParseException e) {
	    	throw new IllegalArgumentException("Formato de horário inválido. Use HH:mm");
	    }
		
	}
	
}
