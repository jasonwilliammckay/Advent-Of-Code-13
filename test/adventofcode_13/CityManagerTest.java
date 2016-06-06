package adventofcode_13;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CityManagerTest 
{   
    String city1 = "Winnipeg";
    String city2 = "Toronto";
    Integer distance = 2235;

    public CityManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        CityManager.reset();
    }

    /**
     * Test of getNumCities method, of class City.
     */
    @Test
    public void testGetNumCities() {
        System.out.println("getNumCities");
        assertEquals(0, CityManager.getNumCities());
        CityManager.processCities(city1, city2, distance);
        assertEquals(2, CityManager.getNumCities());
        CityManager.processCities(city1, city2, distance);
        assertEquals(2, CityManager.getNumCities());
        CityManager.processCities("SamePlace", "SamePlace", distance);
        assertEquals(2, CityManager.getNumCities());
        CityManager.processCities("ToNowhere", "", distance);
        assertEquals(2, CityManager.getNumCities());
    }

    /**
     * Test of getName method, of class City.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        CityManager.processCities(city1, city2, distance);        
        City examine;
        
        // check by string reference, and city id reference
        examine = CityManager.getCitiesByName().get(city1);
        assertEquals("Winnipeg", examine.getName());
        examine = CityManager.getCitiesByID().get(0);
        assertEquals("Winnipeg", examine.getName());
        
        // repeat for second city
        examine = CityManager.getCitiesByName().get(city2);
        assertEquals("Toronto", examine.getName());        
        examine = CityManager.getCitiesByID().get(1);
        assertEquals("Toronto", examine.getName());
    }

    /**
     * Test of getID method, of class City.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        CityManager.processCities(city1, city2, distance);        
        
        // ensure both newly added cities get their own unique ID
        City examine = CityManager.getCitiesByID().get(0);
        assertEquals(0, examine.getID());
        examine = CityManager.getCitiesByID().get(1);
        assertEquals(1, examine.getID());
    }

    /**
     * Test of processCities method, of class City.
     */
    @Test
    public void testProcessCities() {
        System.out.println("processCities");

        CityManager.processCities(city1, city2, distance);    
        
        // check both hashes against their City objects
        City examine = CityManager.getCitiesByName().get("Winnipeg");
        assertEquals("Winnipeg", examine.getName());
        assertEquals(0, examine.getID());

        // check both hashes against their City objects for the second input
        examine = CityManager.getCitiesByName().get("Toronto");
        assertEquals("Toronto", examine.getName());
        assertEquals(1, examine.getID());

        // check distances
        assertEquals(2235, CityManager.getDistance(0, 1));
    }

    /**
     * Test of getDistance method, of class City.
     */
    @Test
    public void testGetDistance_3args() {
        System.out.println("getDistance");

        CityManager.processCities(city1, city2, 2000);
        CityManager.processCities(city2, city1, -50);
        
        // from somewhere to itself should be a distance of zero
        assertEquals(0, CityManager.getDistance(0, 0));
        
        // from a valid city to another valid city
        assertEquals(2000, CityManager.getDistance(0, 1));

        // from a valid city to another valid city
        assertEquals(-50, CityManager.getDistance(1, 0));
    }
}