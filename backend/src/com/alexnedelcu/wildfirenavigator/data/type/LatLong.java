package com.alexnedelcu.wildfirenavigator.data.type;

import java.io.Serializable;

public class LatLong implements Serializable  {
	double latitude;
	double longitude;

	public LatLong(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String toString() {
		return latitude + ", " + longitude;
	}
}
