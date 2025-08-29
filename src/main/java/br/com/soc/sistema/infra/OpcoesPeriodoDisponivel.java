package br.com.soc.sistema.infra;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import br.com.soc.sistema.exception.BusinessException;

public enum OpcoesPeriodoDisponivel {
	MANHA("1", "Manhã"),
	TARDE("2", "Tarde"),
	AMBOS("3", "Ambos");
	
	private final String codigo;
	private final String descricao;
	private final static Map<String, OpcoesPeriodoDisponivel> opcoes = new HashMap<>(); 
	
	// Preenche o map com os valores que eu coloquei
	static {
		Arrays.asList(OpcoesPeriodoDisponivel.values())
		.forEach(
			opcao -> opcoes.put(opcao.getCodigo(), opcao)
		);
	}
	
	private OpcoesPeriodoDisponivel(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static OpcoesPeriodoDisponivel buscarPorCodigo(String codigo) {
		if(codigo == null) 
			throw new IllegalArgumentException("Informe um codigo de periodos disponiveis valido");
		
		OpcoesPeriodoDisponivel opcoes = getOpcao(codigo)
				.orElseThrow(() -> new BusinessException("Codigo de periodos disponiveis informado nao existe"));
		
		return opcoes;
	}
	
	public static Optional<OpcoesPeriodoDisponivel> getOpcao(String codigo) {
		return Optional.ofNullable(opcoes.get(codigo));
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
