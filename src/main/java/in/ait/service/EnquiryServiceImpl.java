package in.ait.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ait.binding.DashboardResponse;
import in.ait.binding.EnquiryForm;
import in.ait.entity.CourseEntity;
import in.ait.entity.EnqStatusEntity;
import in.ait.entity.StudentEnqEntity;
import in.ait.entity.UserDtlsEntity;
import in.ait.repo.CourseRepo;
import in.ait.repo.EnqStatusRepo;
import in.ait.repo.StudentEnqRepo;
import in.ait.repo.UserDtlsRepo;
import jakarta.servlet.http.HttpSession;


@Service
public class EnquiryServiceImpl implements EnquiryService {
	
	@Autowired
	private UserDtlsRepo userDtlsRepo;
	
	@Autowired
	private StudentEnqRepo enqRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private EnqStatusRepo statusRepo;
	
	@Autowired
	private HttpSession session;

	@Override
	public DashboardResponse getDashboardData(Integer userId) {
		
		DashboardResponse dashboardResponse = new DashboardResponse();

       Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);
       
       if(findById.isPresent()) {
    	   UserDtlsEntity userDtlsEntity = findById.get();
    	   
    	   List<StudentEnqEntity> enquiries = userDtlsEntity.getEnquiries();
    	   Integer totalCnt = enquiries.size();
    	   
    	   Integer enrolledCnt = enquiries.stream()
    			              .filter(e -> e.getEnqStatus().equals("Enrolled"))
    			              .collect(Collectors.toList()).size();
    	   
    	   Integer lostCnt = enquiries.stream()
    			   .filter(e -> e.getEnqStatus().equals("Lost"))
    			    .collect(Collectors.toList()).size();
    	   
    	   dashboardResponse.setTotalEnquriesCnt(totalCnt);
    	   dashboardResponse.setEnrolledCnt(enrolledCnt);
    	   dashboardResponse.setLostCnt(lostCnt);
       }
		return dashboardResponse;
	}

	@Override
	public List<String> getCourses() {

           List<CourseEntity> findAll = courseRepo.findAll();
           
           List<String> names = new ArrayList<>();
           
           for(CourseEntity entity : findAll) {
        	   names.add(entity.getCourseName());
           }
		
		return names;
	}

	@Override
	public List<String> getEnqStatus() {
		  List<EnqStatusEntity> findAll = statusRepo.findAll();
          
          List<String> statusList = new ArrayList<>();
          
          for(EnqStatusEntity entity : findAll) {
        	  statusList.add(entity.getStatusName());
          }
		
		return statusList;
	}

	@Override
	public boolean saveEnquriry(EnquiryForm form) {

		StudentEnqEntity enqEntity = new StudentEnqEntity();
		BeanUtils.copyProperties(form, enqEntity);
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		UserDtlsEntity userEntity = userDtlsRepo.findById(userId).get();
		enqEntity.setUser(userEntity);
		
		enqRepo.save(enqEntity);
		
		return true;
	}

	@Override
	public List<StudentEnqEntity> getEnquiries() {
		
		Integer userId = (Integer) session.getAttribute("userId");

		Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);
		if(findById.isPresent()) {
			UserDtlsEntity userDtlsEntity = findById.get();
			List<StudentEnqEntity> enquiries = userDtlsEntity.getEnquiries();
			return enquiries;
		}
		return null;
	}
	

}
