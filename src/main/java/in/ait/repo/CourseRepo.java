package in.ait.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ait.entity.UserDtlsEntity;

public interface CourseRepo extends JpaRepository<UserDtlsEntity, Integer> {

}
