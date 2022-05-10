/**
 * C212 Group 19 Project
 * 
 * 
 * Created:   4/16/20
 * Released:  4/25/2020
 * 
 * @Author  Michael Burnett, micburne; Teresa Ray, teray
 * Last Edited:  4/25/20
 * 
 * Collaboration with Group 19 Project team members Michael Burnett, Joshua Meenan, Zeke Sarvis, and Teresa Ray.
 *
 * Directions:  Account class extends to the other account types
 *
 *        
 */
public class Account {
	private String username;
	private String password;
	private String userID;
	private String firstName;
	private String lastName;
	private int accountType;
	
	
	public Account(String username, String password, String userNum) {
		this.username = username;
		this.password = password;
		this.userID = userNum;
	}
	
	public void setAccType(int i) {
		this.accountType = i;
	}
	
	public int getAccountType() {
		return accountType;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getID() {
		return userID;
	}
	
	public void setName(String first, String last) {
		this.firstName = first;
		this.lastName = last;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String toString() {
		return firstName + " " + lastName + " " + userID;
	}
}
