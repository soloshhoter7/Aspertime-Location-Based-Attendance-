package aspertime.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EmployeeProfileImage")
public class EmployeeProfileImage {
	@Id
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
		return "EmployeeProfileImage [EmployeeId=" + EmployeeId + ", FileDownloadPath=" + FileDownloadPath + "]";
	}

}
