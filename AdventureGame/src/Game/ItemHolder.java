package Game;

import java.util.ArrayList;

public class ItemHolder {

	protected ArrayList<Item> item;

	public ItemHolder() {
		ArrayList<Item> temp = new ArrayList<Item>();
		this.item = temp;
	}

	public void addItem(Item name) {
		this.item.add(name);
	}

	public void remItem(Item name) {
		this.item.remove(name);
	}

	public String listItems(String prefix) {
		if (item.size() > 0) {
			String output = prefix;
			for (Item item : this.item) {
				output += (item.name + ", ");
			}
			return output.substring(0, (output.length() - 2));
		} else {
			if (prefix == "")
				return "";
			else
				return "You don't have anything";
		}
	}

	public boolean findItem(String name) {
		boolean found = false;
		if (item.size() > 0) {
			for (Item item : this.item) {
				if (name.equalsIgnoreCase(item.name)) {
					found = true;
					break;
				}
			}
		}
		return found;
	}

	public String examItem(String name) {
		for (Item item : this.item) {
			if (name.equalsIgnoreCase(item.name)) {
				return item.description;
			}
		}
		return null;
	}

	public Item getItem(String name) {
		Item hold = null;
		for (Item item : this.item) {
			if (name.equalsIgnoreCase(item.name)) {
				hold = item;
				remItem(item);
				break;
			}
		}
		return hold;
	}

}
