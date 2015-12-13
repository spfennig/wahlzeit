package org.wahlzeit.model;

/**
 * Created by stefan on 11/8/15.
 */
public class CartesianCoordinate extends AbstractCoordinate {
    private final double x;
    private final double y;
    private final double z;



    /**
     * @methodtype Helper: Factory Method
     * @methodproperty Implementation: Regular
     * @param x X
     * @param y Y
     * @param z Z
     * @return coordinate
     */
    public static Coordinate getCoordinate(double x, double y, double z) {
        Coordinate coordinate = new CartesianCoordinate(x, y, z);
        if(coordinateMap.containsKey(coordinate.hashCode())) {
            return coordinateMap.get(coordinate.hashCode());
        }
        coordinateMap.put(coordinate.hashCode(), coordinate);
        return coordinate;
    }



    /**
     * @methodtype Mutation: Initialization
     * @methodproperty Convenience: Constructor
     * @param x X
     * @param y Y
     * @param z Z
     */
    /* Access Level Package-Private for Testing purposes. */
    CartesianCoordinate(double x, double y, double z) {
        super();

        assert isValidX(x);
        assert isValidY(y);
        assert isValidZ(z);

        this.x = x;
        this.y = y;
        this.z = z;

        assertClassInvariants();
    }



    /**
     * @methodtype Query: Get
     * @methodproperty Implementation: Primitive
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * @methodtype Query: Get
     * @methodproperty Implementation: Primitive
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * @methodtype Query: Get
     * @methodproperty Implementation: Primitive
     * @return z
     */
    public double getZ() {
        return z;
    }



    /**
     * @methodtype Query: Conversion
     * @methodproperty Implementation: Regular
     * @return Latitude
     */
    @Override
    public double getLatitude() {
        double latitude = Math.toDegrees(Math.asin(z / getRadius()));
        assert isValidLatitude(latitude);
        return latitude;
    }

    /**
     * @methodtype Query: Conversion
     * @methodproperty Implementation: Regular
     * @return Longitude
     */
    @Override
    public double getLongitude() {
        double longitude = Math.toDegrees(Math.atan2(y, x));
        assert isValidLongitude(longitude);
        return longitude;
    }

    /**
     * @methodtype Query, Conversion
     * @methodproperty Implementation: Regular
     * @return Radius
     */
    @Override
    public double getRadius() {
        double radius = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        assert isValidRadius(radius);
        return radius;
    }



    /**
     * @methodtype Helper: Assertion
     * @methodtype Implementation: Composed
     * @param x X
     * @return true if x valid
     */
    private boolean isValidX(double x) {
        return isNotNaN(x);
    }

    /**
     * @methodtype Helper: Assertion
     * @methodproperty Implementation: Composed
     * @param y Y
     * @return true if y valid
     */
    private boolean isValidY(double y) {
        return isNotNaN(y);
    }

    /**
     * @methodtype Helper: Assertion
     * @methodproperty Implementation: Composed
     * @param z Z
     * @return true if z valid
     */
    private boolean isValidZ(double z) {
        return isNotNaN(z);
    }



    /**
     * @methodtype Helper: Assertion
     * @methodproperty Implementation: Composed
     */
    @Override
    protected void assertClassInvariants() {
        assert isValidX(this.x);
        assert isValidY(this.y);
        assert isValidZ(this.z);
    }
}
