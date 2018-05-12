package TelnetServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnection extends Thread {
	private Socket socket;
	private TelnetOutput output;
	private BufferedReader reader;
	
	public ServerConnection(Socket socket) throws IOException {
		this.socket = socket;
		this.output = new TelnetOutput(socket);
		this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		System.out.println("Accepted connection from " + socket.getInetAddress().toString());
		//reader.readLine(); // Read telnet client ID and ignore it.
	}
	
	public void run() {
		output.println("1What's your name?");
		output.println("2What's your name?");
		output.println("3What's your name?");

		String str = "";
		try {
			str = reader.readLine();
		} catch (IOException e) {
			System.out.println("Reader error.");
		}
		
		output.println("Hello, " + str);
		System.out.println("Just said hello to:" + str);
		
		Disconnect();
	}
	
	public void Disconnect() {
		System.out.println("Disconnected from " + socket.getInetAddress().toString());

		this.output.close();

		try {
			this.socket.close();
		} catch (IOException e) {
		}
	}
}
