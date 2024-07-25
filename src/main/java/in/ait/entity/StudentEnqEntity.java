package in.ait.entity;



import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class StudentEnqEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enqId;
	private String studentName;
	private Long phoneNo;
	private String classMode;
	private String className;
	private String enqStatus;
	private Date enqDate;
	private Date createDate;
	private Date updateDate;
	//private Integer userId;

	    @ManyToOne
	    @JoinColumn(name = "user_Id")
	    private UserDtlsEntity user;

}
