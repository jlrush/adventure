package Commands;
import java.util.ArrayList;
import java.util.Arrays;

import Game.Player;
import Game.Room;

public abstract class CommandHandler {
	ArrayList<String> commands = new ArrayList<String>();
	
	protected CommandHandler(String[] commands) {
		this.commands = new ArrayList<String>(Arrays.asList(commands));
	}
	
	public boolean executeCommand(String command, Player player, Room room) {
		// Get each word in the input and split it into an array
		String[] tokens = command.split("\\s+");
		boolean commandMatches = false;

		// Check to see if the first word matches any of our commands
		for (String c : commands) {
			if (c.equalsIgnoreCase(tokens[0])) {
				commandMatches = true;
				break;
			}
		}

		if (commandMatches) {
			// We have a match, so go ahead and process the command
			processCommand(tokens, player, room);
			return true;
		} else {
			return false;
		}
	}
	
	protected abstract void processCommand(String[] tokens, Player player, Room room);
}
