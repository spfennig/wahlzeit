/*
 * 
 */
package org.wahlzeit.model;


public class Coordinate
{
	private double latitude;
	private double longitude;
	
	Coordinate(double latitude, double longitude)
	{
            if(boundsOK(latitude))
            {
                this.latitude = latitude;
            }
            if(boundsOK(longitude))
            {
                this.longitude = longitude;
            }
	}
        
        private boolean boundsOK(double var)
        {
            if(-90 <= var && var <= 90)
            {
                return true;
            }
            throw new IllegalArgumentException("-90 <= lat|lon <= 90");
        }
	
	public double getLatitude()
	{
            return latitude;
	}
	
	public double getLongitude()
	{
            return longitude;
	}
	
	
	
	public double getLatitudinalDistance(Coordinate coordinate)
	{
            return Math.abs(coordinate.getLatitude() - latitude);
	}
	
	public double getLongitudinalDistance(Coordinate coordinate)
	{
            return Math.abs(coordinate.getLongitude() - longitude);
	}
        
        public Coordinate getDistance(Coordinate coordinate)
	{
            return new Coordinate(  getLatitudinalDistance(coordinate),
                                    getLongitudinalDistance(coordinate));
	}
}
