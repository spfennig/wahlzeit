package org.wahlzeit.model;

/**
 * Created by stefan on 11/8/15.
 */
public class SphericCoordinate extends AbstractCoordinate {
    private final double latitude;
    private final double longitude;
    private final double radius;



    /**
     * @methodtype Helper: Factory Method
     * @methodproperty Convenience: Default-Value
     * @param latitude Latitude
     * @param longitude Longitude
     * @return Coordinate
     */
    public static Coordinate getCoordinate(double latitude, double longitude) {
        return getCoordinate(latitude, longitude, DefaultRadius);
    }

    /**
     * @methodtype Helper: Factory Method
     * @methodproperty
     * @param latitude Latitude
     * @param longitude Longitude
     * @param radius Radius
     * @return coordinate
     */
    public static Coordinate getCoordinate(double latitude, double longitude, double radius) {
        Coordinate coordinate = new SphericCoordinate(latitude, longitude, radius);
        if(coordinateMap.containsKey(coordinate.hashCode())) {
            return coordinateMap.get(coordinate.hashCode());
        }
        coordinateMap.put(coordinate.hashCode(), coordinate);
        return coordinate;
    }



    /**
     * @methodtype Mutation: Initialization
     * @methodproperty Convenience: Constructor
     * @param latitude Latitude
     * @param longitude Longitude
     * @param radius Radius
     */
    /* Access Level Package-Private for Testing purposes. */
    SphericCoordinate(double latitude, double longitude, double radius) {
        super();

        assert isValidLatitude(latitude);
        assert isValidLongitude(longitude);
        assert isValidRadius(radius);

        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;

        assertClassInvariants();
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
