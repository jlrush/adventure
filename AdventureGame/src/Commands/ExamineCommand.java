package Commands;

import Game.Player;
import Game.Room;

public class ExamineCommand extends CommandHandler {
	public ExamineCommand() {
		super(new String[] {"exam", "examine"});
	}
	
	@Override
	protected String processCommand(String[] tokens, Player player, Room room) {
		String temp = "";
		if (tokens.length < 2) {
			temp = "What do you want to examine?";
			return temp;
		}
		
		String target = tokens[1];
		if (target != null && !target.isEmpty() ) {
			temp = room.examItem(target);
			if (temp == null) {
				temp = player.examItem(target);
				if (temp == null) {
					temp = "I don't see that here";
				} 
			}
		}
		return temp;
	}
}
