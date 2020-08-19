package in.ecgc.smile.erp.hrd.empfe.controller;



import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.ecgc.smile.erp.hrd.empfe.service.EmployeeReportService;
import net.sf.jasperreports.engine.JRException;



/**
 * Report Controller class
 *
 *@version 1.2 15-May-20
 *@Author Architecture Team C-DAC Mumbai
 **/
@Controller
@RequestMapping("/")
public class ReportController {
	private static final Logger LOGGER=LoggerFactory.getLogger(ReportController.class);

	
	@Autowired
	private EmployeeReportService reportService;
	
	@GetMapping("/report")
	public String report(@Valid String reportFormat,HttpServletResponse response) throws JRException, IOException {

   			LOGGER.info("--Report Generation--");
			
		    reportService.exportReport(reportFormat,response);
			
			return "redirect:/employees";
	}
	
	
	
}
