package in.ait.service;

import java.util.List;

import in.ait.binding.DashboardResponse;
import in.ait.binding.EnquiryForm;
import in.ait.binding.EnquirySearchCriteria;

public interface EnquiryService {
	public List<String> getCourseNames();

	public List<String> getEnqStatus();

	public DashboardResponse getDashboardData(Integer userId);

	public String upsertEnquiry(EnquiryForm form);

	public List<EnquiryForm> gerEnquries(Integer userId, EnquirySearchCriteria criteria);

	public EnquiryForm getEnquiry(Integer enqId);


}
