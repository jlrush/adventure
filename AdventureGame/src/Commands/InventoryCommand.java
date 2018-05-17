package Commands;

import Game.Player;
import Game.Room;
import Game.UserOutput;

public class InventoryCommand extends CommandHandler {
	public InventoryCommand() {
		super(new String[] { "inventory", "inv", "i" });
	}

	@Override
	protected void processCommand(UserOutput output, String[] tokens, Player player, Room room) {
		String message = player.listItems("You currently have a ");
		if (message.length() > 0) {
			output.activity(message);
		}
	}
}
