package br.com.soc.sistema.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import br.com.soc.sistema.vo.CompromissoVo;

@WebService
@SOAPBinding(style = Style.RPC)
public interface WebServiceCompromissos {
	@WebMethod
	public String buscarCompromisso(String codigo);
	
	@WebMethod
	public void criarCompromisso(CompromissoVo CompromissoVo);
	
	@WebMethod
	public void editarCompromisso(CompromissoVo CompromissoVo);
	
	@WebMethod
	public void excluirCompromisso(String codCompromisso);
	
}
