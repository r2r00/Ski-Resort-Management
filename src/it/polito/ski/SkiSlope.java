package it.polito.ski;

public class SkiSlope {
	private String name, difficulty, lift;

	public SkiSlope(String name, String difficulty, String lift) {
	
		this.name = name;
		this.difficulty = difficulty;
		this.lift = lift;
	}

	public String getName() {
		return name;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public String getLift() {
		return lift;
	}
	
}
