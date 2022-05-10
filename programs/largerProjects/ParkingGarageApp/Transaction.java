import java.util.Date;

/**
 * C212 Group 19 Project
 * 
 * 
 * Created:   4/16/20
 * Released:  4/17/20
 * 
 * @Author  Teresa Sue Ray, teray
 * Last Edited:  4/16/20
 * 
 * Collaboration with Group 19 Project team members Joshua Meenan, Zeke Sarvis, and Michael Burnett.
 *
 * Directions:  Implements Transaction class enabling Users to reserve parking spaces in parking lots.
 *
 *        
 */

public class Transaction 

{
	
	/**
	 * Declares fields.
	 */
	
	String userID;
	String creditCard;
	String lotID;
	String spaceID;
	//Date startDateTime;
	//Date endDateTime;
	String parkingOption;
	int time;
	double cost;
	boolean coupon;
	
	/**
	 * Constructor for Transaction class.
	 * @param userID
	 * @param creditCard
	 * @param lotID
	 * @param spaceID
	 * @param startDateTime
	 * @param endDateTime
	 * @param parkingOption
	 * @param cost
	 */
	
	public Transaction(String userID, String creditCard, String lotID, String spaceID, int spaceType, double cost, int time) 
	
	{
		this.userID = userID;
		this.creditCard = creditCard;
		this.lotID = lotID;
		this.spaceID = spaceID;
		//this.startDateTime = startDateTime;
		//this.endDateTime = endDateTime;
		this.parkingOption = parkingOption;
		this.cost = cost;
		this.time = time;
	}
	
public Transaction(String userID, String creditCard, String lotID, String spaceID, int spaceType, double cost, int time, String affirmative) 
	
	{
		this.userID = userID;
		this.creditCard = creditCard;
		this.lotID = lotID;
		this.spaceID = spaceID;
		//this.startDateTime = startDateTime;
		//this.endDateTime = endDateTime;
		this.parkingOption = parkingOption;
		this.cost = cost;
		this.time = time;
		this.coupon = true;
		
		
		
	}
	
	
	// gets userID
	public String getUserID() {
		return userID;
	}


	// sets user id
	public void setUserID(String userID) {
		this.userID = userID;
	}


	//gets the credit card
	public String getCreditCard() {
		return creditCard;
	}


	// sets the credit card
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}


	// get the lot ID
	public String getLotID() {
		return lotID;
	}


	// sets the lotID
	public void setLotID(String lotID) {
		this.lotID = lotID;
	}


	// gets the space ID
	public String getSpaceID() {
		return spaceID;
	}


	// sets the space id
	public void setSpaceID(String spaceID) {
		this.spaceID = spaceID;
	}


	// gets the option
	public String getParkingOption() {
		return parkingOption;
	}


// sets the parking option
	public void setParkingOption(String parkingOption) {
		this.parkingOption = parkingOption;
	}


	// gets the time
	public int getTime() {
		return time;
	}


	// sets the time
	public void setTime(int time) {
		this.time = time;
	}


	// gets the cost
	public double getCost() {
		return cost;
	}


	// sets the cost
	public void setCost(double cost) {
		this.cost = cost;
	}


	// return the total cost of the transaction
	public String total()
	{
		double fnlTot = 0;
		if(this.coupon)
		{
			fnlTot = (this.cost * this.time) * (.9);
		}
		else
		{
			fnlTot = (this.cost * this.time);
		}
		return "Your total is " + fnlTot;
	}
	
}