package in.ait.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ait.binding.SignUpForm;
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
	public boolean signUp(SignUpForm form) {

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
		body.append("Temporary pwd: "+ tempPwd);
		body.append("</br>");
		body.append("<a href= \"http://localhost:8080/unlock?email="+to+"\">Click here to unlock your account");

		emailUtils.sendEmail(to, subject, body.toString());

		return false;
	}





}
