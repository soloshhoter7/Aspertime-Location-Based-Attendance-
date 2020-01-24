package aspertime.model;

import org.springframework.web.multipart.MultipartFile;

public class EmployeeProfileModel {
	private Integer EmployeeId;
	private String FileDownloadPath;
	public Integer getEmployeeId() {
		return EmployeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		EmployeeId = employeeId;
	}
	public String getFileDownloadPath() {
		return FileDownloadPath;
	}
	public void setFileDownloadPath(String fileDownloadPath) {
		FileDownloadPath = fileDownloadPath;
	}
	@Override
	public String toString() {
		return "EmployeeProfileModel [EmployeeId=" + EmployeeId + ", FileDownloadPath=" + FileDownloadPath + "]";
	}


}
