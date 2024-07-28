package in.ait.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ait.binding.SignupForm;
import in.ait.binding.UnlockForm;
import in.ait.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/signup")
	public String signUpPage(Model model) {
		model.addAttribute("user",new SignupForm());
		return "signup";
	}

	@PostMapping("/signup")
    public String handleSingUp(@ModelAttribute("user") SignupForm form, Model model) {

		boolean status = userService.signup(form);

		if(status) {
			model.addAttribute("succMsg","Account created, Check your email");
			
		}else {
			model.addAttribute("errMsg", "Choose a unique email");				
		}
    	return "signup";
    }

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@GetMapping("/unlock")
	public String unlockPage(@RequestParam String email, Model model) {
		UnlockForm unlockForm = new UnlockForm();
		unlockForm.setEmail(email);
		
		model.addAttribute("unlock", unlockForm);
		return "unlock";
	}
	
	@PostMapping("/unlock")
	public String unlockUserAccount(@ModelAttribute UnlockForm unlock) {
		System.out.println(unlock);
		return "unlock";
	}

	@GetMapping("/forgotPwd")
	public String forgotpwdPage() {
		return "forgotPwd";
	}

}
