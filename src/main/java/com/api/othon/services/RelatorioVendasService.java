package com.api.othon.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

@Service
public class RelatorioVendasService {
	
	@Value("classpath:reports/relatorio-vendas.jrxml")
	private Resource relatorioVendasSource;
	
	@Value("classpath:reports/relatorio-vendas.jasper")
	private Resource relatorioVendasCompilado;
	
	@Autowired
	private DataSource dataSource;
	
	public byte[] gerarRelatorioCompilado(Date dataInicio) {
		//try with resources
		try (
			Connection connection = dataSource.getConnection();	
		) {
			Map<String, Object> paramentros = new HashMap<>();
			return JasperRunManager.runReportToPdf(
					relatorioVendasCompilado.getInputStream(), 
					paramentros, 
					connection);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public byte[] gerarRelatorio(Date dataInicio) {
		//try with resources
		try (
			Connection connection = dataSource.getConnection();	
		) {
			Map<String, Object> paramentros = new HashMap<>();
            paramentros.put("DataTransacao", dataInicio);
			JasperPrint print = JasperFillManager
						.fillReport(relatorioVendasCompilado.getInputStream(), 
								paramentros, connection);
			return JasperExportManager.exportReportToPdf(print);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public byte[] gerarRelatorioCompilando() {
		//try with resources
		try (
			Connection connection = dataSource.getConnection();	
		) {
			
			JasperReport compiledReport = JasperCompileManager
									.compileReport(relatorioVendasSource.getInputStream());
			Map<String, Object> paramentros = new HashMap<>();
			
			JasperPrint print = JasperFillManager
									.fillReport(compiledReport, paramentros, connection);
			
			return JasperExportManager.exportReportToPdf(print);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}