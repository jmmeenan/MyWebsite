
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

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
 * Directions:  Implements parkingLot class allowing customers locate parking lot and park vehicles.
 *
 *        
 */

public class ParkingLot {
	
	/**
	 * Declare Fields
	 */
	
	private Scanner inputFile;
	private String line;
	private String lotID;
	private String lotName;
	private String lotAddress;
	private String lotCity;
	private String lotState;
	private String lotZip;
	public StewardAccount steward = null;
	//private static Map<ParkingLot, HashSet<ParkingSpace>> parking  = new HashMap<>();
	public ArrayList<ParkingSpace> parking = new ArrayList<ParkingSpace>();
	
	/**
	 * Constructor for ParkingLot class.
	 * Lot parameter will be "null" when first assigned since the system will need to assign the actual lot number
	 * from parkingLotIDNum.txt.
	 * 
	 * @param lot
	 * @param name
	 * @param address
	 * @param city
	 * @param state
	 * @param zip
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @throws FileNotFoundException 
	 */
	// seaches for the index of a space
	public int getSpaceIndex(String id) {
		int count = 0;
		for (ParkingSpace p : parking) {
			if (p.getSpaceID().equals(id)) {
				return count;
			}
			count += 1;
		}
		return -1;
	}
	// gets a standard space
	public int getStandardSpace() {
		int count = 0;
		for (ParkingSpace s : parking) {
			if (s.getSpaceType().equals("standard") && s.getSpaceStatus().equals("available")) {
				return count;
			}
			count += 1;
		}
		return 1029939929;
	}
	// gets a large space
	public int getLargeSpace() {
		int count = 0;
		for (ParkingSpace s : parking) {
			if (s.getSpaceType().equals("large") && s.getSpaceStatus().equals("available")) {
				return count;
			}
			count += 1;
		}
		return 1029939929;
	}
	
	public ParkingLot(String lot, String name, String address, String city, String state, String zip) 
	
	{
		
		lotID = lot;
		lotName = name;
		lotAddress = address;
		lotCity = city;
		lotState = state;
		lotZip = zip;
		
	}
	
	/**
	 * Returns LotID of parking lot comprised of state name abbreviation and 
	 * number in line pulled from parkingLotIDNum.txt  (example, "IN00001");
	 * Calls ParkingLot Constructor and builds based on user input.
	 * Saves Map information to parkingMap.txt for use by ParkingLot class and ParkingSpace class.
	 * @return String
	 * @throws IOException 
	 */
	
	public void setSteward(StewardAccount st) {
		this.steward = st;
	}
	public StewardAccount getSteward() {
		return this.steward;
	}
	public void addSpace(ParkingSpace s) {
		parking.add(s);
	}
	
	public String getLotID() 
	{
		
		return lotID;
		
	}
	
	/**
	 * Returns name of parking lot (example, "Gas City Parking Lot")
	 * @return String
	 */
	
	public String getLotName() 
	
	{
		
		return lotName;
		
	}
	
	/**
	 * Sets name of parking lot (example, "Swift Street Parking Lot.")
	 * @param name
	 */
	
	public void setLotName(String name)
	
	{
		
		lotName = name;
		
	}
	
	/**
	 * Returns street address of parking lot.
	 * @return String
	 */
	public String getLotAddress() 
	
	{
		
		return lotAddress;
		
	}
	
	/**
	 * Sets street address of parking lot (example, "123 Main Street").
	 * @param address
	 */
	
	public void setLotAddress(String address) 
	
	{
		
		lotAddress = address;
		
	}	
	
	/**
	 * Returns city where parkingLot is located (example, "Indianapolis")
	 * @return String
	 */
	
	public String getLotCity() 
	
	{
		
		return lotCity;
		
	}
	
	/**
	 * Sets city where parking lot is located (example, "Bloomington").
	 * @param city
	 */
	
	public void setLotCity(String city) 
	
	{
		
		lotCity = city;
		
	}
	
	/**
	 * Returns abbreviation for state in which parking lot is located (example, "IN" for Indiana).
	 * @return String
	 */
	
	public String getLotState() 
	
	{
		
		return lotState;
		
	}
	
	/**
	 * Sets abbreviation for state in which parking lot is located (example, "TN" for Tennessee).
	 * @param state
	 */
	
	public void setLotState(String state) 
	
	{
		
		lotState = state;
		
	}
	
	/**
	 * Returns zip code for location of parking lot (example, "46151", "03902").
	 * @return String
	 */
	
	public String getLotZip() 
	
	{
		
		return lotZip;
		
	}
	
	/**
	 * Sets zip code for location of parking lot (example, "46151", "03902").
	 * @param zip
	 */
	
	public void setLotZip(String zip) 
	
	{
		
		lotZip = zip;
		
	}
	

	
	public String toString() 
	
	{
		
		return "Lot ID is " + getLotID() + ", name is " + getLotName() + ", located at " + getLotAddress() + ", in city " + getLotCity() + ", of state " + getLotState() + 
				", zip code is " +  getLotZip() + "";
		
	}
	
}

