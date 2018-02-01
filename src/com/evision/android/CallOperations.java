package com.evision.android;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.telephony.TelephonyManager;

import com.android.internal.telephony.ITelephony;

public class CallOperations {
	private String TAG = "Mobiwhiz-PhoneCall-Testing";
	private Activity activity;
	private Context context;
	private ITelephony telephonyService;
	private TelephonyManager tm;

	CallOperations(Context context){
		//		this.activity = activity ;
		this.context = context;
		getTeleService(context);


	}
	public void acceptCall(){
		try {
			this.telephonyService.answerRingingCall();
		} catch (RemoteException e) {
		}
	}
	public void denyCall(){
		try {
			this.telephonyService.endCall();
			Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
			intent.putExtra("state", true);
			this.context.sendBroadcast(intent);

			Intent intent1 = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
			intent.putExtra("state", false);
			this.context.sendBroadcast(intent1);

		} catch (RemoteException e) {
		}
	}
	public void makeCall(String number){
		try {
			this.telephonyService.call(number);
		} catch (RemoteException e) {
		}
	}


	private void getTeleService(Context context)
	{
		TelephonyManager localTelephonyManager1 = (TelephonyManager)context.getSystemService("phone");
		this.tm = localTelephonyManager1;
		try
		{
			//int i = Log.v(this.TAG, "Get getTeleService...");
			Class localClass = Class.forName(this.tm.getClass().getName());
			Class[] arrayOfClass = new Class[0];
			Method localMethod = localClass.getDeclaredMethod("getITelephony", arrayOfClass);
			localMethod.setAccessible(true);
			TelephonyManager localTelephonyManager2 = this.tm;
			Object[] arrayOfObject = new Object[0];
			ITelephony localITelephony = (ITelephony)localMethod.invoke(localTelephonyManager2, arrayOfObject);
			this.telephonyService = localITelephony;
			return;
		}
		catch (Exception localException)
		{
			localException.printStackTrace();
			//int j = Log.e(this.TAG, "FATAL ERROR: could not connect to telephony subsystem");
			String str1 = this.TAG;
			String str2 = "Exception object: " + localException;
			//int k = Log.e(str1, str2);
		}
	}
}
