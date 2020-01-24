package aspertime.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AttendanceLog")
public class AttendanceLog {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long serial;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_userid")
	private Employee employee;
	private String PunchIn;
	private String PunchOut;
	private int totaltime;
	private String date;
	private String Attendance;
	private String Department;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public long getSerial() {
		return serial;
	}

	public void setSerial(long serial) {
		this.serial = serial;
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

	public int getTotaltime() {
		return totaltime;
	}

	public void setTotaltime(int totaltime) {
		this.totaltime = totaltime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAttendance() {
		return Attendance;
	}

	public void setAttendance(String attendance) {
		Attendance = attendance;
	}

	
	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	@Override
	public String toString() {
		return "AttendanceLog [serial=" + serial + ", employee=" + employee + ", PunchIn=" + PunchIn + ", PunchOut="
				+ PunchOut + ", totaltime=" + totaltime + ", date=" + date + ", Attendance=" + Attendance + "]";
	}

}
