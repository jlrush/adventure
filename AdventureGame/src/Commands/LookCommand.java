package Commands;

import Game.Player;
import Game.Room;

public class LookCommand extends CommandHandler {
	public LookCommand() {
		super(new String[] { "look", "l" });
	}

	@Override
	protected String processCommand(String[] tokens, Player player, Room room) {
		return("\r\n" + room.getDescription());
	}
}
