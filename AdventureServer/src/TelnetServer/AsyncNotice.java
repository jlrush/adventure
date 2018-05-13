package TelnetServer;

public class AsyncNotice extends Thread {
	private TelnetOutput output;
	
	public AsyncNotice(TelnetOutput output) {
		this.output = output;
	}
	
	public void run() {
		for(;;) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
			output.println("Another person enters the room.");
		}
	}
}
