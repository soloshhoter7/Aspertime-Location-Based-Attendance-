package aspertime.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import aspertime.Service.EmployeeService;
import aspertime.model.EmployeeModel;
import aspertime.model.loginDetailsModel;

@Controller
@RequestMapping(value = "/employee")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "http://virtualserver123.ddns.net")
public class EmployeeController {
	
	@Autowired
	private EmployeeService serve;

	// for saving personal details
	@RequestMapping(value = "/save")
	@ResponseBody
	public boolean create(@RequestBody EmployeeModel employee) throws IOException {
	System.out.println("-------------------------------------------------------------------------------");
	System.out.println("api: 1 - create an Employee - create() - /save");
		return serve.saveEmployee(employee);	
	}

	// for login Details
	@RequestMapping(value = "/loginDetails")
	@ResponseBody
	public void loginDetailsSave(@RequestBody loginDetailsModel logindetails) {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 2 - save login Registration details - loginDetailsSave() - /loginDetails");
		serve.saveEmployeeLogin(logindetails);
	}
	
	// for checking login details
	@RequestMapping(value = "/login",produces={"application/json"})
	@ResponseBody
	public boolean login(String username, String password, HttpServletResponse res, HttpServletRequest req) throws IOException {
		System.out.println("-------------------------------------------------------------------------------");
	    System.out.println("api: 3 - Checking Login details - login() - /login");
		loginDetailsModel loginModel = new loginDetailsModel();
		loginModel.setPassword(password);
		loginModel.setUsername(username);
		System.out.println("login Model ->"+loginModel); 
		return serve.check(loginModel);
	}
	//getting an employee with login details after login
	@RequestMapping(value="/getEmployee",produces= {"application/json"})
	@ResponseBody
	public EmployeeModel getEmployee(String username, String password){
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 4 - getting an employee with login details after login - getEmployee() - /getEmployee");
		loginDetailsModel loginModel = new loginDetailsModel();
		loginModel.setUsername(username);
		loginModel.setPassword(password);
		EmployeeModel returnEmployee = serve.getEmployee(loginModel);
		System.out.println("returnEmployee ->"+returnEmployee);
		System.out.println("--------------------" + returnEmployee);
		return returnEmployee;
	}
	// for updating the employee Details
//	@RequestMapping(value = "/updateEmployee",produces= {"application/json"})
//	@ResponseBody
//	public void UpdateEmployeeDetails(@RequestBody EmployeeModel employee) throws IOException {
//		serve.updateEmployee(employee);
//	}
	@RequestMapping(value = "/update")
	@ResponseBody
	public void UpdateEmployee(@RequestBody EmployeeModel employee) throws IOException {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 5 - updating an employee - UpdateEmployee() - /update");
		serve.updateEmployee(employee);
	}
	//getting hashcode of user
	@RequestMapping(value ="/getLogin")
	@ResponseBody
	public loginDetailsModel getEmployeeLogin(@RequestBody EmployeeModel employee) {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 6 - getting logindetails with hashcode - getEmployeeLogin() - /getLogin");
		return serve.getEmployeeLogin(employee);
	}
    //Checking username
	@RequestMapping(value="/checkUsername")
	@ResponseBody
	public Boolean checkUsername(String username) {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 7 - checking username - checkUsername() - /checkUsername");
		return serve.checkUsername(username);
	}
	
	@RequestMapping(value="/checkEmployeeName")
	@ResponseBody
	public Boolean checkName(String name ,String department,String designation) {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 8 - Checking Employee Name from the Departmental database - checkName() - /checkEmployeeName");
		return serve.chekName(name,department,designation);
	}
	
	@RequestMapping(value="/findEmployee")
	@ResponseBody
	public EmployeeModel findEmployee(String empId) {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("api: 9 - find Employee with employeeId - findEmployee() - /findEmployee");
		return serve.findEmployee(Integer.valueOf(empId));
	}
}
