package aspertime.Controller;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import aspertime.Entity.AttendanceLog;
import aspertime.Entity.Employee;
import aspertime.Service.EmployeeService;
import aspertime.Service.homeService;
import aspertime.model.AttendanceLogModel;
import aspertime.model.EmployeeModel;
import aspertime.model.EmployeeProfileModel;
import aspertime.model.LeaveModel;
import aspertime.model.TodayAttendanceModel;
import aspertime.model.checkDateModel;

@Controller
@CrossOrigin(origins = "http://virtualserver123.ddns.net")
//@CrossOrigin(origins = "https://aspertime.xyz")
@RequestMapping(value = "/home")
public class homeController {
	@Autowired
	homeService service;
 
	@Autowired
	EmployeeService empService;
	
	Employee employee = new Employee();

	// for punching in
	@RequestMapping(value = "/punchin", produces = { "application/json" })
	@ResponseBody
	public boolean punchIn(long userid) throws IOException {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 10 - punching in attendance - punchIn() - /punchin");
		EmployeeModel employee = empService.findEmployee(userid);
		if (service.Check(employee)) {
			System.out.println("can't punchin !");
			return false;
		} else {
			service.punchin(employee);
			return true;
		}
	}
    
	// for punching out
	@RequestMapping(value = "/punchout", produces = { "application/json" })
	@ResponseBody
	public boolean punchOut(long userid) throws IOException {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 11 - for punching out attendance - punchOut() - /punchout");
		EmployeeModel employee = empService.findEmployee(userid);
		return service.punchout(employee);
	}
	
	@RequestMapping(value = "/getAttendanceMobile", produces = { "application/json" })
	@ResponseBody
	public AttendanceLogModel getAttendanceMobile(long userid) {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 12 - get Attendance for mobile using employeeID -getAttendanceMobile() - /getAttendanceMobile");
		if(service.getAttendanceMobile(userid)==null) {
			return null;
		}else {
			return service.getAttendanceMobile(userid);
		}
		
	}
    // for getting the attendance tuple 
	@RequestMapping(value = "/getAttendance", produces = { "application/json" })
	@ResponseBody
	public AttendanceLog getAttendance(@RequestBody EmployeeModel employee) {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 13 - getting the attendance tuple for web - getAttendance() - /getAttendance ");
		return service.getAttendance(employee);
	}
    //for uploading the profile image path
	@RequestMapping(value = "/sendImage", produces = { "application/json" })
	@ResponseBody
	public void UploadFile(String fileDownloadPath,String empId) throws IOException {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 14 - for uploading the profile image - UploadFile() - /sendImage ");
		service.setProfileImage(fileDownloadPath,Integer.valueOf(empId));
	}
	//for getting the profile image path
	@RequestMapping(value = "/getImage", produces = { "application/json" })
	@ResponseBody
	public EmployeeProfileModel getImagePath(String empId) {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 15 - for recieving profile image - getImagePath() - /getImage" );
		EmployeeProfileModel employeeProfile = new EmployeeProfileModel();
		employeeProfile = service.getImagePath(Integer.valueOf(empId));
		if(employeeProfile==null) {
			return null;
		}else {
			return employeeProfile;
		}
	}
   //for getting weekly Attendance
	@RequestMapping(value = "/getWeeklyAttendance", produces = { "application/json" })
	@ResponseBody
	public AttendanceLogModel[] getWeeklyAttendance(String empId) {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 16 - for getting weekly attendance  - getWeeklyAttendance() - /getWeeklyAttendance ");
	   return service.WeeklyAttendance(Integer.valueOf(empId));
	}
	@RequestMapping(value="/leave",produces= {"application/json"})
	@ResponseBody
	public void ApplyLeave(@RequestBody LeaveModel leave){
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 17 - for applying leaves - ApplyLeave() - /leave");
		 service.ApplyLeave(leave);
	}
	@RequestMapping(value="/getLeave",produces= {"application/json"})
	@ResponseBody
	public LeaveModel[] GetLeave(String empId) throws NumberFormatException, ParseException{
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 18 - for getting leaves from record - GetLeave() - /getLeave");
		 return service.getLeave(Integer.valueOf(empId));
	}
	@GetMapping(value="/getHello",produces= {"application/json"})
	@ResponseBody
	public String getHello() {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 19 - for getting hello from user - getHello() - /getHello");
		return "Hello user";
	}
	@RequestMapping(value="/checkLeaveDate",produces= {"application/json"})
	@ResponseBody
	public Boolean checkLeaveDate(@RequestBody checkDateModel checkDate ) throws ParseException {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 20 - checking LeaveDate -checkLeaveDate() - /checkLeaveDate");
        return service.checkLeaveDate(checkDate.getDate(),checkDate.getEmpId());	
	}
	//get Today Attendance
	@RequestMapping(value="/getTodayAttendance",produces = {"application/json"})
	@ResponseBody
	public TodayAttendanceModel[] getTodayAttendance(String department) {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 21 - getting today's departmenatal attendance - getTodayAttendance() - /getTodayAttendance ");
		return service.getTodayAttendance(department);
	}
	
	@RequestMapping(value="/getLeavesAdmin",produces = {"application/json"})
	@ResponseBody
	public LeaveModel[] getLeavesAdmin(String department) {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 22 - getting leaves for Admin - getLeavesAdmin() - /getLeavesAdmin ");
		return service.getLeavesAdmin(department);
	}
	
	@RequestMapping(value="/updateLeaveApproval",produces= {"application/json"})
	@ResponseBody
	public void UpdateLeaveApproval(String LeaveId,String ApprovalStatus,String leaveType,String empId) {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 23 - updating leave approvals - UpdateLeaveApproval() - /updateLeaveApproval");
		service.UpdateLeaveApproval(LeaveId,ApprovalStatus,leaveType,Integer.valueOf(empId));
	}
	//check leave date before punchin
	@RequestMapping(value="/checkLeaveDateBeforePunchIn",produces= {"application/json"})
	@ResponseBody
	public Boolean checkLeaveDateBeforePunchIn(String empId) {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 24 - checking leave date before PunchIn - checkLeaveDateBeforePunchIn() - /checkLeaveDateBeforePunchIn");
		return service.CheckingLeaveBeforePunchin(empId);
	}
	
	@RequestMapping(value="/cancelLeave",produces= {"application/json"})
	@ResponseBody
	public void cancelLeave(String leaveId) {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 25 - cancelling leave - cancelLeave() - /cancelLeave");
		 service.cancelLeave(leaveId);
	}
}

