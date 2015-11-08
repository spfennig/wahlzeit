package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;


        
//interface Coordinate {
//    double getDistance(Coordinate coordinate);
//    boolean isEqual(Coordinate coordinate);
//}

public abstract class Coordinate extends DataObject {
    abstract double getDistance(Coordinate coordinate);
    abstract boolean isEqual(Coordinate coordinate);
}
