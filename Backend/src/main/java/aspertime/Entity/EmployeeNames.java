package aspertime.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmployeeNames {
@Id
private String Name;
private String Department;
private String Designation;


public String getDesignation() {
	return Designation;
}
public void setDesignation(String designation) {
	Designation = designation;
}
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public String getDepartment() {
	return Department;
}
public void setDepartment(String department) {
	Department = department;
}
@Override
public String toString() {
	return "EmployeeNames [Name=" + Name + ", Department=" + Department + ", Designation=" + Designation + "]";
}

}
