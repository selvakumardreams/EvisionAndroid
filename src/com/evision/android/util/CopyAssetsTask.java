package com.evision.android.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

/**
 * 
 * @author selvakumar
 *
 */
public class CopyAssetsTask  extends AsyncTask<String, Integer, Boolean>{

	private Context mContext;

	public CopyAssetsTask(Context context) {
		mContext = context;
	}
	@Override
	protected Boolean doInBackground(String... params) {
		
		copyAssets(params[0], params[1]);
		
		return false;
	}

	private void copyAssets(String fileName, String folder) {
		AssetManager assetManager = mContext.getAssets();
		InputStream in = null;
		OutputStream out = null;
		try {
			in = assetManager.open(fileName);
			out = new FileOutputStream("/sdcard/"+ folder +"/" + fileName);
			copyFile(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		} catch(IOException e) {
			Log.e("tag", "Failed to copy asset file: " + fileName, e);
		}      
	}
	private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
	}

}
