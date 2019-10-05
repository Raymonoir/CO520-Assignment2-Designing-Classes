import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RoomTest.
 *
 * @author  Olaf Chitil
 * @version 5/2/2019
 */
public class RoomTest
{
    private Room maze;

    /**
     * Default constructor for test class RoomTest
     */
    public RoomTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        maze = new Room("in the maze");
        maze.setExit(Direction.EAST, maze);
        maze.setExit(Direction.WEST, maze);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    /**
     * Check short description.
     */
    @Test
    public void shortDescription()
    {
        assertEquals("in the maze", maze.getShortDescription());
    }

    /**
     * Check long description.
     */
    @Test
    public void longDescription()
    {
        assertEquals("You are in the maze.\nExits: west east", maze.getLongDescription());
    }

    /** 
     * Check existing exit.
     */
    @Test
    public void exitExists()
    {
        assertEquals(maze, maze.getExit(Direction.WEST)); 
    }

    /**
     * Check non-existing exit.
     */
    @Test
    public void exitNonExists()
    {
        assertEquals(null, maze.getExit(Direction.NORTH));
    }

    /**
     * Check long description with a character.
     */
    @Test
    public void longDescriptionChar()
    {
        maze.addCharacter(new Character("Tom", null));
        assertEquals("You are in the maze.\nExits: west east\nCharacters: Tom; ",
            maze.getLongDescription());
    }

    /**
     * Check long description with two characters.
     */
    @Test
    public void longDescriptionChar2()
    {
        maze.addCharacter(new Character("Jason", null));
        maze.addCharacter(new Character("David", Item.EGG));
        assertEquals("You are in the maze.\nExits: west east\nCharacters: Jason; David having the item egg; ",
            maze.getLongDescription());
    }

    /** 
     * Check that taking existing item from a character works.
     */
    @Test
    public void takeItem()
    {
        maze.addCharacter(new Character("David", Item.EGG));
        assertEquals(true, maze.take(Item.EGG));
    }
}