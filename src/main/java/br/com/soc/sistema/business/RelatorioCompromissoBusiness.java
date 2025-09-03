package br.com.soc.sistema.business;

import java.util.List;

import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.CompromissoVo;

public class RelatorioCompromissoBusiness {
	
	private CompromissoBusiness compromissoBusiness = new CompromissoBusiness();
	
	public List<CompromissoVo> filtrarCompromissosRelatorio(String dataInicial, String dataFinal) {
		
		try {
			return compromissoBusiness.filtrarCompromissos(dataInicial, dataFinal);
		} catch(Exception e) {
			throw new BusinessException("Erro ao filtrar os compromissos do relatorio");
		}
		
	}
	
	public void gerarRelatorioXLSX(String dataInicial, String dataFinal) {
		
		
		
	}
	
}
