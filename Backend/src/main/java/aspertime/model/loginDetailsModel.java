package aspertime.model;

public class loginDetailsModel {
 public String username;
 public String password;
 public String HashCode;
 
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

public String getHashCode() {
	return HashCode;
}
public void setHashCode(String hashCode) {
	HashCode = hashCode;
}
@Override
public String toString() {
	return "loginDetailsModel [username=" + username + ", password=" + password + "]";
}
 
}
