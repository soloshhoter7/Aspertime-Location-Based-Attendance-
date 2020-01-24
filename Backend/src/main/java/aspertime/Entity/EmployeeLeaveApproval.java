package aspertime.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity
public class EmployeeLeaveApproval {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer LeaveId;
	@OneToOne
	private Employee empId;
	private String LeaveSubject;
	private String Date;
	private String LeaveType;
	@Size(min=0,max=10000)
	private String LeaveReason;
	private String ApprovalStatus;
    private String Department;
    private String CancellationStatus;
    
    
	public Integer getLeaveId() {
		return LeaveId;
	}

	public void setLeaveId(Integer leaveId) {
		LeaveId = leaveId;
	}

	public Employee getEmpId() {
		return empId;
	}

	public void setEmpId(Employee empId) {
		this.empId = empId;
	}

	public String getLeaveSubject() {
		return LeaveSubject;
	}

	public void setLeaveSubject(String leaveSubject) {
		LeaveSubject = leaveSubject;
	}

	

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getLeaveType() {
		return LeaveType;
	}

	public void setLeaveType(String leaveType) {
		LeaveType = leaveType;
	}

	public String getLeaveReason() {
		return LeaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		LeaveReason = leaveReason;
	}

	public String getApprovalStatus() {
		return ApprovalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		ApprovalStatus = approvalStatus;
	}

	
	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}
	

	public String getCancellationStatus() {
		return CancellationStatus;
	}

	public void setCancellationStatus(String cancellationStatus) {
		CancellationStatus = cancellationStatus;
	}

	@Override
	public String toString() {
		return "EmployeeLeaveApproval [LeaveId=" + LeaveId + ", empId=" + empId + ", LeaveSubject=" + LeaveSubject
				+ ", Date=" + Date + ", LeaveType=" + LeaveType + ", LeaveReason=" + LeaveReason + ", ApprovalStatus="
				+ ApprovalStatus + ", Department=" + Department + ", CancellationStatus=" + CancellationStatus + "]";
	}


}
