
public class Residential extends Structure {

	public Residential(int bedrooms, String structureMaterial, int numOfWalls, int numOfFloors, int sqFt) {
		super(structureMaterial, numOfWalls, numOfFloors, sqFt);
		this.bedrooms = bedrooms;
	}

	private int bedrooms;
	
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

	public int getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}

	@Override
	public String toString() {
		return "Residential [bedrooms=" + bedrooms + ", structureMaterial=" + structureMaterial + ", numOfWalls="
				+ numOfWalls + ", numOfFloors=" + numOfFloors + ", sqFt=" + sqFt + "]";
	}

}
