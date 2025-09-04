package br.com.soc.sistema.infra;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import br.com.soc.sistema.exception.BusinessException;

public enum OpcoesComboBuscarCompromisso {
	ID("1", "ID"), 
	CODFUNCIONARIO("2", "CÓDIGO FUNCIONÁRIO"),
	NOMEFUNCIONARIO("3", "NOME FUNCIONÁRIO"),
	CODAGENDA("4", "CÓDIGO AGENDA"),
	NOMEAGENDA("5", "NOME AGENDA"),
	DATA("6", "DATA"),
	HORARIO("7", "HORARIO");
	
	private String codigo;
	private String descricao;
	private final static Map<String, OpcoesComboBuscarCompromisso> opcoes = new HashMap<>();
	
	static {
		Arrays.asList(OpcoesComboBuscarCompromisso.values())
		.forEach(
			opcao -> opcoes.put(opcao.getCodigo(), opcao)
		);
	}
	
	private OpcoesComboBuscarCompromisso(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static OpcoesComboBuscarCompromisso buscarPor(String codigo) throws IllegalArgumentException {
		if(codigo == null)
			throw new IllegalArgumentException("informe um codigo valido");
		
		OpcoesComboBuscarCompromisso opcao = getOpcao(codigo)
				.orElseThrow(() -> new BusinessException("Codigo informado nao existe"));
		
		return opcao;
	}
	
	private static Optional<OpcoesComboBuscarCompromisso> getOpcao(String codigo){
		return Optional.ofNullable(opcoes.get(codigo));
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
}