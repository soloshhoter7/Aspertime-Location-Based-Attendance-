package aspertime.model;

public class EmployeeModel {
	public long userid;
	public String name;
	public String Email;
	public String Gender;
	public String MobileNo;
	public String Department;
	public Integer CL;
	public Integer EL;
	public Integer ML;
	public String Designation;
    public Integer MonthlyScore;
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

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
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
		return "EmployeeModel [userid=" + userid + ", name=" + name + ", Email=" + Email + ", Gender=" + Gender
				+ ", MobileNo=" + MobileNo + ", Department=" + Department + ", CL=" + CL + ", EL=" + EL + ", ML=" + ML
				+ ", Designation=" + Designation + "]";
	}


}
