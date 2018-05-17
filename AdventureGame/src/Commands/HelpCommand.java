package Commands;

import Game.Player;
import Game.Room;
import Game.UserOutput;

public class HelpCommand extends CommandHandler {
	public HelpCommand() {
		super(new String[] { "help", "h" });
	}

	@Override
	protected void processCommand(UserOutput output, String[] tokens, Player player, Room room) {
		String message = "";
		
		if ((tokens.length < 2) || (tokens[1].equals("commands"))) {
			output.heading("Available commands: ");
			message += "   Get/Drop:\tObtain items from (and return them to) rooms\r\n";
			message += "   Examine:\tProvides description of a specific item\r\n";
			message += "   Inventory: \tGets a list of items you currently have\r\n";
			message += "   Look:\tRepeats the description of the room you are in\r\n";
			output.println(message);
			
			output.heading("Directional commands:");
			output.println("   North, South, East, West, Up, Down");
		}
	}
}
