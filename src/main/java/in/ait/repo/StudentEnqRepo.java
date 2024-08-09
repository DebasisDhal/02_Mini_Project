package in.ait.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ait.entity.StudentEnqEntity;

public interface StudentEnqRepo extends JpaRepository<StudentEnqEntity, Integer>{

}
