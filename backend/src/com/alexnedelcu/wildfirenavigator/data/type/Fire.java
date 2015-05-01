package com.alexnedelcu.wildfirenavigator.data.type;

import java.io.Serializable;

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
