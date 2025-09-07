package br.com.soc.sistema.soap;

import javax.jws.WebService;

import br.com.soc.sistema.business.AgendaBusiness;
import br.com.soc.sistema.vo.AgendaVo;

@WebService(endpointInterface = "br.com.soc.sistema.soap.WebServiceAgendas" )
public class WebServiceAgendasImpl implements WebServiceAgendas {

	private AgendaBusiness business;
	
	public WebServiceAgendasImpl() {
		this.business = new AgendaBusiness();
	}
	
	@Override
	public String buscarAgenda(String codigo) {		
		return business.buscarAgendaPor(codigo).toString();
	}

	@Override
	public void criarAgenda(AgendaVo agendaVo) {
		business.criarAgenda(agendaVo);
	}

	@Override
	public void editarAgenda(AgendaVo agendaVo) {
		business.editarAgenda(agendaVo);
	}

	@Override
	public void excluirAgenda(String codAgenda) {
		business.excluirAgenda(codAgenda);
	}
}
