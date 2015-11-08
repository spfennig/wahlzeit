package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



/**
 * Created by stefan on 11/8/15.
 */
public class CartesianCoordinateTest {
    private CartesianCoordinate testCoordinate;
    private CartesianCoordinate testCoordinateCopy;
    private CartesianCoordinate testCoordinateDifferent;

    private CartesianCoordinate berlin;
    private CartesianCoordinate tokio;

    private SphericCoordinate sphericCoordinate;
    private SphericCoordinate sphericCoordinateEqualToBerlin;

    private final double EpsilonDouble = 1e-5;
    private final double EpsilonDistance = 10;    /* 10km */



    /* TODO: Consider @BeforeClass */
    @Before
    public void setUp() {
        testCoordinate = new CartesianCoordinate(5.5, 5.5, 5.5);
        testCoordinateCopy = new CartesianCoordinate(testCoordinate);
        testCoordinateDifferent = new CartesianCoordinate(4.4, 4.4, 4.4);

        /*
         * Example provided by https://de.wikipedia.org/wiki/Orthodrome, released 07.09.2015 19:55
         */
        berlin = new CartesianCoordinate(3771.373, 898.468, 5055.605);
        tokio = new CartesianCoordinate(-3949.792, 3341.734, 3717.741);

        sphericCoordinate = new SphericCoordinate(1.1, 2.2);
        sphericCoordinateEqualToBerlin = new SphericCoordinate(52.517, 13.40);
    }



    @Test
    public void testConstructorXYZAndGetter() {
        Assert.assertEquals(5.5, testCoordinate.getX(), EpsilonDouble);
        Assert.assertEquals(5.5, testCoordinate.getY(), EpsilonDouble);
        Assert.assertEquals(5.5, testCoordinate.getZ(), EpsilonDouble);
    }

    @Test
    public void testConstructorCopyAndGetter() {
        /* Copy constructor and getter */
        Assert.assertEquals(5.5, testCoordinateCopy.getX(), EpsilonDouble);
        Assert.assertEquals(5.5, testCoordinateCopy.getY(), EpsilonDouble);
        Assert.assertEquals(5.5, testCoordinateCopy.getZ(), EpsilonDouble);
    }



    @Test
    public void testIsEqual() {
        Assert.assertTrue(testCoordinate.isEqual(testCoordinateCopy));
        Assert.assertFalse(testCoordinate.isEqual(testCoordinateDifferent));

        Assert.assertTrue(berlin.isEqual(sphericCoordinateEqualToBerlin));
        Assert.assertFalse(berlin.isEqual(sphericCoordinate));
    }



    @Test
    public void testDistance() {
        Assert.assertEquals(8912, berlin.getDistance(tokio), EpsilonDistance);
        Assert.assertEquals(8912, tokio.getDistance(berlin), EpsilonDistance);
        Assert.assertEquals(0, berlin.getDistance(berlin), EpsilonDistance);
        Assert.assertEquals(0, tokio.getDistance(tokio), EpsilonDistance);

        Assert.assertEquals(8912, tokio.getDistance(sphericCoordinateEqualToBerlin), EpsilonDistance);
        Assert.assertEquals(0, berlin.getDistance(sphericCoordinateEqualToBerlin), EpsilonDistance);
    }



    @Test(expected = NullPointerException.class)
    public void testConstructorCopyArgumentNull() {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(null);
    }
    @Test(expected = NullPointerException.class)
    public void testIsEqualArgumentNull() {
        testCoordinate.isEqual(null);
    }
    @Test(expected = NullPointerException.class)
    public void testDistanceArgumentNull() {
        testCoordinate.getDistance(null);
    }
}

