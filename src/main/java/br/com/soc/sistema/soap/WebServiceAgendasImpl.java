package br.com.soc.sistema.soap;

import javax.jws.WebService;

import br.com.soc.sistema.business.AgendaBusiness;

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
}
