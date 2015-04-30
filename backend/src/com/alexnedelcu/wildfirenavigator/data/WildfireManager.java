package com.alexnedelcu.wildfirenavigator.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import com.alexnedelcu.wildfirenavigator.datatypes.Fire;
import com.alexnedelcu.wildfirenavigator.settings.Settings;
import com.alexnedelcu.wildfirenavigator.settings.Storage;

public class WildfireManager implements Serializable {
	private static WildfireManager instance;
	ArrayList<Fire> fires;
	
	public void loadFiresFromFile(String fileName) {

		fires = new ArrayList<Fire> (); 
		
		
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
	        	Fire fire = new Fire();
	        	fire.setLatlong(potentialSurroundingFire);
	        	fires.add(fire);
        	}
        }
         
        // close the input file
        scanner.close();
	}

	public static WildfireManager getInstance() {
		if (instance != null) return instance;
		else {
			instance = new WildfireManager();
			return instance;
		}
	}
	
	public void save() {
		Storage.save(Settings.getMostRecentFiresSerializedPath(), this);
	}
	
	public void load() {
		instance = (WildfireManager) Storage.load(Settings.getMostRecentFiresSerializedPath());
		initialize(instance);
	}
	
	public void initialize(WildfireManager wm) {
		this.fires = wm.getFires();
	}

	private ArrayList<Fire> getFires() {
		return this.fires;
	}
}
