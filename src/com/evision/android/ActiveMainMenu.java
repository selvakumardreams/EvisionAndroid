package com.evision.android;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;

import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;

public class ActiveMainMenu extends BaseActivity {

	Button num0Button = null, num1Button = null, num2Button = null,
			num3Button = null, num4Button = null, num5Button = null,
			num6Button = null, num7Button = null, num8Button = null,
			num9Button = null, numFButton = null, numGButton = null,
			numHButton = null, numStarButton = null, numHashButton = null;
	boolean starPressed =false;
	boolean isGFKeyPressed = false;
	CountDownTimer keyPressTimeout = null;

	private String TAG = "ActiveMode";
	public static final int ACTION_DOWN = 0,ACTION_UP = 1;
	public static int counter = 0;
	public static final String ASSIGN_NAME ="EvisionAssignGroup";

	boolean isHashPress = false;
	boolean isOnePress = false;
	boolean isfourPress = false;
	boolean isHGKeyPress = false;
	public static final String PREFS_NAME ="EvisionPrefsFile";
	SharedPreferences getSpeedNumber; 

	String emergencyNumber1;
	String emergencyNumber2;
	String emergencyNumber3;
	String emergencyNumber4;

	CountDownTimer timer = null;
	CountDownTimer startUpTimer = null;

	Intent launchApp = new Intent();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.standbymode_screen);

		num0Button = (Button) findViewById(R.id.num0Button);
		num1Button = (Button) findViewById(R.id.num1Button);
		num2Button = (Button) findViewById(R.id.num2Button);
		num3Button = (Button) findViewById(R.id.num3Button);
		num4Button = (Button) findViewById(R.id.num4Button);
		num5Button = (Button) findViewById(R.id.num5Button);
		num6Button = (Button) findViewById(R.id.num6Button);
		num7Button = (Button) findViewById(R.id.num7Button);
		num8Button = (Button) findViewById(R.id.num8Button);
		num9Button = (Button) findViewById(R.id.num9Button);
		numFButton = (Button) findViewById(R.id.numFButton);
		numGButton = (Button) findViewById(R.id.numGButton);
		numHButton = (Button) findViewById(R.id.numHButton);
		numStarButton = (Button) findViewById(R.id.numStarButton);
		numHashButton = (Button) findViewById(R.id.numHashButton);

		getSpeedNumber = getSharedPreferences(ASSIGN_NAME,0);
		SharedPreferences getEmergencyNumber = getSharedPreferences(ASSIGN_NAME,0);
		emergencyNumber1 = getEmergencyNumber.getString("emergencyNumber_1", "");
		emergencyNumber2 = getEmergencyNumber.getString("emergencyNumber_2", "");
		emergencyNumber3 = getEmergencyNumber.getString("emergencyNumber_3", "");
		emergencyNumber4 = getEmergencyNumber.getString("emergencyNumber_4", "");

		startUpTimer = new CountDownTimer(1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
			}
			@Override
			public void onFinish() {
				Log.i(TAG, "OnFinish : startUpTimer Global: " + mTts);
				ttsForActiveMode();
			}
		}.start();

		keyPressTimeout = new CountDownTimer(1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
			}
			@Override
			public void onFinish() {
				if (isHashPress == true || isOnePress == true || isfourPress == true || 
						starPressed == true || isGFKeyPressed == true || isHGKeyPress == true) {
					isHashPress = false;
					isfourPress = false;
					isGFKeyPressed = false;
					isOnePress = false;
					starPressed = false;
					isHGKeyPress = false;

				}
			}
		};
		timer = new CountDownTimer(30000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
			}
		}.start();
		// to repeat the menu
		num0Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					timer.start();
					stop();
					speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.digit0));
					num0Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					ttsForActiveMode();
					num0Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak("1");
					num1Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_normal);
					if(starPressed==true) {
						starPressed = false;
						String emergencyNumber = getSpeedNumber.getString("speedNumber_1", "");
						String emergencyName = getSpeedNumber.getString("speedName_1", "");
						if(emergencyNumber == "") {
							speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.active_notassign));
						}
						else {
							speak("Call to " + emergencyName);
							waitForShortSpeechFinished();
							CallOperations call = new CallOperations(getApplicationContext());
							call.makeCall(emergencyNumber);
						}
					} else if (isHashPress == true) {
						isOnePress = true;
					} else {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.CELLPHONE); 
						startActivity(launchApp); finish();
					}
					keyPressTimeout.start();
				}
				return true;
			}
		});

		num2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.digit2));
					num2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num2Button.setBackgroundResource(R.drawable.btn_normal);
					if (starPressed == true) {
						starPressed = false;
						String emergencynumber = getSpeedNumber.getString("speedNumber_2", "");
						String emergencyName = getSpeedNumber.getString("speedName_2", "");
						if(emergencynumber == "" ) {
							speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.active_notassign));
						}
						else {
							speak("Call to " + emergencyName);
							waitForShortSpeechFinished();
							CallOperations call = new CallOperations(getApplicationContext());
							call.makeCall(emergencynumber);
						}
					} else {
						
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.EDUCATIONMENU); 
						startActivity(launchApp); 
						finish();
					}
				}
				return true;
			}
		});

		num3Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.digit3));
					num3Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num3Button.setBackgroundResource(R.drawable.btn_normal);
					if(starPressed==true) {
						starPressed = false;
						String emergencynumber = getSpeedNumber.getString("speedNumber_3", "");
						String emergencyName = getSpeedNumber.getString("speedName_3", "");
						if(emergencynumber == "" ) {
							speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.active_notassign));
						} else {
							speak("Call to " + emergencyName);
							waitForShortSpeechFinished();
							CallOperations call = new CallOperations(getApplicationContext());
							call.makeCall(emergencynumber);
						}
					} else {
						speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.update));
					}
				}
				return true;
			}
		});
		num4Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();

					speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.digit4));
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					num4Button.setBackgroundResource(R.drawable.btn_normal);
					if(starPressed==true) {
						starPressed=false;
						String emergencynumber = getSpeedNumber.getString("speedNumber_4", "");
						String emergencyName = getSpeedNumber.getString("speedName_4", "");
						if(emergencynumber == "" ) {

							speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.active_notassign));
						} else {
							speak("Call to " + emergencyName);
							waitForShortSpeechFinished();
							CallOperations call = new CallOperations(getApplicationContext());
							call.makeCall(emergencynumber);
						}
					}  else if (isHashPress == true) {
						isfourPress = true;
					} else
						speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.update));
				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.digit5));
					num5Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num5Button.setBackgroundResource(R.drawable.btn_normal);
					if (starPressed==true) {
						starPressed=false;
						String emergencynumber = getSpeedNumber.getString("speedNumber_5", "");
						String emergencyName = getSpeedNumber.getString("speedName_5", "");
						if (emergencynumber == "" ) {
							speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.active_notassign));
						} else {
							speak("Call to " + emergencyName);
							waitForShortSpeechFinished();
							CallOperations call = new CallOperations(getApplicationContext());
							call.makeCall(emergencynumber);
						}
					} else
						speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.update));
				}
				return true;
			}
		});

		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.digit6));
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num6Button.setBackgroundResource(R.drawable.btn_normal);
					if(starPressed==true) {
						starPressed=false;
						String emergencynumber = getSpeedNumber.getString("speedNumber_6", "");
						String emergencyName = getSpeedNumber.getString("speedName_6", "");
						if(emergencynumber == "" ) {
							speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.active_notassign));
						} else {
							speak("Call to " + emergencyName);
							waitForShortSpeechFinished();
							CallOperations call = new CallOperations(getApplicationContext());
							call.makeCall(emergencynumber);
						}
					} else
						speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.update));
				}
				return true;
			}
		});

		num7Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.digit7));
					num7Button.setBackgroundResource(R.drawable.btn_green);

				} else if (ACTION_UP == event.getAction()) {
					num7Button.setBackgroundResource(R.drawable.btn_normal);
					if(starPressed == true) {
						starPressed=false;
						String emergencynumber = getSpeedNumber.getString("speedNumber_7", "");
						String emergencyName = getSpeedNumber.getString("speedName_7", "");
						if(emergencynumber == "" ) {
							speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.active_notassign));
						} else {
							speak("Call to " + emergencyName);
							waitForShortSpeechFinished();
							CallOperations call = new CallOperations(getApplicationContext());
							call.makeCall(emergencynumber);
						}
					} else if((isHashPress == true) && (isOnePress == true) && (isfourPress == true)) {
						isHashPress = false;
						isOnePress = false;
						isfourPress = false;

						if ((emergencyNumber1 == "") && (emergencyNumber2 == "") && (emergencyNumber3 == "") && (emergencyNumber4 =="")) {
							speak("Please Assign the Emergency Number");
						} else {
							sendMessage();
						}

					} else 
						speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.update));
				}
				return true;
			}
		});

		num8Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.digit8));
					num8Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num8Button.setBackgroundResource(R.drawable.btn_normal);
					if(starPressed==true) {
						String emergencynumber = getSpeedNumber.getString("speedNumber_8", "");
						String emergencyName = getSpeedNumber.getString("speedName_8", "");
						if(emergencynumber == "" ) {
							speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.active_notassign));
						} else {
							starPressed=false;
							speak("Call to " + emergencyName);
							waitForShortSpeechFinished();
							CallOperations call = new CallOperations(getApplicationContext());
							call.makeCall(emergencynumber);
						}

					} else
						speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.update));
				}
				return true;
			}
		});

		num9Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.digit9));
					num9Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num9Button.setBackgroundResource(R.drawable.btn_normal);
					if(starPressed==true) {
						starPressed=false;
						String emergencynumber = getSpeedNumber.getString("speedNumber_9", "");
						String emergencyName = getSpeedNumber.getString("speedName_9", "");
						if(emergencynumber == "" ) {
							speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.active_notassign));
						} else {
							speak("Call to " + emergencyName);
							waitForShortSpeechFinished();
							CallOperations call = new CallOperations(getApplicationContext());
							call.makeCall(emergencynumber);
						}
					} else{
						launchApp.setClassName(PackageUtil.PACKAGE,PackageUtil.ACCESSORIESMENU); 
						startActivity(launchApp);
						finish();
					}
				}
				return true;
			}
		});

		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					numHButton.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.H));
					keyPressTimeout.cancel();
					isHGKeyPress = true;
				} else if (ACTION_UP == event.getAction()) {
					numHButton.setBackgroundResource(R.drawable.btn_normal);
				}
				keyPressTimeout.start();
				return true;
			}
		});

		numGButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.G));
					keyPressTimeout.cancel();
					isGFKeyPressed = true;
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if ( isHGKeyPress == true) {
						isHGKeyPress = false;
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.STANDBYMAINMENU);
						startActivity(launchApp);
						finish();
					}
					keyPressTimeout.start();
					numGButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.F));
					keyPressTimeout.cancel();
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if(isGFKeyPressed == true) {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.STANDBYMAINMENU);
						startActivity(launchApp);
						finish();
					}
					keyPressTimeout.start();
					numFButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numStarButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.star));
					numStarButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					starPressed = true;
					numStarButton.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();
				}
				return true;
			}
		});

		numHashButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					isHashPress = true;
					stop();
					speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.hash));
					numHashButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numHashButton.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();
				}
				return true;
			}
		});
	}

	@Override
	public void onPause() {
		Log.i(TAG, "Active Mode onPause");

		if (timer != null)
			timer.cancel();
		if (startUpTimer != null)
			startUpTimer.cancel();
		if (mTts != null)
			stop();
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.i(TAG, "Active Mode OnStop");
		super.onStop();
	}

	@Override
	public void onResume() {
		Log.i(TAG, "Active Mode onResume");
		if (timer != null)
			timer.start();
		if (startUpTimer != null)
			startUpTimer.start();
		super.onResume();
	}
	public void ttsForActiveMode() {
		timer.cancel();
		speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.active_welcome));
		speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.please));
		speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.active_1));
		speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.active_2));
		//		tts.speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.active_3));
		//		tts.speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.active_4));
		//		timer.start();
		//		tts.speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.active_5));
		//		tts.speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.active_6));
		//		tts.speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.active_7));
		//		tts.speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.active_8));
		speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.active_9));
		speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.standMenu_star));
		speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.standMenu_emergency));
		speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.repeat_0));
		speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.previous_hg));

		timer.cancel();
		timer.start();
	}

	private final void sendMessage() {    
		String SENT = "SMS_SENT";
		String DELIVERED = "SMS_DELIVERED";
		SharedPreferences sPreferences = getSharedPreferences(PREFS_NAME, 0);
		String PersonalInformationGPSLocation= sPreferences.getString("PersonalInformationGPSLocation","empty");

		PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
				new Intent(SENT), 0);

		PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
				new Intent(DELIVERED), 0);

		//---when the SMS has been sent---
		registerReceiver(new BroadcastReceiver() {
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					//    Toast.makeText(getBaseContext(), "SMS sent", 
					//           Toast.LENGTH_SHORT).show();
					speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.sms_send) + "Emergency Contact");
					break;

				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					//   Toast.makeText(getBaseContext(), "Generic failure", 
					//           Toast.LENGTH_SHORT).show();
					speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.generic_failure));
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(getBaseContext(), "No service", 
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(getBaseContext(), "Null PDU", 
							Toast.LENGTH_SHORT).show();
					break;

				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(getBaseContext(), "Radio off", 
							Toast.LENGTH_SHORT).show();
					break;
				}
			}

		}, new IntentFilter(SENT));
		//---when the SMS has been delivered---
		registerReceiver(new BroadcastReceiver(){
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					speak(ResourceUtil.getStringFromResource(ActiveMainMenu.this,R.string.active_deliversms)+ "Emergency Contact");
					break;
				case Activity.RESULT_CANCELED:
					break;                        
				}
			}
		},new IntentFilter(DELIVERED));        

		SmsManager sms = SmsManager.getDefault();
		if (emergencyNumber1 != "")
			sms.sendTextMessage(emergencyNumber1, null, "robert in emergency situation at " + PersonalInformationGPSLocation, sentPI, deliveredPI);
		if (emergencyNumber2 != "")
			sms.sendTextMessage(emergencyNumber2, null, "robert in emergency situation at  " + PersonalInformationGPSLocation, sentPI, deliveredPI);
		if (emergencyNumber3 != "")
			sms.sendTextMessage(emergencyNumber3, null, "robert in emergency situation at  " + PersonalInformationGPSLocation, sentPI, deliveredPI);
		if (emergencyNumber4 != "")
			sms.sendTextMessage(emergencyNumber4, null, "robert in emergency situation at  " + PersonalInformationGPSLocation, sentPI, deliveredPI);
	}
}
