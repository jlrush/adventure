package Game;

public abstract class UserOutput {
	public abstract void print(String text);
	
	public void println(String text) {
		print(text + "\r\n");
	}
	
	public void heading(String message) { 
		this.println(message);
	}

	public void roomName(String message) { 
		this.println(message);
	}

	public void activity(String message) { 
		this.println(message);
	}

	public void warning(String message) { 
		this.println(message);
	}
}
