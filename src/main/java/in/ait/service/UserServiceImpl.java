 package in.ait.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ait.binding.LoginForm;
import in.ait.binding.SignupForm;
import in.ait.binding.UnlockForm;
import in.ait.entity.UserDtlsEntity;
import in.ait.repo.UserDtlsRepo;
import in.ait.util.EmailUtils;
import in.ait.util.PwdUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private EmailUtils emailUtils;

	@Autowired
    private UserDtlsRepo userDtlsRepo;
	
	
	@Override
	public String login(LoginForm form) {
		
		UserDtlsEntity entity = 
				userDtlsRepo.findByEmailAndPwd(form.getEmail(), form.getPassword());
		
		if(entity == null) {
			return "Invalid credential";
		}
		if(entity.getAccStatus().equals("LOCKED")) {
			return "Your account is Locked";
		}
		return "success";
	}
	
	
	@Override
	public boolean unlockAccount(UnlockForm form) {
		
		UserDtlsEntity entity = userDtlsRepo.findByEmail(form.getEmail());
		
		if(entity.getPwd().equals(form.getTempPwd())) {
			entity.setPwd(form.getNewPwd());
			entity.setAccStatus("UNLOCKED");
			userDtlsRepo.save(entity);
			return true;
		}else {
			return false;
		}
	}

	

	@Override
	public boolean signup(SignupForm form) {
		
		
		UserDtlsEntity user = userDtlsRepo.findByEmail(form.getEmail());
		
		if(user != null) {
			return false;
		}
		

		// TODO: copy data from binding obj to entity obj

		UserDtlsEntity entity = new UserDtlsEntity();
		BeanUtils.copyProperties(form, entity);

		//TODO: generate random pwd and set to the object

		String tempPwd = PwdUtils.generateRandomPwd();
		entity.setPwd(tempPwd);

		//TODO: set account status as locked

		entity.setAccStatus("LOCKED");

		//TODO: Insert record

		userDtlsRepo.save(entity);

		//TODO: send email to user to unlock the account

		String to = form.getEmail();
		String subject = "unlock your Account | Ds";

		StringBuffer body = new StringBuffer();

		body.append("<h1>Use below temporary password to unlock your  account </h1>");
		body.append("Temporary pwd::  "+ tempPwd);
		body.append("</br>");
		body.append("<a href= \"http://localhost:8081/unlock?email="+to+"\">Click here to unlock your account");

		emailUtils.sendEmail(to, subject, body.toString());

		return true;
	}


	@Override
	public boolean forgtPwd(String email) {
		
		//check the reocrd is availble or not in DB
		UserDtlsEntity entity = userDtlsRepo.findByEmail(email);
		
		//if record not available in db
		if(entity == null) {
			return false;
		}
		
		//If record is available send pwd into email and send sucess
		
		String Subject = "Recovery password";
		String body = "your pwd::  "+entity.getPwd();
		
		emailUtils.sendEmail(email, Subject, body);
		
		return true;
	}













}
