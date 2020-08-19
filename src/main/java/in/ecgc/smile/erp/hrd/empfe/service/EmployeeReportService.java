package in.ecgc.smile.erp.hrd.empfe.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;


/**
 *Report Service Interface
 *used for calling BE Microservice
 *
 *@version 1.2 15-May-20
 *@Author Architecture Team C-DAC Mumbai
 *
 **/
public interface EmployeeReportService {
	
	//Method for convert object into JasperPrint
	void exportReport(String reportFormat,HttpServletResponse response) throws FileNotFoundException, JRException,IOException;

}
