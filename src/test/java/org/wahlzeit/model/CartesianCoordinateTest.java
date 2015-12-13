package org.wahlzeit.model;


import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by stefan on 11/8/15.
 */
public class CartesianCoordinateTest {
    private final double EpsilonDouble = 1e-5;

    private static CartesianCoordinate berlinCartesian;
    private static CartesianCoordinate berlinCartesianCopy;
    private static CartesianCoordinate tokioCartesian;
    private static SphericCoordinate berlinSpheric;



    @BeforeClass
    public static void setUp() {
        /* Example provided by https://de.wikipedia.org/wiki/Orthodrome, released 07.09.2015 19:55 */
        /* XYZ constructor */
        berlinCartesian = new CartesianCoordinate(3771.373, 898.468, 5055.605);
        /* Copy constructor */
        berlinCartesianCopy = new CartesianCoordinate(3771.373, 898.468, 5055.605);
        tokioCartesian = new CartesianCoordinate(-3949.792, 3341.734, 3717.741);
        berlinSpheric = new SphericCoordinate(52.517, 13.4, 6371);
    }



    @Test
    public void testConstructorXYZAndGetter() {
        Assert.assertEquals(3771.373, berlinCartesian.getX(), EpsilonDouble);
        Assert.assertEquals(898.468, berlinCartesian.getY(), EpsilonDouble);
        Assert.assertEquals(5055.605, berlinCartesian.getZ(), EpsilonDouble);

        Assert.assertEquals(52.517, berlinCartesian.getLatitude(), EpsilonDouble);
        Assert.assertEquals(13.4, berlinCartesian.getLongitude(), EpsilonDouble);
        Assert.assertEquals(6371, berlinCartesian.getRadius(), EpsilonDouble);
    }

    @Test(expected = AssertionError.class)
    public void testConstructorArgumentXNaN() {
        new CartesianCoordinate(Double.NaN, 2, 3);
    }

    @Test(expected = AssertionError.class)
    public void testConstructorArgumentYNaN() {
        new CartesianCoordinate(1, Double.NaN, 3);
    }

    @Test(expected = AssertionError.class)
    public void testConstructorArgumentZNaN() {
        new CartesianCoordinate(1, 2, Double.NaN);
    }



    @Test(expected = AssertionError.class)
    public void testEqualsNull() {
        Assert.assertFalse(berlinCartesian.equals(null));
    }
}
