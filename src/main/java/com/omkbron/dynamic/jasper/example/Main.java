package com.omkbron.dynamic.jasper.example;

import net.sf.jasperreports.engine.JasperExportManager;

import org.apache.log4j.Logger;

public class Main {
	
	private static final Logger logger = Logger.getLogger(Main.class);
	
	public static void main(String[] args) throws Exception {
		logger.info("Iniciando dynamic jasper example");
		SimpleReport report = new SimpleReport();
		report.generateReport();
		JasperExportManager.exportReportToPdfFile(report.getJasperPrint(), "reporte.pdf");
	}
}
