package com.evision.android;

import java.io.*;

import android.content.*;
import android.os.Process;

public class UncaughtExceptionHandler implements java.lang.Thread.UncaughtExceptionHandler {
	private final Context myContext;

	public UncaughtExceptionHandler(Context context) {
		myContext = context;
	}

	public void uncaughtException(Thread thread, Throwable exception) {
		StringWriter stackTrace = new StringWriter();
		exception.printStackTrace(new PrintWriter(stackTrace));
		System.err.println(stackTrace);

		Intent intent = new Intent(myContext, BugReportActivity.class);
		intent.putExtra(BugReportActivity.STACKTRACE, stackTrace.toString());
		myContext.startActivity(intent);

		Process.killProcess(Process.myPid());
		System.exit(10);
	}
}
