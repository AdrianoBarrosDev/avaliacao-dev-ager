package br.com.soc.sistema.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import br.com.soc.sistema.vo.AgendaVo;

@WebService
@SOAPBinding(style = Style.RPC)
public interface WebServiceAgendas {
	@WebMethod
	public String buscarAgenda(String codigo);
	
	@WebMethod
	public void criarAgenda(AgendaVo agendaVo);
	
	@WebMethod
	public void editarAgenda(AgendaVo agendaVo);
	
	@WebMethod
	public void excluirAgenda(String codAgenda);
	
}
