
public abstract class Structure implements Buildable {
	
	public Structure(String structureMaterial, int numOfWalls, int numOfFloors, int sqFt) {
		super();
		this.structureMaterial = structureMaterial;
		this.numOfWalls = numOfWalls;
		this.numOfFloors = numOfFloors;
		this.sqFt = sqFt;
	}
	protected String structureMaterial;
	protected int numOfWalls;
	protected int numOfFloors;
	protected int sqFt;
	
}
