package org.wahlzeit.model;


/**
 * Created by stefan.
 */
public interface Coordinate {
    double getDistance(Coordinate coordinate);
    boolean isEqual(Coordinate coordinate);
}
