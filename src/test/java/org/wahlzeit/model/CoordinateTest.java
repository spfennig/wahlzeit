/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wahlzeit.model;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

/**
 *
 */
public class CoordinateTest {
    private Coordinate testCoordinate;
    
    private Coordinate paramLessCoordinate;
    private Coordinate paramGreaterCoordinate;
    private Coordinate paramMixed1Coordinate;
    private Coordinate paramMixed2Coordinate;
    
    private final double Epsilon = 1e-5;
    
    
    @Before
    public void setUp()
    {
        testCoordinate = new Coordinate(5.5, 5.5);
        
        paramLessCoordinate = new Coordinate(4.4, 4.4);
        paramGreaterCoordinate = new Coordinate(6.6, 6.6);
        paramMixed1Coordinate = new Coordinate(4.4, 6.6);
        paramMixed2Coordinate = new Coordinate(6.6, 4.4);
    }
    
    
    @Test
    public void testGetter()
    {
        Assert.assertEquals(5.5, testCoordinate.getLatitude(), Epsilon);
        Assert.assertEquals(5.5, testCoordinate.getLongitude(), Epsilon);
    }
    
    @Test
    public void testLatitudeDistance()
    {
        Assert.assertEquals(1.1, testCoordinate.getLatitudinalDistance(paramLessCoordinate), Epsilon);
        Assert.assertEquals(1.1, testCoordinate.getLatitudinalDistance(paramGreaterCoordinate), Epsilon);
        Assert.assertEquals(1.1, testCoordinate.getLatitudinalDistance(paramMixed1Coordinate), Epsilon);
        Assert.assertEquals(1.1, testCoordinate.getLatitudinalDistance(paramMixed2Coordinate), Epsilon);
    }
    
    @Test
    public void testLongitudeDistance()
    {
        Assert.assertEquals(1.1, testCoordinate.getLongitudinalDistance(paramLessCoordinate), Epsilon);
        Assert.assertEquals(1.1, testCoordinate.getLatitudinalDistance(paramGreaterCoordinate), Epsilon);
        Assert.assertEquals(1.1, testCoordinate.getLatitudinalDistance(paramMixed1Coordinate), Epsilon);
        Assert.assertEquals(1.1, testCoordinate.getLatitudinalDistance(paramMixed2Coordinate), Epsilon);
    }
    
    @Test
    public void testDistance()
    {
        Coordinate returnCoordinate;
        
        returnCoordinate = testCoordinate.getDistance(paramLessCoordinate);
        Assert.assertEquals(1.1, returnCoordinate.getLatitude(), Epsilon);
        Assert.assertEquals(1.1, returnCoordinate.getLongitude(), Epsilon);
        
        returnCoordinate = testCoordinate.getDistance(paramGreaterCoordinate);
        Assert.assertEquals(1.1, returnCoordinate.getLatitude(), Epsilon);
        Assert.assertEquals(1.1, returnCoordinate.getLongitude(), Epsilon);
        
        returnCoordinate = testCoordinate.getDistance(paramMixed1Coordinate);
        Assert.assertEquals(1.1, returnCoordinate.getLatitude(), Epsilon);
        Assert.assertEquals(1.1, returnCoordinate.getLongitude(), Epsilon);
        
        returnCoordinate = testCoordinate.getDistance(paramMixed2Coordinate);
        Assert.assertEquals(1.1, returnCoordinate.getLatitude(), Epsilon);
        Assert.assertEquals(1.1, returnCoordinate.getLongitude(), Epsilon);
    }
    
    @Test(expected = NullPointerException.class)
    public void testDistanceArgumentNull()
    {
        testCoordinate.getDistance(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentlessBoundaries()
    {
        Coordinate c = new Coordinate(-90.1, -90.1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentGreaterBoundaries()
    {
        Coordinate c = new Coordinate(90.1, 90.1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentMixed1Boundaries()
    {
        Coordinate c = new Coordinate(-90.1, 0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentMixed2Boundaries()
    {
        Coordinate c = new Coordinate(90.1, 0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentMixed3Boundaries()
    {
        Coordinate c = new Coordinate(0, -90.1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentMixed4Boundaries()
    {
        Coordinate c = new Coordinate(0, 90.1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentMixed5Boundaries()
    {
        Coordinate cLess = new Coordinate(-90.1, 90.1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentMixed6Boundaries()
    {
        Coordinate cLess = new Coordinate(90.1, -90.1);
    }
}
