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
    private Coordinate testCoordinate2;
    
    private Coordinate paramLessCoordinate;
    private Coordinate paramGreaterCoordinate;
    private Coordinate paramMixed1Coordinate;
    private Coordinate paramMixed2Coordinate;
    
    private Coordinate berlin;
    private Coordinate tokio;
    
    private final double Epsilon = 1e-5;
    private final double EpsilonDistance = 10;
    
    
    
    @Before
    public void setUp() {
        testCoordinate = new Coordinate(5.5, 5.5);
        testCoordinate2 = new Coordinate(testCoordinate);
        paramLessCoordinate = new Coordinate(4.4, 4.4);
        paramGreaterCoordinate = new Coordinate(6.6, 6.6);
        paramMixed1Coordinate = new Coordinate(4.4, 6.6);
        paramMixed2Coordinate = new Coordinate(6.6, 4.4);
        
        /*
         * Example provided by https://de.wikipedia.org/wiki/Orthodrome, released 07.09.2015 19:55
         */
        berlin = new Coordinate(52.517, 13.40);
        tokio = new Coordinate(35.70, 139.767);
    }
    
    
    
    @Test
    public void testGetter() {
        /* Constructor and getter */
        Assert.assertEquals(5.5, testCoordinate.getLatitude(), Epsilon);
        Assert.assertEquals(5.5, testCoordinate.getLongitude(), Epsilon);
        /* Copy constructor and getter */
        Assert.assertEquals(5.5, testCoordinate2.getLatitude(), Epsilon);
        Assert.assertEquals(5.5, testCoordinate2.getLongitude(), Epsilon);
    }
    
    @Test
    public void testLatitudeDistance() {
        Assert.assertEquals(1.1, testCoordinate.getLatitudinalDistance(paramLessCoordinate), Epsilon);
        Assert.assertEquals(1.1, testCoordinate.getLatitudinalDistance(paramGreaterCoordinate), Epsilon);
        Assert.assertEquals(1.1, testCoordinate.getLatitudinalDistance(paramMixed1Coordinate), Epsilon);
        Assert.assertEquals(1.1, testCoordinate.getLatitudinalDistance(paramMixed2Coordinate), Epsilon);
    }
    
    @Test
    public void testLongitudeDistance() {
        Assert.assertEquals(1.1, testCoordinate.getLongitudinalDistance(paramLessCoordinate), Epsilon);
        Assert.assertEquals(1.1, testCoordinate.getLatitudinalDistance(paramGreaterCoordinate), Epsilon);
        Assert.assertEquals(1.1, testCoordinate.getLatitudinalDistance(paramMixed1Coordinate), Epsilon);
        Assert.assertEquals(1.1, testCoordinate.getLatitudinalDistance(paramMixed2Coordinate), Epsilon);
    }
    
    @Test
    public void testDistance() {
        Assert.assertEquals(8912, berlin.getDistance(tokio), EpsilonDistance);
        Assert.assertEquals(8912, tokio.getDistance(berlin), EpsilonDistance);
        Assert.assertEquals(0, berlin.getDistance(berlin), EpsilonDistance);
        Assert.assertEquals(0, tokio.getDistance(tokio), EpsilonDistance);
    }
    
    @Test
    public void testEquals() {
        Assert.assertTrue(testCoordinate.equals(testCoordinate2));
        Assert.assertFalse(testCoordinate.equals(paramLessCoordinate));
    }
    
    
    
    @Test(expected = NullPointerException.class)
    public void testDistanceArgumentNull() {
        testCoordinate.getDistance(null);
    }
    @Test(expected = NullPointerException.class)
    public void testLatitudinalDistanceArgumentNull() {
        testCoordinate.getLatitudinalDistance(null);
    }
    @Test(expected = NullPointerException.class)
    public void testLongitudinalDistanceArgumentNull() {
        testCoordinate.getLongitudinalDistance(null);
    }
    
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentLessBoundaries() {
        Coordinate c = new Coordinate(-90.1, -180.1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentGreaterBoundaries() {
        Coordinate c = new Coordinate(90.1, 180.1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentMixed1Boundaries() {
        Coordinate c = new Coordinate(-90.1, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentMixed2Boundaries() {
        Coordinate c = new Coordinate(90.1, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentMixed3Boundaries() {
        Coordinate c = new Coordinate(0, -180.1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentMixed4Boundaries() {
        Coordinate c = new Coordinate(0, 180.1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentMixed5Boundaries() {
        Coordinate cLess = new Coordinate(-90.1, 180.1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentMixed6Boundaries() {
        Coordinate cLess = new Coordinate(90.1, -180.1);
    }
}
