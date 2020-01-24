package aspertime.model;

import java.util.Date;
import aspertime.Entity.Employee;

public class LeaveModel {
	public Integer LeaveId;
	public long empId;
	public String LeaveSubject;
	public Date Date;
	public String DateInString;
	public String LeaveType;
	public String LeaveReason;
	public String ApprovalStatus;

//	public Integer getLeaveId() {
//		return LeaveId;
//	}
//
//	public void setLeaveId(Integer leaveId) {
//		LeaveId = leaveId;
//	}

	public String getLeaveSubject() {
		return LeaveSubject;
	}

	public void setLeaveSubject(String leaveSubject) {
		LeaveSubject = leaveSubject;
	}

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
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

	public Integer getLeaveId() {
		return LeaveId;
	}

	public void setLeaveId(Integer leaveId) {
		LeaveId = leaveId;
	}


	
	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getDateInString() {
		return DateInString;
	}

	public void setDateInString(String dateInString) {
		DateInString = dateInString;
	}

	@Override
	public String toString() {
		return "LeaveModel [LeaveId=" + LeaveId + ", empId=" + empId + ", LeaveSubject=" + LeaveSubject + ", Date="
				+ Date + ", DateInString=" + DateInString + ", LeaveType=" + LeaveType + ", LeaveReason=" + LeaveReason
				+ ", ApprovalStatus=" + ApprovalStatus + "]";
	}


	



}
