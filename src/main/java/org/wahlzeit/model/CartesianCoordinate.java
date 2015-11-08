package org.wahlzeit.model;



/**
 * Created by stefan on 11/8/15.
 */
public class CartesianCoordinate extends Coordinate {
    private double x;
    private double y;
    private double z;



    /**
     * @methodtype constructor
     * @param cartesianCoordinate
     */
    public CartesianCoordinate(CartesianCoordinate cartesianCoordinate) {
        this(cartesianCoordinate.getX(), cartesianCoordinate.getY(), cartesianCoordinate.getZ());
    }

    /**
     * @methodtype constructor
     * @param x
     * @param y
     * @param z
     * @throws IllegalArgumentException
     */
    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        touch();
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
    public double getX() {
        return x;
    }

    /**
     * @methodtype get
     * @return
     */
    public double getY() {
        return y;
    }

    /**
     * @methodtype get
     * @return
     */
    public double getZ() {
        return z;
    }



    /**
     * @methodtype query
     * @param coordinate
     * @return
     * @throws IllegalArgumentException
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
        SphericCoordinate thisSpheric = convertToSphericCoordinate(this);
        if(!isEqualFuzzy(sphericCoordinate.getLatitude(), thisSpheric.getLatitude())) {
            return false;
        }
        if(!isEqualFuzzy(sphericCoordinate.getLongitude(), thisSpheric.getLongitude())) {
            return false;
        }
        if(!isEqualFuzzy(sphericCoordinate.getRadius(), thisSpheric.getRadius())) {
            return false;
        }
        return true;
    }

    private boolean isEqual(CartesianCoordinate cartesianCoordinate) {
        return equals(cartesianCoordinate);
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

//    /**
//     * @methodtype conversion
//     * @param sphericCoordinate
//     * @return CartesianCoordinate
//     */
//    private CartesianCoordinate convertToCartesianCoordinate(SphericCoordinate sphericCoordinate) {
//        double latitudeRad = Math.toRadians(sphericCoordinate.getLatitude());
//        double longitudeRad =  Math.toRadians(sphericCoordinate.getLongitude());
//        double x = sphericCoordinate.getRadius() * Math.cos(latitudeRad * Math.cos(longitudeRad));
//        double y = sphericCoordinate.getRadius() * Math.cos(latitudeRad * Math.sin(longitudeRad));
//        double z = sphericCoordinate.getRadius() * Math.sin(latitudeRad);
//        return new CartesianCoordinate(x, y, z);
//    }



    /**
     * @methodtype query
     * @param coordinate
     * @return
     * @throws IllegalArgumentException
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
    public double getDistance(SphericCoordinate sphericCoordinate) {
        SphericCoordinate thisSphreic = convertToSphericCoordinate(this);
        double thisLatitudeRad = Math.toRadians(thisSphreic.getLatitude());
        double thisLongitudeRad = Math.toRadians(thisSphreic.getLongitude());
        double otherLatitudeRad = Math.toRadians(sphericCoordinate.getLatitude());
        double otherLongitudeRad = Math.toRadians(sphericCoordinate.getLongitude());
        double zeta = Math.acos( Math.sin(thisLatitudeRad) * Math.sin(otherLatitudeRad)
                + Math.cos(thisLatitudeRad) * Math.cos(otherLatitudeRad)
                * Math.cos(otherLongitudeRad - thisLongitudeRad) );
        /* TODO: What to do on different radii? */
        return zeta * thisSphreic.getRadius();
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartesianCoordinate)) return false;

        CartesianCoordinate that = (CartesianCoordinate) o;

        if (Double.compare(that.x, x) != 0) return false;
        if (Double.compare(that.y, y) != 0) return false;
        return Double.compare(that.z, z) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
