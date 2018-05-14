package Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Commands.CommandHandler;
import Commands.CommandResult;

public class Adventure1 {
	private Room current;
	private UserOutput output;
	private Player player1;
	private ArrayList<CommandHandler> commands;

	private boolean gameIsOver = false;

	public Adventure1(UserOutput output, String playerName) {
		// First we initialize the rooms
		current = Setup.Initialize();
		this.output = output;
		this.player1 = new Player(playerName);

		commands = new ArrayList<CommandHandler>();
		commands.add(new Commands.ExamineCommand());
		commands.add(new Commands.InventoryCommand());
		commands.add(new Commands.LookCommand());

		output.println(current.preamble());
	}

	public boolean isGameOver() {
		return this.gameIsOver;
	}

	public void ProcessInput(String input) {
		processInternal(input);
		output.println(current.preamble());
	}

	public void processInternal(String input) {
		Room newRoom;

		// Then everything else
		boolean done = false;
		String command = "";
		String object = "";
		String Yellow = "\033[33m";
		String White = "\033[0m";
		String CRLF = "\r\n";

		// Parse the commands
		if (input.indexOf(" ") == -1) {
			command = input;
			object = "";
		} else {
			command = input.substring(0, input.indexOf(" "));
			object = input.substring(input.indexOf(' ') + 1);
		}

		// Assume command is a direction and move there
		newRoom = current.takeExit(command);
		if (newRoom != null) {
			if (current.equals(newRoom))
				output.println(CRLF + "\033[93m" + "There is no exit in that direction" + White);
			else {
				current = newRoom;
			}
			return;
		}

		// Handle any registered command
		CommandResult result = null;
		for (CommandHandler handler : commands) {
			result = handler.executeCommand(input, player1, current);
			if (result.output != null && !result.output.isEmpty()) {
				output.println(result.output);
			}

			if (result.processed) {
				break;
			}
		}

		if (result != null && result.processed) {
			return;
		}

		// Get an object in a room
		if (command.equals("get") && object != "") {
			if (!current.findItem(object)) {
				output.println(CRLF + Yellow + "There is no " + object + " in this room" + White);
				return;
			} else {
				player1.addItem(current.getItem(object));
				output.println(CRLF + Yellow + "You now have the " + object + White);
				return;
			}
		}

		// Get all objects in a room
		if (command.equals("get") && object == "") {
			String[] itemlist = current.listItems("").split(", ");
			//
			if (!current.findItem(object)) {
				output.println(CRLF + Yellow + "There is no " + object + " in this room" + White);
				return;
			} else {
				player1.addItem(current.getItem(object));
				output.println(CRLF + Yellow + "You now have the " + object + White);
				return;
			}
		}
		
		// Drop an object in a room
		if (command.equals("drop") && object != "") {
			if (!player1.findItem(object)) {
				output.println(CRLF + Yellow + "You don't have any " + object + " to drop" + White);
				return;
			} else {
				current.addItem(player1.getItem(object));
				output.println(CRLF+ Yellow + "You no longer have the " + object + White);
				return;
			}
		}

		// Last command to check
		if (command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("quit")) {
			this.gameIsOver = true;
		} else
			output.println(CRLF + Yellow + player1.getInvalidMessage() + White);
	}
}
