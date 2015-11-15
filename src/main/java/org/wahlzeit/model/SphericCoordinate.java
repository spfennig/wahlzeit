package org.wahlzeit.model;


/**
 * Created by stefan on 11/8/15.
 */
public class SphericCoordinate extends AbstractCoordinate {
    private double latitude;
    private double longitude;
    private double radius;



    /**
     * @methodtype initialization
     * @methodproperty constructor
     * @param sphericCoordinate
     */
    public SphericCoordinate(SphericCoordinate sphericCoordinate) {
        this(sphericCoordinate.getLatitude(), sphericCoordinate.getLongitude(), sphericCoordinate.getRadius());
    }

    /**
     * @methodtype initialization
     * @methodproperty constructor, convenience
     * @param latitude
     * @param longitude
     */
    public SphericCoordinate(double latitude, double longitude) {
        this(latitude, longitude, DefaultRadius);
    }

    /**
     * @methodtype initialization
     * @methodproperty constructor
     * @param latitude
     * @param longitude
     * @param radius
     * @throws IllegalArgumentException
     */
    public SphericCoordinate(double latitude, double longitude, double radius) throws IllegalArgumentException {
        super();
        assertValidLatitude(latitude);
        assertValidLongitude(longitude);
        assertValidRadius(radius);
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }



    /**
     * @methodtype assertion
     * @methodproperty
     * @param latitude
     * @return
     */
    private boolean assertValidLatitude(double latitude) throws IllegalArgumentException {
        if(-90 <= latitude && latitude <= 90) {
            return true;
        }
        throw new IllegalArgumentException("Latitude out of bounds: " + latitude);
    }

    /**
     * @methodtype assertion
     * @methodproperty
     * @param longitude
     * @return
     */
    private boolean assertValidLongitude(double longitude) throws IllegalArgumentException {
        if(-180 <= longitude && longitude <= 180) {
            return true;
        }
        throw new IllegalArgumentException("Longitude out of bounds: " + longitude);
    }

    /**
     * @methodtype assertion
     * @methodproperty
     * @param radius
     * @return
     */
    private boolean assertValidRadius(double radius) {
        if(radius >= 0) {
            return true;
        }
        throw new IllegalArgumentException("Radius out of bounds: " + radius);
    }



    /**
     * @methodtype get
     * @methodproperty primitive
     * @return
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @methodtype get
     * @methodproperty primitive
     * @return
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @methodtype get
     * @methodproberty primitive
     * @return
     */
    public double getRadius() {
        return radius;
    }



    /**
     * @methodtype query, conversion
     * @methodproperty
     * @return
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }



    /**
     * @methodtype comparison
     * @methodproperty
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SphericCoordinate)) return false;
        return compare((SphericCoordinate)o);
    }

    /**
     * @methodtype comparison
     * @methodproperty default-value
     * @param sphericCoordinate
     * @return
     */
    private boolean compare(SphericCoordinate sphericCoordinate) {
        final double DefaultEpsilon = 1e-3;
        if(!compare(sphericCoordinate.latitude, latitude, DefaultEpsilon)) {
            return false;
        }
        if(!compare(sphericCoordinate.longitude, longitude, DefaultEpsilon)) {
            return false;
        }
        if(!compare(sphericCoordinate.radius, radius, DefaultEpsilon)) {
            return false;
        }
        return true;
    }

    /**
     * Fuzzy comparison due to conversion errors
     * @methodtype comparison, helper
     * @methodproperty
     * @param a
     * @param b
     * @param epsilon
     * @return
     */
    private boolean compare(double a, double b, double epsilon) {
        if (a == b) {
            return true;
        }
        return Math.abs(a - b) < epsilon;
    }



    /**
     * @methodtype query
     * @methodproperty
     * @param sphericCoordinate
     * @return
     */
    public double getDistance(SphericCoordinate sphericCoordinate) {
        double thisLatitudeRad = Math.toRadians(latitude);
        double thisLongitudeRad = Math.toRadians(longitude);
        double otherLatitudeRad = Math.toRadians(sphericCoordinate.getLatitude());
        double otherLongitudeRad = Math.toRadians(sphericCoordinate.getLongitude());
        double zeta = Math.acos( Math.sin(thisLatitudeRad) * Math.sin(otherLatitudeRad)
                + Math.cos(thisLatitudeRad) * Math.cos(otherLatitudeRad)
                * Math.cos(otherLongitudeRad - thisLongitudeRad) );
        /* TODO: What to do on different radii? */
        return zeta * DefaultRadius;
    }



    /**
     * @methodtype query
     * @methodproperty
     * @return
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(radius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
