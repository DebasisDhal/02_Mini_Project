package in.ait.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ait.binding.DashboardResponse;
import in.ait.binding.EnquiryForm;
import in.ait.entity.StudentEnqEntity;
import in.ait.service.EnquiryService;
import jakarta.mail.Session;
import jakarta.servlet.http.HttpSession;


@Controller
public class EnquiryController {
	
	@Autowired
	private EnquiryService enquiryService;
	
	@Autowired
	private HttpSession httpSession;
	
	@GetMapping("/logout")
	public String logout() {
		httpSession.invalidate();
		return "index";
	}

	@GetMapping("/dashboard")
	public String dashboardPage(Model model) {
		Integer userId = (Integer) httpSession.getAttribute("userId");
		DashboardResponse dashboardData = 
				enquiryService.getDashboardData(userId);
		
		model.addAttribute("dashboardData", dashboardData);
		
		return "dashboard";
	}
	
	
	@PostMapping("/addEnq")
	public String addEnquiryPage(@ModelAttribute("formObj") EnquiryForm formObj, Model model) {
		
		System.out.println(formObj);
		
		boolean status = enquiryService.saveEnquriry(formObj);
		
		if(status) {
			model.addAttribute("succMsg", "Enquiry Added");
		}else {
			model.addAttribute("errMsg", "Problem Occured");
		}
		
		return"add-enquairy";
	}
	
	
	

	@GetMapping("/enquire")
	public String addEnquiryPage(Model model) {
		
		// get courses for drop down
		List<String> courses = enquiryService.getCourses();
		
		//get enq status for drop down
		List<String> enqStatuses = enquiryService.getEnqStatus();
		
		//create binding class object
		EnquiryForm formObj = new EnquiryForm();
		
		//send data in model object
		
		model.addAttribute("courseName",courses);
		model.addAttribute("enqStatusNames",enqStatuses);
		model.addAttribute("formObj",formObj);
		
		return "add-enquairy";
	}

	@GetMapping("/enquires")
	public String viewEnquiryPage(Model model) {
	//	initForm(model);
		List<StudentEnqEntity> enquires = enquiryService.getEnquiries();
				model.addAttribute("enquires", enquires);
				
		return "view-enquairys";
	}





}
