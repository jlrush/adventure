package Game;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Commands.CommandHandler;

public class Adventure1 {

	public static void main(String[] args) {
		
		// First we initialize the rooms
		Room current = Setup.main(args);
		Room newRoom;
		
		UserOutput output = new ConsoleOutput();
		
		// Then everything else
		Scanner scanner = new Scanner(System.in);
		Messages unknown = new Messages();
		boolean done = false;
		String command = "";
		String object = "";
		
		// and build a new player
		output.println("Welcome, Player One!  What shall I call you?");
		String input = scanner.nextLine();
		Player player1 = new Player(input);
//		player1.addItem(new Item("Shirt", "It has short sleeves and a pocket"));  //test
		output.println("Okay, " + player1.getName() + ", here we go!");
		
		ArrayList<CommandHandler> commands = new ArrayList<CommandHandler>(); 
		commands.add(new Commands.ExamineCommand());
		commands.add(new Commands.InventoryCommand());
		commands.add(new Commands.LookCommand());

		// This is the main loop
		do {
			// First describe where we are 
			output.println(current.preamble());

			// Parse the commands
			output.print("\nCommand: ");
			input = scanner.nextLine().toLowerCase();
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
				continue;
			}
			
			// Handle any registered command
			boolean handled = false;
			for (CommandHandler handler : commands) {
				handled = handler.executeCommand(input, player1, current);
				if (handled) {
					break;
				}
			}
			
			if (handled) {
				continue;
			}

			// Get an object in a room
			if (command.equals("get") && object != "") {
				if (!current.findItem(object)) {
					output.println("There is no " + object + " in this room");
					continue;
				} else {
					player1.addItem(current.getItem(object));
					output.println("You now have the " + object);
					continue;
				}
			}

			// Drop an object in a room
			if (command.equals("drop") && object != "") {
				if (!player1.findItem(object)) {
					output.println("You don't have any " + object + " to drop");
					continue;
				} else {
					current.addItem(player1.getItem(object));
					output.println("You no longer have the " + object);
					continue;
				}
			}
			

			// Last command to check
			if (command.equalsIgnoreCase("exit"))
				done = true;
			else
				output.println(unknown.getMesssage());
		}while(!done);
	
		output.println("Player One is dead! Long live Player Two");

	}

}
