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
 * info: Steward account is the class to assign people to lots
 *
 *        
 */
import java.util.Random;
import java.util.Scanner;


	
public class StewardAccount extends Account{
	private String stewardLot;
	private int stewardLotPrice;
	private int stewardLotSize;
	
	public StewardAccount(String name, String pass, String userID) {
		super(name, pass, userID);
		setAccType(2);
	}
	// sets the lot for the steward
	public void setLot(String lot) {
		stewardLot = lot;
	}
	// gives a coupon to a user
	public void giveCoupon(UserAccount u) {
		u.setHasCoupon(true);
	}
	// returns the lot id
	public String getLot() {
		return stewardLot;
	}
	
	public void setLotPrice(int price) {
	//Allow the steward to set the price of his or her parking lot 
	stewardLotPrice = price;
}
	// sets number of spaces
	public void setSpace(int numOfSpaces) {
	//Allow steward to set the size of his or her parking lot
		stewardLotSize = numOfSpaces;
	}
	// can add a charge
	public int addCharge(int valueCharge) {
		//penalty charge for user
		return valueCharge;
		
		
	}
	// to string method to list stewards
	public String toString() {
		return "First name is " + this.getFirstName() + " Last name is " + this.getLastName() + " He currently is at lot " + this.stewardLot;
	}
	
	
}
