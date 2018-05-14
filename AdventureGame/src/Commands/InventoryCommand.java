package Commands;

import Game.Player;
import Game.Room;

public class InventoryCommand extends CommandHandler {
	public InventoryCommand() {
		super(new String[] { "inventory", "inv", "i" });
	}

	@Override
	protected String processCommand(String[] tokens, Player player, Room room) {
		String Yellow = "\033[33m";
		String White = "\033[0m";
		return ("\r\n" + Yellow + player.listItems("You currently have a ") + White);
	}
}
