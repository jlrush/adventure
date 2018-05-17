package Game;

import java.util.ArrayList;
import java.util.Arrays;

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
		commands.add(new Commands.GetCommand());
		commands.add(new Commands.DropCommand());
		commands.add(new Commands.HelpCommand());

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
				output.activity("There is no exit in that direction");
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

		// Last command to check
		if (command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("quit")) {
			this.gameIsOver = true;
		} else
			output.activity(player1.getInvalidMessage());
	}
}
