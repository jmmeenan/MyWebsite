/**
 * C212 Group 19 Project
 * 
 * 
 * Created:   4/16/2020
 * Released:  4/25/2020
 * 
 * @Author  Joshua Meenan
 * Last Edited:  4/10/2022
 * 
 * Collaboration with Group 19 Project team members Michael Burnett, Joshua Meenan, Zeke Sarvis, and Teresa Ray.
 *
 * info: App is our main driver this calls everything the user uses to make the database
 *
 *        
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class App {
	public static Scanner sc = new Scanner(System.in);
	String adminUsername = "BillGates";
	String adminPassword = "Altair";
	public static AdminAccount admin = new AdminAccount("BillGates", "Altair", "49262");
	public static ArrayList<UserAccount> users = new ArrayList<UserAccount>();
	public static ArrayList<ParkingLot> allLots = new ArrayList<ParkingLot>();
	public static ArrayList<StewardAccount> stewards = new ArrayList<StewardAccount>();
	static UserAccount thisUser = null;
	static StewardAccount thisSteward = null;
	
	
	
	public static void read() { // this read all the files we have and adds them to the list
		try {
			users = new ArrayList<UserAccount>();
			allLots = new ArrayList<ParkingLot>();
			stewards = new ArrayList<StewardAccount>();
			Scanner read = new Scanner(new File("programs/largerProjects/ParkingGarageApp/"));
			while (read.hasNextLine()) {
				String[] line = read.nextLine().split("\\s+");
				String userName = line[0];
				String password = line[1];
				String id = line[2];
				String first = line[3];
				String last = line[4];
				boolean coupon = Boolean.parseBoolean(line[5]);
				UserAccount nextUser = new UserAccount(userName, password, id);
				nextUser.setName(first, last);
				nextUser.setHasCoupon(coupon);
				users.add(nextUser);
					
			}
			read = new Scanner(new File("programs/largerProjects/ParkingGarageApp/Stewards.txt"));
			while (read.hasNextLine()) {
				String[] line = read.nextLine().split("\\s+");
				String userName = line[0];
				String password = line[1];
				String ID = line[2];
				String first = line[3];
				String last = line[4];
				String lotID = line[5];
				StewardAccount next = new StewardAccount(userName, password, ID);
				next.setLot(lotID);
				next.setName(first, last);
				stewards.add(next);
					
			}
			
			read = new Scanner(new File("/Users/joshuameenan/fun/MyWebsite-1/programs/largerProjects/ParkingGarageApp/ParkingLots"));
			while (read.hasNextLine()) {
				String[] line = read.nextLine().split("\\s+");
				String lotID = line[0];
				String lotName = line[1];
				String lotAddress = line[2];
				String lotCity = line[3];
				String lotState = line[4];
				String lotZip = line[5];
				ParkingLot newLot = new ParkingLot(lotID, lotName, lotAddress, lotCity, lotState, lotZip);
				for (StewardAccount s : stewards) {
					if(s.getLot().equals(lotID)) {
						newLot.setSteward(s);
						newLot.steward = s;
					}
				}
				allLots.add(newLot);
				
				
					
			}
			read = new Scanner(new File("/Users/joshuameenan/fun/MyWebsite-1/programs/largerProjects/ParkingGarageApp/Cars.txt"));
			while (read.hasNextLine()) {
				String[] line = read.nextLine().split("\\s+");
				String userID = line[0];
				String type = line[1];
				String make = line[2];
				String model = line[3];
				String color = line[4];
				String license = line[5];
				Car newCar = new Car(userID, type, make, model, color, license);
				for (UserAccount u : users) {
					if (u.getID().equals(userID)) {
						u.addVehicle(newCar);
						
					}
				}
				
					
			}
			
			read = new Scanner(new File("programs/largerProjects/ParkingGarageApp/Spaces.txt"));
			while (read.hasNextLine()) {
				String[] line = read.nextLine().split("\\s+");
				String lot = line[0];
				String space = line[1];
				String type = line[2];
				String licence = line[3];
				String date = line[4];
				String status = line[5];
				
				for (ParkingLot parkLot : allLots) {
					if (parkLot.getLotID().equals(lot)) {
						ParkingSpace nextSpace = new ParkingSpace(lot, space, type, licence, date, status);
						nextSpace.setSpaceType(type);
						parkLot.addSpace(nextSpace);
					}
				}
					
			}
			
			read.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	// if the account type is a user this will activate asking for user login information
	public static void UserLogin() throws IOException {
		System.out.println("Please enter your UserName");
		String user = sc.next();
		System.out.println("Please enter your Password");
		String password = sc.next();
		
		boolean login = true;
		while (login) {
			for (UserAccount u : users) {
				if (u.getPassword().equals(password) && u.getUsername().equals(user)) {
					login = false;
					thisUser = new UserAccount(u.getUsername(), u.getPassword(), u.getID());
					thisUser.setName(u.getFirstName(), u.getLastName());
					for (Car c : u.getVehicles()) {
						thisUser.addVehicle(c);
					}
				}
			}if (login) {
				System.out.println();
				System.out.println("Sorry could not find your account please try again");
				System.out.println("Please enter your UserName");
				user = sc.next();
				System.out.println("Please enter your Password");
				password = sc.next();
			}
		}
		userOptions();
		
		
	}
	
	//when the user logins successful this will display the options available to them
	public static void userOptions() throws IOException {
		read();
		System.out.println();
		System.out.println("Welcome " + thisUser.getFirstName());
		System.out.println();
		System.out.println("What would you like to do? Please enter number of selection.");
		System.out.println("1. See all Parking lots");
		System.out.println("2. Add a new Car");
		System.out.println("3. Remove a Car");
		System.out.println("4. See your cars");
		System.out.println("5. Add a car to a parking Space, must first add a car");
		System.out.println("6. Get your car");
		System.out.println("7. See your History");
		System.out.println("8. logout");
		System.out.println();
		switch (sc.next()) {
		case "1":
			showLots();
			break;
		case "2":
			addCar();
			break;
		case "3":
			removeCar();
			break;
		case "4":
			thisUser.getVehiclesToString();
			break;
		case "5":
			if(thisUser.getVehicles().isEmpty()) {
				System.out.println("you have no cars to add");
				userOptions();
			}else {
				reserveSpot();
			}
			break;
		case "6":
			if(thisUser.getVehicles().isEmpty()) {
				System.out.println("you have no cars to get");
				userOptions();
			}else {
				getCar();
			}
			break;
		case "7":
			getHistory();
			userOptions();
			break;
		case "8":
			System.out.println("Goodbye");
			System.out.println();
			start();
			break;
		}
		System.out.println();
		userOptions();
	}
	
	// allows the user to add a car to their account and stores the info in /Users/joshuameenan/fun/MyWebsite-1/programs/largerProjects/ParkingGarageApp/Cars.txt
	public static void addCar() throws IOException {
		String userID = thisUser.getID();
		System.out.println();
		System.out.println("Please input the type, SUV, Sedan or other");
		String type = sc.next();
		System.out.println();
		System.out.println("Please input the make");
		String make = sc.next();
		System.out.println();
		System.out.println("Please input the model");
		String model = sc.next();
		System.out.println();
		System.out.println("Please input the color");
		String color = sc.next();
		System.out.println();
		System.out.println("Please input the license");
		String license = sc.next();
		Car newCar = new Car(userID, type, make, model, color, license);
		thisUser.addVehicle(newCar);
		
		FileWriter fileWriter = new FileWriter("/Users/joshuameenan/fun/MyWebsite-1/programs/largerProjects/ParkingGarageApp/Cars.txt", true);
		PrintWriter writer = new PrintWriter(fileWriter);
		writer.println(userID + " " + type + " " + make + " " + model + " " + color + " " + license);
		writer.close();
	}
	
	//Removes a car if the user decides to do so and also removes it from /Users/joshuameenan/fun/MyWebsite-1/programs/largerProjects/ParkingGarageApp/Cars.txt
	public static void removeCar() throws IOException{
		System.out.println("Which Car would you like to remove");
		thisUser.getVehiclesToString();
		int carToRemove = sc.nextInt() - 1;
		Path path = Paths.get("/Users/joshuameenan/fun/MyWebsite-1/programs/largerProjects/ParkingGarageApp/Cars.txt");
		List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		int lineNumber = 1;
		Scanner read = new Scanner(new File("/Users/joshuameenan/fun/MyWebsite-1/programs/largerProjects/ParkingGarageApp/Cars.txt"));
		while (read.hasNextLine()) {
			String[] line = read.nextLine().split("\\s+");
			String license = line[5];
			if (license.equals(thisUser.getVehicles().get(carToRemove).getLicense())){
				break;
			}
			lineNumber += 1;
		}
		lines.remove(lineNumber - 1);
		Files.write(path,  lines, StandardCharsets.UTF_8);
		thisUser.removeCarFromuser(carToRemove);
		read.close();
	}
	
	//when a steward try to login this will vertify that the password and user is of the steward
	public static void stewardLogin() throws IOException{
		System.out.println("Please enter your UserName");
		String user = sc.next();
		System.out.println("Please enter your Password");
		String password = sc.next();
		
		boolean login = true;
		while (login) {
			for (StewardAccount u : stewards) {
				if (u.getPassword().equals(password) && u.getUsername().equals(user)) {
					login = false;
					thisSteward = u;
				}
			}if (login) {
				System.out.println();
				System.out.println("Sorry. You're account can't be found. Please try again");
				System.out.println("Please enter your UserName");
				user = sc.next();
				System.out.println("Please enter your Password");
				password = sc.next();
			}
		}
		StewardOptions();
		
	}
	
	//shows the options of the steward
	public static void StewardOptions() throws IOException{
		read();
		System.out.println("Welcome "+ thisSteward.getFirstName());
		System.out.println();
		System.out.println("What would you like to do?");
		System.out.println("1. See your lot");
		System.out.println("2. See all Spaces");
		System.out.println("3. Give a coupon");
		System.out.println("4. logOut");
		switch (sc.next()) {
		case "1":
			System.out.println(showLot());
			break;
		case "2":
			read();
			showSpaces();
			break;
		case "3":
			giveCoupon();
			StewardOptions();
			break;
		case "4":
			start();
			break;
		
		}
		StewardOptions();
	}
	
	//stewards can randomly give a coupon if they choose to do so
	public static void giveCoupon() throws IOException {
		System.out.println("How many coupons would you like to hand out?");
		int num = sc.nextInt();
		int count = 0;
	
		for(UserAccount u : users) {
			if (count == num) {
				break;
			}
			if (!u.isHasCoupon()) {
				u.setHasCoupon(true);
				count += 1;
			}
		}
		PrintWriter delete = new PrintWriter("programs/largerProjects/ParkingGarageApp/Username.txt");
		delete.print("");
		delete.close();
		FileWriter fileWriter = new FileWriter("programs/largerProjects/ParkingGarageApp/Username.txt", true);
		PrintWriter writer = new PrintWriter(fileWriter);
		for (UserAccount u : users) {
			writer.println(u.getUsername() + " " + u.getPassword() + " " + u.getID() + " " + u.getFirstName() + " " + u.getLastName() + " " + u.isHasCoupon());
			
		}
		writer.close();
		read();
		
	}
	
	// shows space inforamtion
	public static void showSpaces() throws IOException{
		int lot = lotIndex(thisSteward.getLot());
		int count = 1;
		for(ParkingSpace s : allLots.get(lot).parking) {
			System.out.println("Space " + count + " is ");
			System.out.println(s.toString());
			System.out.println();
			count += 1;
		}
	}
	
	// show the lot
	public static String showLot() {
		if (thisSteward.getLot() == null) {
			return "No lot";
		}else {
			for (ParkingLot p : allLots) {
				if(p.getLotID().equals(thisSteward.getLot())) {
					return p.toString();
				}
			}
			return "error";
		}
	}
	
	// admin login system to tell
	public static void AdminLogin() throws IOException {
		System.out.println("Welcome Admin");
		System.out.println();
		System.out.println("Please enter your UserName");
		System.out.println();
		String user = sc.next();
		System.out.println("Please enter your Password");
		System.out.println();
		String password = sc.next();
		if (user.equals(admin.getUsername()) && (password.equals(admin.getPassword()))){
			System.out.println("Welcome: "+ admin.getFirstName());
			System.out.println();
			adminPanel();
			// take to the admin panel
		}else {
			System.out.println("I am sorry that username or password is incorrect");
			System.out.println();
			System.out.println("Would you like to try again? Yes/No if No will take you back to front page");
			System.out.println();
			String response = sc.next();
			while (!(response.equals("Yes")) || !(response.equals("No"))) {
				if (response.equals("Yes")) {
					AdminLogin();
				}else if (response.equals("No")) {
					start();
				}
			}
		}
		
		
		
	}
	
	// admin options they can choose
	public static void adminPanel() throws IOException {
		read();
		System.out.println();
		System.out.println("Welcome " + admin.getFirstName());
		System.out.println();
		System.out.println("What would you like to do?");
		System.out.println("1. See all Parking lots");
		System.out.println("2. Make a new Parking lot");
		System.out.println("3. Add spaces to a lot");
		System.out.println("4. Remove spaces from a lot");
		System.out.println("5. Add a Steward");
		System.out.println("6. Assign a Steward to Parking Lot");
		System.out.println("7. Remove a steward from a Parking Lot");
		System.out.println("8. Remove a lot");
		System.out.println("9. logout");
		System.out.println();
		String input = sc.next();
		switch (input) {
		case "1":
			showLots();
			break;
		case "2":
			Random rm = new Random();
			int newID = rm.nextInt(1234135);
			int count = 1;
			while(count < allLots.size()) {
				for (ParkingLot u : allLots) {
					if (Integer.toString(newID) == u.getLotID()) {
						newID = rm.nextInt(1234135);
						count = 1;
					}
					count +=1;
				}
				
			}
			allLots.add(addLot(Integer.toString(newID)));
			break;
		case "3":
			addSpaces();
			break;
		case "4":
			removeSpaces();
			break;
		case "5":
			addSteward();
			break;
		
		case "6":
			System.out.println("Which Steward?");
			showStewards();
			int st = (sc.nextInt() - 1);
			
			assignSteward(st);
			break;
		case "7":
			removeSteward();
			break;
		case "8":
			removeLot();
			adminPanel();
			break;
		case "9":
			System.out.println("Goodbye");
			System.out.println();
			start();
			break;
		}
		System.out.println();
		adminPanel();
	}
	
	// removes a steward from a lot does not fire them
	public static void removeSteward() throws IOException {
		System.out.println("Which lot would you like to remove the steward");
		showLots();
		ParkingLot thisLot = allLots.get(sc.nextInt() -1);
		StewardAccount thisStewards = thisLot.steward;
		Path path = Paths.get("programs/largerProjects/ParkingGarageApp/Stewards.txt");
		List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		int lineNumber = 1;
		Scanner read = new Scanner(new File("programs/largerProjects/ParkingGarageApp/Stewards.txt"));
		while (read.hasNextLine()) {
			String[] line = read.nextLine().split("\\s+");
			String ID = line[2];
			if (ID.equals(thisStewards.getID())){
				break;
			}
			lineNumber += 1;
		}
		lines.remove(lineNumber - 1);
		Files.write(path,  lines, StandardCharsets.UTF_8);
		int index = 0;
		for (StewardAccount s : stewards) {
			if(s.getID().equals(thisStewards.getID())) {
				break;
			}
			index += 1;
		}
		int indexLot = 0;
		for (ParkingLot s : allLots) {
			if(s.getLotID().equals(thisLot.getLotID())) {
				break;
			}
			index += 1;
		}
		stewards.get(index).setLot(null);
		allLots.get(indexLot).steward = null;
		read.close();
		FileWriter fileWriter = new FileWriter("Stewatds.txt", true);
		PrintWriter writer = new PrintWriter(fileWriter);
		writer.println(thisStewards.getUsername() + " " + thisStewards.getPassword() + " " + thisStewards.getID() + " " + thisStewards.getFirstName() + " " + thisStewards.getLastName() + " " + null);
		writer.close();
	}
	
	// assgins a steward to a lot if the lot has a steward it will overwrite the previous steward
	public static void assignSteward(int st) throws IOException {
		
		StewardAccount thisStewards = stewards.get(st);;
		
		
		System.out.println("Which lot would you like to assign them to");
		showLots();
		int lot = sc.nextInt() -1;
		ParkingLot thisLot = allLots.get(lot);
		Path path = Paths.get("programs/largerProjects/ParkingGarageApp/Stewards.txt");
		List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		int lineNumber = 1;
		
		Scanner read = new Scanner(new File("programs/largerProjects/ParkingGarageApp/Stewards.txt"));
		while (read.hasNextLine()) {
			String[] line = read.nextLine().split("\\s+");
			String ID = line[2];
			if (ID.equals(thisStewards.getID())){
				break;
			}
			lineNumber += 1;
		}
		
		lines.remove(lineNumber - 1);
		Files.write(path,  lines, StandardCharsets.UTF_8);
		stewards.get(st).setLot(thisLot.getLotID());
		allLots.get(lot).steward = stewards.get(st);
		read.close();
		FileWriter fileWriter = new FileWriter("programs/largerProjects/ParkingGarageApp/Stewards.txt", true);
		PrintWriter writer = new PrintWriter(fileWriter);
		writer.println(thisStewards.getUsername() + " " + thisStewards.getPassword() + " " + thisStewards.getID() + " " + thisStewards.getFirstName() + " " + thisStewards.getLastName() + " " + thisLot.getLotID());
		writer.close();
		
		
	}
	
	// shows all the stewards
	public static void showStewards() throws IOException {
		if (stewards.isEmpty()) {
			System.out.println("No Stewards");
			adminPanel();
		}else {
			int count = 1;
			for (StewardAccount s : stewards) {
				System.out.println();
				System.out.println("Steward " + count + " ");
				System.out.println(s.toString());
				count += 1;
			}
		}
	}
	
	// adds a steward in order to make a steward the admin must login
	public static void addSteward() throws IOException{
		System.out.println();
		System.out.println("What is his/her username?");
		String userName = sc.next();
		System.out.println();
		System.out.println("What is his/her password?");
		String password = sc.next();
		System.out.println();
		System.out.println("What is his/her first name?");
		String first = sc.next();
		System.out.println();
		System.out.println("What is his/her last name?");
		String last = sc.next();
		Random rm = new Random();
		int newID = rm.nextInt(1234135);
		StewardAccount next = new StewardAccount(userName, password, Integer.toString(newID));
		next.setName(first, last);
		System.out.println();
		System.out.println("Would you like to assign them to a lot now? Yes/No");
		stewards.add(next);
		FileWriter fileWriter = new FileWriter("programs/largerProjects/ParkingGarageApp/Stewards.txt", true);
		PrintWriter writer = new PrintWriter(fileWriter);
		writer.println(userName + " " + password + " " + Integer.toString(newID) + " " + first + " " + last + " " + null);
		writer.close();
		switch (sc.next()) {
		case "Yes":
			assignSteward(stewards.size() -1);
			break;
		case "No":
			break;
		}
		adminPanel();
		
		
	}
	
	// add spaces to a lot of type standard or large
	public static void addSpaces() throws IOException {
		System.out.println();
		System.out.println("What lot would you like to add to?");
		showLots();
		int lot = sc.nextInt() - 1;
		System.out.println();
		System.out.println("What type of spots?");
		String type = sc.next();
		
		Random rm = new Random();
		
		System.out.println();
		System.out.println("How many of these would you like to add?");
		int numToAdd = sc.nextInt();
		for (int i = 0; i < numToAdd; i++) {
			int newID = rm.nextInt(1234135);
			ParkingSpace nextSpace = new ParkingSpace(allLots.get(lot).getLotID(), Integer.toString(newID), type);
			allLots.get(lot).addSpace(nextSpace);
			FileWriter fileWriter = new FileWriter("programs/largerProjects/ParkingGarageApp/Spaces.txt", true);
			PrintWriter writer = new PrintWriter(fileWriter);
			writer.println(allLots.get(lot).getLotID() + " " + Integer.toString(newID) + " " + type + " " + null + " " + null + " " + null);
			writer.close();
			
		}
		
		adminPanel();
	}
	
	// removes spaces from a lot if they do not have a car in them
	public static void removeSpaces() throws IOException {
		System.out.println();
		System.out.println("What lot would you like to remove spots from?");
		showLots();
		int lot = sc.nextInt() - 1;
		System.out.println();
		System.out.println("What type of spots?");
		String type = sc.next();
		int numAvailable = 0;
		for (ParkingSpace s : allLots.get(lot).parking) {
			if (s.getSpaceType().equals(type) && s.getSpaceStatus().equals("available")) {
				numAvailable += 1;
			}
		}
		System.out.println("You can remove " + numAvailable + " parking spaces, how many would you like to remove");
		int remove = sc.nextInt();
		if (remove > numAvailable) {
			System.out.println("Sorry not enough spaces to remove that many");
			adminPanel();
		}
		for (int i = 0; i < remove; i++) {
			int index = -1;
			if (type.equals("standard")) {
				index = allLots.get(lot).getStandardSpace();
			}else {
				index = allLots.get(lot).getLargeSpace();
			}
			
			allLots.get(lot).parking.remove(index);
		}
		PrintWriter delete = new PrintWriter("programs/largerProjects/ParkingGarageApp/Spaces.txt");
		delete.print("");
		delete.close();
		FileWriter fileWriter = new FileWriter("programs/largerProjects/ParkingGarageApp/Spaces.txt", true);
		PrintWriter writer = new PrintWriter(fileWriter);
		for (ParkingLot l : allLots) {
			for (ParkingSpace i : l.parking) {
				writer.println(i.getLotID() + " " + i.getSpaceID() + " " + i.getSpaceType() + " " + i.getSpaceLicense() + " " + "Time??" + " " + i.getSpaceStatus());
			}
		}
		writer.close();
		
	}
	
	// shows all lots and the steward if they have one
	public static void showLots() {
		if (allLots.isEmpty()) {
			System.out.println("No Lots");
		}else {
			int count = 1;
			for (ParkingLot c : allLots) {
				System.out.println();
				System.out.println("Lot " + count + " ");
				System.out.println(c.toString());
				if ((c.steward != null)) {
					System.out.println("Steward is " + c.steward.getFirstName() + " " + c.steward.getLastName());
				}
				count += 1;
			}
		}
	}
	
	// removes a lot only if all spaces have already been removed
	public static void removeLot() throws IOException {
		System.out.println();
		System.out.println("What lot would you like to remove spots from?");
		showLots();
		int lot = sc.nextInt() - 1;
		if (!allLots.get(lot).parking.isEmpty()) {
			System.out.println("Sorry you must first remove all spaces from that lot");
			adminPanel();
		}
		allLots.remove(lot);
		PrintWriter delete = new PrintWriter("/Users/joshuameenan/fun/MyWebsite-1/programs/largerProjects/ParkingGarageApp/ParkingLots");
		delete.print("");
		delete.close();
		FileWriter fileWriter = new FileWriter("/Users/joshuameenan/fun/MyWebsite-1/programs/largerProjects/ParkingGarageApp/ParkingLots", true);
		PrintWriter writer = new PrintWriter(fileWriter);
		for (ParkingLot l : allLots) {
			writer.println(l.getLotID() + " " + l.getLotName() + " " + l.getLotAddress()  + " " + l.getLotCity()  + " " + l.getLotState() + " " + l.getLotZip());
			
		}
		writer.close();
	}
	
	// adds a lot to the system
	public static ParkingLot addLot(String ID) throws IOException 
		
		{
			
			try 
			
			{
				
				/**
				 * Prompts user for parameters to create ParkingLot object (parking lot).
				 */
				
				
				String lotID = ID;
				String error = sc.nextLine().replaceAll("\\s", ","); // can't figure out why this does not work without this the first case always returns nothing
				System.out.println("Please enter parking lot name: ");
				System.out.println();
				String lotName = sc.nextLine().replaceAll("\\s", ",");
				System.out.println("Please enter parking lot address: ");
				System.out.println();
				String lotAddress = sc.nextLine().replaceAll("\\s", ",");
				System.out.println("Please enter city where parking lot is located: ");
				System.out.println();
				String lotCity = sc.nextLine().replaceAll("\\s", ",");
				System.out.println("Please enter the abbreviation of the state where parking lot is located: ");
				System.out.println();
				String lotState = sc.nextLine().replaceAll("\\s", ",");
				System.out.println("Please enter zip code of parking lot location: ");
				System.out.println();
				String lotZip = sc.nextLine().replaceAll("\\s", ",");
				
				FileWriter fileWriter = new FileWriter("/Users/joshuameenan/fun/MyWebsite-1/programs/largerProjects/ParkingGarageApp/ParkingLots", true);
				PrintWriter writer = new PrintWriter(fileWriter);
				writer.println(lotID + " " + lotName + " " + lotAddress + " " + lotCity + " " + lotState + " " + lotZip);
				writer.close();
				//Users/joshuameenan/eclipse-workspace/ParkingGarage//Users/joshuameenan/fun/MyWebsite-1/programs/largerProjects/ParkingGarageApp/ParkingLots
				ParkingLot thisLot = new ParkingLot(lotID, lotName, lotAddress, lotCity, lotState, lotZip);
				return thisLot;
			}
			
			catch(FileNotFoundException e) 
			
			{
				
				e.printStackTrace();
				
			}
			
			return null;
			
		}
	
	//allows a new user to make an account
	public static void makeAccount() throws IOException{
		System.out.println("Please enter your first name");
		System.out.println();
		String firstname = sc.next();
		System.out.println("Please enter your last name");
		System.out.println();
		String lastname = sc.next();
		System.out.println("Please enter a UserName");
		System.out.println();
		String user = sc.next();
		System.out.println("Please enter a Password");
		System.out.println();
		String password = sc.next();
		Random rm = new Random();
		int newID = rm.nextInt(1234135);
		int count = 1;
		while(count < users.size()) {
			for (UserAccount u : users) {
				if (Integer.toString(newID) == u.getID()) {
					newID = rm.nextInt(1234135);
					count = 1;
				}
				count +=1;
			}
			
		}
		
		FileWriter fileWriter = new FileWriter("programs/largerProjects/ParkingGarageApp/Username.txt", true);
		PrintWriter writer = new PrintWriter(fileWriter);
		writer.println(user + " " + password + " " + Integer.toString(newID) + " " + firstname + " " + lastname + " " + false);
		writer.close();
		UserAccount nextUser = new UserAccount(user, password, Integer.toString(newID));
		nextUser.setName(firstname, lastname);
		nextUser.setHasCoupon(false);
		thisUser = nextUser;
		read();
		userOptions();
		
	}
	
	//first interaction with the user and points them in the direction to go
	public static void start() throws IOException {
		String response = "";
		while (!(response.equals("Yes")) || !(response.equals("No")) || !(response.equals("Steward")) || !(response.equals("Admin"))) {
			System.out.println("Hello Welcome to the parking lot interface. \nDo you have an account? (Yes/No) else if you are an Steward or Admin type Steward or Admin");
			System.out.println();
			response = sc.next();
			if (response.equals("Yes")) {
				System.out.println("Welcome back");
				UserLogin();
				break;
			}else if (response.equals("No")) {
				System.out.println("Please make an Account");
				makeAccount();
				break;
			}else if (response.equals("Steward")) {
				System.out.println("Welcome Steward");
				stewardLogin();
				break;
			}else if (response.equals("Admin")) {
				AdminLogin();
				break;
			}else {
				System.out.println("I am sorry I did not understand Please Respond Yes or No");
			}
		}
		
	}
	
	// a user can drop off their car at a lot and pick it up later
	public static void reserveSpot() throws IOException {
		
		System.out.println("Which car are you Parking?");//should we allow them to enter their own lot?
		thisUser.getVehiclesToString();
		int car = sc.nextInt() - 1;
		Car thisCar = thisUser.getVehicles().get(car);
		String typeOfSpace = "";
		if (thisCar.getType().equals("SUV") || thisCar.getType().equals("Sedan")) {
			typeOfSpace = "standard";
		}else {
			typeOfSpace = "large";
		}
		if (typeOfSpace.equals("standard")) {
			System.out.println();
			System.out.println("Even though your car fits a standard Parking Spcae would you like a large one?");
			switch(sc.next()) {
			case "Yes":
				typeOfSpace = "large";
				break;
			case "No":
				break;
			}
		}
		System.out.println();
		System.out.println("Which lot would you like?");
		
		if (allLots.isEmpty()) {
			System.out.println("No Lots for that type of car");
			userOptions();
		}
		showLots();
		int lot = sc.nextInt()-1;
		ParkingLot thisLot = allLots.get(lot);
		boolean check = true;
		
		for (ParkingSpace p : thisLot.parking) {
			if (p.getSpaceType().equals(typeOfSpace) && p.getSpaceStatus().equals("available")) {
				check = false;
				break;
			}
		}
		if (check) {
			System.out.println("No spaces for that type of car try again");
			userOptions();
		}
		int thisSpace = 388688386;
		if (typeOfSpace.equals("standard")) {
			thisSpace = thisLot.getStandardSpace();
		}else {
			thisSpace = thisLot.getLargeSpace();
		}
		double rate = 0;
		int time = 0;
		System.out.println();
		System.out.println("How long will you be staying?");
		System.out.println("1. hours");
		System.out.println("2. days");
		System.out.println("3. months");
		String response = sc.next();
		switch(response) {
		case "1":
			System.out.println();
			System.out.println("How many hours?");
			time = sc.nextInt();
			rate = thisLot.parking.get(thisSpace).getSpaceFeeHr();
			break;
		case"2":
			System.out.println();
			System.out.println("How many days?");
			time = sc.nextInt();
			rate = thisLot.parking.get(thisSpace).getSpaceFeeDay();
			break;
		case "3":
			System.out.println();
			System.out.println("How many months?");
			time = sc.nextInt();
			rate = thisLot.parking.get(thisSpace).getSpaceFeeMon();
			break;
		}
		System.out.println("Please enter your credit card");
		String credit = sc.next();
		int type = 0;
		if (typeOfSpace.equals("standard")) {
			type = 1;
		}
		if (users.get(userIndex(thisUser.getID())).isHasCoupon()) {
			System.out.println("You have coupon would you like to use it?");
			switch(sc.next()) {
			case "Yes":
				rate *= .9;
				users.get(userIndex(thisUser.getID())).setHasCoupon(false);
				PrintWriter delete2 = new PrintWriter("programs/largerProjects/ParkingGarageApp/Username.txt");
				delete2.print("");
				delete2.close();
				FileWriter fileWriter3 = new FileWriter("programs/largerProjects/ParkingGarageApp/", true);
				PrintWriter writer3 = new PrintWriter(fileWriter3);
				for (UserAccount l : users) {
					writer3.println(l.getUsername() + " " + l.getPassword() + " " + l.getID() + " " + l.getFirstName() + " " + l.getLastName() + " " + l.isHasCoupon());
				}
				writer3.close();
			}
		}
		allLots.get(lot).parking.get(thisSpace).setSpaceLicense(thisCar.getLicense());
		PrintWriter delete = new PrintWriter("programs/largerProjects/ParkingGarageApp/Spaces.txt");
		delete.print("");
		delete.close();
		FileWriter fileWriter = new FileWriter("programs/largerProjects/ParkingGarageApp/Spaces.txt", true);
		PrintWriter writer = new PrintWriter(fileWriter);
		for (ParkingLot l : allLots) {
			for (ParkingSpace i : l.parking) {
				writer.println(i.getLotID() + " " + i.getSpaceID() + " " + i.getSpaceType() + " " + i.getSpaceLicense() + " " + "Time??" + " " + i.getSpaceStatus());
			}
		}
		writer.close();
		FileWriter fileWriter2 = new FileWriter("programs/largerProjects/ParkingGarageApp/Transaction.txt", true);
		PrintWriter writer2 = new PrintWriter(fileWriter2);
		writer2.println(thisUser.getID() + " " + credit + " " + thisLot.getLotID() + " " + thisLot.parking.get(thisSpace).getSpaceID() + " " + type + " " + rate+ " " + time);
		writer2.close();
		
		}
		
	public static int userIndex(String ID) {
		int count = 0;
		for (UserAccount l : users) {
			if(l.getID().equals(ID)) {
				return count;
			}
			count += 1;
		}
		return -1;
	}
	// gets the index in the Arraylist of a lot based on the ID of the lot
	public static int lotIndex(String lotID) {
		int count = 0;
		for (ParkingLot l : allLots) {
			if(l.getLotID().equals(lotID)) {
				return count;
			}
			count += 1;
		}
		return -1;
	}
	
	//shows the history of a user
	public static void getHistory() throws IOException{
		Scanner read = new Scanner(new File("programs/largerProjects/ParkingGarageApp/Transaction.txt"));
		String userID = "";
		String credit = "";
		String lotID = "";
		String spaceID = "";
		int type = -1;
		double cost = 0;
		int time = -1;
		boolean check = true;
		while (read.hasNextLine()) {
			String[] line = read.nextLine().split("\\s+");
			userID = line[0];
			credit = line[1];
			lotID = line[2];
			spaceID = line[3];
			type = Integer.parseInt(line[4]);
			cost = Double.parseDouble(line[5]);
			time = Integer.parseInt(line[6]);
			if (userID.equals(thisUser.getID())) {
				Transaction thisTran = new Transaction(userID, credit, lotID, spaceID, type, cost, time);
				int lot = lotIndex(lotID);
				int space = allLots.get(lot).getSpaceIndex(spaceID);
				System.out.println("----------------------------------------------------------------------------");
				System.out.println();
				System.out.println("Thank you for parking with us at " + allLots.get(lot).getLotName() + " At space " + allLots.get(lot).parking.get(space).getLotID());
				System.out.println();
				System.out.println("We hope you enjoyed your visit");
				System.out.println(thisTran.total());
				System.out.println();
				System.out.println("Bill was paid with card number " + credit);
				System.out.println();
				System.out.println("----------------------------------------------------------------------------");
				check = false;
				break;
			}
				
		}
		if (check) {
			System.out.println();
			System.out.println("Sorry there is no history");
			userOptions();
		}
		read.close();
		
	}
	
	// picks up the user' car and sets the space to avaialable again
	public static void getCar() throws IOException{
		System.out.println("Which car are you picking up?");
		thisUser.getVehiclesToString();
		System.out.println();
		int car = sc.nextInt() -1;
		thisUser.getVehicles().get(car);
		
		Scanner read = new Scanner(new File("programs/largerProjects/ParkingGarageApp/Transaction.txt"));
		String userID = "";
		String credit = "";
		String lotID = "";
		String spaceID = "";
		int type = -1;
		double cost = 0;
		int time = -1;
		boolean check = true;
		while (read.hasNextLine()) {
			String[] line = read.nextLine().split("\\s+");
			userID = line[0];
			credit = line[1];
			lotID = line[2];
			spaceID = line[3];
			type = Integer.parseInt(line[4]);
			cost = Double.parseDouble(line[5]);
			time = Integer.parseInt(line[6]);
			if (userID.equals(thisUser.getID())) {
				check = false;
				break;
			}
				
		}
		if (check) {
			System.out.println();
			System.out.println("Sorry ther is no history of that car being parked");
			userOptions();
		}
		read.close();
		Transaction thisTran = new Transaction(userID, credit, lotID, spaceID, type, cost, time);
		int lot = lotIndex(lotID);
		int space = allLots.get(lot).getSpaceIndex(spaceID);
		System.out.println("----------------------------------------------------------------------------");
		System.out.println();
		System.out.println("Thank you for parking with us at " + allLots.get(lot).getLotName()+ " At space " + allLots.get(lot).parking.get(space).getLotID());
		System.out.println();
		System.out.println("We hope you enjoyed your visit");
		System.out.println(thisTran.total());
		System.out.println();
		System.out.println("Bill was paid with card number " + credit);
		System.out.println();
		System.out.println("----------------------------------------------------------------------------");
		allLots.get(lot).parking.get(space).setSpaceLicense("null");
		PrintWriter delete = new PrintWriter("programs/largerProjects/ParkingGarageApp/Spaces.txt");
		delete.print("");
		delete.close();
		FileWriter fileWriter = new FileWriter("programs/largerProjects/ParkingGarageApp/Spaces.txt", true);
		PrintWriter writer = new PrintWriter(fileWriter);
		for (ParkingLot l : allLots) {
			for (ParkingSpace i : l.parking) {
				writer.println(i.getLotID() + " " + i.getSpaceID() + " " + i.getSpaceType() + " " + i.getSpaceLicense() + " " + "Time??" + " " + i.getSpaceStatus());
			}
		}
		writer.close();
		userOptions();
		
	}
	
	public static void main(String[] args) throws IOException {
		admin.setName("Bill", "Gates");
		
		read();
		start();
	}
}
