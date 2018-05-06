package domain;

public class Transactions {
	
	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transactions(int id, String time, String type, int modifier) {
		super();
		this.id = id;
		this.time = time;
		this.type = type;
		this.modifier = modifier;
	}
	private int id;
	private String time;
	private String type;
	private int modifier;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getModifier() {
		return modifier;
	}
	public void setModifier(int modifier) {
		this.modifier = modifier;
	}
	@Override
	public String toString() {
		//return "Transactions [id=" + id + ", time=" + time + ", type=" + type + ", modifier=" + modifier + "]";
		return "Time:\t" + time + "\t" + type + "\t" + "$" + modifier;
	}
	
	
}
