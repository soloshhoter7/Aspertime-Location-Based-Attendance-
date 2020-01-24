package aspertime.model;

import java.util.Date;

public class checkDateModel {
 public Date date;
 public String empId;
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getEmpId() {
	return empId;
}
public void setEmpId(String empId) {
	this.empId = empId;
}
@Override
public String toString() {
	return "checkDateModel [date=" + date + ", empId=" + empId + "]";
}
 
}
