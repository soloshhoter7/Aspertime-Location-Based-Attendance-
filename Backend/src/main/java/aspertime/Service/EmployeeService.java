package aspertime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aspertime.Dao.EmployeeDAO;
import aspertime.Entity.Employee;
import aspertime.Entity.loginDetails;
import aspertime.model.EmployeeModel;
import aspertime.model.loginDetailsModel;

@Service
public class EmployeeService {
	@Autowired
	EmployeeDAO dao;
	
	Employee employee=new Employee();
	
	loginDetails logindetails =  new loginDetails();
	
	//for saving employee personal details
    public boolean saveEmployee(EmployeeModel emp) {
    	employee.setName(emp.getName());
    	employee.setEmail(emp.getEmail());
    	employee.setGender(emp.getGender());
    	employee.setMobileNo(emp.getMobileNo());
    	employee.setDepartment(emp.getDepartment());
    	employee.setDesignation(emp.getDesignation());
    	if(dao.findEmail(employee)) {
    	dao.employeeSave(employee);
    	return true;
    	}
    	else {
    		return false;
    	}
    }
    
    //for saving employee personal details
    public void saveEmployeeLogin(loginDetailsModel loginModel) {
    logindetails.setEmployee(employee);
    logindetails.setUsername(loginModel.getUsername());
    logindetails.setPassword(loginModel.getPassword());
    logindetails.setHashCode(loginModel.getHashCode());
    System.out.println("login Details->"+logindetails);
    dao.saveLoginDetails(logindetails);
    }
    
	// for checking login credentials
	public boolean check(loginDetailsModel loginModel) {
		logindetails.setUsername(loginModel.getUsername());
		logindetails.setPassword(loginModel.getPassword());
		System.out.println("login details->"+logindetails);
		if (dao.loginCheck(logindetails)) {
			return true;
		} else {
			return false;
		}
	}
   //for getting employee after login
	public EmployeeModel getEmployee(loginDetailsModel loginModel) {
		Employee employee = new Employee();
		EmployeeModel InEmployee = new EmployeeModel();
		loginDetails logindetails = new loginDetails(); 
		logindetails.setPassword(loginModel.getPassword());
		logindetails.setUsername(loginModel.getUsername());
		employee=dao.findEmployee(logindetails);
		System.out.println("bugged employee"+employee);
		InEmployee.setCL(employee.getCL());
		InEmployee.setEL(employee.getEL());
		InEmployee.setML(employee.getML());
		InEmployee.setUserid(employee.getUserid());
		InEmployee.setName(employee.getName());
		InEmployee.setEmail(employee.getEmail());
		InEmployee.setDepartment(employee.getDepartment());
		InEmployee.setGender(employee.getGender());
		InEmployee.setMobileNo(employee.getMobileNo());
		InEmployee.setDesignation(employee.getDesignation());
		InEmployee.setMonthlyScore(employee.getMonthlyScore());
		System.out.println("bugged employeeModel"+InEmployee);
		return InEmployee;
	}

	public void updateEmployee(EmployeeModel emp) {
		employee.setName(emp.getName());
    	employee.setEmail(emp.getEmail());
    	employee.setGender(emp.getGender());
    	employee.setMobileNo(emp.getMobileNo());
    	employee.setDepartment(emp.getDepartment());
    	employee.setDesignation(emp.getDesignation());
    	dao.EmployeeUpdate(employee,emp.getUserid());
		
	}
  // Checking username in the database
	public Boolean checkUsername(String username) {
		return dao.checkUsername(username);
	}
    //for getting login details using employee details
	public loginDetailsModel getEmployeeLogin(EmployeeModel emp) {
		Employee employee = new Employee();
		employee.setName(emp.getName());
    	employee.setEmail(emp.getEmail());
    	employee.setGender(emp.getGender());
    	employee.setMobileNo(emp.getMobileNo());
    	employee.setDepartment(emp.getDepartment());
    	employee.setUserid(emp.getUserid());
		loginDetails login = dao.getEmployeeLogin(employee);
		loginDetailsModel logd = new loginDetailsModel();
		logd.setUsername(login.getUsername());
		logd.setPassword(login.getPassword());
		logd.setHashCode(login.getHashCode());
		return logd;
	}

	public Boolean chekName(String name,String department,String designation) {
	return dao.checkName(name,department,designation);
	}
   //Getting Employee with userid
	public Employee getEmployee(long userid) {
		return dao.getEmployeeLogin(userid);
	}
	//Getting Employee with userid In EmployeeModel
	public EmployeeModel findEmployee(long userid) {
		Employee employee =	dao.getEmployeeLogin(userid);
		EmployeeModel InEmployee = new EmployeeModel();
		InEmployee.setCL(employee.getCL());
		InEmployee.setEL(employee.getEL());
		InEmployee.setML(employee.getML());
		InEmployee.setUserid(employee.getUserid());
		InEmployee.setName(employee.getName());
		InEmployee.setEmail(employee.getEmail());
		InEmployee.setDepartment(employee.getDepartment());
		InEmployee.setGender(employee.getGender());
		InEmployee.setMobileNo(employee.getMobileNo());
		InEmployee.setDesignation(employee.getDesignation());
	    return InEmployee;
	}
	
}
