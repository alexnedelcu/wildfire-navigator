package com.alexnedelcu.wildfirenavigator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import com.alexnedelcu.wildfirenavigator.data.LatLong;


public class Grid {
	ArrayList<ArrayList<SquareUnit>> grid = new ArrayList<ArrayList<SquareUnit>>();
	ArrayList<ArrayList<Boolean>> visitedGrid;
	double latitude, longitude;
	double latLongRBCorner, latLongRTCorner, latLongLBCorner, latLongLTCorner;
	
	Grid (double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public void generateGrid(int distance, double increment) {
		
		double n = distance/increment;
		
		// make sure the center of the grid is on the user's location
		if (n % 2 == 0) { n++; distance += increment; }

		// start creating squares so that the center of the covered area is the location given by the user
		LatLong topMostLatLong = Util.getNewLatLong (longitude, latitude, distance/2, 0);
		LatLong leftMostLatLong = Util.getNewLatLong (topMostLatLong.getLongitude(), topMostLatLong.getLatitude(), distance/2, Math.PI * 3/2);
		LatLong nextEastLatLong;
		
		
		for (int i=0; i<n; i++) {
			
			nextEastLatLong = leftMostLatLong;

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

			leftMostLatLong = Util.getNewLatLong (leftMostLatLong.getLongitude(), leftMostLatLong.getLatitude(), increment, Math.PI * 1); // going north one increment
		}
		
		
		
	}
	
	public void loadFires(String fileName) {

		ArrayList<LatLong> fires = new ArrayList<LatLong> (); 
		
		
        //Get scanner instance
        Scanner scanner=null;
		try {
			scanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
         
        //Set the delimiter used in file
        scanner.useDelimiter(",");
         
        //Get all tokens and store them in some data structure
        //I am just printing them
        scanner.nextLine();
        while (scanner.hasNextLine())
        {
        	String line = scanner.nextLine();
        	String[] words = line.split(",");
        	
        	if (words.length > 1) {
        	
	        	double latitude, longitude;
	        	latitude = Double.parseDouble(new String(words[0]));
	        	longitude = Double.parseDouble(new String(words[1]));
	        	
	        	LatLong potentialSurroundingFire = new LatLong(latitude, longitude);
				if (this.isLatLongConfined(potentialSurroundingFire)) 
		        	fires.add(potentialSurroundingFire);
        	}
        }
         
        //Do not forget to close the scanner 
        scanner.close();
		

		for (int i=0; i<fires.size(); i++ ) {
			for (int r=0; r<grid.size(); r++) {
				for (int c=0; c<grid.size(); c++) {
					if (grid.get(r).get(c).isLatLongConfined(fires.get(i))) {
						grid.get(r).get(c).markAsBurning();
					}
				}
			}
		}
	}
	

	public boolean isLatLongConfined(LatLong x) {
		if (grid.get(0).get(0).getLatitude() >= x.getLatitude() &&
				grid.get(grid.size()-1).get(0).getLatitude() <= x.getLatitude() &&
				grid.get(0).get(0).getLongitude() < x.getLongitude() &&
				grid.get(0).get(grid.size()-1).getLongitude() > x.getLongitude() )
			return true;
		return false;
	}
	
	void printGrid() {
		for(int i=0; i<grid.size(); i++) {
			for (int j=0; j<grid.size(); j++) {
				System.out.print(grid.get(i).get(j).fire + " ");
			}
			System.out.println();
		}
	}
	
	public String printPolygons() {
		visitedGrid = new ArrayList<ArrayList<Boolean>>();
		String json = "[";
		for(int i=0; i<grid.size(); i++) {
			for (int j=0; j<grid.size(); j++) {
				if (grid.get(i).get(j).isFire()) {
					String jsn = printPolygonCoords(i, j) + ",";
					if (jsn.length() > 3)
						json = json + jsn;
				}
			}
		}
		json = json + "]";
		System.out.println(json);
		return json;
	}
	
	private String printPolygonCoords(int y, int x) {
		String result = "[";
		ArrayList<SquareUnit> pendingSUs = new ArrayList<SquareUnit>();
		
		// write false in all cells
		for(int i=0; i<grid.size(); i++) {
			visitedGrid.add(new ArrayList<Boolean>());
			for (int j=0; j<grid.size(); j++) 
				visitedGrid.get(i).add( false);
		}
		

		for(int i=0; i<grid.size(); i++) 
			for (int j=0; j<grid.size(); j++) 
				if (!visitedGrid.get(i).get(j)) {
					if (grid.get(i).get(j).isFire())
						result = result + printPolygonCoordsRecursive(i, j, result, visitedGrid);

					visitedGrid.get(i).set(j, true);
				}

		return result+"]";
	}

	private String printPolygonCoordsRecursive(int y, int x, String result, ArrayList<ArrayList<Boolean>> visitedGrid) {

		ArrayList<Object> neighborsUnderFireSet = getNeighborsUnderFire(y, x);
		ArrayList<Object> neighborsUnderFire =  (ArrayList<Object>) neighborsUnderFireSet.get(0);
		ArrayList<Integer> posY = (ArrayList<Integer>) neighborsUnderFireSet.get(1);
		ArrayList<Integer> posX = (ArrayList<Integer>) neighborsUnderFireSet.get(2);

		result = result + "{";
		result = result + "lat:" + grid.get(y).get(x).getLatitude() + ",";
		result = result + "lng:" + grid.get(y).get(x).getLongitude() + "},";
		
//		System.out.println (neighborsUnderFire.size() + " " + posY.size() +" " + posX.size());
//		System.out.println (posY.size());
//		System.out.println (posX.size());

		ArrayList<SquareUnit> uniqueNeighborsUnderFire = new ArrayList<SquareUnit>();
		ArrayList<Integer> uniquePosY = new ArrayList<Integer>();
		ArrayList<Integer> uniquePosX = new ArrayList<Integer>();
		

		ArrayList<SquareUnit> edgeNeighborsUnderFire = new ArrayList<SquareUnit>();
		ArrayList<Integer> edgePosY = new ArrayList<Integer>();
		ArrayList<Integer> edgePosX = new ArrayList<Integer>();

		// get rid of the visited ones
		for (int i=0; i<neighborsUnderFire.size(); i++) {
			
			
			if (!visitedGrid.get(posY.get(i)).get(posX.get(i))) {
				
				ArrayList<Object> neighborsUnderFireSetRec = getNeighborsUnderFire(posY.get(i), posX.get(i));
				neighborsUnderFire.addAll((Collection<? extends Object>) neighborsUnderFireSetRec.get(0));
				posY.addAll((Collection<? extends Integer>) neighborsUnderFireSetRec.get(1));
				posX.addAll((Collection<? extends Integer>) neighborsUnderFireSetRec.get(2));
				
				uniqueNeighborsUnderFire.add ((SquareUnit) neighborsUnderFire.get(i));
				uniquePosY.add (posY.get(i));
				uniquePosX.add (posX.get(i));
				visitedGrid.get(posY.get(i)).set(posX.get(i), true);
			}
		}
		

		// get only the edge neighbors
		for (int i=0; i<uniqueNeighborsUnderFire.size(); i++) {
			ArrayList<Object> ngrs = getNeighborsUnderFire(uniquePosY.get(i), uniquePosX.get(i));
			if(ngrs.size() < 7) {
				edgeNeighborsUnderFire.add ((SquareUnit) uniqueNeighborsUnderFire.get(i));
				edgePosY.add (uniquePosY.get(i));
				edgePosX.add (uniquePosX.get(i));
			}
		}
		
		// create json
		for (int i=0; i<uniqueNeighborsUnderFire.size(); i++) {
			result = result + "{";
			result = result + "lat:" + edgeNeighborsUnderFire.get(i).getLatitude() + ",";
			result = result + "lng:" + edgeNeighborsUnderFire.get(i).getLongitude() + "},";
		}

		visitedGrid.get(y).set(x, true);
		
		return result;
		
	}
	
	public ArrayList<Object> getNeighbors(int i, int j) {
		ArrayList<SquareUnit> neighbors = new ArrayList<SquareUnit>();
		ArrayList<Integer> posY = new ArrayList<Integer>();
		ArrayList<Integer> posX = new ArrayList<Integer>();
		
		// get the neighbors on top
		if (i>0) {
			if (j>0) {
				neighbors.add(this.grid.get(i-1).get(j-1));
				posY.add(i-1);
				posX.add(j-1);
			}
			
			neighbors.add(this.grid.get(i-1).get(j));
			posY.add(i-1);
			posX.add(j);
			
			
			if (j<grid.size()-1) {
				neighbors.add(this.grid.get(i-1).get(j+1));
				posY.add(i-1);
				posX.add(j+1);
			}
				
		}

		// get the neighbors in the right
		if (j<grid.size()-1) {
			neighbors.add(this.grid.get(i).get(j+1));
			posY.add(i);
			posX.add(j+1);
			
			
			if (i<grid.size()-1) {
				neighbors.add(this.grid.get(i+1).get(j+1));
				posY.add(i+1);
				posX.add(j+1);
			}
		}

		// get the neighbors in the left
		if (j>0) {
			neighbors.add(this.grid.get(i).get(j-1));
			posY.add(i);
			posX.add(j-1);
			if (i<grid.size()-1) {
				neighbors.add(this.grid.get(i+1).get(j-1));
				posY.add(i+1);
				posX.add(j-1);
			}
		}

		// get the neighbors in the bottom
		if (i<grid.size()-1) {
				neighbors.add(this.grid.get(i+1).get(j));
				posY.add(i+1);
				posX.add(j);
		}
		
		ArrayList<Object> results = new ArrayList<Object>();
		results.add(neighbors);
		results.add(posY);
		results.add(posX);
		
		return results;
	}
	
	public ArrayList<Object> getNeighborsUnderFire (int i, int j) {
		ArrayList<Object> neighbors = getNeighbors(i,j);
		ArrayList<Object> neighborsOnFire = new ArrayList<Object>();
		ArrayList<Integer> posY = new ArrayList<Integer>();
		ArrayList<Integer> posX = new ArrayList<Integer>();
		
		for(int c=0; c<((ArrayList<ArrayList<SquareUnit>>) neighbors.get(0)).size(); c++) {

			
			if (((SquareUnit) ((ArrayList<Object>)neighbors.get(0)).get(c)).isFire()) {
				neighborsOnFire.add(((List<ArrayList<SquareUnit>>) neighbors.get(0)).get(c));
				posY.add((Integer) ((ArrayList<Integer>) neighbors.get(1)).get(c));
				posX.add((Integer) ((ArrayList<Integer>) neighbors.get(2)).get(c));

			}
		}

		ArrayList<Object> results = new ArrayList<Object>();
		results.add(neighborsOnFire);
		results.add(posY);
		results.add(posX);
		
		return results;
	}
	
	
}
