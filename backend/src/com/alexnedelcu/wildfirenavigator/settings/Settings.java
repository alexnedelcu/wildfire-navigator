package com.alexnedelcu.wildfirenavigator.settings;

import java.io.File;
import java.io.Serializable;

public class Settings implements Serializable {
	private static String localWildfireDataDir = System.getProperty("user.dir") + File.separator + "data";
	private static String localWildfireDataCSVFilename = "Global24h.csv";
	private static String ftpNASAServerAddress = "nrt1.modaps.eosdis.nasa.gov";
	private static String ftpNASAServerUser = "alexnedelcu";
	private static String ftpNASAServerPass = "1d7f8d53aF!";
	private static String ftpNASAServerDirPath = "/FIRMS/Global";
	private static String settingsFilePath = System.getProperty("user.dir") + File.separator + "settings.ser";
	private static String mostRecentFiresSerializedPath = localWildfireDataDir + File.separator + "listOfFires.ser";
	
	public static String getLocalWildfireDataDir() {
		return localWildfireDataDir;
	}
	public static String getLocalWildfireDataCSVFilename() {
		return localWildfireDataCSVFilename;
	}
	public static String getFtpNASAServerAddress() {
		return ftpNASAServerAddress;
	}
	public static String getFtpNASAServerUser() {
		return ftpNASAServerUser;
	}
	public static String getFtpNASAServerPass() {
		return ftpNASAServerPass;
	}
	public static String getFtpNASAServerDirPath() {
		return ftpNASAServerDirPath;
	}
	public static String getFtpNASAServerFilePath() {
		return ftpNASAServerDirPath;
	}
	public static String getSettingsFilePath() {
		return settingsFilePath;
	}
	public static String getMostRecentFiresSerializedPath() {
		return mostRecentFiresSerializedPath;
	}
	public static void setMostRecentFiresSerializedPath(
			String mostRecentFiresSerializedPath) {
		Settings.mostRecentFiresSerializedPath = mostRecentFiresSerializedPath;
	}
}
