package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



/**
 * Created by stefan on 11/8/15.
 */
public class SphericCoordinateTest {
    private SphericCoordinate testCoordinate;
    private SphericCoordinate testCoordinateCopy;
    private SphericCoordinate testCoordinateDifferent;
    private SphericCoordinate testCoordinateDefaultRadius;

    private SphericCoordinate berlin;
    private SphericCoordinate tokio;

    private CartesianCoordinate cartesianCoordinate;
    private CartesianCoordinate cartesianCoordinateEqualToBerlin;

    private final double EpsilonDouble = 1e-5;
    private final double EpsilonDistance = 10;  /* 10km */

    private final double RadiusEarth = 6371;



    /* TODO: Consider @BeforeClass */
    @Before
    public void setUp() {
        testCoordinate = new SphericCoordinate(5.5, 5.5, 5.5);
        testCoordinateCopy = new SphericCoordinate(testCoordinate);
        testCoordinateDefaultRadius = new SphericCoordinate(3.3, 3.3);
        testCoordinateDifferent = new SphericCoordinate(4.4, 4.4, 4.4);

        /*
         * Example provided by https://de.wikipedia.org/wiki/Orthodrome, released 07.09.2015 19:55
         */
        berlin = new SphericCoordinate(52.517, 13.40, RadiusEarth);
        tokio = new SphericCoordinate(35.70, 139.767, RadiusEarth);

        cartesianCoordinate = new CartesianCoordinate(1, 2, 3);
        cartesianCoordinateEqualToBerlin = new CartesianCoordinate(3771.373, 898.468, 5055.605);
    }



    @Test
    public void testConstructorLatLonRadAndGetter() {
        Assert.assertEquals(5.5, testCoordinate.getLatitude(), EpsilonDouble);
        Assert.assertEquals(5.5, testCoordinate.getLongitude(), EpsilonDouble);
        Assert.assertEquals(5.5, testCoordinate.getRadius(), EpsilonDouble);
    }

    @Test
    public void testConstructorLatLonAndGetter() {
        /* Constructor and getter */
        Assert.assertEquals(3.3, testCoordinateDefaultRadius.getLatitude(), EpsilonDouble);
        Assert.assertEquals(3.3, testCoordinateDefaultRadius.getLongitude(), EpsilonDouble);
        Assert.assertEquals(RadiusEarth, testCoordinateDefaultRadius.getRadius(), EpsilonDouble);
    }

    @Test
    public void testConstructorCopyAndGetter() {
        /* Copy constructor and getter */
        Assert.assertEquals(5.5, testCoordinateCopy.getLatitude(), EpsilonDouble);
        Assert.assertEquals(5.5, testCoordinateCopy.getLongitude(), EpsilonDouble);
        Assert.assertEquals(5.5, testCoordinateCopy.getRadius(), EpsilonDouble);
    }



    @Test
    public void testIsEqual() {
        Assert.assertTrue(testCoordinate.isEqual(testCoordinateCopy));
        Assert.assertFalse(testCoordinate.isEqual(testCoordinateDifferent));

        Assert.assertTrue(berlin.isEqual(cartesianCoordinateEqualToBerlin));
        Assert.assertFalse(berlin.isEqual(tokio));
    }



    @Test
    public void testGetDistance() {
        Assert.assertEquals(8912, berlin.getDistance(tokio), EpsilonDistance);
        Assert.assertEquals(8912, tokio.getDistance(berlin), EpsilonDistance);
        Assert.assertEquals(0, berlin.getDistance(berlin), EpsilonDistance);
        Assert.assertEquals(0, tokio.getDistance(tokio), EpsilonDistance);

        Assert.assertEquals(8912, tokio.getDistance(cartesianCoordinateEqualToBerlin), EpsilonDistance);
        Assert.assertEquals(0, berlin.getDistance(cartesianCoordinateEqualToBerlin), EpsilonDistance);
    }



    @Test(expected = NullPointerException.class)
    public void testConstructorCopyArgumentNull() {
        SphericCoordinate sphericCoordinate = new SphericCoordinate(null);
    }
    @Test(expected = NullPointerException.class)
    public void testIsEqualArgumentNull() {
        testCoordinate.isEqual(null);
    }
    @Test(expected = NullPointerException.class)
    public void testDistanceArgumentNull() {
        testCoordinate.getDistance(null);
    }



    @Test(expected = IllegalArgumentException.class)
    public void testArgumentLessBoundaries() {
        Coordinate c = new SphericCoordinate(-90.1, -180.1, RadiusEarth);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArgumentGreaterBoundaries() {
        Coordinate c = new SphericCoordinate(90.1, 180.1, RadiusEarth);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArgumentMixed1Boundaries() {
        Coordinate c = new SphericCoordinate(-90.1, 0, RadiusEarth);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArgumentMixed2Boundaries() {
        Coordinate c = new SphericCoordinate(90.1, 0, RadiusEarth);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArgumentMixed3Boundaries() {
        Coordinate c = new SphericCoordinate(0, -180.1, RadiusEarth);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArgumentMixed4Boundaries() {
        Coordinate c = new SphericCoordinate(0, 180.1, RadiusEarth);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArgumentMixed5Boundaries() {
        Coordinate cLess = new SphericCoordinate(-90.1, 180.1, RadiusEarth);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArgumentMixed6Boundaries() {
        Coordinate cLess = new SphericCoordinate(90.1, -180.1, RadiusEarth);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArgumentRadiusBoundary() {
        Coordinate cLess = new SphericCoordinate(45, 90, -0.1);
    }
}
