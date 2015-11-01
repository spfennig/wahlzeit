/*
 * 
 */
package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

        

public class Coordinate extends DataObject {
    private double latitude;
    private double longitude;

    
    
    /**
     * @methodtype constructor
     * @param coordinate 
     */
    public Coordinate(Coordinate coordinate) {
        this(coordinate.getLatitude(), coordinate.getLongitude());
    }

    /**
     * @methodtype constructor
     * @param latitude
     * @param longitude
     * @throws IllegalArgumentException 
     */
    public Coordinate(double latitude, double longitude) throws IllegalArgumentException {
        assertValidLatitude(latitude);
        assertValidLongitude(longitude);
        this.latitude = latitude;
        this.longitude = longitude;
        touch();
    }

    /**
     * @methodtype assert
     * @param latitude
     * @return 
     */
    private boolean assertValidLatitude(double latitude) {
        if(-90 <= latitude && latitude <= 90) {
            return true;
        }
        throw new IllegalArgumentException("Latitude out of bounds: " + latitude);
    }

    /**
     * @methodtype assert
     * @param longitude
     * @return 
     */
    private boolean assertValidLongitude(double longitude) {
        if(-180 <= longitude && longitude <= 180) {
            return true;
        }
        throw new IllegalArgumentException("Longitude out of bounds: " + longitude);
    }

    /**
     * @methodtype get
     * @return 
     */
    public double getLatitude(){
        return latitude;
    }

    /**
     * @methodtype get
     * @return 
     */
    public double getLongitude() {
        return longitude;
    }



    /**
     * @methodtype query
     * @param coordinate
     * @return Angle in degrees
     */
    public double getLatitudinalDistance(Coordinate coordinate) {
        return Math.abs(latitude - coordinate.getLatitude());
    }

    /**
     * @methodtype query
     * @param coordinate
     * @return Angle in degrees
     */
    public double getLongitudinalDistance(Coordinate coordinate) {
        return Math.abs(longitude - coordinate.getLongitude());
    }

    /**
     * @methodtype query
     * @param coordinate
     * @return Great-circle distance in km
     */
    public double getDistance(Coordinate coordinate) {
        final double RadiusEarth = 6371;
        double zeta = Math.acos(  Math.sin(Math.toRadians(latitude)) * Math.sin(Math.toRadians(coordinate.getLatitude()))
                                + Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(coordinate.getLatitude()))
                                * Math.cos(Math.toRadians(coordinate.getLongitude()) - Math.toRadians(longitude)) );
        return zeta * RadiusEarth;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinate other = (Coordinate) obj;
        if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)) {
            return false;
        }
        if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude)) {
            return false;
        }
        return true;
    }
}
