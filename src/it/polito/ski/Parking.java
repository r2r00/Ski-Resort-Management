package it.polito.ski;


import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;



public class Parking {
	private String name;
	private int slots;
	private Map<String, Integer> lifts;

	public Parking(String name, int slots) {
		this.name = name;
		this.slots = slots;
		this.lifts = new TreeMap<>();
	
	}
	public int getSlots() {
		return slots;
	}
	public String getName() {
		return name;
	}
	public void addLift(String lift, int capacity) {
		lifts.put(lift, capacity);
	}
	public boolean checkProportionate() {
		return slots/lifts.values().stream().mapToInt(Integer::intValue).sum() < 30;
	}
	public Collection<String> returnLifts(){
		return lifts.keySet().stream().collect(Collectors.toList());
		
	}
}
