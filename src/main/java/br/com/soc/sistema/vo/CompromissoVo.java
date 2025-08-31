package br.com.soc.sistema.vo;

import java.time.LocalDate;
import java.time.LocalTime;

public class CompromissoVo {

	private String codigoFuncionario;
	private String codigoAgenda;
	private LocalDate data;
	private LocalTime horario;
	
	public CompromissoVo() {}

	public CompromissoVo(String codigoFuncionario, String codigoAgenda, LocalDate data, LocalTime horario) {
		this.codigoFuncionario = codigoFuncionario;
		this.codigoAgenda = codigoAgenda;
		this.data = data;
		this.horario = horario;
	}

	public String getCodigoFuncionario() {
		return codigoFuncionario;
	}

	public void setCodigoFuncionario(String codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}

	public String getCodigoAgenda() {
		return codigoAgenda;
	}

	public void setCodigoAgenda(String codigoAgenda) {
		this.codigoAgenda = codigoAgenda;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	
}
