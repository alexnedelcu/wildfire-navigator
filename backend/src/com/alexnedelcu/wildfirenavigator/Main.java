package com.alexnedelcu.wildfirenavigator;

public class Main {
	public static void main(String[] args) {
		
		double currLat =10.35;
		double currLong = -63.456;
		
		Grid grid = new Grid(currLat, currLong);
		
		grid.generateGrid(30, 1);
		grid.loadFires("Global_24h.csv");
		
		grid.printGrid();
		grid.printPolygons();
		
//		LatLong x = new LatLong(40.6316723, -73.82572174); // center of the square
//		LatLong y = new LatLong(40.62489761, -73.80924225); // center of the square
//		SquareUnit su = new SquareUnit(x, 1);
//
////		System.out.println(su.getLTCorner().getLatitude() + "," + su.getLTCorner().getLongitude());
//		System.out.println(su.getLatitude() + "," + su.getLongitude());
//		System.out.println(su.getLBCorner().getLatitude() + "," + su.getLBCorner().getLongitude());
//		System.out.println(su.getRTCorner().getLatitude() + "," + su.getRTCorner().getLongitude());
//		System.out.println(su.getRBCorner().getLatitude() + "," + su.getRBCorner().getLongitude());
////		System.out.println(su.isLatLongConfined(x));
////		System.out.println(su.isLatLongConfined(y));
	}
	
	
	
}
