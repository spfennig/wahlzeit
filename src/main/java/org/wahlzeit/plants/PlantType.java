package org.wahlzeit.plants;

import com.googlecode.objectify.annotation.Entity;
import org.wahlzeit.services.DataObject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;



@Entity
public class PlantType extends DataObject {
    private Set<Plant> plants = new HashSet<Plant>();
    private PlantType superType;
    private Set<PlantType> subTypes = new HashSet<PlantType>();
    private String name;
    private boolean hasBloom;
    private boolean indoorSuitable;
    private double typicalHeight;


    /**
     * @methodtype Mutation: Initialization
     * @methodproperty Convenience: Constructor
     * @param name Plant Name
     */
    public PlantType(String name) {
        super();
        this.name = name;
        this.superType = null;
        this.hasBloom = false;
        this.indoorSuitable = false;
        this.typicalHeight = Double.NaN;
        touch();
    }



    /**
     * @methodtype get
     * @methodproperty primitive
     * @return Name
     */
    public String getName() {
        return name;
    }



    /**
     * @methodtype Query: Get
     * @methodproperty Implementation: Primitive
     * @return true if plant has a bloom
     */
    public boolean getHasBloom() {
        return hasBloom;
    }

    /**
     * @methodtype Mutation: Set
     * @methodproperty Implementation: Primitive
     * @param hasBloom true if plant as a bloom
     */
    public void setHasBloom(boolean hasBloom) {
        this.hasBloom = hasBloom;
    }



    /**
     * @methodtype get, boolean-query
     * @methodproperty primitive
     * @return true if indoor suitable
     */
    public boolean isIndoorSuitable() {
        return indoorSuitable;
    }

    /**
     * @methodtype Mutation: Set
     * @methodproperty Implementation: Primitve
     * @param indoorSuitable true if indoor suitable
     */
    public void setIndoorSuitable(boolean indoorSuitable) {
        this.indoorSuitable = indoorSuitable;
    }



    /**
     * @methodtype Query: Get
     * @methodproperty Implementation: Primitive
     * @return Typical Height
     */
    public double getTypicalHeight() {
        return typicalHeight;
    }

    /**
     * @methodtype Mutation: Set
     * @methodproperty Implementation: Primitve
     * @param typicalHeight Typical Heigt
     */
    public void setTypicalHeight(double typicalHeight) {
        this.typicalHeight = typicalHeight;
    }



    /**
     * @methodtype Mutation: Command
     * @methodproperty Implementation: Primitive
     * @param plant Plant
     */
    public void addPlant(Plant plant) {
        if(!plants.contains(plant)) {
            plants.add(plant);
        }
    }

    /**
     * @methodtype Query: Get
     * @methodproperty Inheritance: Regular
     * @return Plants Iterator
     */
    public Iterator<Plant> getPlantIterator() {
        return plants.iterator();
    }



    /**
     * @methodtype Query: Get
     * @methodproperty Implementation: Primitive
     * @return Super Type
     */
    public PlantType getSuperType() {
        return superType;
    }

    /**
     * @methodtype Mutation: Set
     * @methodproperty Implementation: Primitve
     * @param superType PlantType
     */
    public void setSuperType(PlantType superType) {
        this.superType = superType;
    }



    /**
     * @methodtype Query: Get
     * @methodproperty Inheritance: Regular
     * @return Sub Types Iterator
     */
    public Iterator<PlantType> getSubtypeIterator() {
        return subTypes.iterator();
    }

    /**
     * @methodtype Mutation: Command
     * @methodproperty Implementation: Primitive
     * @param plantType PlantType
     */
    public void addSubtype(PlantType plantType) {
        assert plantType != null : "plantType is null.";
        if(!subTypes.contains(plantType)) {
            subTypes.add(plantType);
        }
    }
}
