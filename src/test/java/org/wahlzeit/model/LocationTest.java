package org.wahlzeit.model;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;



public class LocationTest {
    private static Location berlinSpheric;
    private static Location berlinCartesian;

    private static Coordinate berlinSphericCoordinate;
    private static Coordinate berlinCartesianCoordinate;

    

    @BeforeClass
    public static void setUp() {
        berlinSphericCoordinate = new SphericCoordinate(52.517, 13.40, 6371);
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
