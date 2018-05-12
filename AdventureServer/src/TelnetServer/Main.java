package TelnetServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		final int portNumber = 8023;
		System.out.println("Creating server socket on port " + portNumber);
		ArrayList<ServerConnection> clients = new ArrayList<ServerConnection>();
		
		ServerSocket serverSocket = new ServerSocket(portNumber);
		while (true) {
			ServerConnection client = new ServerConnection(serverSocket.accept());
			client.start();
			clients.add(client);
		}
		//serverSocket.close();
	}
}
