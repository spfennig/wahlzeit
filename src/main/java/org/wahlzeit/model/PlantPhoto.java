package org.wahlzeit.model;



public class PlantPhoto extends Photo {
    private String plantName;
    private boolean indoorSuitable;
    private Location location;



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
     * @param id 
     */
    public PlantPhoto(PhotoId id) {
        super(id);
    }



    /**
     * @methodtype get
     * @methodproperty primitive
     * @return 
     */
    public String getPlantName() {
        return plantName;
    }
    
    /**
     * @methodtype set
     * @methodproperty primitive
     * @param plantName 
     */
    public void setPlantName(String plantName) {
        this.plantName = plantName;
        touch();
    }



    /**
     * @methodtype get, boolean-query
     * @methodproperty primitive
     * @return 
     */
    public boolean isIndoorSuitable() {
        return indoorSuitable;
    }
    
    /**
     * @methodtype set
     * @methodproperty primitive
     * @param indoorSuitable
     */
    public void setIndoorSuitable(boolean indoorSuitable) {
        this.indoorSuitable = indoorSuitable;
        touch();
    }



    /**
     * @methodtype get
     * @methodproperty primitive
     * @return Location
     */
    public Location getLocation()
    {
        return location;
    }

    /**
     * @methodtype set
     * @methodproperty primitive
     * @param location
     */
    public void setLocation(Location location)
    {
        this.location = location;
        touch();
    }
}
