package Commands;

import Game.Player;
import Game.Room;

public class GetCommand extends CommandHandler {
	public GetCommand() {
		super(new String[] { "get" });
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
			if (!room.findItem(object)) {
				output = CRLF + Yellow + "There is no " + object + " in this room, " + player.getName() + White;
			} else {
				player.addItem(room.getItem(object));
				output = CRLF + Yellow + "You now have the " + object + White;
			}
			return output;
		}

		// Get all objects in a room
		String list = room.listItems("");
		if (list == "")
			output = CRLF + Yellow + "There are no objects in this room to get, " + player.getName() + White;
		else {
			String itemlist[] = list.split(", ");
			for (String temp : itemlist) {
				object = temp;
				player.addItem(room.getItem(object));
				output += CRLF + Yellow + "You now have the " + object + White;
			}
		}
		return output;
	}
}
