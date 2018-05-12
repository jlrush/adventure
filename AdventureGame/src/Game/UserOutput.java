package Game;

public abstract class UserOutput {
	public abstract void print(String text);
	
	public void println(String text) {
		print(text + "\n");
	}
}
