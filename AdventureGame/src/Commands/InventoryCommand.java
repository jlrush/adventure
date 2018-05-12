package Commands;

import Game.Player;
import Game.Room;

public class InventoryCommand extends CommandHandler {
	public InventoryCommand() {
		super(new String[] { "inventory", "inv", "i" });
	}

	@Override
	protected void processCommand(String[] tokens, Player player, Room room) {
		System.out.println(player.listItems("You currently have a "));
	}
}
