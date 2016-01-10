package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Entity;
import org.wahlzeit.plants.Plant;



@Entity
public class PlantPhoto extends Photo {
    private Plant plant = null;



    /**
     * @methodtype initialization
     * @methodproperty constructor
     */
    public PlantPhoto() {
        super();
    }
    
    /**
     * @methodtype initialization
     * @methodproperty constructor
     * @param id PhotoId
     */
    public PlantPhoto(PhotoId id) {
        super(id);
    }



    /**
     * @methodtype Query: Get
     * @methodproperty Implementation: Primitive
     * @return Plant
     */
    public Plant getPlant() {
        return plant;
    }

    /**
     * @methodtype Mutation: Set
     * @methodproperty Implementation: Primitive
     * @param plant Plant
     */
    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}
