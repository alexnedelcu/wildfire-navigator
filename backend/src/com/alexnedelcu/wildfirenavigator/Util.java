package com.alexnedelcu.wildfirenavigator;

import com.alexnedelcu.wildfirenavigator.data.type.LatLong;

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

	 public static double distance(LatLong pos1, LatLong pos2) {
		double lat1 = pos1.getLatitude();
		double lng1 = pos1.getLongitude();
		double lat2 = pos2.getLatitude();
		double lng2 = pos2.getLongitude();

	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lng2-lng1);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double dist = (float) (R * c);

	    return dist;
    }
}
