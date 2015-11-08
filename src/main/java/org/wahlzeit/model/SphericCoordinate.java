package org.wahlzeit.model;



/**
 * Created by stefan on 11/8/15.
 */
public class SphericCoordinate extends Coordinate {
    private double latitude;
    private double longitude;
    private double radius;



    /**
     * @methodtype constructor
     * @param sphericCoordinate
     */
    public SphericCoordinate(SphericCoordinate sphericCoordinate) {
        this(sphericCoordinate.getLatitude(), sphericCoordinate.getLongitude(), sphericCoordinate.getRadius());
    }

    /**
     * @methodtype constructor
     * @param latitude
     * @param longitude
     */
    public SphericCoordinate(double latitude, double longitude) {
        this(latitude, longitude, 6371);
    }

    /**
     * @methodtype constructor
     * @param latitude
     * @param longitude
     * @param radius
     * @throws IllegalArgumentException
     */
    public SphericCoordinate(double latitude, double longitude, double radius) throws IllegalArgumentException {
        assert assertValidLatitude(latitude);
        assert assertValidLongitude(longitude);
        assert assertValidRadius(radius);
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        touch();
    }



    /**
     * @methodtype assert
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
     * @methodtype assert
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
     * @methodtype assert
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
     * @methodtype assert
     * @param object
     * @return
     * @throws NullPointerException
     */
    private boolean assertNotNull(Object object) throws NullPointerException {
        if(object != null) {
            return true;
        }
        throw new NullPointerException();
    }



    /**
     * @methodtype get
     * @return
     */
    public double getLatitude() {
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
     * @methodtype get
     * @return
     */
    public double getRadius() {
        return radius;
    }


    /**
     * @methodtype query
     * @param coordinate
     * @return
     */
    public boolean isEqual(Coordinate coordinate) throws IllegalArgumentException {
        assertNotNull(coordinate);
        if(coordinate instanceof SphericCoordinate) {
            return isEqual((SphericCoordinate)coordinate);
        }
        if(coordinate instanceof CartesianCoordinate) {
            return isEqual((CartesianCoordinate)coordinate);
        }
        throw new IllegalArgumentException();
    }

    /**
     * @methodtype query
     * @param sphericCoordinate
     * @return
     */
    private boolean isEqual(SphericCoordinate sphericCoordinate) {
        return equals(sphericCoordinate);
    }

    /**
     * @methodtype query
     * @param cartesianCoordinate
     * @return
     */
    private boolean isEqual(CartesianCoordinate cartesianCoordinate) {
        SphericCoordinate sphericCoordinate = convertToSphericCoordinate(cartesianCoordinate);
        if(!isEqualFuzzy(sphericCoordinate.getLatitude(), latitude)) {
            return false;
        }
        if(!isEqualFuzzy(sphericCoordinate.getLongitude(), longitude)) {
            return false;
        }
        if(!isEqualFuzzy(sphericCoordinate.getRadius(), radius)) {
            return false;
        }
        return true;
    }



    /**
     * Fuzzy comparison due to conversion errors
     * Default tolerance: 1 meter
     * @methodtype helper
     * @param a
     * @param b
     * @return
     */
    private boolean isEqualFuzzy(double a, double b) {
        return isEqualFuzzy(a, b, 0.001);
    }

    /**
     * Fuzzy comparison due to conversion errors
     * @methodtype helper
     * @param a
     * @param b
     * @param epsilon
     * @return
     */
    private boolean isEqualFuzzy(double a, double b, double epsilon) {
        if (a == b) {
            return true;
        }
        return Math.abs(a - b) < epsilon;
    }



    /**
     * @methodtype conversion
     * @param cartesianCoordinate
     * @return SphericCoordinate
     */
    private SphericCoordinate convertToSphericCoordinate(CartesianCoordinate cartesianCoordinate) {
        double radius = Math.sqrt(Math.pow(cartesianCoordinate.getX(), 2) + Math.pow(cartesianCoordinate.getY(), 2) + Math.pow(cartesianCoordinate.getZ(), 2));
        double latitude = Math.toDegrees(Math.asin(cartesianCoordinate.getZ() / radius));
        double longitude = Math.toDegrees(Math.atan2(cartesianCoordinate.getY(), cartesianCoordinate.getX()));
        return new SphericCoordinate(latitude, longitude, radius);
    }



    /**
     * @methodtype query
     * @param coordinate
     * @return Great-circle distance in km
     */
    public double getDistance(Coordinate coordinate) throws IllegalArgumentException {
        assertNotNull(coordinate);
        if(coordinate instanceof SphericCoordinate) {
            return getDistance((SphericCoordinate)coordinate);
        }
        if(coordinate instanceof CartesianCoordinate) {
            return getDistance(convertToSphericCoordinate((CartesianCoordinate)coordinate));
        }
        throw new IllegalArgumentException();
    }



    /**
     * @methodtype query
     * @param sphericCoordinate
     * @return
     */
    private double getDistance(SphericCoordinate sphericCoordinate) {
        double thisLatitudeRad = Math.toRadians(latitude);
        double thisLongitudeRad = Math.toRadians(longitude);
        double otherLatitudeRad = Math.toRadians(sphericCoordinate.getLatitude());
        double otherLongitudeRad = Math.toRadians(sphericCoordinate.getLongitude());
        double zeta = Math.acos( Math.sin(thisLatitudeRad) * Math.sin(otherLatitudeRad)
                + Math.cos(thisLatitudeRad) * Math.cos(otherLatitudeRad)
                * Math.cos(otherLongitudeRad - thisLongitudeRad) );
        /* TODO: What to do on different radii? */
        return zeta * radius;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SphericCoordinate)) return false;

        SphericCoordinate that = (SphericCoordinate) o;

        if (Double.compare(that.latitude, latitude) != 0) return false;
        if (Double.compare(that.longitude, longitude) != 0) return false;
        return Double.compare(that.radius, radius) == 0;
    }

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
