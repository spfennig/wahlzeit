
package org.wahlzeit.model;

import java.util.logging.Logger;
import org.wahlzeit.services.LogBuilder;



 public class PlantPhotoFactory extends PhotoFactory {
	/**
	 * @methodtype factory
         * @return PlantPhoto
	 */
	public PlantPhoto createPhoto() {
            return new PlantPhoto();
	}

	/**
	 * Creates a new photo with the specified id
         * @methodtype factory
         * @param id
         * @return PlantPhoto
	 */
	public PlantPhoto createPhoto(PhotoId id) {
            return new PlantPhoto(id);
	}

	/**
	 * Loads a photo. The Java object is loaded from the Google Datastore, the Images in all sizes are loaded from the
	 * Google Cloud storage.
         * @methodtype command
         * @param id
         * @return PlantPhoto
	 */
	public PlantPhoto loadPhoto(PhotoId id) {
            return null;
	}
}
