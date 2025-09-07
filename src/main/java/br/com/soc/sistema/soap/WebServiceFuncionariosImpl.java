package br.com.soc.sistema.soap;

import javax.jws.WebService;

import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.vo.FuncionarioVo;

@WebService(endpointInterface = "br.com.soc.sistema.soap.WebServiceFuncionarios" )
public class WebServiceFuncionariosImpl implements WebServiceFuncionarios {

	private FuncionarioBusiness business;
	
	public WebServiceFuncionariosImpl() {
		this.business = new FuncionarioBusiness();
	}
	
	@Override
	public String buscarFuncionario(String codigo) {		
		return business.buscarFuncionarioPor(codigo).toString();
	}

	@Override
	public void criarFuncionario(FuncionarioVo funcionarioVo) {
		business.criarFuncionario(funcionarioVo);
	}

	@Override
	public void editarFuncionario(FuncionarioVo funcionarioVo) {
		business.editarFuncionario(funcionarioVo);
	}

	@Override
	public void excluirFuncionario(String codFuncionario) {
		business.excluirFuncionario(codFuncionario);
	}
	
}
