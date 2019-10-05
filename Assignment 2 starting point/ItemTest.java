
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ItemTest.
 *
 * @author  Olaf Chitil
 * @version 5/2/2019
 */
public class ItemTest
{
    /**
     * Default constructor for test class ItemTest
     */
    public ItemTest()
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
     * Check that each item yields the expected description.
     */
    @Test
    public void descriptions()
    {
        assertEquals("flour", Item.FLOUR.toString());
        assertEquals("sugar", Item.SUGAR.toString());
        assertEquals("egg", Item.EGG.toString());
    }
}
