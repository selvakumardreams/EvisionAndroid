package com.evision.android.util;

import android.content.Context;

/**
 * Resource Util to get the resource String 
 * @author Santha Kumar
 *
 */

public class ResourceUtil {
	
	/**
	 * Convert the resource int to string
	 * @param resourceId
	 * @return
	 */
	public static String getStringFromResource(Context context, int resourceId) {
		return context.getResources().getString(resourceId);
	}

}
