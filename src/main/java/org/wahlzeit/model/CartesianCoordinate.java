package org.wahlzeit.model;


/**
 * Created by stefan on 11/8/15.
 */
public class CartesianCoordinate extends AbstractCoordinate {
    private double x;
    private double y;
    private double z;



    /**
     * @methodtype Mutation: Initialization
     * @methodproperty Convenience: Constructor
     * @param cartesianCoordinate CartesianCoordinate
     */
    public CartesianCoordinate(CartesianCoordinate cartesianCoordinate) throws NullPointerException {
        this(cartesianCoordinate.getX(), cartesianCoordinate.getY(), cartesianCoordinate.getZ());
    }

    /**
     * @methodtype Mutation: Initialization
     * @methodproperty Convenience: Constructor
     * @param x X
     * @param y Y
     * @param z Z
     */
    public CartesianCoordinate(double x, double y, double z) {
        super();

        assert isValidX(x);
        assert isValidY(y);
        assert isValidZ(z);

        basicSetX(x);
        basicSetY(y);
        basicSetZ(z);

        assertClassInvariants();
    }



    /**
     * @methodtype Mutation: Set
     * @methodproperty iImplementation: Primitive
     * @param x X
     */
    private void basicSetX(double x) {
        this.x = x;
    }

    /**
     * @methodtype Mutation: Set
     * @methodproperty Implementation: Primitive
     * @param y Y
     */
    private void basicSetY(double y) {
        this.y = y;
    }

    /**
     * @methodtype Mutation: Set
     * @methodproperty Implementation: Primitive
     * @param z Z
     */
    private void basicSetZ(double z) {
        this.z = z;
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
