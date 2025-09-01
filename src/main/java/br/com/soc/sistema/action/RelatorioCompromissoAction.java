package br.com.soc.sistema.action;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.business.CompromissoBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.CompromissoVo;
import br.com.soc.sistema.vo.RelatorioCompromissoVo;

public class RelatorioCompromissoAction extends Action {

	// private RelatorioCompromissoVo relatorioVo = new RelatorioCompromissoVo();
	private String dataInicial;
	private String dataFinal;
	private List<CompromissoVo> compromissos = new ArrayList<>();
	private CompromissoBusiness business = new CompromissoBusiness();
	
	
	public String filtrar() {
		if(dataInicial == null || dataFinal == null)
			return REDIRECT;
		
		compromissos = business.filtrarCompromissos(dataInicial, dataFinal);
		
		return SUCCESS;
	}

	public String getDataInicial() {
		return dataInicial;
	}
	
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public List<CompromissoVo> getCompromissos() {
		return compromissos;
	}

	public void setCompromissos(List<CompromissoVo> compromissos) {
		this.compromissos = compromissos;
	}
	
}
