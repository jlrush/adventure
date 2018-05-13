package Commands;

import Game.Player;
import Game.Room;

public class LookCommand extends CommandHandler {
	public LookCommand() {
		super(new String[] { "look", "l" });
	}

	@Override
	protected String processCommand(String[] tokens, Player player, Room room) {
		String Yellow = "\033[33m";
		String White = "\033[0m";
		return(Yellow + room.getDescription() + White);
	}
}
