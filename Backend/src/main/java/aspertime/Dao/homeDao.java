package aspertime.Dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import aspertime.Entity.AttendanceLog;
import aspertime.Entity.Employee;
import aspertime.Entity.EmployeeLeaveApproval;
import aspertime.Entity.EmployeeProfileImage;
import aspertime.Service.TimeAndDate;
import aspertime.Service.WeekDays;
import aspertime.model.AttendanceLogModel;
import aspertime.model.EmployeeModel;
import aspertime.model.LeaveModel;
import aspertime.model.TodayAttendanceModel;

@Repository
@Transactional
public class homeDao {
	@Autowired
	private SessionFactory sessionFactory;
    @Autowired
	private EmployeeDAO empDao;
	// getting sessions
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public boolean saveAttendance(AttendanceLog atl) {
		System.out.println("dao auto wiring ");
		System.out.println(atl);
		try {
			getSession().save(atl);
			return true;
		} catch (Exception e) {
			System.out.println("exception occured !");
		}
		return false;
	}

	public Boolean checkAttendance(Employee employee) {
		TimeAndDate td1 = new TimeAndDate();
		Query q = getSession().createQuery("from AttendanceLog where employee_userid=" + employee.getUserid()
				+ " AND date='" + td1.getDate() + "'");
		System.out.println(q);
		AttendanceLog atl = (AttendanceLog) q.uniqueResult();
		if (atl != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean CheckPunchout(Employee employee) {
		TimeAndDate td = new TimeAndDate();
		Query q = getSession().createQuery("from AttendanceLog where employee_userid=" + employee.getUserid()
				+ " AND date='" + td.getDate() + "'");
		System.out.println(q);
		AttendanceLog atl = (AttendanceLog) q.uniqueResult();
		if (atl.getPunchOut() == null) {
			return true;
		} else {
			return false;
		}
	}

	public void UpdateAttendance(AttendanceLog atl, Employee employee) {
		TimeAndDate td1 = new TimeAndDate();
		Query q = getSession().createQuery("from AttendanceLog where employee_userid=" + employee.getUserid()
				+ " AND date='" + td1.getDate() + "'");
		System.out.println(q);
		AttendanceLog atl1 = (AttendanceLog) q.uniqueResult();
		if (atl1 != null) {
			Query q2 = getSession().createQuery("update AttendanceLog set PunchOut='" + atl.getPunchOut()
					+ "' where employee_userid=" + employee.getUserid() + " AND date='" + td1.getDate() + "'");
			int result = q2.executeUpdate();
			System.out.println("database Updated !");
			System.out.println("result:" + result);
		} else {
			System.out.println("No record found !");
		}
	}

	public void UpdateTotal(AttendanceLog atl, Employee employee) {
		TimeAndDate td1 = new TimeAndDate();
		Query q = getSession().createQuery("from AttendanceLog where employee_userid=" + employee.getUserid()
				+ " AND date='" + td1.getDate() + "'");
		System.out.println(q);
		AttendanceLog atl1 = (AttendanceLog) q.uniqueResult();
		if (atl1 != null) {
			Query q2 = getSession().createQuery(
					"update AttendanceLog set TotalTime=" + atl.getTotaltime() + ",Attendance='" + atl.getAttendance()
							+ "' where employee_userid=" + employee.getUserid() + " AND date='" + td1.getDate() + "'");
			int result = q2.executeUpdate();
			System.out.println("result:" + result);
			System.out.println("database updated ");
		} else {
			System.out.println("No record found !");
		}
	}

	public String[] timeDetails(Employee employee) {
		TimeAndDate td = new TimeAndDate();
		Query q = getSession().createQuery("select PunchIn,PunchOut from AttendanceLog  where employee_userid="
				+ employee.getUserid() + "and date='" + td.getDate() + "'");
		Object[] emp = (Object[]) q.uniqueResult();
		String[] timeDetails = new String[2];
		timeDetails[0] = (String) emp[0];
		timeDetails[1] = (String) emp[1];
		return timeDetails;

	}

	public AttendanceLog getAttendance(Employee employee) {
		TimeAndDate td1 = new TimeAndDate();
		Query q = getSession().createQuery("from AttendanceLog where employee_userid=" + employee.getUserid()
				+ " AND date='" + td1.getDate() + "'");
		System.out.println(q);
		AttendanceLog atl = (AttendanceLog) q.uniqueResult();
		return atl;
	}
    public AttendanceLog getAttendanceMobile(Employee employee) {
    	TimeAndDate td1 = new TimeAndDate();
		Query q = getSession().createQuery("from AttendanceLog where employee_userid=" + employee.getUserid()
				+ " AND date='" + td1.getDate() + "'");
		System.out.println(q);
		AttendanceLog atl = (AttendanceLog) q.uniqueResult();
		if(atl==null) {
			return null;
		}
		return atl;
    }
	public void setProfileImage(EmployeeProfileImage image) {
		getSession().save(image);
		System.out.println("image record saved !");
	}

	public EmployeeProfileImage getImagePath(Integer empId) {
		Query q = getSession().createQuery("from EmployeeProfileImage where EmployeeId=" + empId);
		EmployeeProfileImage image = (EmployeeProfileImage) q.uniqueResult();
		System.out.println(image);
		if (image == null) {
			return null;
		}
		return image;
	}
   //for getting weekly Attendance
	public AttendanceLogModel[] getWeeklyAtendance(Integer userid) {
		AttendanceLogModel[] atlModel = new AttendanceLogModel[7];
		WeekDays dates = new WeekDays();
		ArrayList<String> Weekdates = new ArrayList<String>();
		Weekdates = dates.ReturnWeekDays();

		for (int i = 0; i <= 6; i++) {
			AttendanceLogModel atl = new AttendanceLogModel();
			Query q = getSession().createQuery(
					"from AttendanceLog where employee_userid=" + userid + " and date='" + Weekdates.get(i) + "'");
			System.out.println(q);
			AttendanceLog attendance = (AttendanceLog) q.uniqueResult();

			if (attendance != null) {
				atl.setAttendance(attendance.getAttendance());
				atl.setDate(attendance.getDate());
				atl.setPunchIn(attendance.getPunchIn());
				atl.setPunchOut(attendance.getPunchOut());
				atl.setTotalTime(this.CalculateTotalTime(attendance.getTotaltime()));
			} else {
				atl.setAttendance("A");
				atl.setDate(Weekdates.get(i));
				atl.setPunchIn("NULL");
				atl.setPunchOut("NULL");
				atl.setTotalTime("0");
			}
			atlModel[i] = atl;
			System.out.println("atl=>" + atl);

		}

		return atlModel;
	}
    // converting minutes to hours
	public String CalculateTotalTime(int minutes) {
		Integer Hours = minutes / 60;
		Integer Minutes = minutes % 60;
		String TotalTime = Hours.toString() + ":" + Minutes.toString() + " Hrs";
		return TotalTime;
	}
	
    //for saving leave in database
	public void ApplyLeave(EmployeeLeaveApproval empleave) {
		getSession().save(empleave);
	}

	public EmployeeLeaveApproval[] getLeaves(int empId) throws ParseException {
		String query = "from EmployeeLeaveApproval where empId_userid="+empId+" AND CancellationStatus='N'";
		Query q = getSession().createQuery(query);
		 List<EmployeeLeaveApproval> leavesInList = new ArrayList<EmployeeLeaveApproval>();
		leavesInList=q.list();
		List<EmployeeLeaveApproval> DesiredLeaves = new ArrayList<EmployeeLeaveApproval>();

		for(int i=0;i<leavesInList.size();i++) {
			if(this.checkGetLeavesDate(leavesInList.get(i))) {
				DesiredLeaves.add(leavesInList.get(i));
			}
		}		
		EmployeeLeaveApproval[] leavesInArray = new EmployeeLeaveApproval[DesiredLeaves.size()];
		for(int i=0;i<DesiredLeaves.size();i++) {
		leavesInArray[i]=DesiredLeaves.get(i);	
		}
		return leavesInArray;
	}
	public Boolean checkGetLeavesDate(EmployeeLeaveApproval leave) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date LeaveDate = formatter.parse(leave.getDate());
		String currentDateInString = formatter.format(new Date());
		Date currentDate = formatter.parse(currentDateInString);
		
		if ((LeaveDate.after(currentDate)) || (LeaveDate.equals(currentDate))) {
			return true;
		}else {
			return false;	
		}
	}

	public Boolean checkLeaveDate(String strDate, String empId) {
	 String qryString = "from EmployeeLeaveApproval where Date='"+strDate+"' AND empId_userid="+empId+" AND ( ApprovalStatus='A' OR ApprovalStatus='P') AND CancellationStatus='N'";
	 Query q = getSession().createQuery(qryString);
	 List<EmployeeLeaveApproval> leave= q.list();
	 if(leave.size()!=0) {
		 return false;
	 }else{
		 return true;
	 }
	}
	public Boolean checkLeaveDateBeforePunchIn(String strDate, String empId) {
		 String qryString = "from EmployeeLeaveApproval where Date='"+strDate+"' AND empId_userid="+empId+" AND ApprovalStatus='A' AND CancellationStatus='N'";
		 Query q = getSession().createQuery(qryString);
		 List<EmployeeLeaveApproval> leave= q.list();
		 if(leave.size()!=0) {
			 return false;
		 }else{
			 return true;
		 }
		}
	public boolean checkCancelledLeave(String strDate,long empId) {
		 String qryString = "from EmployeeLeaveApproval where Date='"+strDate+"' AND empId_userid="+empId+" AND CancellationStatus='Y' AND ApprovalStatus='A'";
		 Query q = getSession().createQuery(qryString);
		 EmployeeLeaveApproval leave = (EmployeeLeaveApproval)q.uniqueResult();
		 if(leave!=null) {
			 if(leave.getApprovalStatus().equals("A")) {
				 String query2 = "update Employee set "+leave.getLeaveType()+"="+leave.getLeaveType()+"+1"+"where userid="+empId;
				 Query q2 = getSession().createQuery(query2);
				 q2.executeUpdate();
				 System.out.println("database updated ");
				 if(leave.getLeaveType().equals("CL")) {
					 String query3 = "update Employee set MonthlyScore=1 where userid="+empId;
					 Query q3 = getSession().createQuery(query3);
					 q3.executeUpdate();
					 System.out.println("database updated ");
				 }
			 }
			 return true;
		 }else {
			 return false;
		 }
	}

	public TodayAttendanceModel[] getTodayAttendanceModel(String department) {
		TimeAndDate td = new TimeAndDate();
		String date=td.getDate();
		String query = "from AttendanceLog where date='"+date+"' AND Department='"+department+"'";
		Query q = getSession().createQuery(query);
		List<AttendanceLog> todayAttendanceLog = new ArrayList<AttendanceLog>();
		todayAttendanceLog =q.list();
		TodayAttendanceModel[] attendanceLogModel = new TodayAttendanceModel[todayAttendanceLog.size()];
	    for(int i=0;i<todayAttendanceLog.size();i++) {
	    	TodayAttendanceModel atl = new TodayAttendanceModel();
	    	EmployeeModel empd = new EmployeeModel();
		    empd.setUserid(todayAttendanceLog.get(i).getEmployee().getUserid());
			empd.setDepartment(todayAttendanceLog.get(i).getEmployee().getDepartment());
			empd.setName(todayAttendanceLog.get(i).getEmployee().getName());
			empd.setGender(todayAttendanceLog.get(i).getEmployee().getGender());
			empd.setEmail(todayAttendanceLog.get(i).getEmployee().getEmail());
			empd.setMobileNo(todayAttendanceLog.get(i).getEmployee().getMobileNo());
			empd.setDesignation(todayAttendanceLog.get(i).getEmployee().getDesignation());
			empd.setCL(todayAttendanceLog.get(i).getEmployee().getCL());
			empd.setEL(todayAttendanceLog.get(i).getEmployee().getEL());
			empd.setML(todayAttendanceLog.get(i).getEmployee().getML());
	    	atl.setEmployee(empd);
	    	atl.setPunchIn(todayAttendanceLog.get(i).getPunchIn());
	    	atl.setPunchOut(todayAttendanceLog.get(i).getPunchOut());
	    	attendanceLogModel[i]=atl;
	    }
	    return attendanceLogModel;
	}
	public LeaveModel[] getLeavesAdmin(String department) {
		String query = "from EmployeeLeaveApproval where Department='"+department+"' AND ApprovalStatus='P' AND CancellationStatus='N'";
	    Query q = getSession().createQuery(query);
	    List<EmployeeLeaveApproval> LeavesInList = new ArrayList<EmployeeLeaveApproval>();
	    LeavesInList = q.list();
	    EmployeeLeaveApproval[] leavesInArray = new EmployeeLeaveApproval[LeavesInList.size()];
	    for(int i=0;i<LeavesInList.size();i++) {
            leavesInArray[i]= LeavesInList.get(i);
	    }
		LeaveModel[] leave =new LeaveModel[leavesInArray.length];
		for(int i=0;i<leavesInArray.length;i++) {
		    LeaveModel Leave = new LeaveModel();
		    Leave.setApprovalStatus(leavesInArray[i].getApprovalStatus());
		    Leave.setDateInString(leavesInArray[i].getDate());
		    Leave.setEmpId(leavesInArray[i].getEmpId().getUserid());
		    Leave.setLeaveId(leavesInArray[i].getLeaveId());
		    Leave.setLeaveReason(leavesInArray[i].getLeaveReason());
		    Leave.setLeaveSubject(leavesInArray[i].getLeaveSubject());
		    Leave.setLeaveType(leavesInArray[i].getLeaveType());
		    leave[i]=Leave;
		}
		return leave;
	}

	public void UpdateLeaveApproval(String leaveId, String approvalStatus,String leaveType,long empId) {
		System.out.println(approvalStatus);
		if(approvalStatus.equals("A")) {
			 String query1 = "update Employee set "+leaveType+"="+leaveType+"-1"+" , MonthlyScore = 0 where userid="+empId;
			 System.out.println("query1 ="+query1);
			 Query q1 = getSession().createQuery(query1);
			 q1.executeUpdate();
			 System.out.println("database updated ");
		} 
		else if(approvalStatus.equals("C")) {
			 String query2 = "update Employee set "+leaveType+"="+leaveType+"+1"+"where userid="+empId;
			 Query q2 = getSession().createQuery(query2);
			 q2.executeUpdate();
			 System.out.println("database updated ");
		}
	String query ="update EmployeeLeaveApproval set ApprovalStatus='"+approvalStatus+"' where LeaveId="+leaveId;
	 Query q = getSession().createQuery(query);
	 q.executeUpdate();
	 System.out.println("database updated ");
	
	}

	public void cancelLeave(String leaveId) {
       String query ="update EmployeeLeaveApproval set CancellationStatus='Y' where LeaveId="+leaveId;
       Query q = getSession().createQuery(query);
       q.executeUpdate();
       System.out.println("database updated ");
       System.out.println("Leave cancelled successfully :"+leaveId);
	}
}
