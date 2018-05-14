package Game;
import java.util.ArrayList;
import java.util.Arrays;

public class Room extends ItemHolder {
	private String name;
	private String description;
	private boolean beenHere;
	private ArrayList<Link> links;
	private static final ArrayList<String> validcmds = new ArrayList<>(Arrays.asList("north", "south", "east", "west",
			"northeast", "northwest", "southeast", "southwest", "up", "down"));
	private static final ArrayList<String> shortcmds = new ArrayList<>(
			Arrays.asList("n", "s", "e", "w", "ne", "nw", "se", "sw", "u", "d"));

	public Room(String name, String description) {
		this.name = name;
		this.description = description;
		this.beenHere = false;
		ArrayList<Link> links = new ArrayList<Link>();
		this.links = links;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean getBeenHere() {
		return this.beenHere;
	}

	public void setBeenHere() {
		this.beenHere = true;
	}

	public void setNotBeenHere() {
		this.beenHere = false;
	}

	public void addLink(Link name) {
		this.links.add(name);
	}

	public String preamble() {
		String temp = "";
		String temp2 = "";
		if (!this.getBeenHere()) {
			temp += ("\r\n\033[33m" + this.description + "\r\n\033[0m");
			setBeenHere();
		}
		temp += ("\r\nLocation: " + this.name + "\r\n");
		temp += listExits();
		temp2 = listItems("Room contains: ");
		if (temp2.substring(0, 4).equals("Room"))
			temp += ("\r\n" + temp2);
		return temp;
	}

	public String listExits() {
		String output = "Exit(s): ";
		for (Link link : this.links) {
			output += (link.direction + ", ");
		}
		return output.substring(0, (output.length() - 2));
	}

	public Room takeExit(String direction) {
		Room answer = null;
		for (int i = 0; i < shortcmds.size(); i++) {
			if (shortcmds.get(i).equals(direction)) {
				direction = validcmds.get(i);
				break;
			}
		}
		for (Link link : this.links) {
			answer = link.source;
			if (direction.equals(link.direction))
				return link.destination;
		}
		if (validcmds.contains(direction))
			return answer;
		else
			return null;
	}

}
