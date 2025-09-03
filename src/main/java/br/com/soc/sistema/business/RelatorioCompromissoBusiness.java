package br.com.soc.sistema.business;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
	
	public void gerarRelatorioXLSX(String dataInicial, String dataFinal, List<CompromissoVo> listaCompromissos) {
		
		Workbook workbook = new XSSFWorkbook();
		
        Sheet sheet = workbook.createSheet("Relatório Compromissos");
        
        Row header = sheet.createRow(0);
        int j=0;
        header.createCell(j++).setCellValue("ID");
        header.createCell(j++).setCellValue("Código Funcionário");
        header.createCell(j++).setCellValue("Nome Funcionário");
        header.createCell(j++).setCellValue("Código Agenda");
        header.createCell(j++).setCellValue("Nome Agenda");
        header.createCell(j++).setCellValue("Data");
        header.createCell(j++).setCellValue("Horário");

        for(int i = 0; i < listaCompromissos.size(); i++) {
        	
        	CompromissoVo vo = listaCompromissos.get(i);
        	
        	Row linhaDados = sheet.createRow(i + 1);
        	linhaDados.createCell(0).setCellValue(Long.parseLong(vo.getRowid()));
        	linhaDados.createCell(1).setCellValue(Long.parseLong(vo.getCodigoFuncionario()));
        	linhaDados.createCell(2).setCellValue(vo.getNomeFuncionario());
        	linhaDados.createCell(3).setCellValue(Long.parseLong(vo.getCodigoAgenda()));
        	linhaDados.createCell(4).setCellValue(vo.getNomeAgenda());
        	linhaDados.createCell(5).setCellValue(vo.getData());
        	linhaDados.createCell(6).setCellValue(vo.getHorario());
        	
        }
        
        for(int i = 0; i < 7; i++) {
        	sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream("D:/Adriano/Cursos/Programa de Formação Ager/Projeto Final/Relatório Compromissos.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
}
