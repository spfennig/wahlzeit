package org.wahlzeit.model;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;



public class LocationTest {
    private Location berlinSpheric;
    private Location berlinCartesian;

    private Coordinate berlinSphericCoordinate;
    private Coordinate berlinCartesianCoordinate;

    

    /* TODO: Consider @BeforeClass */
    @Before
    public void setUp() {
        berlinSphericCoordinate = new SphericCoordinate(52.517, 13.40);
        berlinCartesianCoordinate = new CartesianCoordinate(3771.373, 898.468, 5055.605);

        berlinSpheric = new Location("Berlin", berlinSphericCoordinate);
        berlinCartesian = new Location("Berlin", berlinCartesianCoordinate);
    }
    
    
    
    @Test
    public void testConstructorAndGetterSpheric() {
        Assert.assertEquals("Berlin", berlinSpheric.getName());
        Assert.assertEquals(berlinSphericCoordinate, berlinSpheric.getCoordinate());
    }

    @Test
    public void testConstructorAndGetterCartesian() {
        Assert.assertEquals("Berlin", berlinCartesian.getName());
        Assert.assertEquals(berlinCartesianCoordinate, berlinCartesian.getCoordinate());
    }
}
