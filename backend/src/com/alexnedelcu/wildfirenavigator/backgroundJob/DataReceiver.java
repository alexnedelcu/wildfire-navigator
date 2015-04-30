package com.alexnedelcu.wildfirenavigator.backgroundJob;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;

import com.alexnedelcu.wildfirenavigator.data.WildfireManager;
import com.alexnedelcu.wildfirenavigator.settings.Settings;

public class DataReceiver {

	public static void main(String[] args) {

		String server = Settings.getFtpNASAServerAddress();
		String user = Settings.getFtpNASAServerUser();
		String pass = Settings.getFtpNASAServerPass();
		String dir  = Settings.getFtpNASAServerDirPath();
		String localFileDir = Settings.getLocalWildfireDataDir();
		String localFilename = Settings.getLocalWildfireDataCSVFilename();
		
		
		downloadFileFromFTP(server, user,
				pass, dir,
				localFileDir, localFilename);
		
		WildfireManager wm = WildfireManager.getInstance();
		wm.loadFiresFromFile(localFileDir + File.separator + localFilename);
		wm.save();
	}


	public static void downloadFileFromFTP(String server, String username,
			String password, String remoteDir, String localDir, String localFilename) {
		FTPClient client = new FTPClient();
		FileOutputStream fos = null;

		try {
			client.connect(server);
			client.enterLocalPassiveMode();
			client.login(username, password);

			//
			// The remote filename to be downloaded.
			//
			fos = new FileOutputStream(localDir + File.separator + localFilename);
			

			//
			// Download file from FTP server
			//
			FTPFile[] files = client.listFiles(remoteDir);
			FTPFile newestFile=null;
			if (files.length > 0) {
				long newestFileTimestamp = files[0].getTimestamp().getTimeInMillis();
				newestFile = files[0];
				for (FTPFile file : files) {
					if (file.getTimestamp().getTimeInMillis() > newestFileTimestamp) {
						newestFileTimestamp = file.getTimestamp().getTimeInMillis();
						newestFile = file;
					}
				}
				System.out.println(remoteDir + "/" + newestFile.getName());
				client.retrieveFile(remoteDir + "/" + newestFile.getName(), fos);
				
				// download the file with the same name for our long term records
				fos.close();
				fos = new FileOutputStream(localDir + File.separator + newestFile.getName());
				client.retrieveFile(remoteDir + "/" + newestFile.getName(), fos);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				client.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
