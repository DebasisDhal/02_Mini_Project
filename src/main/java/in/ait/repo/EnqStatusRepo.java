package in.ait.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ait.entity.CourseEntity;
import in.ait.entity.EnqStatusEntity;

public interface EnqStatusRepo extends JpaRepository<EnqStatusEntity, Integer> {

	

}
