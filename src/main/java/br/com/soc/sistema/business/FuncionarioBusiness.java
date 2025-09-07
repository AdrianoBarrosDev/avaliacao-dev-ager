package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.FuncionarioDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.FuncionarioFilter;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioBusiness {

	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private FuncionarioDao dao;
	
	public FuncionarioBusiness() {
		this.dao = new FuncionarioDao();
	}
	
	public List<FuncionarioVo> trazerTodosOsFuncionarios(){
		return dao.findAllFuncionarios();
	}	
	
	public void salvarFuncionario(FuncionarioVo funcionarioVo) {
		try {
			
			if(funcionarioVo.getRowid() != null && !funcionarioVo.getRowid().isEmpty()) {
				editarFuncionario(funcionarioVo);
			} else {
				criarFuncionario(funcionarioVo);
			}
			
		} catch (IllegalArgumentException e) {
	        throw e;
	    } catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}
		
	}
	
	public void criarFuncionario(FuncionarioVo funcionarioVo) {
		try {
	    	
	    	validarFuncionario(funcionarioVo);
	    
		    dao.insertFuncionario(funcionarioVo);
	        
	    } catch (IllegalArgumentException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new BusinessException("Erro ao inserir funcionário");
	    }
		
	}
	
	public void editarFuncionario(FuncionarioVo funcionarioVo) {
	    try {	    	
	    	
	    	if (funcionarioVo.getRowid() == null || funcionarioVo.getRowid().isEmpty()) 
		        throw new IllegalArgumentException("ID do funcionario obrigatorio para atualizacao");
	    	
	    	validarFuncionario(funcionarioVo);
		    
		    if(buscarFuncionarioPor(funcionarioVo.getRowid()) == null)
	    		throw new IllegalArgumentException("Esse ID de funcionario nao existe");
	    
	        dao.updateFuncionario(funcionarioVo);
	        
	    } catch (IllegalArgumentException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new BusinessException("Erro ao atualizar funcionário");
	    }
	    
	}
	
	public void excluirFuncionario(String codFuncionario) {
		try {
			
			if(codFuncionario == null || codFuncionario.isEmpty())
				throw new IllegalArgumentException("ID do funcionario obrigatorio para exclusao");
			
			if(buscarFuncionarioPor(codFuncionario) == null)
	    		throw new IllegalArgumentException("Esse ID de funcionario nao existe");
			
			dao.deleteFuncionario(codFuncionario);
			
		} catch (IllegalArgumentException e) {
	        throw e;
	    } catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a exclusão do registro");
		}
	}
	
	public List<FuncionarioVo> filtrarFuncionarios(FuncionarioFilter filter){
		List<FuncionarioVo> funcionarios = new ArrayList<>();
		
		switch (filter.getOpcoesCombo()) {
			case ID:
				
				if(filter.getValorBusca().isEmpty()) {
					return funcionarios;
				}
				
				funcionarios.add(buscarFuncionarioPor(filter.getValorBusca()));
				
			break;

			case NOME:
				funcionarios.addAll(dao.findAllByNome(filter.getValorBusca()));
			break;
		}
		
		return funcionarios;
	}
	
	public FuncionarioVo buscarFuncionarioPor(String codigo) {
		try {
			
			Long cod = Long.parseLong(codigo);
			FuncionarioVo funcionario = dao.findByCodigo(cod);
			
			if(funcionario == null) 
				throw new BusinessException("Funcionario nao encontrado para o ID informado");
			
			return funcionario;
			
		} catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		} catch (IllegalArgumentException e) {
	        throw e;
	    }
	}
	
	public void validarFuncionario(FuncionarioVo vo) {
		if (vo == null)
	        throw new IllegalArgumentException("Funcionário inválido");

	    if (vo.getNome() == null || vo.getNome().isEmpty())
	        throw new IllegalArgumentException("Nome não pode ser em branco");
	}
	
}
