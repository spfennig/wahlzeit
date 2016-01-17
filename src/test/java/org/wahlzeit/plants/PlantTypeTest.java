package org.wahlzeit.plants;


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PlantPhoto;

import java.util.Iterator;



public class PlantTypeTest {
    private static PlantType awesomeSuperType;
    private static PlantType smallType;
    private static PlantType smallTypeCopy;
    private static PlantType subType1;
    private static PlantType subType2;
    private static PlantPhoto photoSmallPlant;
    private static PlantPhoto photo2;
    private static Plant smallPlant;



    @BeforeClass
    public static void setUp() {
        smallPlant = PlantManager.getInstance().createPlant("Small Plant");

        awesomeSuperType = PlantManager.getInstance().getPlantType("Awesome Plant");

        subType1 = PlantManager.getInstance().getPlantType("Sub Type 1");
        subType2 = PlantManager.getInstance().getPlantType("Sub Type 2");

        smallType = PlantManager.getInstance().getPlantType("Some Small Plant");
        smallType.setHasBloom(false);
        smallType.setIndoorSuitable(true);
        smallType.setTypicalHeight(10);
        smallType.setSuperType(awesomeSuperType);
        smallType.addSubtype(subType1);
        smallType.addSubtype(subType2);

        smallTypeCopy = PlantManager.getInstance().getPlantType("Some Small Plant");

        /* TODO:
            org.wahlzeit.plants.PlantTypeTest > testPlantType FAILED
            java.lang.ExceptionInInitializerError at PlantTypeTest.java:48
            Caused by: java.lang.NullPointerException at PlantTypeTest.java:48
        */
        //photoSmallPlant = new PlantPhoto();
        //Photo p = new Photo();
        //Photo p = PhotoFactory.getInstance().createPhoto();
        //photo2 = new PlantPhoto();

//        smallPlant.addPhoto(photoSmallPlant);
//        smallPlant.addPhoto(photo2);

        smallPlant.getPlantType().setSuperType(awesomeSuperType);
        smallPlant.getPlantType().addSubtype(subType1);
        smallPlant.getPlantType().addSubtype(subType2);
    }



    @Test
    public void testPantManager() {
        PlantType subtype1Copy = PlantManager.getInstance().getPlantType("Sub Type 1");
        Assert.assertEquals(subType1, subtype1Copy);
    }



    @Test
    public void testPlantType() {
        Plant smallPlant = new Plant(smallType);
        Assert.assertEquals(smallPlant.getPlantType(), smallType);

        Plant awesomePlant = new Plant(awesomeSuperType);
        awesomePlant.setPlantType(smallType);
        Assert.assertEquals(awesomePlant.getPlantType(), smallType);
    }



    /* TODO: Add test */
//    @Test
    public void testPhotos() {
        /* Try to add same photo again */
        smallPlant.addPhoto(photo2);
        Iterator<Photo> it = smallPlant.getPhotoIterator();
        int n = 0;
        while(it.hasNext()) {
            Photo photo = it.next();
            if(photo != photoSmallPlant || photo != photo2) {
                Assert.fail();
            }
            n++;
        }
        Assert.assertEquals(2, n);
    }



    @Test
    public void testSuperType() {
        Assert.assertEquals(smallType.getSuperType(), awesomeSuperType);
    }



    @Test
    public void testSubTypes() {
        Iterator<PlantType> it = smallPlant.getPlantType().getSubtypeIterator();
        int n = 0;
        while(it.hasNext()) {
            PlantType plantType = it.next();
            System.out.println(plantType.getName());
            if(!(plantType == subType1 || plantType == subType2)) {
                Assert.fail();
            }
            n++;
        }
        Assert.assertEquals(2, n);
    }
}
