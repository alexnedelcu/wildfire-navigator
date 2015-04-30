package com.alexnedelcu.wildfirenavigator.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {

	/**
	 * Saves a serializable object to a pecified path
	 * 
	 * @param path
	 * @param objToSerialize
	 */
	public static void save(String path, Object objToSerialize) {
		try {
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(objToSerialize);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			// TODO - handle exception
			i.printStackTrace();
		}
	}

	/**
	 * Loads a file that contains a serialized object into an object
	 * 
	 * @param file
	 * @return loaded object
	 */
	public static Object load(String file) {
		Object s = null;
		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			s = in.readObject();
			in.close();
			fileIn.close();
			return s;
		} catch (IOException i) {
			// TODO - handle exception
			i.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			// TODO - handle exception
			c.printStackTrace();
			return null;
		}

	}

	/**
	 * Deletes a file
	 */
	public static boolean delete(String path) {
		File file = new File(path);
		return file.delete();
	}

}
