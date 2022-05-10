
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * C212 Group 19 Project
 * 
 * 
 * Created:   4/16/20
 * Released:  4/27/2020
 * 
 * @Author  Michael Burnett, micburne; Teresa Ray, teray
 * Last Edited:  4/27/20
 * 
 * Collaboration with Group 19 Project team members Michael Burnett, Joshua Meenan, Zeke Sarvis, and Teresa Ray.
 *
 * Directions:  Implements ParkingSpace class allowing customers locate parking space and park vehicles.
 *
 *        
 */

public class ParkingSpace

{
	
	/**
	 * Declare Fields
	 */
	
	private Scanner userInput;
	private String line;
	private String lotID;
	private String spaceID;
	private String spaceType;
	private String spaceLicense;
	private String startDateTime;
	private double spaceWidth;
	private double spaceLength;
	private String spaceStatus;
	private double spaceFeeHr;
	private double spaceFeeDay;
	private double spaceFeeMon;
	private double overtimeFeeHr;
	private double overtimeFeeDay;
	public ArrayList<ParkingSpace> spaces = new ArrayList<ParkingSpace>();
	public ParkingSpace p;
	public final double STANDARD_WIDTH = 9.00;
	public final double STANDARD_LENGTH = 16.00;
	public final double LARGE_WIDTH = 11.00;
	public final double LARGE_LENGTH = 20.00;
	public final double STANDARD_PARKING_FEE_HOURLY = 6.00;
	public final double STANDARD_PARKING_FEE_DAILY = 17.50;
	public  final double STANDARD_PARKING_FEE_MONTHLY = 137.50;
	public final double LARGE_PARKING_FEE_HOURLY = 9.00;
	public final double LARGE_PARKING_FEE_DAILY = 20.00;
	public final double LARGE_PARKING_FEE_MONTHLY = 150.00;
	public final double PARKING_OVERTIME_HOURLY = 2.00;
	public final double PARKING_OVERTIME_DAILY = 8.00;
	public StewardAccount steward = null;
	
	
//	/**
//	 * Constructor for ParkingLot class.
//	 * Lot parameter will be "null" when first assigned since the system will need to assign the actual lot number
//	 * from parkingLotIDNum.txt.
//	 * 
	

	
	public ParkingSpace(String lot, String space, String type, String spaceLicense2, String startDateTime2,  String spaceStatus2)
	
	{
		
		lotID = lot;
		spaceID = space;
		spaceType = type;
		spaceLicense = spaceLicense2;
		startDateTime = startDateTime2;
		spaceStatus = spaceStatus2;
		setSpaceType(type);
		setSpaceWidth(type);
		setSpaceLicense(spaceLicense2);
		
		
	}
	
	public ParkingSpace(String lot, String space, String type)
	
	{
		
		lotID = lot;
		spaceID = space;
		spaceType = type;
		spaceLicense = null;
		startDateTime = null;
		spaceStatus = null;
		setSpaceType(type);
		setSpaceWidth(type);
		setSpaceLicense("null");
		
	}
	
	
	
	/**
	 * Returns spaceID of parking lot comprised of "SP" and 
	 * number in line pulled from parkingSpaceIDNum.txt  (example, "SP00001");
	 * Calls ParkingSpace Constructor and builds based on user input.
	 * Saves ParkingSpace definitions to parkingSpacesSet.txt for use by ParkingLot class and ParkingSpace class.
	 * @return String
	 * @throws FileNotFoundException 
	 */
	
	public void userInput(String license, String date, String status) throws FileNotFoundException 
	
	{
		spaceLicense = license;
		
		
		
	}
	
	/**
	 * Returns lotID associated with parking space.
	 * @return String
	 */
	
	public String getLotID()
	
	{
		
		return lotID;
		
	}
	
	/**
	 * Returns parking space spaceID.
	 * @return String
	 */
	
	public String getSpaceID() 
	
	{
		
		return spaceID;
		
	}
	
	/**
	 * Returns type of parking space as either standard or large.
	 * @return String
	 */
	
	public String getSpaceType() 
	
	{
		
		return spaceType;
		
	}
	
	/**
	 * Sets type of parking space as either standard or large.
	 * @param type
	 */
	
	public void setSpaceType(String type) 
	
	{
		
		spaceType = type;
		
		if(type.contentEquals("standard")) 
			
		{
			
			setSpaceWidth("standard");
			setSpaceLength("standard");
			setSpaceFeeHr("standard");
			setSpaceFeeDay("standard");
			setSpaceFeeMon("standard");
			
		}
		
		else if(type.contentEquals("large")) 
		
		{	
			
			setSpaceWidth("large");
			setSpaceLength("large");
			setSpaceFeeHr("large");
			setSpaceFeeDay("large");
			setSpaceFeeMon("large");
			
		}
		
	}
	
	/**
	 * Returns license of vehicle occupying parking space.
	 * @return String
	 */
	
	public String getSpaceLicense() 
	
	{
		
		return spaceLicense;
		
	}
	
	/**
	 * Sets spaceLicense to the license of the vehicle occupying the parking space and sets status.
	 * @param license
	 */
	
	public void setSpaceLicense(String license) 
	
	{
		
		if(license.equals("null"))
		{
			
			spaceLicense = "null";
			setSpaceStatus("available");
			setStartDateTime();
			
		}
		
		else 
		
		{
			
			spaceLicense = license;
			setSpaceStatus("unavailable");
			setStartDateTime();
			
		}
		
	}
	
	/**
	 * Returns the start time for a vehicle with spaceLicense license number occupying ParkingSpace.
	 * @return Date
	 */
	
	public String getStartDateTime() 
	
	{
		
		return startDateTime;
		
	}
	
	/**
	 * Sets the start time for a vehicle with spaceLicense license number occupying ParkingSpace.
	 * @param start
	 */
	
	public void setStartDateTime() 
	
	{
		
		Date dateObj = new Date();
		startDateTime = dateObj.toString();
		
	}
	
	/**
	 * Returns width of parking space.
	 * @return double
	 */
	
	public double getSpaceWidth() 
	
	{
		
		return spaceWidth;
		
	}
	
	/**
	 * Set width of parking space based on type.
	 * Standard parking space has width of 9 feet.
	 * Large parking space has width of 11 feet..
	 * @param width
	 */
	
	public void setSpaceWidth(String type) 
	
	{
		
		if(type.equals("standard")) 
			
		{
			
			spaceWidth = STANDARD_WIDTH;
			
		}
		
		else
		
		{
			
			spaceWidth = LARGE_WIDTH;
			
		}
		
	}
	
	/**
	 * Returns length of parking space
	 * @return double
	 */
	
	public double getSpaceLength() 
	
	{
		
		return spaceLength;
		
	}
	
	/**
	 * Set length of parking space based on type.
	 * Standard parking space has length of 16 feet.
	 * Large parking space has length of 20 feet.
	 * Allows Admin to override length if standard or large parking space length changes.
	 * @param length
	 */
	
	public void setSpaceLength(String type) 
	
	{
		
		if(type.equals("standard")) 
			
		{
			
			spaceLength = STANDARD_LENGTH;
			
		}
		
		else
		
		{
			
			spaceLength = LARGE_LENGTH;
			
		}
		
	}
	
	/**
	 * Returns "unavailable" for new or unavailable/filled parking spaces and "available" for active/open parking spaces.
	 * @return String
	 */
	
	public String getSpaceStatus() 
	
	{
		
		return spaceStatus;
		
	}
	
	/**
	 * Sets parking space status.
	 * When a license is updated in ParkingSpace, parking space status is changed to "unavailable" and 
	 * a time/date stamp is updated for the ParkingSpace .
	 * When license is changed or set to null, status is changed to "available" and 
	 * setEndDateTime() is called to set time/date stamp is updated for the ParkingSpace.
	 * When license is changed or set to an actual license String, setStartDateTime() is called.
	 * These transactions are written to parkingSpaceHistory.txt.
	 * @param s
	 */
	
	public void setSpaceStatus(String s) 
	
	{
		
		spaceStatus = s;
		
	}
	
	/**
	 * Returns hourly space fee for ParkingSpace.
	 * @return double
	 */
	
	public double getSpaceFeeHr() 
	
	{
		
		return spaceFeeHr;
		
	}
	
	/**
	 * Sets hourly space fee for ParkingSpace.
	 * @return double
	 */
	
	public void setSpaceFeeHr(String type) 
	
	{
		
		if(type.equals("standard")) 
			
		{
			
			spaceFeeHr = STANDARD_PARKING_FEE_HOURLY;
			
		}
		
		else
		
		{
			
			spaceFeeHr = LARGE_PARKING_FEE_HOURLY;
			
		}
		
	}
	
	/**
	 * Returns daily space fee for ParkingSpace.
	 * @return double
	 */
	
	public double getSpaceFeeDay() 
	
	{
		
		return spaceFeeDay;
		
	}
	
	/**
	 * Sets daily space fee for ParkingSpace.
	 * @param feeDay
	 */
	
	public void setSpaceFeeDay(String type) 
	
	{
		
		if(type.equals("standard")) 
			
		{
			
			spaceFeeDay = STANDARD_PARKING_FEE_DAILY;
			
		}
		
		else
		
		{
			
			spaceFeeDay = LARGE_PARKING_FEE_DAILY;
			
		}
		
	}
	
	/**
	 * Returns monthly space fee for ParkingSpace.
	 * @return double
	 */
	
	public double getSpaceFeeMon() 
	
	{
		
		return spaceFeeMon;
		
	}
	
	/**
	 * Sets monthly space fee for ParkingSpace.
	 * @param feeMon
	 */
	
	public void setSpaceFeeMon(String type) 
	
	{
		
		if(type.equals("standard")) 
			
		{
			
			spaceFeeMon = STANDARD_PARKING_FEE_MONTHLY;
			
		}
		
		else 
		
		{
			
			spaceFeeMon = LARGE_PARKING_FEE_MONTHLY;
			
		}
		
	}
	
	/**
	 * Returns hourly add-on overtime fee for ParkingSpace. Daily overtime fees cannot exceed $8.00 per day.
	 * @return double
	 */
	
	public double getOvertimeFeeHr() 
	
	{
		
		return PARKING_OVERTIME_HOURLY;
		
	}
	
	/**
	 * Sets hourly overtime fee for ParkingSpace.
	 * @param overtimeHr
	 */
	
	public void setOvertimeFeeHr() 
	
	{
		
		overtimeFeeHr = PARKING_OVERTIME_HOURLY;
		
	}
	
	/**
	 * Returns daily add-on overtime fee for ParkingSpace. Daily overtime fees cannot exceed $8.00 per day.
	 * @return double
	 */
	
	public double getOvertimeFeeDay() 
	
	{
		
		return PARKING_OVERTIME_DAILY;
		
	}
	
	/**
	 * Sets daily overtime fee for ParkingSpace.
	 * @param overtimeHr
	 */
	
	public void setOvertimeFeeDay() 
	
	{
		
		overtimeFeeDay = PARKING_OVERTIME_DAILY;
		
	}
	
	/**
	 * 
	 */
	
	public String toString() 
	
	{
		
		
		return getLotID() + ", " + getSpaceID() + ", " + getSpaceType() + ", " + 
		getSpaceLicense() + ", " + getStartDateTime() + ", " + getSpaceWidth() + ", " + 
		getSpaceLength() + ", " + getSpaceStatus() + ", " + getSpaceFeeHr() + ", " + 
		getSpaceFeeDay() + ", " + getSpaceFeeMon() + ", " + getOvertimeFeeHr() + ", " + 
		getOvertimeFeeDay();
		
	}
	
	
		
	
	
		
		
	
	
	
	
	
	
}
