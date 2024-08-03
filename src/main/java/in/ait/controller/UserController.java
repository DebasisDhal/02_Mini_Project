package in.ait.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ait.binding.LoginForm;
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

	@GetMapping("/unlock")
	public String unlockPage(@RequestParam String email, Model model) {
		UnlockForm unlockForm = new UnlockForm();
		unlockForm.setEmail(email);
		
		model.addAttribute("unlock", unlockForm);
		return "unlock";
	}
	
	@PostMapping("/unlock")
	public String unlockUserAccount(@ModelAttribute("unlock") UnlockForm unlock, Model model) {
		System.out.println(unlock);
		
		if(unlock.getNewPwd().equals(unlock.getConfirmPwd())) {
			boolean status = userService.unlockAccount(unlock);
			
			if(status) {
				model.addAttribute("succMsg", "Your account is unlocked");
			}else {
				model.addAttribute("errMsg", "Given temporary Pwd is wrong, check your email");
			}	
		}else {
			model.addAttribute("errMsg", "New pwd and conform pwd should be same");
		}
		return "unlock";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("loginForm",new LoginForm());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
		
		String status = userService.login(loginForm);
		if(status.contains("success")){
			return "redirect:/dashboard";
		}
		
		model.addAttribute("errMsg", status);
		return "login";
	}

	@GetMapping("/forgotPwd")
	public String forgotpwdPage() {
		return "forgotPwd";
	}

}
