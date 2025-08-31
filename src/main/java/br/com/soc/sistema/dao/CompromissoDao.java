package br.com.soc.sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.vo.CompromissoVo;

public class CompromissoDao extends Dao {
	
	public List<CompromissoVo> findAllCompromissos() {
		StringBuilder query = new StringBuilder("SELECT codigoFuncionario, codigoAgenda, data, horario FROM compromisso");
		try (
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery()) {
			
			CompromissoVo vo = null;
			List<CompromissoVo> compromissos = new ArrayList<>();
			while(rs.next()) {
				vo = new CompromissoVo();
				vo.setCodigoFuncionario(rs.getString("codigoFuncionario"));
				vo.setCodigoAgenda(rs.getString("codigoAgenda"));
				vo.setData(rs.getDate("data").toLocalDate());
				vo.setHorario(rs.getTime("horario").toLocalTime());
				
				compromissos.add(vo);
			}
			return compromissos;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
