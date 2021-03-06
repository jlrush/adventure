package Game;

public class Setup {

	public static Room Initialize() {		
		Room Master = new Room("Master Bedroom","You are in a fairly large room with a queen-size bed");
		Room Hallway = new Room("South end of Hallway","You are in a hallway with a number of doors");
		Room Closet = new Room("Closet","This is the closet in the Master Bedroom");
		Master.addLink(new Link(Master,Closet,"east"));
		Master.addItem(new Item("Pillow","It's so soft and light it almost floats away"));
		Closet.addLink(new Link(Closet,Master,"west"));
		Closet.addItem(new Item("Coat","A coat with lots of pockets in your size"));
		Master.addLink(new Link(Master,Hallway,"north"));
		Hallway.addLink(new Link(Hallway,Master,"south"));
		Room Bathroom = new Room("Bathroom","Skip, skip, skip to my loo");
		Bathroom.addLink(new Link(Bathroom,Hallway,"west"));
		Hallway.addLink(new Link(Hallway,Bathroom,"east"));
		Room HallEnd = new Room("North end of Hallway","You are in a hallway with a number of doors");
		Hallway.addLink(new Link(Hallway,HallEnd,"north"));
		HallEnd.addLink(new Link(HallEnd,Hallway,"south"));
		Room Bedroom2 = new Room("Bedroom 2","This is a girl's bedroom\r\nThere is a small canopy bed beneath a pile of stuffed animals");
		Bedroom2.addLink(new Link(Bedroom2,Hallway,"east"));
		Hallway.addLink(new Link(Hallway,Bedroom2,"west"));
		Room Bedroom3 = new Room("Bedroom 3","This is a teenage boy's bedroom\r\nThere is a twin-size water bed against one wall");
		Bedroom3.addLink(new Link(Bedroom3,HallEnd,"east"));
		HallEnd.addLink(new Link(HallEnd,Bedroom3,"west"));
		Room Living = new Room("Living Room","There's such a lot of livin' to do!");
		HallEnd.addLink(new Link(HallEnd,Living,"east"));
		Living.addLink(new Link(Living,HallEnd,"west"));
		Room Porch = new Room("Front Porch","You're standing outside the house.\r\nThere's not much to see here.\r\nYou should probably just go inside.");
		Living.addLink(new Link(Living,Porch,"north"));
		Porch.addLink(new Link(Porch,Living,"south"));
		
		return(Master);
	}

}
