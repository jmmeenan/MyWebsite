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
 * info: Car is what is stored in a parking space and is also linked to the user that makes them
 *
 *        
 */
public class Car {
	private String userID;
	private String carModel;
	private String carColor;
	private ParkingSpace space = null;
	private String carType; //Suv hatch back etc.
	private String licenseNum;
	private String make;
	
	public Car(String userID, String type ,String make, String model, String color, String license) {
		this.userID = userID;
		this.carType = type;
		this.carModel = model;
		this.carColor = color;
		this.licenseNum = license;
		this.make = make;
	}
	// returns the type SUV, sedan
	public String getType() {
		return this.carType;
	}
	//gets the user assigned to the car
	public String getUser() {
		return userID;
	}
	// gets the license on the car
	public String getLicense() {
		return licenseNum;
	}
	// sets the lot name
	public void setLot(ParkingSpace lotName) {
		this.space = lotName;
	}
	// returns the space
	public ParkingSpace getSpace() {
		return space;
	}
	// returns the car type 
	public String getCarType() {
		return carType;
	}
	// returns infomation about the car
	public String toString() {
		String output = ""+carType + ", make is " + make+ ", model is " + carModel + ", color is " + carColor + ", license number is " + licenseNum;
		return output;
	}
	// checks if a car is the same car
	public boolean isEqual(Car o) {
		boolean b = false;
		if(this.licenseNum == o.licenseNum)
		{
			b = true;
		}
		else
		{
			b = false;
		}
		return b;
	}
}
