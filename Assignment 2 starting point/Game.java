import java.util.ArrayList;

/**
 *  This class is the central class of the "World of Home" application. 
 *  "World of Home" is a very simple, text based travel game.  Users 
 *  can walk around some house. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 * @author  Michael Kölling, David J. Barnes and Olaf Chitil
 * 
 * @author Raymond Ward
 * 
 * @version 12/02/2019
 */

public class Game 
{
    private Room currentRoom;
    private Room goalRoom;
    private boolean finished;
    private int moveLimit;
    private int currentMoves;
    private ArrayList<Item> items;
    private Room cookingRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        finished = false;
        createRooms();
        moveLimit = 12;
        currentMoves = 0;
        items = new ArrayList<>();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room front, hall, kitchen, livingroom, garden, cloakroom, stairs, bathroom, 
        master, ensuite, guest;

        // create the rooms
        front = new Room("in front of the house");
        hall = new Room("in the hallway");
        kitchen = new Room("in the kitchen");
        livingroom = new Room("in the livingroom");
        garden = new Room("in the garden");
        cloakroom = new Room("in the cloakroom");
        stairs = new Room("at the top of the stairs");
        bathroom = new Room("in the bathroom");
        master = new Room("in the master bedroom");
        ensuite = new Room("in the ensuite");
        guest = new Room("in the guest bedroom");

        // initialise room exits
        front.setExit(Direction.NORTH, hall);
        hall.setExit(Direction.SOUTH, front);
        hall.setExit(Direction.UP, stairs);
        hall.setExit(Direction.WEST, cloakroom);
        hall.setExit(Direction.EAST, kitchen);
        hall.setExit(Direction.NORTH, livingroom);
        kitchen.setExit(Direction.WEST, hall);
        kitchen.setExit(Direction.NORTH, livingroom);
        cloakroom.setExit(Direction.EAST, hall);
        livingroom.setExit(Direction.SOUTH, hall);
        livingroom.setExit(Direction.EAST, kitchen);
        livingroom.setExit(Direction.NORTH, garden);
        garden.setExit(Direction.SOUTH, livingroom);
        stairs.setExit(Direction.DOWN, hall);
        stairs.setExit(Direction.EAST, bathroom);
        stairs.setExit(Direction.SOUTH, guest);
        stairs.setExit(Direction.NORTH, master);
        bathroom.setExit(Direction.WEST, stairs);
        guest.setExit(Direction.NORTH, stairs);
        master.setExit(Direction.SOUTH, stairs);
        master.setExit(Direction.EAST, ensuite);
        ensuite.setExit(Direction.WEST, master);
        
        
        stairs.addCharacter(new Character("Mother", Item.EGG));
        livingroom.addCharacter(new Character("Father", Item.SUGAR));
        kitchen.addCharacter(new Character("Son", Item.FLOUR));
        hall.addCharacter(new Character("Daughter", null));

        currentRoom = front;  // start game at the front of the house
        goalRoom = ensuite; 
        cookingRoom = kitchen;
    }

    /**
     * Return the current room.
     * Post-condition: not null.
     */
    public Room getCurrent()
    {
        assert currentRoom != null : "Current room is null.";
        return currentRoom;
    }

    /**
     * Return whether the game has finished or not.
     */
    public boolean finished()
    {
        return finished;
    }
    
    

    /**
     * Opening message for the player.
     */
    public String welcome()
    {
        return "\nWelcome to the World of Home!\n" +
        "World of Home is a new game.\n" +
        currentRoom.getLongDescription() + "\n";
    }

    // implementations of user commands:
    /**
     * Give some help information.
     */
    public String help() 
    {
        return "You are lost. You are alone. You wander around the home.\n";
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room and return its long description; otherwise return an error message.
     * 
     * Also checks if user has entered the goal room or has used up all their moves
     * 
     * @param direction The direction in which to go.
     * Pre-condition: direction is not null.
     */
    public String goRoom(Direction direction) 
    {
    	
        assert direction != null : "Game.goRoom gets null direction";

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            return "There is no exit in that direction!";
        }
        else {
            currentRoom = nextRoom;
            currentMoves++;

            
            if (currentRoom == goalRoom)
            {
            	return currentRoom.getLongDescription() +  congrat() + quit();
            }
            else if (currentMoves == moveLimit)
            {
            	return currentRoom.getLongDescription() + movesLoss() + quit();
            }
            else
            {
            	return currentRoom.getLongDescription();
            }
            
        }
    }

    /**
     * Execute quit command.
     */
    public String quit()
    {
        finished = true;
        return "Thank you for playing.  Good bye.";
    }
    
   

    /**
     * Congratulation message for the player.
     */
    public String congrat() 
    {
        return "\nCongratulations! You reached the goal.\n";
    }
    
    /**
     * Too many moves taken message for the player.
     */
    public String movesLoss() 
    {
        return "\nLost! You ran out of time.\n";
    }
    
    
    /**
     * Execute look command.
     */
    public String look()
    {
        return currentRoom.getLongDescription(); 
    }

    /**
     * Execute take command.
     * @param item The item to take.
     * Pre-condition: item is not null.
     */
    public String take(Item item)
    {
        assert item != null : "Game.take item is null";
        
    	if (currentRoom.take(item))
    	{
    		items.add(item);
    		return "Item taken.";
    	}
    	else
    	{
    		return "Item not in this room.";
    	}
    }
    
    

    /**
     * Execute cook command.
     */
    public String cook()
    {
    	
    	if (items.contains(Item.EGG) && items.contains(Item.FLOUR) && items.contains(Item.SUGAR))
    	{
    		if (currentRoom == cookingRoom)
			{
    
    			return "Congratulations! You have won.\n" + quit();
			}
    		else
    		{
    			return "You are not in the correct room to cook";
    		}

    	}
    	else
    	{
    		return "You cannot cook yet.";
    	}
    	
    	
    }
}
