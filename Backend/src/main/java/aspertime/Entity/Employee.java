package aspertime.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employee_Details")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userid;
	private String name;
	private String Email;
	private String Gender;
	private String MobileNo;
	private String Department;
	private Integer CL=15;
	private Integer EL = 10;
	private Integer ML =5;
	private String Designation;
    private Integer MonthlyScore =1;
    
	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getMobileNo() {
		return MobileNo;
	}

	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}
  
	public Integer getCL() {
		return CL;
	}

	public void setCL(Integer cL) {
		CL = cL;
	}

	public Integer getEL() {
		return EL;
	}

	public void setEL(Integer eL) {
		EL = eL;
	}

	public Integer getML() {
		return ML;
	}

	public void setML(Integer mL) {
		ML = mL;
	}
 
	
	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}
 
	

	public Integer getMonthlyScore() {
		return MonthlyScore;
	}

	public void setMonthlyScore(Integer monthlyScore) {
		MonthlyScore = monthlyScore;
	}

	@Override
	public String toString() {
		return "Employee [userid=" + userid + ", name=" + name + ", Email=" + Email + ", Gender=" + Gender
				+ ", MobileNo=" + MobileNo + ", Department=" + Department + ", CL=" + CL + ", EL=" + EL + ", ML=" + ML
				+ ", Designation=" + Designation + "]";
	}



	

}
