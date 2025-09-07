package br.com.soc.sistema.soap;

import javax.jws.WebService;

import br.com.soc.sistema.business.CompromissoBusiness;
import br.com.soc.sistema.vo.CompromissoVo;

@WebService(endpointInterface = "br.com.soc.sistema.soap.WebServiceCompromissos" )
public class WebServiceCompromissosImpl implements WebServiceCompromissos {

	private CompromissoBusiness business;
	
	public WebServiceCompromissosImpl() {
		this.business = new CompromissoBusiness();
	}
	
	@Override
	public String buscarCompromisso(String codigo) {		
		return business.buscarCompromissoPor(codigo).toString();
	}

	@Override
	public void criarCompromisso(CompromissoVo CompromissoVo) {
		business.criarCompromisso(CompromissoVo);
	}

	@Override
	public void editarCompromisso(CompromissoVo CompromissoVo) {
		business.editarCompromisso(CompromissoVo);
	}

	@Override
	public void excluirCompromisso(String codCompromisso) {
		business.excluirCompromisso(codCompromisso);
	}
}
