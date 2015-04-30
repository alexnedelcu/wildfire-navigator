package com.alexnedelcu.wildfirenavigator;

import com.alexnedelcu.wildfirenavigator.data.LatLong;

public class SquareUnit {
	double sideSize; // the size of the square in meters
	LatLong latLongPoint;	// lat and long of the center of the square
	boolean fire = false;
	LatLong rightLatLong, leftLatLong, topLatLong, bottomLatLong;
	
	SquareUnit (LatLong latLongPoint, double sideSize) {
		this.latLongPoint=latLongPoint;
		this.sideSize=sideSize;
		
		rightLatLong = Util.getNewLatLong(latLongPoint.getLongitude(), latLongPoint.getLatitude(), sideSize, Math.PI*1/2);
		leftLatLong = Util.getNewLatLong(latLongPoint.getLongitude(), latLongPoint.getLatitude(), sideSize, Math.PI*3/2);
//		topLatLong = Util.getNewLatLong(latLongPoint.getLongitude(), latLongPoint.getLatitude(), Math.sqrt(2)*sideSize, 0*Math.PI);
//		bottomLatLong = Util.getNewLatLong(latLongPoint.getLongitude(), latLongPoint.getLatitude(), Math.sqrt(2)*sideSize, 1*Math.PI);
//		System.out.println("right latlong: "+rightLatLong.getLatitude() + ","+rightLatLong.getLongitude());
		//System.out.println(rightLatLong);
	}
	
	public double getSideSize() {
		return sideSize;
	}
	public void setSideSize(int sideSize) {
		this.sideSize = sideSize;
	}
	public double getLatitude() {
		return latLongPoint.getLatitude();
	}
	public double getLongitude() {
		return latLongPoint.getLongitude();
	}

	public LatLong getLTCorner () {
		return Util.getNewLatLong(leftLatLong.getLongitude(), leftLatLong.getLatitude(),sideSize, 0*Math.PI);
	}

	public LatLong getLBCorner () {
		return Util.getNewLatLong(leftLatLong.getLongitude(), leftLatLong.getLatitude(), sideSize, 1*Math.PI);
	}

	public LatLong getRBCorner () {
		return Util.getNewLatLong(rightLatLong.getLongitude(), rightLatLong.getLatitude(), sideSize, 1*Math.PI);
	}

	public LatLong getRTCorner () {
		return Util.getNewLatLong(rightLatLong.getLongitude(), rightLatLong.getLatitude(), sideSize, 0*Math.PI);
	}
	
	public boolean isLatLongConfined(LatLong x) {
		if (getLTCorner().getLatitude() >= x.getLatitude() &&
				getLBCorner().getLatitude() <= x.getLatitude() &&
				getRTCorner().getLongitude() >= x.getLongitude() &&
				getLTCorner().getLongitude() <= x.getLongitude() )
			return true;
		return false;
	}

	public void markAsBurning() {
		this.fire=true;
	}
	public boolean isFire() {
		return fire;
	}
	
	
}
