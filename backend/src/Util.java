
public class Util {
	final static double R = 6378.1;
	/**
	 * Given a LatLong point, a distance and a bearing, calculate the destination LatLong point
	 * @param currLong
	 * @param currLat
	 * @param distance
	 * @param bearing
	 * @return
	 */
	public static LatLong getNewLatLong (double currLong, double currLat, double distance, double bearing) {
		double currLatRad = currLat * Math.PI / 180;
		double currLongRad = currLong * Math.PI / 180; // converting to radians
		
		double newLat = Math.asin( Math.sin(currLatRad)*Math.cos(distance/R) +
                Math.cos(currLatRad)*Math.sin(distance/R)*Math.cos(bearing) );
		
		double newLong = currLongRad + Math.atan2(Math.sin(bearing)*Math.sin(distance/R)*Math.cos(currLatRad),
                     Math.cos(distance/R)-Math.sin(currLatRad)*Math.sin(newLat));
		
		newLat = newLat * 180 / Math.PI;
		newLong = newLong * 180 / Math.PI;
		
		return new LatLong(newLat, newLong);
				
	}

}
