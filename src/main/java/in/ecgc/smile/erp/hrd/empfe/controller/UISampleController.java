package in.ecgc.smile.erp.hrd.empfe.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.hrd.empfe.dto.FormFields;

/**
 *UI Sample Controller class
 *
 *@version 1.2 15-May-20
 *@Author Architecture Team C-DAC Mumbai
 **/
@Controller
@RequestMapping("/")
public class UISampleController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(UISampleController.class);
	
	@Autowired
	ServletContext servletContext;

	/*
	 * @GetMapping("/") public String home() {
	 * System.out.println("Inside Home Controller"); return "index"; }
	 */
	
	@GetMapping("/indexwithsm")
	public String indexpage() {
		System.out.println("Inside Home Controller index With SM");
		return "index";
	}
	@GetMapping("/formelementswiththymelaf")
    public String getFormData(Model model)  {
		model.addAttribute("addApplicant",new FormFields());

		List<String> categories = new ArrayList<String>();
		categories.add("composite");
		categories.add("nonlife");
		categories.add("life");
		categories.add("general");
		
		model.addAttribute("categories",categories);
		
	return "view/FormElementsWithThymeleaf";
	}
	
	@PostMapping("/formelementswiththymelafview")
    public String getFormData(@ModelAttribute("addApplicant") @Valid FormFields fields,BindingResult result,Model model) {
		if (result.hasErrors()) {
			model.addAttribute("addApplicant",fields);
			System.out.println("+++++++++++++++++"+fields+"+++++++++++++++");
         return "view/FormElementsWithThymeleaf";
     }
	model.addAttribute("viewApplicant",fields);
	List<String> categories = new ArrayList<String>();
	categories.add("composite");
	categories.add("nonlife");
	categories.add("life");
	categories.add("general");
	model.addAttribute("categories",categories);
	
	return "view/FormElementsWithThymeleafView";
	}
	
	@GetMapping("/form-with-sections")
    public String inputData() {
	return "view/form-with-sections";
	}
	@GetMapping("/form-with-no-sections")
    public String formwithNoSection() {
        return "view/form-with-no-sections";
    }
	@GetMapping("/form-view-with-sections")
    public String formViewWithSection() {
        return "view/form-view-with-sections";
    }
	



	@GetMapping("/datatablewithsm")
	public String datatables() {
		System.out.println("Inside Home Controller With SM datatable");
		return "view/datatable";
	}

	@GetMapping("/datatable-with-centreal-edit-view-button")
	public String datatableWithCentralEdit(Model model) throws JsonParseException, JsonMappingException, IOException {
		
		System.out.println("inside json to table");
		;

		File file = ResourceUtils.getFile("classpath:result.json");

		//Read File Content
		String content = new String(Files.readAllBytes(file.toPath()));
		System.out.println("content is :"+content);

		byte[] mapData = Files.readAllBytes(file.toPath());

		ObjectMapper objectMapper=new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);    
		List<Map<String, Object>> myObjects =
		objectMapper.readValue(mapData , new TypeReference<List<Map<String, Object>>>(){});
		System.out.println("Map using TypeReference: "+myObjects);
	
		model.addAttribute("myObjects",myObjects);
	
		return "view/DataTable-With-Central-Edit-View-Button";
	}
	
	@GetMapping("/buttonswithsm")
	public String buttons() {
		System.out.println("Inside Home Controller With SM buttons");
		return "view/buttons";
	}


	
	@GetMapping("/mutitabform")
	public String multiTabForm() {
		System.out.println("Inside Home Controller With SM multiTabForm");
		return "view/MultiTab";
	}

	@GetMapping("/mutitab-with-restriction")
	public String multiTabWithRestriction() {
		System.out.println("Inside Home Controller With SM multiTabForm");
		return "view/multitab-with-restriction";
	}

	@GetMapping("/alertwithsm")
	public String alert() {
		System.out.println("Inside Home Controller With SM alert");
		return "view/alert";
	}
	
	@GetMapping("/loaderwithsm")
	public String loader() {
		System.out.println("Inside Home Controller With SM loaders");
		return "view/loader";
	}

	@GetMapping("/employeeprofilewithsm")
	public String profile() {
		System.out.println("Inside Home Controller With SM profile");
		return "view/employeeprofile";
	}
	@GetMapping("/tabletojsonwithsm")
	public String tabletojson() {
		System.out.println("Inside Home Controller With SM table to json");
		return "view/tabletojson";
	}

	@GetMapping("/dashboardwithsm")
	public String dashboard() {
		System.out.println("Inside Home Controller With SM dashboard");
		return "view/dashboard";
	}

	@GetMapping("/calenderwithsm")
	public String calender() {
		System.out.println("Inside Home Controller With SM calender");
		return "view/calender";
	}
	
}
