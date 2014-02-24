package com.omkbron.dynamic.jasper.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.log4j.Logger;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.constants.GroupLayout;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;

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
		GroupBuilder gb1 = new GroupBuilder();
		
		DJGroup group1 = gb1.setCriteriaColumn((PropertyColumn) builder.getColumn(0))
				.setGroupLayout(GroupLayout.VALUE_IN_HEADER)
				.build();
		
		builder.addGroup(group1);
		getParams().put("paramAlgo", "la valosr");
		builder.setTemplateFile("template-report.jrxml");
		return builder.build();
	}
	
//	@Override
//	public DynamicReport buildReport() throws Exception {
//		logger.debug("Armando reporte");
//		FastReportBuilder builder = new FastReportBuilder();
//		builder.setMargins(20, 40, 30, 30);
//		builder.setDetailHeight(10);
//		builder.setTitle("Reporte de prueba");
//		builder.setSubtitle("Hora de generacion: " + new Date());
//		builder.setWhenNoDataAllSectionNoDetail();
//		builder.addConcatenatedReport(createSubReport("subreport1"), getLayoutManager(), 
//				"personas", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, 
//				DJConstants.DATA_SOURCE_TYPE_COLLECTION, false);
//		builder.addConcatenatedReport(createSubReport2("subreport2"), getLayoutManager(), 
//				"personas2", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, 
//				DJConstants.DATA_SOURCE_TYPE_COLLECTION, false);
//		builder.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_RIGHT);
//		builder.setPrintBackgroundOnOddRows(true);
//		builder.setUseFullPageWidth(true);
//
//		getParams().put("personas", getDataSourceReport());
//		getParams().put("personas2", getDataSourceReport());
//		getParams().put("paramAlgo", "la valosr");
//		builder.setTemplateFile("template-report.jrxml");
//		return builder.build();
//	}
	
	private Object getDataSourceReport() {
		List<Persona> lista = new ArrayList<Persona>();
		for (int i = 0; i <= 5; i++) {
			Persona persona = new Persona();
			persona.setNombre("Omar");
			persona.setApellidoPaterno("Velasco" + i);
			persona.setApellidoMaterno("Peña" + i);
			lista.add(persona);
		}
	return lista;
}

	private DynamicReport createSubReport(String title) throws ColumnBuilderException, ClassNotFoundException {
		FastReportBuilder builder = new FastReportBuilder();
		builder.addColumn("Nombre", "nombre", String.class.getName(), 30);
		builder.addColumn("Apellido Paterno", "apellidoPaterno", String.class.getName(), 45);
		builder.addColumn("Apellido Materno", "apellidoMaterno", String.class.getName(), 45);
		builder.setUseFullPageWidth(true);
		builder.setTitle(title);
		return builder.build();
	}

	private DynamicReport createSubReport2(String title) throws ColumnBuilderException, ClassNotFoundException {
		Style titleStyle = new Style();
		titleStyle.setHorizontalAlign(HorizontalAlign.LEFT);
		FastReportBuilder builder = new FastReportBuilder();
		builder.addColumn("Nombre", "nombre", String.class.getName(), 30);
		builder.addColumn("Apellido Materno", "apellidoMaterno", String.class.getName(), 45);
		builder.setUseFullPageWidth(true);
		builder.setTitle(title);
		builder.setTitleStyle(titleStyle);
		return builder.build();
	}
	
	@Override
	public JRDataSource getDataSource() {
		List<Persona> lista = new ArrayList<Persona>();
		for (int i = 0; i <= 5; i++) {
			Persona persona = new Persona();
			persona.setNombre("Omar");
			persona.setApellidoPaterno("Velasco" + i);
			persona.setApellidoMaterno("Peña" + i);
			lista.add(persona);
		}
		for (int i = 0; i <= 10; i++) {
			Persona persona = new Persona();
			persona.setNombre("Omar2");
			persona.setApellidoPaterno("Velasco" + i);
//			persona.setApellidoMaterno("Peña" + i);
			lista.add(persona);
		}
		for (int i = 0; i <= 3; i++) {
			Persona persona = new Persona();
			persona.setNombre("Omar3");
//			persona.setApellidoPaterno("Velasco" + i);
			persona.setApellidoMaterno("Peña" + i);
			lista.add(persona);
		}
		JRDataSource dataSource = new JRBeanCollectionDataSource(lista);
		return dataSource;
	}
	
	
}
