package in.ait.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name ="AIT_ENQURIRY_STATUS")
@Data
public class EnqStatusEntity {

	@Id
	private Integer statusId;
	private String statusName;

}
