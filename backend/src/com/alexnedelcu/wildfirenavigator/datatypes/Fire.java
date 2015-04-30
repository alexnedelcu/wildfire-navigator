package com.alexnedelcu.wildfirenavigator.datatypes;

import java.io.Serializable;

import com.alexnedelcu.wildfirenavigator.data.LatLong;

public class Fire implements Serializable {
	LatLong latlong;
	
	public LatLong getLatlong() {
		return latlong;
	}

	public void setLatlong(LatLong latlong) {
		this.latlong = latlong;
	}

	public Fire () {
		
	}
	
	
}
