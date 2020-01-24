package aspertime.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name = "logindetails")
public class loginDetails {
    @Id
	private String username;
	private String password;
	private String HashCode;
	@OneToOne
	@JoinColumn(name="employeeId")
	private Employee employee;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public String getHashCode() {
		return HashCode;
	}
	public void setHashCode(String hashCode) {
		HashCode = hashCode;
	}
	@Override
	public String toString() {
		return "loginDetails [username=" + username + ", password=" + password + ", userid=" + employee + "]";
	}
	
	
	
}
