package com.omkbron.dynamic.jasper.example;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;

public abstract class BaseReport {
	
	private static final Logger logger = Logger.getLogger(BaseReport.class);
	
	protected JasperPrint jasperPrint;
	protected JasperReport jasperReport;
	protected Map<String, Object> params = new HashMap<String, Object>();
	protected DynamicReport dynamicReport;
	
	public abstract DynamicReport buildReport() throws Exception;
	
	public abstract JRDataSource getDataSource();
	
	public void generateReport() throws Exception {
		dynamicReport = buildReport();
		
		JRDataSource dataSource = getDataSource();
		
		jasperReport = DynamicJasperHelper.generateJasperReport(dynamicReport, getLayoutManager(), params);
		
		logger.debug("Llenando el reporte");
		
		if (dataSource != null) {
			jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		} else {
			jasperPrint = JasperFillManager.fillReport(jasperReport, params);
		}
		
		logger.debug("Llenado terminado");
		
	}

	protected LayoutManager getLayoutManager() {
		return new ClassicLayoutManager();
	}
	
	public JasperPrint getJasperPrint() {
		return jasperPrint;
	}
	
	public JasperReport getJasperReport() {
		return jasperReport;
	}
}
