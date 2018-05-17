package Commands;

import Game.Player;
import Game.Room;
import Game.UserOutput;

public class LookCommand extends CommandHandler {
	public LookCommand() {
		super(new String[] { "look", "l" });
	}

	@Override
	protected void processCommand(UserOutput output, String[] tokens, Player player, Room room) {
		room.setNotBeenHere();
	}
}
