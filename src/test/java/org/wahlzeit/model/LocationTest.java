
package org.wahlzeit.model;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;



public class LocationTest {
    private Location testLocation;
    
    private final double Epsilon = 1e-5;
    
    
    
    @Before
    public void setUp() {
        testLocation = new Location("MyLocation", new Coordinate(5.5, 5.5));
    }
    
    
    
    @Test
    public void testGetter() {
        Assert.assertEquals("MyLocation", testLocation.getName());
        Assert.assertEquals(5.5, testLocation.getCoordinate().getLatitude(), Epsilon);
        Assert.assertEquals(5.5, testLocation.getCoordinate().getLongitude(), Epsilon);
    }
}
