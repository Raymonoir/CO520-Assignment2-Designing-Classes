import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class GameTest.
 *
 * @author  Olaf Chitil
 * @version 5/2/2019
 */
public class GameTest
{
    private Game game;

    /**
     * Default constructor for test class GameTest
     */
    public GameTest()
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
        game = new Game();
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
     * Check that at start not yet finished.
     */
    @Test
    public void notFinished()
    {
        assertEquals(false, game.finished());
    }

    /**
     * Check that finished after quit.
     */
    @Test
    public void quitFinishes()
    {
        assertEquals("Thank you for playing.  Good bye.",game.quit());
        assertEquals(true, game.finished());
    }

    /**
     * Check looking at start.
     */
    @Test
    public void lookAtStart()
    {
        assertEquals(game.getCurrent().getLongDescription(), game.look());
    }

    /**
     * Go in wrong direction.
     */
    @Test
    public void goWrong()
    {
        assertEquals("There is no exit in that direction!",
            game.goRoom(Direction.WEST));
    }

    /**
     * Go to goal.
     */
    @Test
    public void goGoal()
    {
        String ig1 = game.goRoom(Direction.NORTH);
        String ig2 = game.goRoom(Direction.UP);
        String ig3 = game.goRoom(Direction.NORTH);
        String result = game.goRoom(Direction.EAST);
        assertEquals(game.getCurrent().getLongDescription() + 
            "\nCongratulations! You reached the goal.\nThank you for playing.  Good bye.",
            result);
    }

    /**
     * Run out of time.
     */
    @Test
    public void timeout()
    {
        String ig1 = game.goRoom(Direction.NORTH);
        String ig2 = game.goRoom(Direction.SOUTH);
        String ig3 = game.goRoom(Direction.NORTH);
        String ig4 = game.goRoom(Direction.SOUTH);
        String ig5 = game.goRoom(Direction.NORTH);
        String ig6 = game.goRoom(Direction.SOUTH);
        String ig7 = game.goRoom(Direction.NORTH);
        String ig8 = game.goRoom(Direction.SOUTH);
        String ig9 = game.goRoom(Direction.NORTH);
        String ig10 = game.goRoom(Direction.SOUTH);
        String ig11 = game.goRoom(Direction.NORTH);
        String result = game.goRoom(Direction.SOUTH);
        assertEquals(game.getCurrent().getLongDescription() + 
            "\nLost! You ran out of time.\nThank you for playing.  Good bye.", 
            result);
    }

    /**
     * Check looking after a step.
     */
    @Test
    public void lookAfterStep()
    {
        String ignore = game.goRoom(Direction.NORTH); 
        assertEquals(game.getCurrent().getLongDescription(), game.look());
    }

    /**
     * Fail taking an item.
     */
    @Test
    public void takeFail()
    {
        assertEquals("Item not in this room.", game.take(Item.EGG));
    }

    /**
     * Succeed taking an item.
     */
    @Test
    public void takeSucceed()
    {
        game.getCurrent().addCharacter(new Character("Palani", Item.FLOUR));
        assertEquals("Item taken.", game.take(Item.FLOUR));
    }

    /**
     * Fail cooking.
     */
    @Test
    public void cookFail()
    {
        assertEquals("You cannot cook yet.", game.cook());
    }

    /**
     * Succeed cooking.
     */
    @Test
    public void cookSucceed()
    {
        Room here = game.getCurrent();
        here.addCharacter(new Character("Laura", Item.FLOUR));
        here.addCharacter(new Character("Andy", Item.EGG));
        here.addCharacter(new Character("Rogerio", Item.SUGAR));
        assertEquals("Item taken.", game.take(Item.FLOUR));
        assertEquals("Item taken.", game.take(Item.EGG));
        assertEquals("Item taken.", game.take(Item.SUGAR));
        game.goRoom(Direction.NORTH);
        game.goRoom(Direction.EAST);
      
        
        assertEquals("Congratulations! You have won.\nThank you for playing.  Good bye.", 
            game.cook());
    }
}
