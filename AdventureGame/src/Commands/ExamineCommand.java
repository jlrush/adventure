package Commands;

import Game.Player;
import Game.Room;
import Game.UserOutput;

public class ExamineCommand extends CommandHandler {
	public ExamineCommand() {
		super(new String[] {"exam", "examine"});
	}
	
	@Override
	protected void processCommand(UserOutput output, String[] tokens, Player player, Room room) {
		String message = "";

		if (tokens.length < 2) {
			message = "What do you want to examine, " + player.getName() + "?";
		} else {
			String target = tokens[1];
			if (target != null && !target.isEmpty() ) {
				message = room.examItem(target);
				if (message == null) {
					message = player.examItem(target);
					if (message == null) {
						output.warning("I don't see that here");
						return;
					} 
				}
			}
		}
		
		output.activity(message);
	}
}
