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
 * info: userAccount class allows the user to make his or her own account and interact with the parking interface
 *
 *        
 */

import java.util.ArrayList;

public class UserAccount extends Account{
	private Boolean blackList = false;
	private ArrayList<Car> vehicles = new ArrayList<Car>();
	private String email;
	private boolean hasCoupon = false;
	
	public UserAccount(String userName, String userPassword, String ID) {
		super(userName, userPassword, ID);
		setAccType(1); // for now we will let 1 be customer 2 be steward and 3 be admin
	}
	
	public void setHasCoupon(boolean x) {
		hasCoupon = x;
	}
	
	public boolean isHasCoupon() {
		return hasCoupon;
	}
	
	public void addVehicle(Car obj) {
		//adds the vehicle to the users list of Vehicles
		vehicles.add(obj);
	}
	
	public ArrayList<Car> getVehicles(){
		return vehicles;
		//gets all the vehicles for this client
	}
	
	public void getVehiclesToString(){
		if (vehicles.isEmpty()) {
			System.out.println("No cars");
		}else {
			int count = 1;
			for (Car c : vehicles) {
				System.out.println();
				System.out.println("Car " + count + " is a");
				System.out.println(c.toString());
				count += 1;
			}
		}
		
		//gets all the vehicles for this client
	}
	
	public void setLot(Car obj, ParkingSpace name) {
		obj.setLot(name);
		//links the car to the parking lot
	}
	
	public void removeCarFromLot(Car obj) {
		// needs to remove the car from the lot and call to get the payment info
		
	}
	
	public void removeCarFromuser(int obj) {
		//removes a car from the users list, but takes in the index of the car they wish to remove
		vehicles.remove(obj);
	}
	
	public void setBlackList(Boolean in) {
		//only the admin should be able to do this so will need to check who is trying to blacklist
		blackList = in;
	}
	
	public Boolean getBlackList() {
		return blackList;
	}
	
	public void setEmail(String e) {
		email = e;
	}
	
	public String getEmail() {
		return email;
	}
	
	
	
}
