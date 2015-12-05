package org.wahlzeit.model;


import org.wahlzeit.services.DataObject;

/**
 * Created by stefan on 11/15/15.
 */
public abstract class AbstractCoordinate extends DataObject implements Coordinate {
    protected final static double DefaultRadius = 6371;
    protected final double DefaultValidDistanceRadius = DefaultRadius + 10;



    /**
     * @methodtype Mutation: Initialization
     * @methodproperty Convenience: Constructor
     */
    public AbstractCoordinate() {
        touch();
    }



    /**
     * @methodtype Query: Conversion
     * @methodproperty Inheritance: Abstract
     * @return Latitude
     */
    protected abstract double getLatitude();

    /**
     * @methodtype Query: Conversion
     * @methodproperty Inheritance: Abstract
     * @return Longitude
     */
    protected abstract double getLongitude();

    /**
     * @methodtype Query: Conversion
     * @methodproperty Inheritance: Abstract
     * @return Radius
     */
    protected abstract double getRadius();

    /**
     * @methodtype Helper: Assertion
     * @methodproperty Inheritance: Abstract
     */
    protected abstract void assertClassInvariants();



    /**
     * @methodtype Query: Get
     * @methodproperty Implementation: Composed
     * @param coordinate Coordinate
     * @return Distance
     * @throws NullPointerException, IllegalArgumentException
     */
    @Override
    public double getDistance(Coordinate coordinate) {
        assert isNotNull(coordinate);
        assert isInstanceOfAbstractCoordinate(coordinate);
        /* Parameter coordinate takes care of its class invariants itself */
        assertClassInvariants();

        double distance = basicGetDistance((AbstractCoordinate)coordinate);

        assertClassInvariants();
        assert isValidDistance(distance);

        return distance;
    }

    /**
     * @methodtype Query: Get
     * @methodproperty Implementation: Composed
     * @param abstractCoordinate AbstractCoordinate
     * @return Distance
     */
    protected double basicGetDistance(AbstractCoordinate abstractCoordinate) {
        double thisLatitudeRad = Math.toRadians(this.getLatitude());
        double thisLongitudeRad = Math.toRadians(this.getLongitude());
        double otherLatitudeRad = Math.toRadians(abstractCoordinate.getLatitude());
        double otherLongitudeRad = Math.toRadians(abstractCoordinate.getLongitude());
        double zeta = Math.acos( Math.sin(thisLatitudeRad) * Math.sin(otherLatitudeRad)
                + Math.cos(thisLatitudeRad) * Math.cos(otherLatitudeRad)
                * Math.cos(otherLongitudeRad - thisLongitudeRad) );
        /* TODO: What to do on different radii? */
        return zeta * DefaultRadius;
    }



    /**
     * @methodtype Query: Comparison
     * @methodproperty Implementation: Composed
     * @param object Object
     * @return Equality
     */
    @Override
    public boolean equals(Object object) {
        assert isNotNull(object);
        assertClassInvariants();

        if(isInstanceOfCoordinate(object)) {
            return getDistance((AbstractCoordinate)object) < DefaultValidDistanceRadius;
        }
        return false;
    }

    /**
     * @methodtype Query: Comparison
     * @methodproperty Implementation: Composed
     * @param coordinate Coordinate
     * @return Equality
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        return equals(coordinate);
    }



    /**
     * @methodtype Query: Conversion
     * @methodproperty Implementation: Composed
     * @return HashCode
     */
    @Override
    public int hashCode() {
        assertClassInvariants();

        int result;
        long temp;
        temp = Double.doubleToLongBits(getLatitude());
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLongitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getRadius());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }



    /**
     * @methodtype Query: Boolean query
     * @methodproperty Implementation: Regular
     * @param object Object to test for null
     * @return true if object not null
     */
    protected final boolean isNotNull(Object object) {
        return (object != null);
    }

    /**
     * @methodtype Query: Boolean query
     * @methodproperty Implementation: Regular
     * @param d Double to check for NaN
     * @return true if d not NaN
     */
    protected final boolean isNotNaN(double d) {
        return (!Double.isNaN(d));
    }

    /**
     * @methodtype Query: Boolean query
     * @methodproperty Implementation: Regular
     * @param object
     * @return
     */
    protected final boolean isInstanceOfCoordinate(Object object) {
        return object instanceof Coordinate;
    }

    /**
     * @methodtype Query: Boolean query
     * @methodproperty Implementation: Regular
     * @param object Coordinate to check for instance of AbstractCoordinate
     * @return true if instance of AbstractCoordinate
     */
    protected final boolean isInstanceOfAbstractCoordinate(Object object) {
        return object instanceof AbstractCoordinate;
    }

    /**
     * @methodtype Query: Boolean query
     * @methodproperty Implementation: Primitive
     * @param latitude Latitude
     * @return true if latitude inside valid range
     */
    private boolean isValidLatitudeRange(double latitude) {
        return (-90 <= latitude && latitude <= 90);
    }

    /**
     * @methodtype Query: Boolean query
     * @methodproperty Implementation: Primitive
     * @param longitude Longitude
     * @return true if longitude inside valid range
     */
    private boolean isValidLongitudeRange(double longitude) {
        return (-180 <= longitude && longitude <= 180);
    }

    /**
     * @methodtype Helper: Assertion
     * @methodproperty Implementation: Primitive
     * @param radius Radius
     * @return true if radius inside valid range
     */
    private boolean isValidRadiusRange(double radius) {
        return (radius >= 0);
    }

    /**
     * @methodtype Helper: Assertion
     * @methodproperty Implementation: Composed
     * @param latitude Latitude
     * @return true if latitude valid
     */
    protected boolean isValidLatitude(double latitude) {
        return isNotNaN(latitude) && isValidLatitudeRange(latitude);
    }

    /**
     * @methodtype Helper: Assertion
     * @methodtype Implementation: Composed
     * @param longitude Longitude
     * @return true if longitude valid
     */
    protected boolean isValidLongitude(double longitude) {
        return isNotNaN(longitude) && isValidLongitudeRange(longitude);
    }

    /**
     * @methodtype Helper: Assertion
     * @methodproperty Implementation: Composed
     * @param radius Radius
     * @return true if radius valid
     */
    protected boolean isValidRadius(double radius) {
        return isNotNaN(radius) && isValidRadiusRange(radius);
    }

    /**
     * @methodtype Helper: Assertion
     * @methodproperty Implementation: Regular
     * @param distance Distance
     * @return true if distance valid
     */
    private boolean isValidDistance(double distance) {
        if(distance < 0) return false;
        if(distance > (DefaultValidDistanceRadius * Math.PI)) return false;
        return true;
    }
}
