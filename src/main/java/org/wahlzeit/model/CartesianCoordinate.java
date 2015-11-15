package org.wahlzeit.model;


/**
 * Created by stefan on 11/8/15.
 */
public class CartesianCoordinate extends AbstractCoordinate {
    private double x;
    private double y;
    private double z;



    /**
     * @methodtype initialization
     * @methodproperty constructor
     * @param cartesianCoordinate
     */
    public CartesianCoordinate(CartesianCoordinate cartesianCoordinate) {
        this(cartesianCoordinate.getX(), cartesianCoordinate.getY(), cartesianCoordinate.getZ());
    }

    /**
     * @methodtype initialization
     * @methodproperty constructor
     * @param x
     * @param y
     * @param z
     * @throws IllegalArgumentException
     */
    public CartesianCoordinate(double x, double y, double z) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
    }



    /**
     * @methodtype get
     * @methodproperty primitive
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     * @methodtype get
     * @methodproperty primitive
     * @return
     */
    public double getY() {
        return y;
    }

    /**
     * @methodtype get
     * @methodproperty primitive
     * @return
     */
    public double getZ() {
        return z;
    }



    /**
     * @methodtype query, conversion
     * @methodproperty
     * @return
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        double radius = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        double latitude = Math.toDegrees(Math.asin(z / radius));
        double longitude = Math.toDegrees(Math.atan2(y, x));
        return new SphericCoordinate(latitude, longitude, radius);
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
        if (!(o instanceof CartesianCoordinate)) return false;

        CartesianCoordinate that = (CartesianCoordinate) o;

        if (Double.compare(that.x, x) != 0) return false;
        if (Double.compare(that.y, y) != 0) return false;
        return Double.compare(that.z, z) == 0;

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
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
