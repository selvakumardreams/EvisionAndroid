package com.evision.android.util;

import java.io.File;

public interface DownloadListener {
	public void onDownloadComplete(File filename);
	public void onProgressUpdate(int progress);
	public void onDownloadFailure(final String msg);
}
