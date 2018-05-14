package Game;

public class Setup {

	public static Room Initialize() {		
		Room Master = new Room("Master Bedroom","You are in a fairly large room with a queen-size bed");
		Room Hallway = new Room("South end of Hallway","You are in a hallway with a number of doors");
		Room Closet = new Room("Closet","This is the closet in the Master Bedroom.\r\nThere's nobody living in here at the moment");
		Master.addLink(new Link(Master,Closet,"east"));
		Master.addItem(new Item("Pillow","It's so soft and light it almost floats away"));
		Closet.addLink(new Link(Closet,Master,"west"));
		Closet.addItem(new Item("Coat","The famous 'Coat of many pockets'"));
		Master.addLink(new Link(Master,Hallway,"north"));
		Hallway.addLink(new Link(Hallway,Master,"south"));
		Room Bathroom = new Room("Bathroom","Skip, skip, skip to my loo");
		Bathroom.addLink(new Link(Bathroom,Hallway,"west"));
		Hallway.addLink(new Link(Hallway,Bathroom,"east"));
		Room HallEnd = new Room("North end of Hallway","You are in a hallway with a number of doors");
		Hallway.addLink(new Link(Hallway,HallEnd,"north"));
		HallEnd.addLink(new Link(HallEnd,Hallway,"south"));
		Room Bedroom2 = new Room("Bedroom 2","This is a girl's room\r\nThere is a small canopy bed beneath a pile of stuffed animals");
		Bedroom2.addLink(new Link(Bedroom2,Hallway,"east"));
		Hallway.addLink(new Link(Hallway,Bedroom2,"west"));
		Room Bedroom3 = new Room("Bedroom 3","This is a teenage boy's bedroom\r\nThere is a twin-size water bed against one wall");
		Bedroom3.addLink(new Link(Bedroom3,HallEnd,"east"));
		HallEnd.addLink(new Link(HallEnd,Bedroom3,"west"));
		
		
		
		return(Master);
	}

}
