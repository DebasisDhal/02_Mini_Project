package in.ait.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="JAVADSHUB_COURSES")
@Data
public class CourseEntity {
	
	@Id
	@GeneratedValue
	private Integer courseId;
	private String courseName;
	

}
