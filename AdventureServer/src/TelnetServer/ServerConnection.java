package TelnetServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import Game.Adventure1;

public class ServerConnection extends Thread {
	private Socket socket;
	private TelnetOutput output;
	private BufferedReader reader;
	private OutputStream stream;
	private PrintWriter writer;

	public ServerConnection(Socket socket) throws IOException {
		this.socket = socket;
		// this.output = new TelnetOutput(socket);
		// this.reader = new BufferedReader(new
		// InputStreamReader(socket.getInputStream()));
		System.out.println("Accepted connection from " + socket.getInetAddress().toString());
		// reader.readLine(); // Read telnet client ID and ignore it.
	}
	
	private String filter(String str) {
		String command = "";
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if ((c >= '0' && c <= '9') || c == ' ' || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
				command += str.charAt(i);
			}
		}
		
		return command.trim();
	}

	public void run() {
		try {
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			Adventure1 game;
			
//			output.println("Welcome, Player One!  What shall I call you?");
//			String input = scanner.nextLine();
//			output.println("Okay, " + player1.getName() + ", here we go!");

			pw.println("Welcome, Player One! What shall I call you?");
			String playerName = filter(br.readLine());
			pw.println("Okay, " + playerName + ", here we go!");
			System.out.println(playerName + ", has bravely entered the dungeon.");
			game = new Adventure1(new TelnetOutput(pw), playerName);

			for(;;) {
				pw.print("Command: ");
				pw.flush();
				
				String command = filter(br.readLine());
				game.ProcessInput(command);
				if (game.isGameOver()) {
					break;
				}
			}
			
			System.out.println("Disconnecting");
			pw.close();
			socket.close();
		} catch(Exception ex) {
		}

	// output.println("1What's your name?");
	// output.println("2What's your name?");
	// output.println("3What's your name?");
	//
	// String str = "";
	// try {
	// str = reader.readLine();
	// } catch (IOException e) {
	// System.out.println("Reader error.");
	// }
	//
	// output.println("Hello, " + str);
	// System.out.println("Just said hello to:" + str);
	//
	// Disconnect();
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
