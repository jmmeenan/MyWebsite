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
 * info: admin account makes the gets and setters for just admin
 *
 *        
 */

import java.util.ArrayList;

public class AdminAccount extends Account{

	
	public AdminAccount(String name, String pass, String userID) {
		super(name, pass, userID);
		setAccType(3);
		// TODO Auto-generated constructor stub
	}
	
	
	// sets a user to be blacklisted
	public void blacklist(UserAccount user) {
		user.setBlackList(true);
	}
}
