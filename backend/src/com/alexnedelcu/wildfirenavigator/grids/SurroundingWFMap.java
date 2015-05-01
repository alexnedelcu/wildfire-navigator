package com.alexnedelcu.wildfirenavigator.grids;

import java.util.ArrayList;

import com.alexnedelcu.wildfirenavigator.SquareUnit;
import com.alexnedelcu.wildfirenavigator.Util;
import com.alexnedelcu.wildfirenavigator.data.FireFilter;
import com.alexnedelcu.wildfirenavigator.data.WildfireManager;
import com.alexnedelcu.wildfirenavigator.data.type.Fire;
import com.alexnedelcu.wildfirenavigator.data.type.LatLong;

public class SurroundingWFMap {
	

	ArrayList<ArrayList<SquareUnit>> grid = new ArrayList<ArrayList<SquareUnit>>();
	double distance, increment;
	LatLong source, destination;
	FireFilter fireFilter;
	LatLong leftTopMostLatLong;
	
	SurroundingWFMap (LatLong currentLocation, LatLong destinationLocation, double increment) {
		
		double srcDestdistance = Util.distance(currentLocation, destinationLocation);
		distance = Math.round(srcDestdistance * 4); // the center will be the current position, and we want to cover a square area twice as large as the area to the destination
		this.increment = increment;
		LatLong topMostLatLong;
		
		topMostLatLong = Util.getNewLatLong (source.getLongitude(), source.getLatitude(), distance/2, 0);
		leftTopMostLatLong = Util.getNewLatLong (topMostLatLong.getLongitude(), topMostLatLong.getLatitude(), distance/2, Math.PI * 3/2);

		//
		//
		//	Create the filter that only loads fires within the area of interest
		//
		//
		final SquareUnit vecinityArea = new SquareUnit(currentLocation, distance);
		fireFilter = new FireFilter() {

			@Override
			public boolean isValid(Fire fire) {
				if (vecinityArea.isLatLongConfined(fire.getLatlong()))
					return true;
				else
					return false;
			}
			
		};

	}

	
	public void generateGrid() {
		
		double n = distance/increment;
		
		// make sure the center of the grid is on the user's location
		if (n % 2 == 0) { n++; distance += increment; }

		// start creating squares so that the center of the covered area is the location given by the user
		LatLong nextEastLatLong;
		
		
		for (int i=0; i<n; i++) {
			
			nextEastLatLong = leftTopMostLatLong;

			ArrayList<SquareUnit> newGridRow = new ArrayList<SquareUnit>();
			for (int j=0; j<n; j++) {

				System.out.println(""+nextEastLatLong.getLatitude()+","+nextEastLatLong.getLongitude()+"");
				
				// calculate the coordinates of the next right tile
				nextEastLatLong = Util.getNewLatLong(nextEastLatLong.getLongitude(), nextEastLatLong.getLatitude(), increment, Math.PI * 1/2);
				
				// add the tile to the grid row
				SquareUnit square = new SquareUnit(nextEastLatLong, increment);
				newGridRow.add(square);
				
				
			}
			
			// add the rows of tiles to the grid
			grid.add(newGridRow);

			leftTopMostLatLong = Util.getNewLatLong (leftTopMostLatLong.getLongitude(), leftTopMostLatLong.getLatitude(), increment, Math.PI * 1); // going north one increment
		}
		
		markDangerousTiles();
	}
	
	private void markDangerousTiles () {
		
		WildfireManager wfManager = WildfireManager.getInstance();
		wfManager.loadMostRecentWildfiresFromFile(fireFilter);
		
		ArrayList<Fire> fires = (ArrayList<Fire>) wfManager.getFires().clone();

		for (int r=0; r<grid.size(); r++) {
			for (int c=0; c<grid.size(); c++) {
				for (int i=0; i<fires.size(); i++ ) {
					if (grid.get(r).get(c).isLatLongConfined(fires.get(i).getLatlong())) {
						grid.get(r).get(c).markAsBurning();
					}
				}
			}
		}
	}
}
