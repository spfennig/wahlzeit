package org.wahlzeit.model;


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created by stefan.
 */
public class CoordinateTest {
    private final double EpsilonDistance = 10;  /* 10km */

    private static Coordinate berlinSpheric;
    private static Coordinate tokioSpheric;
    private static Coordinate berlinCartesian;
    private static Coordinate tokioCartesian;
    private static Coordinate berlinSphericSameHashCode;
    private static Coordinate berlinSpheric2;
    private static Coordinate berlinCartesian2;



    @BeforeClass
    public static void setUp() {
        /* Example provided by https://de.wikipedia.org/wiki/Orthodrome, released 07.09.2015 19:55 */
        berlinSpheric = SphericCoordinate.getCoordinate(52.517, 13.4);
        tokioSpheric = SphericCoordinate.getCoordinate(35.7, 139.767);
        berlinCartesian = CartesianCoordinate.getCoordinate(3771.373, 898.468, 5055.605);
        tokioCartesian = CartesianCoordinate.getCoordinate(-3949.792, 3341.734, 3717.741);
        berlinSphericSameHashCode = SphericCoordinate.getCoordinate(
                ((AbstractCoordinate)berlinCartesian).getLatitude(),
                ((AbstractCoordinate)berlinCartesian).getLongitude(),
                ((AbstractCoordinate)berlinCartesian).getRadius()
        );
        berlinSpheric2 = SphericCoordinate.getCoordinate(52.517, 13.4);
        berlinCartesian2 = CartesianCoordinate.getCoordinate(3771.373, 898.468, 5055.605);

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

    @Test(expected = AssertionError.class)
    public void testSphericGetDistanceArgumentNull() {
        berlinSpheric.getDistance(null);
    }

    @Test(expected = AssertionError.class)
    public void testCartesianGetDistanceArgumentNull() {
        berlinCartesian.getDistance(null);
    }



    @Test
    public void testIsEqual() {
        Assert.assertTrue(berlinSpheric.isEqual(berlinSpheric));
        Assert.assertTrue(berlinCartesian.isEqual(berlinCartesian));
        Assert.assertFalse(berlinSpheric.isEqual(tokioSpheric));
        Assert.assertFalse(berlinCartesian.isEqual(tokioCartesian));

        Assert.assertTrue(berlinSpheric.isEqual(berlinSpheric2));
        Assert.assertTrue(berlinCartesian.isEqual(berlinCartesian2));
        Assert.assertTrue(berlinCartesian.isEqual(berlinSphericSameHashCode));
    }

    @Test(expected = AssertionError.class)
    public void testIsEqualNull() {
        Assert.assertFalse(berlinSpheric.isEqual(null));
        Assert.assertFalse(berlinCartesian.isEqual(null));
    }
}
