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
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.vo.CompromissoVo;

public class CompromissoDao extends Dao {
	
	public void insertCompromisso(CompromissoVo compromissoVo) {
		StringBuilder query = new StringBuilder("INSERT INTO compromisso(codigoFuncionario, codigoAgenda, data, horario) VALUES (?, ?, ?, ?)");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i=1;
			ps.setLong(i++, Long.parseLong(compromissoVo.getCodigoFuncionario()));
			ps.setLong(i++, Long.parseLong(compromissoVo.getCodigoAgenda()));
			ps.setDate(i++, Date.valueOf(LocalDate.parse(compromissoVo.getData())));
			ps.setTime(i++, Time.valueOf(LocalTime.parse(compromissoVo.getHorario())));
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateCompromisso(CompromissoVo compromissoVo) {
		StringBuilder query = new StringBuilder("UPDATE compromisso SET codigoFuncionario = ?, codigoAgenda = ?, data = ?, horario = ? ")
								.append("WHERE rowid = ?");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i=1;
			ps.setLong(i++, Long.parseLong(compromissoVo.getCodigoFuncionario()));
			ps.setLong(i++, Long.parseLong(compromissoVo.getCodigoAgenda()));
			ps.setDate(i++, Date.valueOf(LocalDate.parse(compromissoVo.getData())));
			ps.setTime(i++, Time.valueOf(LocalTime.parse(compromissoVo.getHorario())));
			ps.setLong(i++, Long.parseLong(compromissoVo.getRowid()));
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteCompromisso(CompromissoVo compromissoVo) {
		StringBuilder query = new StringBuilder("DELETE FROM compromisso WHERE rowid = ?");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i=1;
			ps.setLong(i, Long.parseLong(compromissoVo.getRowid()));
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<CompromissoVo> buscarPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
		StringBuilder query = new StringBuilder("SELECT C.rowid id, C.codigoFuncionario, F.nm_funcionario, C.codigoAgenda, A.nm_agenda, C.data, C.horario FROM compromisso AS C ")
								.append("JOIN funcionario AS F ON F.rowid = C.codigoFuncionario ")
								.append("JOIN agenda AS A ON A.rowid = C.codigoAgenda ")
								.append("WHERE C.data BETWEEN ? AND ?");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i=1;
			ps.setDate(i++, Date.valueOf(dataInicial));
			ps.setDate(i++, Date.valueOf(dataFinal));
			
			CompromissoVo vo = null;
			List<CompromissoVo> compromissos = new ArrayList<>();
			try(ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					vo = new CompromissoVo();
					vo.setRowid(rs.getString("id"));
					vo.setCodigoFuncionario(rs.getString("codigoFuncionario"));
					vo.setNomeFuncionario(rs.getString("nm_funcionario"));
					vo.setCodigoAgenda(rs.getString("codigoAgenda"));
					vo.setNomeAgenda(rs.getString("nm_agenda"));
					vo.setData(rs.getDate("data").toString());
					vo.setHorario(rs.getTime("horario").toString());
					
					compromissos.add(vo);
				}
			}
			return compromissos;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
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
	
	public List<CompromissoVo> findAllByNomeFuncionario(String nome) {
		StringBuilder query = new StringBuilder("SELECT C.rowid id, C.codigoFuncionario, F.nm_funcionario, C.codigoAgenda, A.nm_agenda, C.data, C.horario FROM compromisso AS C ")
								.append("JOIN funcionario AS F ON C.codigoFuncionario = F.rowid ")
								.append("JOIN agenda AS A ON C.codigoAgenda = A.rowid ")
								.append("WHERE lower(F.nm_funcionario) LIKE lower(?)");
		try (
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i=1;
			ps.setString(i, "%"+nome+"%");
			
			try(ResultSet rs = ps.executeQuery()) {
				CompromissoVo vo = null;
				List<CompromissoVo> compromissos = new ArrayList<>();
				
				while(rs.next()) {
					vo = new CompromissoVo();
					vo.setRowid(rs.getString("id"));
					vo.setCodigoFuncionario(rs.getString("codigoFuncionario"));
					vo.setNomeFuncionario(rs.getString("nm_funcionario"));
					vo.setCodigoAgenda(rs.getString("codigoAgenda"));
					vo.setNomeAgenda(rs.getString("nm_agenda"));
					vo.setData(rs.getDate("data").toString());
					vo.setHorario(rs.getTime("horario").toString());
					compromissos.add(vo);
				}
				
				return compromissos;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
		
	}
	
	public List<CompromissoVo> findAllByNomeAgenda(String nome) {
		StringBuilder query = new StringBuilder("SELECT C.rowid id, C.codigoFuncionario, F.nm_funcionario, C.codigoAgenda, A.nm_agenda, C.data, C.horario FROM compromisso AS C ")
								.append("JOIN funcionario AS F ON C.codigoFuncionario = F.rowid ")
								.append("JOIN agenda AS A ON C.codigoAgenda = A.rowid ")
								.append("WHERE lower(A.nm_agenda) LIKE lower(?)");
		try (
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i=1;
			ps.setString(i, "%"+nome+"%");
			
			try(ResultSet rs = ps.executeQuery()) {
				CompromissoVo vo = null;
				List<CompromissoVo> compromissos = new ArrayList<>();
				
				while(rs.next()) {
					vo = new CompromissoVo();
					vo.setRowid(rs.getString("id"));
					vo.setCodigoFuncionario(rs.getString("codigoFuncionario"));
					vo.setNomeFuncionario(rs.getString("nm_funcionario"));
					vo.setCodigoAgenda(rs.getString("codigoAgenda"));
					vo.setNomeAgenda(rs.getString("nm_agenda"));
					vo.setData(rs.getDate("data").toString());
					vo.setHorario(rs.getTime("horario").toString());
					compromissos.add(vo);
				}
				
				return compromissos;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
		
	}
	
	public CompromissoVo findByCodigo(Long codigo) {
		StringBuilder query = new StringBuilder("SELECT rowid id, codigoFuncionario, codigoAgenda, data, horario FROM compromisso ")
								.append("WHERE rowid = ?");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i=1;
			ps.setLong(i, codigo);
			
			try(ResultSet rs = ps.executeQuery()) {
				CompromissoVo vo = null;
				if(rs.next()) {
					vo = new CompromissoVo();
					vo.setRowid(rs.getString("id"));
					vo.setCodigoFuncionario(rs.getString("codigoFuncionario"));
					vo.setCodigoAgenda(rs.getString("codigoAgenda"));
					vo.setData(rs.getString("data").toString());
					vo.setHorario(rs.getString("horario").toString());
				}
				return vo;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<CompromissoVo> findAllByCodigoFuncionario(Long codFuncionario) {
		StringBuilder query = new StringBuilder("SELECT C.rowid id, C.codigoFuncionario, F.nm_funcionario, C.codigoAgenda, A.nm_agenda, C.data, C.horario FROM compromisso AS C ")
								.append("JOIN funcionario AS F ON C.codigoFuncionario = F.rowid ")
								.append("JOIN agenda AS A ON C.codigoAgenda = A.rowid ")
								.append("WHERE C.codigoFuncionario = ?");
		try (
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i=1;
			ps.setLong(i, codFuncionario);
			
			try(ResultSet rs = ps.executeQuery()) {
				CompromissoVo vo = null;
				List<CompromissoVo> compromissos = new ArrayList<>();
				
				while(rs.next()) {
					vo = new CompromissoVo();
					vo.setRowid(rs.getString("id"));
					vo.setCodigoFuncionario(rs.getString("codigoFuncionario"));
					vo.setNomeFuncionario(rs.getString("nm_funcionario"));
					vo.setCodigoAgenda(rs.getString("codigoAgenda"));
					vo.setNomeAgenda(rs.getString("nm_agenda"));
					vo.setData(rs.getDate("data").toString());
					vo.setHorario(rs.getTime("horario").toString());
					compromissos.add(vo);
				}
				
				return compromissos;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
		
	}
	
	public List<CompromissoVo> findAllByCodigoAgenda(Long codAgenda) {
		StringBuilder query = new StringBuilder("SELECT C.rowid id, C.codigoFuncionario, F.nm_funcionario, C.codigoAgenda, A.nm_agenda, C.data, C.horario FROM compromisso AS C ")
								.append("JOIN funcionario AS F ON C.codigoFuncionario = F.rowid ")
								.append("JOIN agenda AS A ON C.codigoAgenda = A.rowid ")
								.append("WHERE C.codigoAgenda = ?");
		try (
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i=1;
			ps.setLong(i, codAgenda);
			
			try(ResultSet rs = ps.executeQuery()) {
				CompromissoVo vo = null;
				List<CompromissoVo> compromissos = new ArrayList<>();
				
				while(rs.next()) {
					vo = new CompromissoVo();
					vo.setRowid(rs.getString("id"));
					vo.setCodigoFuncionario(rs.getString("codigoFuncionario"));
					vo.setNomeFuncionario(rs.getString("nm_funcionario"));
					vo.setCodigoAgenda(rs.getString("codigoAgenda"));
					vo.setNomeAgenda(rs.getString("nm_agenda"));
					vo.setData(rs.getDate("data").toString());
					vo.setHorario(rs.getTime("horario").toString());
					compromissos.add(vo);
				}
				
				return compromissos;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
		
	}
	
	public List<CompromissoVo> findAllByData(String data) {
		StringBuilder query = new StringBuilder("SELECT C.rowid id, C.codigoFuncionario, F.nm_funcionario, C.codigoAgenda, A.nm_agenda, C.data, C.horario FROM compromisso AS C ")
								.append("JOIN funcionario AS F ON C.codigoFuncionario = F.rowid ")
								.append("JOIN agenda AS A ON C.codigoAgenda = A.rowid ")
								.append("WHERE C.data = ?");
		try (
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i=1;
			ps.setDate(i, Date.valueOf(data));
			
			try(ResultSet rs = ps.executeQuery()) {
				CompromissoVo vo = null;
				List<CompromissoVo> compromissos = new ArrayList<>();
				
				while(rs.next()) {
					vo = new CompromissoVo();
					vo.setRowid(rs.getString("id"));
					vo.setCodigoFuncionario(rs.getString("codigoFuncionario"));
					vo.setNomeFuncionario(rs.getString("nm_funcionario"));
					vo.setCodigoAgenda(rs.getString("codigoAgenda"));
					vo.setNomeAgenda(rs.getString("nm_agenda"));
					vo.setData(rs.getDate("data").toString());
					vo.setHorario(rs.getTime("horario").toString());
					compromissos.add(vo);
				}
				
				return compromissos;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
		
	}
	
	public List<CompromissoVo> findAllByHorario(String horario) {
		StringBuilder query = new StringBuilder("SELECT C.rowid id, C.codigoFuncionario, F.nm_funcionario, C.codigoAgenda, A.nm_agenda, C.data, C.horario FROM compromisso AS C ")
								.append("JOIN funcionario AS F ON C.codigoFuncionario = F.rowid ")
								.append("JOIN agenda AS A ON C.codigoAgenda = A.rowid ")
								.append("WHERE C.horario = ?");
		try (
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i=1;
			ps.setTime(i, Time.valueOf(horario));
			
			try(ResultSet rs = ps.executeQuery()) {
				CompromissoVo vo = null;
				List<CompromissoVo> compromissos = new ArrayList<>();
				
				while(rs.next()) {
					vo = new CompromissoVo();
					vo.setRowid(rs.getString("id"));
					vo.setCodigoFuncionario(rs.getString("codigoFuncionario"));
					vo.setNomeFuncionario(rs.getString("nm_funcionario"));
					vo.setCodigoAgenda(rs.getString("codigoAgenda"));
					vo.setNomeAgenda(rs.getString("nm_agenda"));
					vo.setData(rs.getDate("data").toString());
					vo.setHorario(rs.getTime("horario").toString());
					compromissos.add(vo);
				}
				
				return compromissos;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
		
	}
	
}
