package Commands;

import Game.Player;
import Game.Room;

public class HelpCommand extends CommandHandler {
	public HelpCommand() {
		super(new String[] { "help", "h" });
	}

	@Override
	protected String processCommand(String[] tokens, Player player, Room room) {
		String Yellow = "\033[33m";
		String White = "\033[0m";
		String CRLF = "\r\n";
		String temp = "";
		
		if ((tokens.length < 2) || (tokens[1].equals("commands"))) {
		temp = CRLF + Yellow + "Available commands: " + White;
		temp += CRLF + "   Get/Drop:\tObtain items from (and return them to) rooms";
		temp += CRLF + "   Examine:\tProvides description of a specific item";
		temp += CRLF + "   Inventory: \tGets a list of items you currently have";
		temp += CRLF + "   Look:\tRepeats the description of the room you are in";
		temp += CRLF + Yellow + "Directional commands:" + White;
		temp += CRLF + "   North, South, East, West, Up, Down";
		}
		return temp;
	}
}
