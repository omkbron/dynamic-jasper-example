package com.omkbron.dynamic.jasper.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.log4j.Logger;

import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;

public class SimpleReport extends BaseReport {

	private static final Logger logger = Logger.getLogger(SimpleReport.class);
	
	@Override
	public DynamicReport buildReport() throws Exception {
		logger.debug("Armando reporte");
		FastReportBuilder builder = new FastReportBuilder();
		builder.setMargins(20, 40, 30, 30);
		builder.setDetailHeight(10);
		builder.setTitle("Reporte de prueba");
		builder.setSubtitle("Hora de generacion: " + new Date());
		builder.addColumn("Nombre", "nombre", String.class.getName(), 30);
		builder.addColumn("Apellido Paterno", "apellidoPaterno", String.class.getName(), 45);
		builder.addColumn("Apellido Materno", "apellidoMaterno", String.class.getName(), 45);
		builder.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_RIGHT);
		builder.setPrintBackgroundOnOddRows(true);
		builder.setUseFullPageWidth(true);
		getParams().put("paramAlgo", "la valor");
		builder.setTemplateFile("template-report.jrxml");
		return builder.build();
	}
	
	@Override
	public JRDataSource getDataSource() {
		List<Persona> lista = new ArrayList<Persona>();
		for (int i = 0; i <= 100; i++) {
			Persona persona = new Persona();
			persona.setNombre("Omar" + i);
			persona.setApellidoPaterno("Velasco" + i);
			persona.setApellidoMaterno("Peña" + i);
			lista.add(persona);
		}
		JRDataSource dataSource = new JRBeanCollectionDataSource(lista);
		return dataSource;
	}
}
