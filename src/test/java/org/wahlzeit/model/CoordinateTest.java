package org.wahlzeit.model;


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created by stefan.
 */
public class CoordinateTest {
    private final double EpsilonDistance = 10;  /* 10km */

    public static Coordinate berlinSpheric;
    public static Coordinate tokioSpheric;
    public static Coordinate berlinCartesian;
    public static Coordinate tokioCartesian;



    @BeforeClass
    public static void setUp() {
        /* Example provided by https://de.wikipedia.org/wiki/Orthodrome, released 07.09.2015 19:55 */
        berlinSpheric = new SphericCoordinate(52.517, 13.4);
        tokioSpheric = new SphericCoordinate(35.70, 139.767, 6371);
        berlinCartesian = new CartesianCoordinate(3771.373, 898.468, 5055.605);
        tokioCartesian = new CartesianCoordinate(-3949.792, 3341.734, 3717.741);
    }



    @Test
    public void testGetDistance() {
        Assert.assertEquals(8912, berlinSpheric.getDistance(tokioSpheric), EpsilonDistance);
        Assert.assertEquals(8912, tokioSpheric.getDistance(berlinSpheric), EpsilonDistance);
        Assert.assertEquals(8912, tokioSpheric.getDistance(berlinCartesian), EpsilonDistance);
        Assert.assertEquals(8912, berlinCartesian.getDistance(tokioSpheric), EpsilonDistance);
        Assert.assertEquals(0, berlinSpheric.getDistance(berlinSpheric), EpsilonDistance);
        Assert.assertEquals(0, berlinCartesian.getDistance(berlinCartesian), EpsilonDistance);
        Assert.assertEquals(0, berlinSpheric.getDistance(berlinCartesian), EpsilonDistance);
        Assert.assertEquals(0, berlinCartesian.getDistance(berlinSpheric), EpsilonDistance);
    }

    @Test(expected = NullPointerException.class)
    public void testSphericGetDistanceArgumentNull() {
        berlinSpheric.getDistance(null);
    }

    @Test(expected = NullPointerException.class)
    public void testCartesianGetDistanceArgumentNull() {
        berlinCartesian.getDistance(null);
    }



    @Test
    public void testIsEqual() {
        Assert.assertTrue(berlinSpheric.isEqual(berlinSpheric));
        Assert.assertTrue(berlinSpheric.isEqual(berlinCartesian));
        Assert.assertTrue(berlinCartesian.isEqual(berlinSpheric));
        Assert.assertTrue(berlinCartesian.isEqual(berlinCartesian));
        Assert.assertFalse(berlinSpheric.isEqual(tokioSpheric));
        Assert.assertFalse(berlinSpheric.isEqual(tokioCartesian));
        Assert.assertFalse(berlinCartesian.isEqual(tokioSpheric));
        Assert.assertFalse(berlinCartesian.isEqual(tokioCartesian));
        Assert.assertFalse(berlinSpheric.isEqual(null));
        Assert.assertFalse(berlinCartesian.isEqual(null));
    }
}
