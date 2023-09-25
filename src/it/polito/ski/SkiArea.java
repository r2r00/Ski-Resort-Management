package it.polito.ski;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;



public class SkiArea {

	/**
	 * Creates a new ski area
	 * @param name name of the new ski area
	 */
	private String name;
	private Map<String ,Lift> liftTypes = new TreeMap<>(); //code ,lift
	private Map<String ,String> lifts = new TreeMap<>(); // name, code
	private Map<String ,SkiSlope> skiSlopes = new TreeMap<>(); //name, skiSlopes
	private Map<String ,Parking> parkings= new TreeMap<>(); //name, parkings

	
	
	
	public SkiArea(String name) {
		this.name = name;
		
    }

	/**
	 * Retrieves the name of the ski area
	 * @return name
	 */
	public String getName() { return name; }

    /**
     * define a new lift type providing the code, the category (Cable Cabin, Chair, Ski-lift)
     * and the capacity (number of skiers carried) of a single unit
     * 
     * @param code		name of the new type
     * @param category	category of the lift
     * @param capacity	number of skiers per unit
     * @throws InvalidLiftException in case of duplicate code or if the capacity is <= 0
     */
    public void liftType(String code, String category, int capacity) throws InvalidLiftException {
    	if (liftTypes.containsKey(code) ) throw new InvalidLiftException("duplicate code");
    	if (capacity <= 0) throw new InvalidLiftException("capacity not valid");
    	liftTypes.put(code, new Lift(code, category, capacity));
    	

    }
    
    /**
     * retrieves the category of a given lift type code
     * @param typeCode lift type code
     * @return the category of the type
     * @throws InvalidLiftException if the code has not been defined
     */
    public String getCategory(String typeCode) throws InvalidLiftException {
    	if (!liftTypes.containsKey(typeCode)) throw new InvalidLiftException("Code not defined");
    	
		return liftTypes.get(typeCode).getCategrory();
    }

    /**
     * retrieves the capacity of a given lift type code
     * @param typeCode lift type code
     * @return the capacity of the type
     * @throws InvalidLiftException if the code has not been defined
     */
    public int getCapacity(String typeCode) throws InvalidLiftException {
    	if (liftTypes.containsKey(typeCode)) return liftTypes.get(typeCode).getCapacity();
    	else throw new InvalidLiftException("Code not defined");
    	
    }


    /**
     * retrieves the list of lift types
     * @return the list of codes
     */
	public Collection<String> types() {
		return liftTypes.keySet().stream().collect(Collectors.toList());
	}
	
	/**
	 * Creates new lift with given name and type
	 * 
	 * @param name		name of the new lift
	 * @param typeCode	type of the lift
	 * @throws InvalidLiftException in case the lift type is not defined
	 */
    public void createLift(String name, String typeCode) throws InvalidLiftException{
    	if (!liftTypes.containsKey(typeCode)) throw new InvalidLiftException("Type not defined");
    	lifts.put(name, typeCode);
    	

    }
    
	/**
	 * Retrieves the type of the given lift
	 * @param lift 	name of the lift
	 * @return type of the lift
	 */
	public String getType(String lift) {
		return lifts.get(lift);
	}

	/**
	 * retrieves the list of lifts defined in the ski area
	 * @return the list of names sorted alphabetically
	 */
	public List<String> getLifts(){
		return lifts.keySet().stream().sorted().collect(Collectors.toList());
    }

	/**
	 * create a new slope with a given name, difficulty and a starting lift
	 * @param name			name of the slope
	 * @param difficulty	difficulty
	 * @param lift			the starting lift for the slope
	 * @throws InvalidLiftException in case the lift has not been defined
	 */
    public void createSlope(String name, String difficulty, String lift) throws InvalidLiftException {
    	if (!lifts.containsKey(lift)) throw new InvalidLiftException("lift not defined");
    	skiSlopes.put(name, new SkiSlope(name, difficulty, lift));
    	

    }
    
    /**
     * retrieves the name of the slope
     * @param slopeName name of the slope
     * @return difficulty
     */
	public String getDifficulty(String slopeName) {
		return skiSlopes.get(slopeName).getDifficulty();
	}

	/**
	 * retrieves the start lift
	 * @param slopeName name of the slope
	 * @return starting lift
	 */
	public String getStartLift(String slopeName) {
		return skiSlopes.get(slopeName).getLift();
	}

	/**
	 * retrieves the list of defined slopes
	 * 
	 * @return list of slopes
	 */
    public Collection<String> getSlopes(){
		return skiSlopes.keySet().stream().collect(Collectors.toList());
    }

    /**
     * Retrieves the list of slopes starting from a given lift
     * 
     * @param lift the starting lift
     * @return the list of slopes
     */
    public Collection<String> getSlopesFrom(String lift){
		return skiSlopes.keySet().stream().filter(x-> skiSlopes.get(x).getLift().equals(lift)).collect(Collectors.toList());
    }

    /**
     * Create a new parking with a given number of slots
     * @param name 	new parking name
     * @param slots	slots available in the parking
     */
    public void createParking(String name, int slots){
    	parkings.put(name, new Parking(name, slots));
    	
    }

    /**
     * Retrieves the number of parking slots available in a given parking
     * @param parking	parking name
     * @return number of slots
     */
	public int getParkingSlots(String parking) {
		return parkings.get(parking).getSlots();
	}

	/**
	 * Define a lift as served by a given parking
	 * @param lift		lift name
	 * @param parking	parking name
	 */
	public void liftServedByParking(String lift, String parking) {
		parkings.get(parking).addLift(lift, liftTypes.get(lifts.get(lift)).getCapacity());
	}

	
	/**
	 * Retrieves the list of lifts served by a parking.
	 * @param parking	parking name
	 * @return the list of lifts
	 */
	public Collection<String> servedLifts(String parking) {
		return parkings.get(parking).returnLifts();
	}

	/**
	 * Checks whether the parking is proportional to the capacity of the lift it is serving.
	 * A parking is considered proportionate if its size divided by the sum of the capacity of the lifts 
	 * served by the parking is less than 30.
	 * 
	 * @param parkingName name of the parking to check
	 * @return true if the parking is proportionate
	 */
	public boolean isParkingProportionate(String parkingName) {
		return parkings.get(parkingName).checkProportionate();
	}

	/**
	 * reads the description of lift types and lift descriptions from a text file. 
	 * The contains a description per line. 
	 * Each line starts with a letter indicating the kind of information: "T" stands for Lift Type, 
	 * while "L" stands for Lift.
	 * A lift type is described by code, category and seat number. 
	 * A lift is described by the name and the lift type.
	 * Different data on a line are separated by ";" and possible spaces surrounding the separator are ignored.
	 * If a line contains the wrong number of information it should be skipped and
	 * the method should continue reading the following lines. 
	 * 
	 * @param path 	the path of the file
	 * @throws IOException	in case IO error
	 * @throws InvalidLiftException in case of duplicate type or non-existent lift type
	 */
    public void readLifts(String path) throws IOException, InvalidLiftException {
    	try (BufferedReader in = new BufferedReader(new FileReader(path))) {
    		List<String> lines = in.lines().collect(Collectors.toList());
    		boolean wrongInput = false;
    		for (String line : lines) {
    			String [] entries = lines.remove(0).split(";");

    			if (entries[0].strip().equals("T") && entries.length != 4) wrongInput = true ;
    			if (entries[0].strip().equals("L") && entries.length != 3) wrongInput = true ;
    			if (!(entries[0].strip().equals("L")) || !(entries[0].strip().equals("T") ))  wrongInput = true ;
    			if (!wrongInput) {
    				if (entries[0].strip().equals("T")) {
    					this.liftType(entries[1].strip(), entries[2].strip(),Integer.parseInt(entries[3].strip()) );
    				}
    				if (entries[0].strip().equals("L")) {
    					this.createLift(entries[1].strip(), entries[2].strip());
    				}
    			}
    			wrongInput = false;
    		}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
    	


    }

}
