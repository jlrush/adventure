package Commands;

import Game.Player;
import Game.Room;

public class LookCommand extends CommandHandler {
	public LookCommand() {
		super(new String[] { "look", "l" });
	}

	@Override
	protected void processCommand(String[] tokens, Player player, Room room) {
		System.out.println("\n" + room.getDescription());
	}
}
