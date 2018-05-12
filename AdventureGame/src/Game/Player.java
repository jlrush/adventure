package Game;
import java.util.ArrayList;

public class Player extends ItemHolder {
	private String name;
		
	public Player(String name) {
		this.name = name;
		
	}
	
	public void addName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	}
