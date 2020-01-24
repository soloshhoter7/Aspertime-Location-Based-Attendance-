package aspertime.model;

import aspertime.Entity.Employee;

public class TodayAttendanceModel {
	public EmployeeModel employee;
	public String PunchIn;
	public String PunchOut;



	public EmployeeModel getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeModel employee) {
		this.employee = employee;
	}

	public String getPunchIn() {
		return PunchIn;
	}

	public void setPunchIn(String punchIn) {
		PunchIn = punchIn;
	}

	public String getPunchOut() {
		return PunchOut;
	}

	public void setPunchOut(String punchOut) {
		PunchOut = punchOut;
	}

	@Override
	public String toString() {
		return "TodayAttendanceModel [employee=" + employee + ", PunchIn=" + PunchIn + ", PunchOut=" + PunchOut + "]";
	}

	
}
