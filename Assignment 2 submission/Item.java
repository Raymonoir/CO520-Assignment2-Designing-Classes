
/**
 * Enumeration class Item
 * The items in the game.
 * 
 * @author Olaf Chitil
 * @author Raymond Ward
 * 
 * @version 12/02/2019
 * 
 **/
public enum Item
{
    FLOUR("flour"), 

    SUGAR("sugar"), 

    EGG("egg");

	private String name;
	
	
	private Item (String name)
	{
		this.name = name;
	}
	
	
    /**
     * Return the description of the item.
     */
    public String toString()
    {
        return name;
    }
}
