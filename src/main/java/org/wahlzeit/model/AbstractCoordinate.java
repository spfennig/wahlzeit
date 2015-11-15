package org.wahlzeit.model;


import org.wahlzeit.services.DataObject;

/**
 * Created by stefan on 11/15/15.
 */
public abstract class AbstractCoordinate extends DataObject implements Coordinate {
    protected final static double DefaultRadius = 6371;

    /**
     * @methodtype conversion
     * @methodproperty abstract
     * @return
     */
    protected abstract SphericCoordinate asSphericCoordinate();



    /**
     * @methodtype initialization
     * @methodproperty constructor
     */
    public AbstractCoordinate() {
        touch();
    }



    /**
     * @methodtype assertion
     * @methodproperty
     * @param object Object to test for null
     * @return true if not null
     * @throws NullPointerException
     */
    protected final boolean assertNotNull(Object object) throws NullPointerException {
        if(object != null) {
            return true;
        }
        throw new NullPointerException();
    }



    /**
     * @methodtype query
     * @methodproperty
     * @param coordinate
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public double getDistance(Coordinate coordinate) throws IllegalArgumentException {
        assertNotNull(coordinate);
        if(coordinate instanceof AbstractCoordinate) {
            return getDistance((AbstractCoordinate)coordinate);
        }
        throw new IllegalArgumentException("coordinate is not an instance of AbstractCoordinate.");
    }

    /**
     * @methodtype query
     * @methodproperty
     * @param abstractCoordinate
     * @return
     */
    private double getDistance(AbstractCoordinate abstractCoordinate) {
        return this.asSphericCoordinate().getDistance(abstractCoordinate.asSphericCoordinate());
    }



    /**
     * @methodtype query, comparison
     * @methodproperty
     * @param coordinate
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public boolean isEqual(Coordinate coordinate) throws IllegalArgumentException {
        if(coordinate == null) return false;
        /* Same type, avoid conversion to SphericCoordinate */
        if(this.getClass().equals(coordinate.getClass())) {
            return this.equals(coordinate);
        }
        /* Different type but AbstractCoordinate */
        if(coordinate instanceof AbstractCoordinate) {
            return this.isEqual((AbstractCoordinate)coordinate);
        }
        throw new IllegalArgumentException("coordinate is not an instance of AbstractCoordinate.");
    }

    /**
     * @methodtype query, comparison
     * @methodproperty
     * @param abstractCoordinate
     * @return
     */
    private boolean isEqual(AbstractCoordinate abstractCoordinate) {
        return this.asSphericCoordinate().isEqual(abstractCoordinate.asSphericCoordinate());
    }
}
