package in.ecgc.smile.erp.hrd.empfe.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import in.ecgc.smile.erp.hrd.empfe.dto.Employee;
import in.ecgc.smile.erp.hrd.empfe.proxy.EmployeeClient;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.export.SimpleXmlExporterOutput;

/**
 *Report Service Implementation 
 *used for calling BE Microservice
 *
 *@version 1.2 15-May-20
 *@Author Architecture Team C-DAC Mumbai
 *
 **/
@Service
public class EmployeeReportServiceImpl implements EmployeeReportService {
	
	@Autowired
	EmployeeClient empClient;
	
	@Override
	public void exportReport(String reportFormat,HttpServletResponse response) throws JRException, IOException {
		List<Employee> employeeData=empClient.getAllEmployees();
		File file=ResourceUtils.getFile("classpath:employeeReport.jrxml");
		JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(employeeData);
		Map<String,Object> parameters=new HashMap<>();
		parameters.put("createdBy", "cdac");
		JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,parameters,dataSource);
		
		 if(reportFormat.equalsIgnoreCase("pdf")) 
		  {
			    final OutputStream outStream = response.getOutputStream();
			    JRPdfExporter exporter=new JRPdfExporter();
			    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outStream));
			    
			    SimplePdfReportConfiguration reportConfig=new SimplePdfReportConfiguration();
			    
			    reportConfig.setSizePageToContent(true);
			    reportConfig.setForceLineBreakPolicy(false);
			    SimplePdfExporterConfiguration exportConfig= new SimplePdfExporterConfiguration();
			    exportConfig.setMetadataAuthor("CDAC-MUMBAI");
			    exportConfig.setEncrypted(true);
			  	exportConfig.setAllowedPermissionsHint("PRINTING");
			    exporter.setConfiguration(reportConfig);
			    exporter.setConfiguration(exportConfig);
			    exporter.exportReport();
			    ByteArrayOutputStream outputByteArray = new ByteArrayOutputStream();
			    outStream.write(outputByteArray.toByteArray());   
		  }
		
		if(reportFormat.equalsIgnoreCase("xml"))
		{
			 response.setContentType("application/x-xml");
			 response.setHeader("Content-disposition", "inline; filename=EmployeeReport.xml");
			  final OutputStream outStream = response.getOutputStream();
			  JRXmlExporter exporter=new JRXmlExporter();
			  
			  exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			  exporter.setExporterOutput(new SimpleXmlExporterOutput(outStream));
			  exporter.exportReport();
			  
		}
		 if(reportFormat.equalsIgnoreCase("html"))
	        {
			 	response.setContentType("application/x-html");
			    response.setHeader("Content-disposition", "inline; filename=EmployeeReport.html");
			    final OutputStream outStream = response.getOutputStream();
	        	HtmlExporter exporter = new HtmlExporter();
	        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	        	exporter.setExporterOutput(
	        	  new SimpleHtmlExporterOutput(outStream));
	        	 
	        	exporter.exportReport();
	        }
		 if(reportFormat.equalsIgnoreCase("xls"))
		 {		
			 	response.setContentType("application/x-xls");
			    response.setHeader("Content-disposition", "inline; filename=EmployeeReport.xls");

			    final OutputStream outStream = response.getOutputStream();
			    JRXlsxExporter exporter = new JRXlsxExporter();
			  
			    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outStream ));
			    
			    SimpleXlsxReportConfiguration reportConfig= new SimpleXlsxReportConfiguration();
			    reportConfig.setSheetNames(new String[] { "Employee Data" });
			    reportConfig.setOnePagePerSheet(true);
			    reportConfig.setWhitePageBackground(false);
		
			    reportConfig.setRemoveEmptySpaceBetweenRows(true);
			    reportConfig.setRemoveEmptySpaceBetweenColumns(true);
		 
			    reportConfig.setCollapseRowSpan(true);
			    reportConfig.setCellHidden(true);
			    reportConfig.setFreezeRow(5);
			    reportConfig.setWrapText(true);
			    reportConfig.setAutoFitPageHeight(true);
			    reportConfig.setShrinkToFit(true);
			    exporter.setConfiguration(reportConfig);
			    exporter.exportReport();
			    
		 }
		 if(reportFormat.equalsIgnoreCase("csv"))
		 {
			 response.setContentType("application/x-csv");
			 response.setHeader("Content-disposition", "inline; filename=EmployeeReport.csv");
			    final OutputStream outStream = response.getOutputStream();
			  JRCsvExporter exporter = new JRCsvExporter();
			  
			  exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			  exporter.setExporterOutput(new SimpleWriterExporterOutput(outStream));			 
			
			 exporter.exportReport();
		 }
	}
	
	
	
	
}
