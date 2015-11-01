
package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;
import java.util.Objects;



public class Location extends DataObject {
    private String name;
    private Coordinate coordinate;
    
    /**
     * @methodtype constructor
     * @param name
     * @param coordinate 
     */
    public Location(String name, Coordinate coordinate) {
        this.name = new String(name);
        this.coordinate = new Coordinate(coordinate);
        touch();
    }
    
    
    
    /**
     * @methodtype get
     * @return 
     */
    public String getName() {
        return name;
    }
    
    /**
     * @methodtype get
     * @return 
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    
    
    /**
     * @metodtype query
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     * @methodtype query
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.coordinate, other.coordinate)) {
            return false;
        }
        return true;
    }
}
