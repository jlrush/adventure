package Game;
import java.util.ArrayList;
import java.util.Arrays;

public class Room extends ItemHolder {
	private String name;
	private String description;
	private boolean beenHere;
	private ArrayList<Link> links;
	
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

	public void addLink(Link name) {
		this.links.add(name);
	}

	public void preamble() {
	System.out.println();
	System.out.println("Location: " + this.name);
	if (!this.getBeenHere()) {
		System.out.println("\n" + this.description + "\n");
		setBeenHere();
	}
	System.out.println(listExits());
	System.out.println(listItems("Room contains: "));
}
	public String listExits() {
		String output = "Exit(s): ";
		for (Link link : this.links) {
			output += (link.direction + ", ");
		}
		return output.substring(0, (output.length() - 2));
	}

	public Room takeExit(String direction) {
		ArrayList<String> validcmds = new ArrayList<>(Arrays.asList("north","south","east","west","northeast","northwest","southeast","southwest"));
		ArrayList<String> shortcmds = new ArrayList<>(Arrays.asList("n", "s", "e", "w", "ne", "nw", "se", "sw"));
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
	

/*	public void addItem(Item name) {
		this.items.add(name);
	}

	public void remItems(Item name) {
		this.items.remove(name);
	}

	public String listItems() {
		if (items.size() != 0) {
			boolean found = false;
			String output = "";
			for (Item item : this.items) {
				output += (item.name + ", ");
				found = true;
			}
			if (found)
				return "Room contains: " + output.substring(0, (output.length() - 2));
		}
		return "";
	}

	public boolean findItem(String name) {
		boolean found = false;
		for (Item item : this.items) {
			if (name.equalsIgnoreCase(item.name)) {
				found = true;
				break;
			}
		}
		return found;
	}

	public boolean examItem(String name) {
		boolean found = false;
		for (Item item : this.items) {
			if (name.equalsIgnoreCase(item.name)) {
				System.out.println(item.description);
				found = true;
				break;
			}
		}
		return found;
	}

	public Item getItem(String name) {
		Item hold = null;
		for (Item item : this.items) {
			if (name.equalsIgnoreCase(item.name)) {
				hold = item;
				remItems(item);
				break;
			}
		}
		return hold;
	}
*/
}
