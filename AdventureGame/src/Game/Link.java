package Game;
public class Link {
	Room source;
	Room destination;
	String direction;

	public Link(Room source, Room destination, String direction) {
		this.source = source;
		this.destination = destination;
		this.direction = direction;
	}

}
