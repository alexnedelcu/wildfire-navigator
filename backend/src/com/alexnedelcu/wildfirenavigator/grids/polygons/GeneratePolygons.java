package com.alexnedelcu.wildfirenavigator.grids.polygons;

import java.util.ArrayList;

import com.alexnedelcu.wildfirenavigator.SquareUnit;
import com.alexnedelcu.wildfirenavigator.data.type.Fire;
import com.alexnedelcu.wildfirenavigator.grids.SurroundingWFMap;

public class GeneratePolygons {
	
	SurroundingWFMap grid ;
	
	
	public GeneratePolygons (SurroundingWFMap grid) {
		this.grid = grid;
	}
	
	public String generatePolygonsForGoogleMaps() {
		ArrayList<SquareUnit> dangerousTiles = grid.getListOfDangerousTiles();
		
		String json = "[";
		for (int i=0; i<dangerousTiles.size(); i++) {
			json = json + generateTilePolygon(dangerousTiles.get(i)) + ",";
		}
		
		// remove  the  last comma
		if (json.length()>1) json = json.substring(0, json.length()-1);
		
		json = json + "]";
		return json;
	}
	
	private String generateTilePolygon(SquareUnit su) {
		return "[{"
					+ "\"lat\":"+su.getLBCorner().getLatitude()+","
					+ "\"lng\":"+su.getLBCorner().getLongitude()
				+ "},{"
					+ "\"lat\":"+su.getLTCorner().getLatitude()+","
					+ "\"lng\":"+su.getLTCorner().getLongitude()
				+ "},{"
					+ "\"lat\":"+su.getRTCorner().getLatitude()+","
					+ "\"lng\":"+su.getRTCorner().getLongitude()
				+ "},{"
					+ "\"lat\":"+su.getRBCorner().getLatitude()+","
					+ "\"lng\":"+su.getRBCorner().getLongitude()
				+ "},{"
					+ "\"lat\":"+su.getLBCorner().getLatitude()+","
					+ "\"lng\":"+su.getLBCorner().getLongitude()
				+ "}]";
	}
}
