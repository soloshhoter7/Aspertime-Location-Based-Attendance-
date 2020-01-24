package aspertime.model;

public class AttendanceLogModel {
	private String PunchIn;
	private String PunchOut;
	private String Attendance;
	private String Date;
	private String TotalTime;

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

	public String getAttendance() {
		return Attendance;
	}

	public void setAttendance(String attendance) {
		Attendance = attendance;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getTotalTime() {
		return TotalTime;
	}

	public void setTotalTime(String totalTime) {
		TotalTime = totalTime;
	}

	@Override
	public String toString() {
		return "AttendanceLogModel [PunchIn=" + PunchIn + ", PunchOut=" + PunchOut + ", Attendance=" + Attendance
				+ ", Date=" + Date + ", TotalTime=" + TotalTime + "]";
	}

}
