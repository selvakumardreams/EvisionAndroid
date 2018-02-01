package com.evision.android.manager;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 
 * @author selvakumar
 *
 */
public class SharedPrefManager {

	// Shared Preferences
		SharedPreferences pref;
		
		// Editor for Shared preferences
		Editor editor;
		
		// Context
		Context context;
		
		// Shared pref mode
		int PRIVATE_MODE = 0;
		
		// Sharedpref file name
		private static final String PREF_NAME = "evision";
		
		//TODO: define the keys
		
		
		
		// Constructor
		@SuppressLint("CommitPrefEdits")
		public SharedPrefManager(Context context) {
			this.context = context;
			pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
			editor = pref.edit();
		}
		
		
}
