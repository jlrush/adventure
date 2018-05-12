package Game;
import java.util.Arrays;

public class Messages {
	private int next;
	private String[] message;

	public Messages() {
		String[] temp = new String[4];
		temp[0] = "I don't know what you mean";
		temp[1] = "You can't do that";
		temp[2] = "I suppose one could, but I'd rather not";
		temp[3] = "Has your mother heard that mouth?";
		this.message = temp;
		this.next = temp.length;
	}

	public String getMesssage() {
		if (this.next >= message.length - 1)
			this.next = -1;
		this.next ++;
		return message[this.next];
		
	}
}
