package br.com.soc.sistema.vo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CompromissoVo {
	
	private String rowid;
	private String codigoFuncionario;
	private String codigoAgenda;
	private String data;
	private String horario;
	
	public CompromissoVo() {}

	public String getRowid() {
		return rowid;
	}

	public void setRowid(String rowid) {
		this.rowid = rowid;
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

	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}

	public String getHorario() {
		return horario;
	}
	
	public void setHorario(String horario) {
		this.horario = horario;
	}
	
}
