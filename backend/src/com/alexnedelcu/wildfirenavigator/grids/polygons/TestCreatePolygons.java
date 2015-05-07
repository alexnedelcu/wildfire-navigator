package com.alexnedelcu.wildfirenavigator.grids.polygons;

import java.util.ArrayList;

import com.alexnedelcu.wildfirenavigator.SquareUnit;
import com.alexnedelcu.wildfirenavigator.data.type.LatLong;
import com.alexnedelcu.wildfirenavigator.grids.SurroundingWFMap;

public class TestCreatePolygons {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// creating the source code
		LatLong source =  new LatLong(18.484837, -68.457084);
		LatLong destination =  new LatLong(19.802903, -71.692558);
		
		SurroundingWFMap map = new SurroundingWFMap(source, destination, 2.0);
		map.generateGrid();
		GeneratePolygons genPoly = new GeneratePolygons(map);
		
		String json = genPoly.generatePolygonsForGoogleMaps();
		
		System.out.println(json);
	}

}
