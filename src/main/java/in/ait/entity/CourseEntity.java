package in.ait.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="JAVADSHUB_COURSES")
@Data
public class CourseEntity {
	
	@Id
	private Integer courseId;
	private String courseName;
	

}
