package org.wahlzeit.model;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by stefan on 11/8/15.
 */
public class SphericCoordinateTest {
    private final double EpsilonDouble = 1e-5;
    private final double EpsilonDistance = 10;  /* 10km */

    public static SphericCoordinate berlinSpheric;
    public static SphericCoordinate berlinSphericCopy;
    public static SphericCoordinate tokioSpheric;
    public static CartesianCoordinate berlinCartesian;



    @BeforeClass
    public static void setUp() {
        /* Example provided by https://de.wikipedia.org/wiki/Orthodrome, released 07.09.2015 19:55 */
        /* LatLon constructor */
        berlinSpheric = new SphericCoordinate(52.517, 13.4);
        /* Copy constructor */
        berlinSphericCopy = new SphericCoordinate(berlinSpheric);
        /* LatLonRad constructor */
        tokioSpheric = new SphericCoordinate(35.70, 139.767, 6371);
        berlinCartesian = new CartesianCoordinate(3771.373, 898.468, 5055.605);
    }



    @Test
    public void testConstructorLatLonRadAndGetter() {
        Assert.assertEquals(35.7, tokioSpheric.getLatitude(), EpsilonDouble);
        Assert.assertEquals(139.767, tokioSpheric.getLongitude(), EpsilonDouble);
        Assert.assertEquals(6371, tokioSpheric.getRadius(), EpsilonDouble);
        Assert.assertEquals(5000, new SphericCoordinate(1, 2, 5000).getRadius(), EpsilonDouble);
    }

    @Test
    public void testConstructorLatLonAndGetter() {
        /* Constructor and getter */
        Assert.assertEquals(52.517, berlinSpheric.getLatitude(), EpsilonDouble);
        Assert.assertEquals(13.4, berlinSpheric.getLongitude(), EpsilonDouble);
        Assert.assertEquals(6371, berlinSpheric.getRadius(), EpsilonDouble);
    }

    @Test
    public void testConstructorCopyAndGetter() {
        /* Copy constructor and getter */
        Assert.assertEquals(52.517, berlinSphericCopy.getLatitude(), EpsilonDouble);
        Assert.assertEquals(13.4, berlinSphericCopy.getLongitude(), EpsilonDouble);
        Assert.assertEquals(6371, berlinSphericCopy.getRadius(), EpsilonDouble);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorCopyArgumentNull() {
        SphericCoordinate sphericCoordinate = new SphericCoordinate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorArgumentLessBoundaries() {
        new SphericCoordinate(-90.1, -180.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorArgumentGreaterBoundaries() {
        new SphericCoordinate(90.1, 180.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorArgumentMixed1Boundaries() {
        new SphericCoordinate(-90.1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorArgumentMixed2Boundaries() {
        new SphericCoordinate(90.1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorArgumentMixed3Boundaries() {
        new SphericCoordinate(0, -180.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorArgumentMixed4Boundaries() {
        new SphericCoordinate(0, 180.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorArgumentMixed5Boundaries() {
        new SphericCoordinate(-90.1, 180.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorArgumentMixed6Boundaries() {
        new SphericCoordinate(90.1, -180.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorArgumentRadiusBoundary() {
        new SphericCoordinate(45, 90, -0.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorArgumentLatitudeNaN() {
        new SphericCoordinate(Double.NaN, 45, 6371);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorArgumentLongitudeNaN() {
        new SphericCoordinate(45, Double.NaN, 6371);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorArgumentRadiusNaN() {
        new SphericCoordinate(45, 45, Double.NaN);
    }



    @Test
    public void testEquals() {
        Assert.assertTrue(berlinSpheric.equals(berlinSphericCopy));
        Assert.assertFalse(berlinSpheric.equals(tokioSpheric));
        Assert.assertFalse(berlinSpheric.equals(null));
    }



    @Test
    public void testGetDistanceSameType() {
        Assert.assertEquals(8912, berlinSpheric.getDistance(tokioSpheric), EpsilonDistance);
        Assert.assertEquals(8912, tokioSpheric.getDistance(berlinSpheric), EpsilonDistance);
        Assert.assertEquals(0, berlinSpheric.getDistance(berlinSpheric), EpsilonDistance);
    }

    @Test
    public void testGetDistanceDifferentType() {
        Assert.assertEquals(8912, tokioSpheric.getDistance(berlinCartesian), EpsilonDistance);
        Assert.assertEquals(0, berlinSpheric.getDistance(berlinCartesian), EpsilonDistance);
    }

    @Test(expected = NullPointerException.class)
    public void testDistanceArgumentNull() {
        berlinSpheric.getDistance(null);
    }
}
