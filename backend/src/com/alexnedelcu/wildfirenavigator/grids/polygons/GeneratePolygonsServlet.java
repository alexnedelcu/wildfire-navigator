package com.alexnedelcu.wildfirenavigator.grids.polygons;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alexnedelcu.wildfirenavigator.SquareUnit;
import com.alexnedelcu.wildfirenavigator.data.type.LatLong;
import com.alexnedelcu.wildfirenavigator.grids.SurroundingWFMap;

public class GeneratePolygonsServlet extends HttpServlet  {

	public static void main(String[] args) {
		
	}
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            		throws ServletException, IOException
	{
		
		PrintWriter writer = response.getWriter();
		
		Double sourceLat = Double.parseDouble(request.getParameter("sLat"));
		
		Double sourceLong = Double.parseDouble(request.getParameter("sLng"));
		Double destinationLat = Double.parseDouble(request.getParameter("dLat"));
		Double destinationLong = Double.parseDouble(request.getParameter("dLng"));

		//writer.println(sourceLat);
		
		// creating the source code
		//LatLong source =  new LatLong(31.720218, -88.453191);
		//LatLong destination =  new LatLong(31.741461, -88.461174);
		
		LatLong source =  new LatLong(sourceLat, sourceLong);
		LatLong destination =  new LatLong(destinationLat, destinationLong);
		
		SurroundingWFMap map = new SurroundingWFMap(source, destination, 1.0);
		map.generateGrid();
		GeneratePolygons genPoly = new GeneratePolygons(map);
		
		String json = genPoly.generatePolygonsForGoogleMaps();
		
		writer.print(json);
		
		response.addHeader("Access-Control-Allow-Origin", "*");
	
	}
}
