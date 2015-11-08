package org.wahlzeit.model;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;



public class CoordinateTest {
    private Coordinate berlinSphericCoordinate;
    private Coordinate tokioSphericCoordinate;
    private Coordinate berlinCartesianCoordinate;
    private Coordinate tokioCartesianCoordinate;

    private final double EpsilonDistance = 10; /* 10km */



    /* TODO: Consider @BeforeClass */
    @Before
    public void setUp() {
        /*
         * Example provided by https://de.wikipedia.org/wiki/Orthodrome, released 07.09.2015 19:55
         */
        berlinSphericCoordinate = new SphericCoordinate(52.517, 13.40);
        tokioSphericCoordinate = new SphericCoordinate(35.70, 139.767);
        berlinCartesianCoordinate = new CartesianCoordinate(3771.373, 898.468, 5055.605);
        tokioCartesianCoordinate = new CartesianCoordinate(-3949.792, 3341.734, 3717.741);
    }



    @Test
    public void testInterchangeability() {
        Assert.assertEquals(0, berlinSphericCoordinate.getDistance(berlinCartesianCoordinate), EpsilonDistance);
        Assert.assertEquals(0, berlinCartesianCoordinate.getDistance(berlinSphericCoordinate), EpsilonDistance);
        Assert.assertEquals(8912, berlinSphericCoordinate.getDistance(tokioCartesianCoordinate), EpsilonDistance);
        Assert.assertEquals(8912, berlinCartesianCoordinate.getDistance(tokioSphericCoordinate), EpsilonDistance);
    }
}
