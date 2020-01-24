package aspertime.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aspertime.Dao.homeDao;
import aspertime.Entity.AttendanceLog;
import aspertime.Entity.Employee;
import aspertime.Entity.EmployeeLeaveApproval;
import aspertime.Entity.EmployeeProfileImage;
import aspertime.model.AttendanceLogModel;
import aspertime.model.EmployeeModel;
import aspertime.model.EmployeeProfileModel;
import aspertime.model.LeaveModel;
import aspertime.model.TodayAttendanceModel;

@Service
public class homeService {
	@Autowired
	homeDao dao;
	@Autowired
	EmployeeService empService;
	private AttendanceLog atl = new AttendanceLog();

	// for punch in
	public boolean punchin(EmployeeModel employee) {
		TimeAndDate td = new TimeAndDate();
	    dao.checkCancelledLeave(td.getDate(),employee.getUserid());
		Employee empd = new Employee();
		empd.setUserid(employee.getUserid());
		empd.setDepartment(employee.getDepartment());
		empd.setName(employee.getName());
		empd.setGender(employee.getGender());
		empd.setEmail(employee.getEmail());
		empd.setMobileNo(employee.getMobileNo());
		empd.setDesignation(employee.getDesignation());
		atl.setEmployee(empd);
		atl.setPunchIn(td.getTime());
		atl.setDate(td.getDate());
		atl.setDepartment(empd.getDepartment());
		System.out.println("service autowiring");
		if (dao.saveAttendance(atl)) {
			return true;
		} else {
			return false;
		}
	}

	// to check whether a record exists
	public boolean Check(EmployeeModel employee) {
		Employee empd = new Employee();
		empd.setUserid(employee.getUserid());
		empd.setDepartment(employee.getDepartment());
		empd.setName(employee.getName());
		empd.setGender(employee.getGender());
		empd.setEmail(employee.getEmail());
		empd.setMobileNo(employee.getMobileNo());
		if (dao.checkAttendance(empd)) {
			return true;
		} else {
			return false;
		}
	}
 
	// to punch out
	public boolean punchout(EmployeeModel empd) {
		Employee employee = new Employee();
		employee.setUserid(empd.getUserid());
		TimeAndDate td1 = new TimeAndDate();
		AttendanceLog atl1 = new AttendanceLog();
		AttendanceLog atl2 = new AttendanceLog();
		if (dao.checkAttendance(employee) && dao.CheckPunchout(employee)) {
			// for updating punchout time
			atl1.setPunchOut(td1.getTime());
			dao.UpdateAttendance(atl1, employee);

			String[] timeDetails = timeAndAttendance(employee);
			String Attendance = timeDetails[0];
			int TotalTime = Integer.parseInt(timeDetails[1]);
			System.out.println("total time:" + TotalTime);
			System.out.println("Attendace:");
			atl2.setTotaltime(TotalTime);
			atl2.setAttendance(Attendance);
			// for updating total time and attendance
			dao.UpdateTotal(atl2, employee);
			return true;
		} else {
			System.out.println("punchout not possible");
			return false;
		}
	}

	// for setting time in proper format
	public String[] timeAndAttendance(Employee employee) {
		String Attendance;
		String[] timeDetails = dao.timeDetails(employee);
		String[] Result = new String[2];
		String punchin = timeDetails[0];
		String punchout = timeDetails[1];
		System.out.println("punchin time:" + timeDetails[0] + "   punchout time" + timeDetails[1]);
		String hm[] = punchin.split(":");
		String hm2[] = punchout.split(":");
		int punchinHour = Integer.parseInt(hm[0]);
		int punchinMinutes = Integer.parseInt(hm[1]);
		int punchoutHour = Integer.parseInt(hm2[0]);
		int punchoutMinutes = Integer.parseInt(hm2[1]);
		int punchinMin = punchinHour * 60 + punchinMinutes;
		int punchoutMin = punchoutHour * 60 + punchoutMinutes;
		int totalTime = punchoutMin - punchinMin;
		if (totalTime >= 540) {
			Attendance = "P";
		} else if (totalTime >= 390 && totalTime <= 540) {
			Attendance = "HD";
		} else {
			Attendance = "A";
		}
		Result[0] = Attendance;
		Result[1] = String.valueOf(totalTime);
		return Result;
	}

	// for getting the attendance log tuple
	public AttendanceLog getAttendance(EmployeeModel empd) {
		AttendanceLog atl = new AttendanceLog();
		Employee employee = new Employee();
		employee.setUserid(empd.getUserid());
		atl = dao.getAttendance(employee);
		return atl;
	}

	// for setting the profile image path
	public void setProfileImage(String FileDownloadPath, Integer EmployeeID) {
		EmployeeProfileImage image = new EmployeeProfileImage();
		image.setEmployeeId(EmployeeID);
		image.setFileDownloadPath(FileDownloadPath);
		dao.setProfileImage(image);

	}

	public EmployeeProfileModel getImagePath(Integer empId) {
		EmployeeProfileModel profile = new EmployeeProfileModel();
		EmployeeProfileImage image = new EmployeeProfileImage();
		image = dao.getImagePath(empId);
		if (image == null) {
			return null;
		}
		profile.setEmployeeId(image.getEmployeeId());
		profile.setFileDownloadPath(image.getFileDownloadPath());
		return profile;
	}

	public AttendanceLogModel[] WeeklyAttendance(Integer empId) {
		return dao.getWeeklyAtendance(empId);
	}

	// Applying Leave
	public LeaveModel ApplyLeave(LeaveModel leave) {
		Date date = leave.getDate();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = formatter.format(date);
		Employee employee = empService.getEmployee(leave.getEmpId());
		EmployeeLeaveApproval empleave = new EmployeeLeaveApproval();
		Employee empd = new Employee();
		// setting employee from employee model
		empd.setUserid(employee.getUserid());
		empd.setDepartment(employee.getDepartment());
		empd.setName(employee.getName());
		empd.setGender(employee.getGender());
		empd.setEmail(employee.getEmail());
		empd.setMobileNo(employee.getMobileNo());
		empd.setDesignation(employee.getDesignation());
		empd.setCL(employee.getCL());
		empd.setEL(employee.getEL());
		empd.setML(employee.getML());
		// setting leave from leave model
		empleave.setLeaveSubject(leave.getLeaveSubject());
		empleave.setLeaveType(leave.getLeaveType());
		empleave.setLeaveReason(leave.getLeaveReason());
		empleave.setDate(strDate);
		empleave.setApprovalStatus(leave.getApprovalStatus());
		empleave.setEmpId(empd);
		empleave.setDepartment(employee.getDepartment());
		empleave.setCancellationStatus("N");
		dao.ApplyLeave(empleave);
		return null;
	}

	// Getting Leave
	public LeaveModel[] getLeave(int empId) throws ParseException {
		EmployeeLeaveApproval[] leaves = dao.getLeaves(empId);
		LeaveModel[] leave =new LeaveModel[leaves.length];
		for(int i=0;i<leaves.length;i++) {
		    LeaveModel Leave = new LeaveModel();
		    Leave.setApprovalStatus(leaves[i].getApprovalStatus());
		    Leave.setDateInString(leaves[i].getDate());
		    Leave.setEmpId(leaves[i].getEmpId().getUserid());
		    Leave.setLeaveId(leaves[i].getLeaveId());
		    Leave.setLeaveReason(leaves[i].getLeaveReason());
		    Leave.setLeaveSubject(leaves[i].getLeaveSubject());
		    Leave.setLeaveType(leaves[i].getLeaveType());
		    leave[i]=Leave;
		}
		return leave;
	}

	public Boolean checkLeaveDate(Date date, String empId) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date finalDate = formatter.parse("12-12-2020");
		String strDate = formatter.format(date);
		String currentDateInString = formatter.format(new Date());
		Date currentDate = formatter.parse(currentDateInString);
		Date LeaveDate = formatter.parse(strDate);
		if ((LeaveDate.before(currentDate)) || (LeaveDate.after(finalDate))) {
			System.out.println("date ahead");
			return false;
		} else {
			return dao.checkLeaveDate(strDate, empId);
		}
	}

	public TodayAttendanceModel[] getTodayAttendance(String department) {
		return dao.getTodayAttendanceModel(department);
	}

	public LeaveModel[] getLeavesAdmin(String department) {
		return dao.getLeavesAdmin(department);
	}

	public void UpdateLeaveApproval(String leaveId, String ApprovalStatus, String leaveType, Integer empId) {
		dao.UpdateLeaveApproval(leaveId, ApprovalStatus, leaveType, empId);

	}

	public Boolean CheckingLeaveBeforePunchin(String empId) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = formatter.format(date);
		return dao.checkLeaveDateBeforePunchIn(strDate, empId);
	}

	public void cancelLeave(String leaveId) {
		 dao.cancelLeave(leaveId);
	}

	public AttendanceLogModel getAttendanceMobile(long userid) {
		EmployeeModel employee = new EmployeeModel();
		employee = empService.findEmployee(userid);
		AttendanceLog atl = new AttendanceLog();
		AttendanceLogModel atlModel = new AttendanceLogModel();
		Employee empd = new Employee();
		empd.setUserid(employee.getUserid());
		empd.setDepartment(employee.getDepartment());
		empd.setName(employee.getName());
		empd.setGender(employee.getGender());
		empd.setEmail(employee.getEmail());
		empd.setMobileNo(employee.getMobileNo());
		empd.setDesignation(employee.getDesignation());
		empd.setCL(employee.getCL());
		empd.setEL(employee.getEL());
		empd.setML(employee.getML());
		atl = dao.getAttendanceMobile(empd);
		if(atl==null) {
			return null;
		}
		atlModel.setAttendance(atl.getAttendance());
		atlModel.setDate(atl.getDate());
		atlModel.setPunchIn(atl.getPunchIn());
		atlModel.setPunchOut(atl.getPunchOut());
		atlModel.setTotalTime(String.valueOf(atl.getTotaltime()));
	    return atlModel;
		
	}
}
