package Commands;

import Game.Player;
import Game.Room;

public class ExamineCommand extends CommandHandler {
	public ExamineCommand() {
		super(new String[] {"exam", "examine"});
	}
	
	@Override
	protected void processCommand(String[] tokens, Player player, Room room) {
		String temp = ""
		if (tokens.length < 2) {
			temp = "What do you want to examine?";
			return;
		}
		
		String target = tokens[1];
		if (target != null && !target.isEmpty() ) {
			if (!room.examItem(target)) {
				if (!player.examItem(target)) {
					temp = "I don't see that here";
				}
			}
		}
		return = temp;
	}
}
