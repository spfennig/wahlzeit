package org.wahlzeit.model;


/**
 * Created by stefan on 11/8/15.
 */
public class SphericCoordinate extends AbstractCoordinate {
    private double latitude;
    private double longitude;
    private double radius;



    /**
     * @methodtype Mutation: Initialization
     * @methodproperty Convenience: Constructor
     * @param sphericCoordinate SphericCoordinate
     */
    public SphericCoordinate(SphericCoordinate sphericCoordinate) {
        this(sphericCoordinate.getLatitude(), sphericCoordinate.getLongitude(), sphericCoordinate.getRadius());
    }

    /**
     * @methodtype Mutation: Initialization
     * @methodproperty Convenience: Constructor
     * @param latitude Latitude
     * @param longitude Longitude
     */
    public SphericCoordinate(double latitude, double longitude) {
        this(latitude, longitude, DefaultRadius);
    }

    /**
     * @methodtype Mutation: Initialization
     * @methodproperty Convenience: Constructor
     * @param latitude Latitude
     * @param longitude Longitude
     * @param radius Radius
     * @throws IllegalArgumentException
     */
    public SphericCoordinate(double latitude, double longitude, double radius) throws IllegalArgumentException {
        super();
        if(!isValidLatitude(latitude)) {
            throw new IllegalArgumentException("Invalid latitude: " + latitude);
        }
        if(!isValidLongitude(longitude)) {
            throw new IllegalArgumentException("Invalid longitude: " + longitude);
        }
        if(!isValidRadius(radius)) {
            throw new IllegalArgumentException("Invalid radius: " + radius);
        }
        basicSetLatitude(latitude);
        basicSetLongitude(longitude);
        basicSetRadius(radius);
        assertClassInvariants();
    }



    /**
     * @methodtype Mutation: Set
     * @methodproperty Implementation: Primitive
     * @param latitude Latitude
     */
    private void basicSetLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @methodtype Mutation: Set
     * @methodproperty Implementation: Primitive
     * @param longitude Longitude
     */
    private void basicSetLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @methodtype Mutation: Set
     * @methodproperty Implementation: Primitive
     * @param radius Radius
     */
    private void basicSetRadius(double radius) {
        this.radius = radius;
    }



    /**
     * @methodtype Query: Get
     * @methodproperty Implementation: Primitive
     * @return Latitude
     */
    @Override
    public double getLatitude() {
        return latitude;
    }

    /**
     * @methodtype Query: Get
     * @methodproperty Implementation: Primitive
     * @return Longitude
     */
    @Override
    public double getLongitude() {
        return longitude;
    }

    /**
     * @methodtype Query: Get
     * @methodproperty Implementation: Primitive
     * @return Radius
     */
    @Override
    public double getRadius() {
        return radius;
    }



    /**
     * @methodtype Helper: Assertion
     * @methodproperty Implementation: Composed
     */
    @Override
    protected void assertClassInvariants() {
        assert isValidLatitude(this.latitude);
        assert isValidLongitude(this.longitude);
        assert isValidRadius(this.radius);
    }
}
