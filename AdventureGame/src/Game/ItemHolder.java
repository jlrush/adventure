package Game;
import java.util.ArrayList;

public class ItemHolder {

	protected ArrayList<Item> item;

	
	public ItemHolder() {
	ArrayList<Item> item = new ArrayList<Item>();
	this.item = item;
	}

	public void addItem(Item name) {
		this.item.add(name);
	}
	
	public void remItem(Item name) {
		this.item.remove(name);
	}

	public String listItems(String prefix) {
		if (item.size() != 0) {
			boolean found = false;
			String output = "";
			for (Item item : this.item) {
				output += (item.name + ", ");
				found = true;
			}
			if (found)
				return "" + prefix + output.substring(0, (output.length() - 2));
		}
		return "";
	}

	public boolean findItem(String name) {
		boolean found = false;
		for (Item item : this.item) {
			if (name.equalsIgnoreCase(item.name)) {
				found = true;
				break;
			}
		}
		return found;
	}

	public boolean examItem(String name) {
		boolean found = false;
		for (Item item : this.item) {
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
