package it.polito.ski;

public class Lift {
	private String code, categrory;
	private int capacity;
	public Lift(String code, String categrory, int capacity) {
		
		this.code = code;
		this.categrory = categrory;
		this.capacity = capacity;
	}
	public String getCode() {
		return code;
	}
	public String getCategrory() {
		return categrory;
	}
	public int getCapacity() {
		return capacity;
	}
	
}
