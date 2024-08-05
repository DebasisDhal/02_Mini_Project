package in.ait.service;

import in.ait.binding.LoginForm;
import in.ait.binding.SignupForm;
import in.ait.binding.UnlockForm;

public interface UserService {
	


	public boolean signup(SignupForm form);

	public boolean unlockAccount(UnlockForm form);
	
	public String login(LoginForm form);

	public boolean forgtPwd(String email);



}
