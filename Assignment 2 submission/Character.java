
/**
 * Enumeration class Character
 * A character in the game.
 * 
 * @author Olaf Chitil
 * @author Raymond Ward
 * 
 * @version 12/02/2019
 */
public class Character
{
	
	private String description;
	private Item item;
	
	
	
    /**
     * Constructor initialising description and item.
     */
    public Character(String desc, Item it)
    {
        this.description = desc;
        this.item = it;
    }

    /**
     * Return the description and description of item if it exists.
     */
    public String toString()
    {
    	if (item != null)
    	{
    		return description + " having the item " +  item.toString(); 
    	}
    	else
    	{
    		return description;
    	}
        
    }

    /**
     * Take the given item from the character if it has that item.
     * Return whether item was taken.
     */
    public boolean take(Item it)
    {
    	if (item != null && item.toString().equalsIgnoreCase(it.toString()))
    	{
    		item = null;
    		return true;
    	}
    	else
    	{
    		return false; 
    	}
    	
        
    }
}
