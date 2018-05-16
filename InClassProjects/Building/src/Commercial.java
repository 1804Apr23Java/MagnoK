
public class Commercial extends Structure {
	
	public Commercial(int escalators, int elevators, String structureMaterial, int numOfWalls, int numOfFloors, int sqFt) {
		super(structureMaterial, numOfWalls, numOfFloors, sqFt);
		this.escalators = escalators;
		this.elevators = elevators;
	}
	
	//Overload
	public Commercial(String structureMterial, int numOfWalls, int numOfFloors, int sqFt) {
		super(structureMterial, numOfWalls, numOfFloors, sqFt);
	}

	private int escalators;
	private int elevators;

	@Override
	public void installWall() {
		System.out.println("Wall Installed!");
	}

	@Override
	public void installWindow() {
		System.out.println("Window Installed!");
	}

	@Override
	public void installUtil() {
		System.out.println("Utility Installed!");
	}

	@Override
	public void installFloor() {
		System.out.println("Floor Installed!");
	}

	public int getEscalators() {
		return escalators;
	}

	public void setEscalators(int escalators) {
		this.escalators = escalators;
	}

	public int getElevators() {
		return elevators;
	}

	public void setElevators(int elevators) {
		this.elevators = elevators;
	}

	@Override
	public String toString() {
		return "Commercial [escalators=" + escalators + ", elevators=" + elevators + ", structureMaterial="
				+ structureMaterial + ", numOfWalls=" + numOfWalls + ", numOfFloors=" + numOfFloors + ", sqFt=" + sqFt
				+ "]";
	}

}
