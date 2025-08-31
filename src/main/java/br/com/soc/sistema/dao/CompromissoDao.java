package br.com.soc.sistema.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.vo.CompromissoVo;

public class CompromissoDao extends Dao {
	
	public void insertCompromisso(CompromissoVo compromissoVo) {
		StringBuilder query = new StringBuilder("INSERT INTO compromisso(codigoFuncionario, codigoAgenda, data, horario) VALUES (?, ?, ?, ?)");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i=1;
			ps.setInt(i++, Integer.parseInt(compromissoVo.getCodigoFuncionario()));
			ps.setInt(i++, Integer.parseInt(compromissoVo.getCodigoAgenda()));
			ps.setDate(i++, Date.valueOf(LocalDate.parse(compromissoVo.getData())));
			ps.setTime(i++, Time.valueOf(LocalTime.parse(compromissoVo.getHorario())));
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateCompromisso(CompromissoVo compromissoVo) {
		
	}
	
	public List<CompromissoVo> findAllCompromissos() {
		StringBuilder query = new StringBuilder("SELECT rowid id, codigoFuncionario, codigoAgenda, data, horario FROM compromisso");
		try (
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery()) {
			
			CompromissoVo vo = null;
			List<CompromissoVo> compromissos = new ArrayList<>();
			while(rs.next()) {
				vo = new CompromissoVo();
				vo.setRowid(rs.getString("id"));
				vo.setCodigoFuncionario(rs.getString("codigoFuncionario"));
				vo.setCodigoAgenda(rs.getString("codigoAgenda"));
				vo.setData(rs.getDate("data").toString());
				vo.setHorario(rs.getTime("horario").toString());
				
				compromissos.add(vo);
			}
			return compromissos;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
