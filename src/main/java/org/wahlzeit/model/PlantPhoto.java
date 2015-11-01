
package org.wahlzeit.model;



public class PlantPhoto extends Photo {
    private String plantName;
    private boolean isIndoorUsable;
    
    
    
    /**
     * @methodtype constructor
     */
    public PlantPhoto() {
        super();
    }
    
    /**
     * @methodtype constructor
     * @param id 
     */
    public PlantPhoto(PhotoId id) {
        super(id);
    }
    
    /**
     * @methodtype get
     * @return 
     */
    public String getPlantName() {
        return plantName;
    }
    
    /**
     * @methodtype set
     * @param plantName 
     */
    public void setPlantName(String plantName) {
        this.plantName = plantName;
        touch();
    }
    
    /**
     * @methodtype get
     * @return 
     */
    public boolean isIndoorUsable() {
        return isIndoorUsable;
    }
    
    /**
     * @methodtype set
     * @param isIndoorUsable 
     */
    public void setIndoorUsable(boolean isIndoorUsable) {
        this.isIndoorUsable = isIndoorUsable;
        touch();
    }
}
