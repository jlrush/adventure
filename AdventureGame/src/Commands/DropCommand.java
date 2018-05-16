package Commands;

import Game.Player;
import Game.Room;

public class DropCommand extends CommandHandler {
	public DropCommand() {
		super(new String[] { "drop" });
	}

	@Override
	protected String processCommand(String[] tokens, Player player, Room room) {
		String Yellow = "\033[33m";
		String White = "\033[0m";
		String CRLF = "\r\n";
		String output = "";
		String object;

		if (tokens.length >= 2) {
				object = tokens[1];
				if (!player.findItem(object)) {
					output = CRLF + Yellow + "You don't have any " + object + " to drop, " + player.getName() + White;
				} else {
					room.addItem(player.getItem(object));
					output = CRLF + Yellow + "You no longer have the " + object + White;
			}
			return output;
		}

		// Get all objects in a room
		String list = player.listItems("");
		if (list == "")
			output = CRLF + Yellow + "You don't have anything to drop, " + player.getName() + White;
		else {
			String itemlist[] = list.split(", ");
			for (String temp : itemlist) {
				object = temp;
				room.addItem(player.getItem(object));
				output += CRLF + Yellow + "You no longer have the " + object + White;
			}
		}
		return output;
	}
}