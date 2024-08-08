package in.ait.service;

import java.util.List;

import in.ait.binding.DashboardResponse;
import in.ait.binding.EnquiryForm;
import in.ait.binding.EnquirySearchCriteria;

public interface EnquiryService {

//
	public DashboardResponse getDashboardData(Integer userId);
	
	public List<String> getCourses();
	public List<String> getEnqStatus();
	
	public boolean saveEnquriry(EnquiryForm form);
//
//	public String upsertEnquiry(EnquiryForm form);
//
//	public List<EnquiryForm> gerEnquries(Integer userId, EnquirySearchCriteria criteria);
//
//	public EnquiryForm getEnquiry(Integer enqId);


}
