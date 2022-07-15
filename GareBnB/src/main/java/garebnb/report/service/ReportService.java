package garebnb.report.service;

import java.util.List;
import java.util.Map;

public interface ReportService {

	//report One list
	List<Map<String, Object>> selectOneReportList(Map<String, Object> map) throws Exception;
	
	//report insert
	void insertReport(Map<String, Object> map) throws Exception;
		
	//Admin report list
	List<Map<String, Object>> selectAdminReportList(Map<String, Object> map) throws Exception;
	
	//Admin report detail
	List<Map<String, Object>> selectAdminOneReport(Map<String, Object> map) throws Exception;

	//Admin report comment
	void updateReportComment(Map<String, Object> map) throws Exception;



}
