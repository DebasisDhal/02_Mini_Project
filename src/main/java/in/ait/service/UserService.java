package in.ait.service;

import in.ait.binding.LoginForm;
import in.ait.binding.SignUpForm;
import in.ait.binding.UnlockForm;

public interface UserService {
	//public String login(LoginForm form);
	
	public boolean signUp(SignUpForm form);
	
	//public boolean unlockAccount(UnlockForm form);
	
	//public String forgtPwd(String email);

}
