package in.ait.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnquiryController {
	
	@GetMapping("/dashboard")
	public String dashboardPage() {
		return "dashboard";
	}
	
	@GetMapping("/enquire")
	public String addEnquiryPage() {	
		return "add-enquairy";
	}
	
	@GetMapping("/enquires")
	public String viewEnquiryPage() {	
		return "view-enquairys";
	}

}
