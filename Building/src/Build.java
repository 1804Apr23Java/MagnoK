
public class Build {

	public static void main(String[] args) {
		
		Residential r = new Residential(20, "Marble", 200, 10, 20000);
		Commercial c = new Commercial(12, 5, "Gold", 2, 1, 20);
		
		Commercial c2 = new Commercial("Diamond", 36, 100, 500000);
		
		r.installFloor();
		c.installUtil();
		c2.installWall();
		r.installWindow();
		
		System.out.println(r.toString());
		System.out.println(c.toString());
		System.out.println(c2.toString());

	}

}