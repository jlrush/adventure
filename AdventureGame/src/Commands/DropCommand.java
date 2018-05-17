package Commands;

import Game.Player;
import Game.Room;
import Game.UserOutput;

public class DropCommand extends CommandHandler {
	public DropCommand() {
		super(new String[] { "drop" });
	}

	@Override
	protected void processCommand(UserOutput output, String[] tokens, Player player, Room room) {
		String message = "";
		String object;

		if (tokens.length >= 2) {
			object = tokens[1];
			if (!player.findItem(object)) {
				message = "You don't have any " + object + " to drop, " + player.getName();
			} else {
				room.addItem(player.getItem(object));
				message = "You no longer have the ";
			}

			output.activity(message);
			return;
		}

		// Get all objects in a room
		String list = player.listItems("");
		if (list == "")
			message = "You don't have anything to drop, " + player.getName();
		else {
			String itemlist[] = list.split(", ");
			for (String temp : itemlist) {
				object = temp;
				room.addItem(player.getItem(object));
				message += "You no longer have the " + object;
			}
		}
		
		output.activity(message);
	}
}