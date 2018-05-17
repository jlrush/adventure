package TelnetServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
	public static void main(String[] args) throws IOException {
		final int portNumber = 8023;
		System.out.println("Creating server socket on port " + portNumber);
		ServerSocket serverSocket = new ServerSocket(portNumber);
		ServerConnection client;
		while (true) {
			Socket socket = serverSocket.accept();
			client = new ServerConnection(socket);		
			client.start();
		}
	}
}
