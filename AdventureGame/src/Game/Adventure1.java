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
		current = Setup.Initiatlize();
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
		Messages unknown = new Messages();
		boolean done = false;
		String command = "";
		String object = "";
		
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
				output.println("There is no exit in that direction");
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
				output.println("There is no " + object + " in this room");
				return;
			} else {
				player1.addItem(current.getItem(object));
				output.println("You now have the " + object);
				return;
			}
		}

		// Drop an object in a room
		if (command.equals("drop") && object != "") {
			if (!player1.findItem(object)) {
				output.println("You don't have any " + object + " to drop");
				return;
			} else {
				current.addItem(player1.getItem(object));
				output.println("You no longer have the " + object);
				return;
			}
		}
		
		// Last command to check
		if (command.equalsIgnoreCase("exit"))
			this.gameIsOver = true;
		else
			output.println(unknown.getMesssage());
	}
}
