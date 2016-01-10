package org.wahlzeit.plants;

import com.googlecode.objectify.annotation.Entity;
import org.wahlzeit.model.Location;
import org.wahlzeit.model.Photo;
import org.wahlzeit.services.DataObject;

import java.util.HashSet;
import java.util.Iterator;



@Entity
public class Plant extends DataObject {
    private HashSet<Photo> photos = new HashSet<Photo>();
    private PlantType plantType;
    private Location location;
    private float height;



    /**
     * @methodtype Mutation: Initialization
     * @methodproperty Convenience: Constructor
     * @param plantType PlantType
     */
    public Plant(PlantType plantType) {
        super();
        this.plantType = plantType;
    }



    /**
     * @methodtype Query: Get
     * @methodproperty Implementation: Primitive
     * @return PlantType
     */
    public PlantType getPlantType() {
        return plantType;
    }

    /**
     * @methodtype Mutation: Set
     * @methodproperty Implementation: Primitive
     * @param plantType PlantType
     */
    public void setPlantType(PlantType plantType) {
        this.plantType = plantType;
    }



    /**
     * @methodtype Query: Get
     * @methodproperty Implementatino: Primitive
     * @return Location
     */
    public Location getLocation()
    {
        return location;
    }

    /**
     * @methodtype Mutation: Set
     * @methodproperty Implementation: Primitive
     * @param location Location
     */
    public void setLocation(Location location)
    {
        this.location = location;
        touch();
    }



    /**
     * @methodtype Query: Get
     * @methodproperty Implementation: Primitive
     * @return Heit
     */
    public float getHeight() {
        return height;
    }

    /**
     * @methodtype Mutation: Set
     * @methodproperty Implementation: Primitive
     * @param height Height
     */
    public void setHeight(float height) {
        this.height = height;
    }



    /**
     * @methodtype Mutation: Command
     * @methodproperty Implementation: Primitive
     * @param photo Photo
     */
    public void addPhoto(Photo photo) {
        if(!photos.contains(photo)) {
            photos.add(photo);
        }
    }

    /**
     * @methodtype Query: Get
     * @methodproperty Implementation: Primitive
     * @return Photo Iterator
     */
    public Iterator getPhotoIterator() {
        return photos.iterator();
    }
}
