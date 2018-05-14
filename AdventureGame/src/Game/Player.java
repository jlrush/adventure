package Game;
import java.util.ArrayList;

public class Player extends ItemHolder {
	private String name;
	private Messages invalidCommand;
		
	public Player(String name) {
		this.name = name;
		Messages temp = new Messages();
		this.invalidCommand = temp;
	}
	
	public void addName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getInvalidMessage() {
		return invalidCommand.getMesssage();
	}
	}
