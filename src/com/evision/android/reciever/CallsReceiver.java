package com.evision.android.reciever;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.telephony.TelephonyManager;

import com.evision.android.CallsReceiverActivity;

public class CallsReceiver extends BroadcastReceiver {
	private String TAG = "Prasanta-PhoneCall";
	private static boolean isInComingCall;
	public static boolean isIncall;
	Context context;
	private TextToSpeech tts;
	String phonenumberis = "";
	TelephonyManager tm;

	public void onReceive(Context paramContext, Intent paramIntent) {
		this.context = paramContext;
		Bundle bundle = paramIntent.getExtras();
		String extras = bundle.getString(TelephonyManager.EXTRA_STATE);
		if (extras != null) {
			try {
				if (extras.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
					CallsReceiverActivity.isCallEnd = true;
					isInComingCall = false;
					isIncall = false;
				}
				if (extras.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {

					isIncall = true;
					Thread.sleep(2000);
					KeyguardManager kg = (KeyguardManager) paramContext.getSystemService(Context.KEYGUARD_SERVICE);
					final KeyguardManager.KeyguardLock kmkl = kg.newKeyguardLock("kCaller");
					kmkl.disableKeyguard();
					if (!isInComingCall) {
						Intent intent = new Intent(paramContext,CallsReceiverActivity.class);
						intent.putExtra("outgoingcall", "outgoingcall");
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						paramContext.startActivity(intent);
					}

				}
				if (extras.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
					isIncall = true;
					Thread.sleep(1000);
					isInComingCall = true;
					String phno = bundle.getString("incoming_number");
					String name = bundle.getString("incoming_name");
					phonenumberis = phno;
					KeyguardManager kg = (KeyguardManager) paramContext.getSystemService(Context.KEYGUARD_SERVICE);
					final KeyguardManager.KeyguardLock kmkl = kg.newKeyguardLock("kCaller");
					kmkl.disableKeyguard();
					Intent intent = new Intent(paramContext,CallsReceiverActivity.class);
					intent.putExtra("incomingcall", "incomingcall");
					intent.putExtra("phno", phno);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					paramContext.startActivity(intent);

				}
			} catch (Exception e) {
			}
		}
	}

}
