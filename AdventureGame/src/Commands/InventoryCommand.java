package Commands;

import Game.Player;
import Game.Room;

public class InventoryCommand extends CommandHandler {
	public InventoryCommand() {
		super(new String[] { "inventory", "inv", "i" });
	}

	@Override
	protected String processCommand(String[] tokens, Player player, Room room) {
		return(player.listItems("You currently have a "));
	}
}
