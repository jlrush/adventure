package Commands;

import Game.Player;
import Game.Room;
import Game.UserOutput;

public class GetCommand extends CommandHandler {
	public GetCommand() {
		super(new String[] { "get" });
	}

	@Override
	protected void processCommand(UserOutput output, String[] tokens, Player player, Room room) {
		String message = "";
		String object;
		
		if (tokens.length >= 2) {
			object = tokens[1];
			if (!room.findItem(object)) {
				message = "There is no " + object + " in this room, " + player.getName();
			} else {
				player.addItem(room.getItem(object));
				message = "You now have the " + object;
			}
			
			output.activity(message);
			return;
		}

		// Get all objects in a room
		String list = room.listItems("");
		if (list == "")
			message = "There are no objects in this room to get, " + player.getName();
		else {
			String itemlist[] = list.split(", ");
			for (String temp : itemlist) {
				object = temp;
				player.addItem(room.getItem(object));
				message += "You now have the " + object;
			}
		}

		output.activity(message);
		return;
	}
}
