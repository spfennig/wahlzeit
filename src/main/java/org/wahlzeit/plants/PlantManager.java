package org.wahlzeit.plants;

import java.util.HashMap;



public class PlantManager {
    private static HashMap<String, PlantType> plantTypes = null;
    private static PlantManager instance = null;



    /**
     * @methodtype Query: Get
     * @methodproperty Implementation: Regular, Primitive
     * @return PlantManager
     */
    public static PlantManager getInstance() {
        if(instance == null) {
            instance = new PlantManager();
            plantTypes = new HashMap<String, PlantType>();
        }
        return instance;
    }



    /**
     * @methodtype Query: Get
     * @methodproperty Implementation: Regular, Composed
     * @param typeName Type Name
     * @return Plant
     */
    public Plant getPlant(String typeName) {
        assert typeName != null : "typename is null";

        PlantType plantType = getPlantType(typeName);
        Plant plant = new Plant(plantType);
        plantType.addPlant(plant);
        return plant;
    }



    /**
     * @methodtype Query: Get
     * @methodproperty Implementation: Regular, Composed
     * @param typeName Type Name
     * @return PlantType
     */
    public PlantType getPlantType(String typeName) {
        PlantType plantType = basicGetPlantType(typeName);
        if(plantType == null) {
            plantType = new PlantType(typeName);
            addPlantType(typeName, plantType);
        }
        return plantType;
    }

    /**
     * @methodtype Query: Get
     * @methodproperty Implementation: Primitive
     * @param typeName Type Name
     * @return PlantType, null
     */
    private PlantType basicGetPlantType(String typeName) {
        if(plantTypes.containsKey(typeName)) {
            return plantTypes.get(typeName);
        }
        return null;
    }

    /**
     * @methodtype Mutation: Command
     * @methodproperty Implementation: Regular, Primitive
     * @param typeName Type Name
     * @param plantType PlantType
     */
    public void addPlantType(String typeName, PlantType plantType) {
        if(!plantTypes.containsKey(typeName)) {
            plantTypes.put(typeName, plantType);
        }
    }
}
