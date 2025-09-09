package br.com.soc.sistema.action;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.business.RelatorioCompromissoBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.CompromissoVo;

public class RelatorioCompromissoAction extends Action {

	private String dataInicial;
	private String dataFinal;
	private List<CompromissoVo> compromissos = new ArrayList<>();
	private RelatorioCompromissoBusiness business = new RelatorioCompromissoBusiness();
	
	public String novo() {
		if(dataInicial == null || dataFinal == null)
			return REDIRECT;
		
		compromissos = business.filtrarCompromissosRelatorio(dataInicial, dataFinal);
		business.gerarRelatorioXLSX(dataInicial, dataFinal, compromissos);
		
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
