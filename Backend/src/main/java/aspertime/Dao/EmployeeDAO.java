package aspertime.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import aspertime.Entity.AttendanceLog;
import aspertime.Entity.Employee;
import aspertime.Entity.EmployeeNames;
import aspertime.Entity.loginDetails;
import aspertime.Service.TimeAndDate;

@Repository
//@CrossOrigin(origins = "http://localhost:4200")
@Transactional
public class EmployeeDAO {
	@Autowired
	private SessionFactory sessionFactory;

	// getting sessions
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
   // for checking an existing email
	public Boolean findEmail(Employee employee) {
		Query q = getSession().createQuery("from Employee where Email='" + employee.getEmail() + "'");
		Employee empd = (Employee) q.uniqueResult();
		if (empd != null) {
			return false;
		} else {
			return true;
		}
	}

	// for Saving employee personal details
	public String employeeSave(Employee employee) {
		Long isSuccess = (Long) getSession().save(employee);
		if (isSuccess >= 1) {
			return "Success";
		} else {
			return "Error while Saving Employee";
		}

	}

	// finding employee details when login
	public Employee findEmployee(loginDetails logindetails) {
		TimeAndDate td = new TimeAndDate();
		Query q = getSession().createQuery("from loginDetails where username='" + logindetails.getUsername() + "'");
		loginDetails logd = (loginDetails) q.uniqueResult();
		long userid = logd.getEmployee().getUserid();
		if(td.getRemainingDays()==0) {
			Query q2 = getSession().createQuery("update Employee set MonthlyScore=1 where userid="+userid);
			System.out.println(" database updated");
			q2.executeUpdate();
		}
		Query q1 = getSession().createQuery("from Employee where userid=" + userid);
		System.out.println(q1);
		Employee emp = (Employee) q1.uniqueResult();
		System.out.println("bugged Employee in dao"+emp);
		return emp;
	}
	// getting employee Login Details
    public loginDetails getEmployeeLogin(Employee employee) {
    	Query q = getSession().createQuery("from loginDetails where employeeId="+employee.getUserid());
    	return (loginDetails)q.uniqueResult();
    }
    
    // Checking username at time of login
    public Boolean checkUsername(String username) {
    	Query q = getSession().createQuery("from loginDetails where username='"+username+"'");
    	if(q.uniqueResult()!=null) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
	// for saving registration user login credentials
	public void saveLoginDetails(loginDetails logindetails) {

		getSession().save(logindetails);

	}

	// for saving punchin time
	public String punchin(AttendanceLog atl) {
		Long isSuccess = (Long) getSession().save(atl);
		if (isSuccess >= 1) {
			return "Success";
		} else {
			return "Error while Saving punchin";
		}
	}

	// for checking login credentials at login form
	public boolean loginCheck(loginDetails logindetails) {
		Query q = getSession().createQuery("from loginDetails where username='" + logindetails.getUsername() + "'");
		System.out.println(q);
		loginDetails logd = (loginDetails) q.uniqueResult();
		System.out.println(logd);
		System.out.println("hello !");
		if (logd != null && logindetails.getUsername().equals(logd.getUsername())
				&& logd.getPassword().equals(logindetails.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	// for deleting employee
	public boolean delete(Employee employee) {
		getSession().delete(employee);
		return true;
	}

	// to list all employees
	@SuppressWarnings("unchecked")
	public List getAllEmployee() {
		return getSession().createQuery("from Employee").list();
	}
   // For updating the employee personal details
	public void EmployeeUpdate(Employee employee, long userid) {
		String qryString = "update Employee  set" + "  Email='" + employee.getEmail() + "'," + " Gender='"
				+ employee.getGender() + "'," + " MobileNo='" + employee.getMobileNo() + "'," + " Department='"
				+ employee.getDepartment() + "', Designation='"+employee.getDesignation() + "', CL = "+employee.getCL()+" ,EL = "+employee.getEL()+ ", ML = "+employee.getML()+" where userid=" + userid;
		System.out.println(qryString);
		Query query = getSession().createQuery(qryString);
		query.executeUpdate();
		System.out.println("database updated");
	}
	public Boolean checkName(String name,String department,String designation) {
	 String qryString = "from EmployeeNames where Name='"+name+"' AND Department='"+department+"' AND Designation='"+designation+"'";
	 Query q = getSession().createQuery(qryString);
	 EmployeeNames emp = (EmployeeNames)q.uniqueResult();
	 if(emp==null) {
		 return false;
	 }
	 else {
		 return true;
	 }
	}
	public Employee getEmployeeLogin(long userid) {
		String qryString = "from Employee where userid="+userid;
		Query q= getSession().createQuery(qryString);
		return (Employee)q.uniqueResult();
	}

}