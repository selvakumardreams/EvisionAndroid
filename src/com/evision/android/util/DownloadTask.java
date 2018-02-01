package com.evision.android.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.os.Environment;

public class DownloadTask extends AsyncTask<String, Integer, Boolean> {
	/** This is our listener*/
	private final DownloadListener listener;
	/** Hold message if download failure*/
	private String msg;
	/** Save Destination */
	private File saveTo;

	public DownloadTask(DownloadListener listener) {
		this.listener = listener;
	}

	@Override
	protected Boolean doInBackground(String... params) {
		/** Params should be array of string with length 2.
		 * 1. url
		 * 2. filename destination*/
		if(params == null || params.length < 2) {
			msg = "Incomplete parameters";
			return false;
		}

		String sUrl = params[0];
		String filename = params[1];

		/** get root directory: /sdcard */
		File rootDir = Environment.getExternalStorageDirectory();
		/** create destination file*/
		saveTo = new File(rootDir, filename);

		try {
			/** define url*/
			URL url = new URL(sUrl);
			/** open the connection*/
			URLConnection conn = url.openConnection();
			conn.connect();

			/** get the size of file that will be downloaded. It will be used to measure the progress*/
			int fileLength = conn.getContentLength();

			/** create input and outpout stream*/
			InputStream is = new BufferedInputStream(url.openStream());
			OutputStream os = new FileOutputStream(saveTo);

			/** create buffer*/
			byte buffer[] = new byte[1024];
			/** hold total of downloaded bytes*/
			long totalDownloaded = 0;
			int count;

			while ((count = is.read(buffer)) != -1) {
				totalDownloaded += count;

				/**cause call to onProgressUpdate, which is run on UI thread*/
				publishProgress((int) (totalDownloaded * 100 / fileLength));                
				os.write(buffer, 0, count);
			}

			/** closing stream*/
			os.flush();
			os.close();
			is.close();
			return true;
		}
		catch (MalformedURLException e) {
			msg = "Invalid URL";
		}
		catch (IOException e) {
			msg = "No internet connection";
		}

		return false;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		if(listener != null) listener.onProgressUpdate(values[0]);
	}

	@Override
	protected void onPostExecute(Boolean result) {
		if(!result) {
			if(listener != null) listener.onDownloadFailure(msg);
			return;
		}

		if(listener != null) listener.onDownloadComplete(saveTo);
	}
}
