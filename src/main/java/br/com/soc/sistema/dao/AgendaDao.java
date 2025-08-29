package br.com.soc.sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.infra.OpcoesPeriodoDisponivel;
import br.com.soc.sistema.vo.AgendaVo;

public class AgendaDao extends Dao {
	
	public List<AgendaVo> findAllAgendas() {
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_agenda nome, periodoDisponivel FROM agenda");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery()) {
			
			AgendaVo vo = null;
			List<AgendaVo> agendas = new ArrayList<>();
			while(rs.next()) {
				
				vo = new AgendaVo();
				vo.setRowid(rs.getString("id"));
				vo.setNome(rs.getString("nome"));
				vo.setPeriodoDisponivel(OpcoesPeriodoDisponivel.buscarPorCodigo(rs.getString("periodoDisponivel")));
				agendas.add(vo);
				
			}
			return agendas;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
		
	}

}
