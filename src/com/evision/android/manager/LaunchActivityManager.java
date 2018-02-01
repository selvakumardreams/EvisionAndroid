package com.evision.android.manager;

import com.evision.android.util.PackageUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * 
 * @author selvakumar
 *
 */
public class LaunchActivityManager {

	// Context
	Context context;

	public LaunchActivityManager(Context context) {
		this.context = context;
	}
	
	public void LaunchActivity(String className) {
		Intent launchApp = new Intent();
		launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.PACKAGE +"."+ className);
		context.startActivity(launchApp);
		((Activity) context).finish();
	}
	

}
