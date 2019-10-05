
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CharacterTest.
 *
 * @author  Olaf Chitil
 * @version 5/2/2019
 */
public class CharacterTest
{
    private Character alex;
    private Character sian;

    /**
     * Default constructor for test class CharacterTest
     */
    public CharacterTest()
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
        alex = new Character("Alex", Item.FLOUR);
        sian = new Character("Sian", null);
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
     * Check descriptions.
     */
    @Test
    public void descriptions()
    {
        assertEquals("Alex having the item flour", alex.toString());
        assertEquals("Sian", sian.toString());
    }

    /**
     * Check taking an item from Alex.
     */
    @Test
    public void takeFromAlex()
    {
        assertEquals(true, alex.take(Item.FLOUR));
        assertEquals(false, alex.take(Item.FLOUR));
    }

    /**
     * Check taking an item from Sian.
     */
    @Test
    public void takeFromSian()
    {
        assertEquals(false, sian.take(Item.EGG));
    }
}
