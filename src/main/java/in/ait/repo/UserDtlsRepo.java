package in.ait.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ait.entity.UserDtlsEntity;
import java.util.List;


public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity, Integer>{
	public UserDtlsEntity findByEmail(String email);
	
	public UserDtlsEntity findByEmailAndPwd(String email, String pwd);
	
}
