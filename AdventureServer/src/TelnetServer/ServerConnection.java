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
	//private AsyncNotice notice;

	public ServerConnection(Socket socket) throws IOException {
		this.socket = socket;
		this.output = new TelnetOutput(socket);
		this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.output = new TelnetOutput(socket);
		System.out.println("Accepted connection from " + socket.getInetAddress().toString());
		//this.notice = new AsyncNotice(this.output);
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
			Adventure1 game;

			output.println("\033[2J"); // Clear Screen
			output.println("\033[0;0H"); //Home cursor
			output.println("Welcome, Player One! What shall I call you?\r\n");
			String playerName = filter(reader.readLine());
			
			output.println("\r\nOkay, " + playerName + ", here we go!\r\n\r\n\r\n");
			System.out.println(playerName + ", has bravely entered the dungeon.");
			game = new Adventure1(output, playerName);
			//this.notice.run();

			for (;;) {
				// pw.print("\033[2J"); // Clear Screen
				// pw.print("\033[0;0H"); // Go to top of screen
				output.print("\r\n\033[32mCommand: \033[0m");

				String command = filter(reader.readLine());
				game.ProcessInput(command);
				if (game.isGameOver()) {
					break;
				}
			}
		} catch (Exception ex) {
		}

		Disconnect();
	}

	public void Disconnect() {
		System.out.println("Disconnected from " + socket.getInetAddress().toString());
		
		try {
			this.reader.close();
		} catch (IOException ioException) {
		} finally {
			this.reader = null;
		}

		this.output.close();
		this.output = null;

		try {
			this.socket.close();
		} catch (IOException ioException) {
		} finally {
			this.socket = null;
		}		
	}
}
