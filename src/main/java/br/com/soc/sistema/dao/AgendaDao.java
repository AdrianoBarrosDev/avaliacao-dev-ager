package br.com.soc.sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.vo.AgendaVo;

public class AgendaDao extends Dao {
	
	public void insertAgenda(AgendaVo agendaVo) {
		StringBuilder query = new StringBuilder("INSERT INTO agenda(nm_agenda, periodoDisponivel) VALUES (?, ?)");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i=1;
			ps.setString(i++, agendaVo.getNome());
			ps.setString(i++, agendaVo.getPeriodoDisponivel().getCodigo());
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateAgenda(AgendaVo agendaVo) {
		StringBuilder query = new StringBuilder("UPDATE agenda SET nm_agenda = ?, periodoDisponivel = ? ")
								.append("WHERE rowid = ?");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i=1;
			ps.setString(i++, agendaVo.getNome());
			ps.setString(i++, agendaVo.getPeriodoDisponivel().getCodigo());
			ps.setLong(i++, Long.parseLong(agendaVo.getRowid()));
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteAgenda(String codAgenda) {
		StringBuilder query = new StringBuilder("DELETE FROM agenda WHERE rowid = ?");
		try (
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i=1;
			ps.setLong(i, Long.parseLong(codAgenda));
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<AgendaVo> findByPeriodo(String codPeriodo) {
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_agenda nome, periodoDisponivel FROM agenda ")
								.append("WHERE periodoDisponivel = ?");
		try (
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i=1;
			ps.setString(i, codPeriodo);
			
			try(ResultSet rs = ps.executeQuery()) {
				AgendaVo vo = null;
				List<AgendaVo> agendas = new ArrayList<>();
				
				while(rs.next()) {
					vo = new AgendaVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));
					vo.setPeriodoDisponivel(rs.getString("periodoDisponivel"));
					agendas.add(vo);
				}
				return agendas;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
		
	}
	
	public List<AgendaVo> findAllByNome(String nome) {
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_agenda nome, periodoDisponivel FROM agenda ")
								.append("WHERE lower(nm_agenda) LIKE lower(?)");
		try (
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i=1;
			ps.setString(i, "%"+nome+"%");
			
			try(ResultSet rs = ps.executeQuery()) {
				AgendaVo vo = null;
				List<AgendaVo> agendas = new ArrayList<>();

				while(rs.next()) {
					vo = new AgendaVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));
					vo.setPeriodoDisponivel(rs.getString("periodoDisponivel"));
					agendas.add(vo);
				}
				return agendas;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
		
	}
	
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
				vo.setPeriodoDisponivel(rs.getString("periodoDisponivel"));
				agendas.add(vo);
				
			}
			return agendas;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
		
	}
	
	public AgendaVo findByCodigo(Long codigo) {
		
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_agenda nome, periodoDisponivel FROM agenda ")
								.append("WHERE rowid = ?");
		try (
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i=1;
			ps.setLong(i, codigo);
			
			try(ResultSet rs = ps.executeQuery()) {
				
				AgendaVo vo = null;
				if(rs.next()) {
					vo = new AgendaVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));
					vo.setPeriodoDisponivel(rs.getString("periodoDisponivel"));
				}
				return vo;
				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
