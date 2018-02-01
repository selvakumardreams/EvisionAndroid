package com.evision.android;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;
import android.os.BatteryManager;
import android.os.Vibrator;
import android.provider.CallLog;
import android.provider.ContactsContract.PhoneLookup;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

import com.evision.android.reciever.BatteryReceiver;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.CopyAssetsTask;
import com.evision.android.util.DownloadListener;
import com.evision.android.util.DownloadTask;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;

//QUEUE_ADD: Queue mode where the new entry is added at the end of the playback queue.
//QUEUE_FLUSH: Queue mode where all entries in the playback queue 
//(media to be played and text to be synthesized) are dropped 
//and replaced by the new entry.

public class StandbyMainMenu extends BaseActivity implements LocationListener, DownloadListener {

	boolean b1 = false, b2 = false, b3 = false;

	Button num0Button = null;
	Button num1Button = null;
	Button num2Button = null;
	Button num3Button = null;
	Button num4Button = null;
	Button num5Button = null;
	Button num6Button = null;
	Button num7Button = null;
	Button num8Button = null;
	Button num9Button = null;
	Button numFButton = null;
	Button numGButton = null;
	Button numHButton = null;
	Button numStarButton = null;
	Button numHashButton = null;
	Context context;
	//Button resetButton = null;
	boolean starPressed =false;
	public static final String ASSIGN_NAME ="EvisionAssignGroup";



	public static final Uri SMS_CONTENT_URI = Uri.parse("content://sms");
	public static final String SMS_ID = "_id";
	public static final Uri SMS_INBOX_CONTENT_URI = Uri.withAppendedPath(SMS_CONTENT_URI, "inbox");

	TelephonyManager Tel;
	MyPhoneStateListener mPhoneStateListener;
	String value;
	String signalvalue;
	SignalStrength signalStrength;
	String WEAK = "WEAK";
	String AVERAGE = "AVERAGE";
	String GOOD = "GOOD";
	String STRONG = "STRONG";
	Vibrator vibrate;
	protected LocationManager locationManager;
	boolean isNetworkEnabled = false;
	boolean canGetLocation = false;
	boolean isHashPress = false;
	boolean isOnePress = false;
	boolean isfourPress = false;
	Location location = null; // location
	double latitude; // latitude
	double longitude; // longitude
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
	boolean isGPSEnabled = false;
	SharedPreferences getSpeedNumber;


	public static final String PREFS_NAME ="EvisionPrefsFile";

	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	CountDownTimer timer = null, startUpTimer = null;
	CountDownTimer keyPressTimeout = null;
	CountDownTimer doubleClickTimer = null;

	Intent launchApp = new Intent();



	private BatteryReceiver mBatteryBroadcastReciever;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(StandbyMainMenu.this));
		setContentView(R.layout.standbymode_screen);

		ttsForStandbyMode();
		copyManual();
		copyEbook();
		downloadDictionary();

		getSpeedNumber = getSharedPreferences(ASSIGN_NAME,0);
		vibrate = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		initialize(); //Initialize the buttons

		mPhoneStateListener = new MyPhoneStateListener();
		Tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		Tel.listen(mPhoneStateListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

		doubleClickTimer = new CountDownTimer(2000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				// This single key is pressed & timer expires and no docble key
				// pressed
				ttsForStandbyMode();
			}
		};

		keyPressTimeout = new CountDownTimer(1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				if (isfourPress == true || isHashPress == true || isOnePress == true
						|| starPressed == true) {
					isfourPress = false;
					isHashPress = false;
					isOnePress = false;
					starPressed = false;
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
		//	if(ttsspeak=="speak")

		startUpTimer = new CountDownTimer(1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				ttsForStandbyMode();
			}
		}.start();
	}

	private void downloadDictionary() {
		String url = "http://thelieus.com/dictionary";
		/** destination filename*/
		String filename = "dictionary";

		File newFile = new File(BrailleCommonUtil.DICTIONARY_PATH);
		if(!newFile.exists()){
			newFile.mkdirs();
		}
		File file = new File(Environment.getExternalStorageDirectory() +"/dictionary/", filename);

		if (!file.exists()) {
			DownloadTask task = new DownloadTask(this);
			task.execute(url, filename);
		}
	}


	private void copyManual() {
		File newFile = new File(BrailleCommonUtil.MEDIA_PATH_EVISION);
		if(!newFile.exists()){
			newFile.mkdirs();
		}

		String[] fileList = {"brailletutor.txt","cellphone.txt","education.txt", 
				"calculator.txt","introduction.txt","numerical.txt","calendar.txt"};

		for (int i=0; i<fileList.length; i++) {
			File file = new File(BrailleCommonUtil.MEDIA_PATH_EVISION, fileList[i] );

			if (!file.exists()) {
				CopyAssetsTask copy = new CopyAssetsTask(StandbyMainMenu.this);
				copy.execute(fileList[i],"evision");
			}	
		}

	}

	private void copyEbook() {

		File newFile = new File(BrailleCommonUtil.MEDIA_PATH_EBOOK);
		if(!newFile.exists()){
			newFile.mkdirs();
		}

		File file = new File(BrailleCommonUtil.MEDIA_PATH_EBOOK, "entrepreneurial mindset.txt" );

		if (!file.exists()) {
			CopyAssetsTask copy = new CopyAssetsTask(StandbyMainMenu.this);
			copy.execute("entrepreneurial mindset.txt","ebook");
		}	
	}



	public void ttsForStandbyMode() {

		//timer.cancel();
		speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.standMenu_welcome));
		speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.please));
		speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.standMenu_one));
		speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.standMenu_two));
		speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.standMenu_three));
		speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.standMenu_four));
		speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.standMenu_five));
		speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.standMenu_six));
		speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.standMenu_seven));
		//timer.start();
		speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.standMenu_nine));
		speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.repeat_0));
		speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.fgh_active));
		//	timer.cancel();
		//	timer.start();
	}

	public void onDestroy()
	{
		super.onDestroy();
		unregisterReceiver(mBatteryBroadcastReciever);
	}

	private boolean setBoolean() {
		return true;
	}

	public void lookuptable() {

		if (b1 == true && b2 == true && b3 == true) {
			final Intent resetIntent = new Intent(this,
					com.evision.android.ActiveMainMenu.class);
			startActivity(resetIntent);

		} else if (b1 == false && b2 == true && b3 == true) {
			speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.enotescr_pressf));
			speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.fgh_active));
		} else if (b1 == true && b2 == false && b3 == true) {
			speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.enotescr_pressg));
			speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.fgh_active));
		}
		b1 = false;
		b2 = false;
		b3 = false;
	}

	//Read 10 numbers max...

	@SuppressLint("SimpleDateFormat")
	private void getCallDetails(int logType) {

		final String[] CALL_LOG_PROJECTION = new String[] {
				CallLog.Calls.DATE,
				CallLog.Calls.DURATION,
				CallLog.Calls.NUMBER,
				CallLog.Calls.CACHED_NAME,
				CallLog.Calls.TYPE,
		};

		ContentResolver resolver = getContentResolver();
		String where = CallLog.Calls.TYPE+"="+logType;
		Cursor callCursor = resolver.query(CallLog.Calls.CONTENT_URI, CALL_LOG_PROJECTION, where, null, null);

		try {
			String contactName = null;
			if (callCursor == null || !callCursor.moveToFirst()) {
				speak( "No call logs at the moment");
			}

			int date = callCursor.getColumnIndex( CallLog.Calls.DATE);
			int callName = callCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);

			int count = 0;
			if (callCursor.moveToFirst()) {

				if (count != 5 ) {
					do {
						long callDate = callCursor.getLong( date );

						//Date
						SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm");
						String dateValue = formatter.format(new Date(callDate));

						//Get name
						String name = callCursor.getString(callName);

						if (name != null) {
							contactName = name;
						} else {
							contactName = "Unknown";
						}


						switch( logType ) {
						case CallLog.Calls.OUTGOING_TYPE:
							//dir = "OUTGOING";
							speak( contactName +" " + " at the time " + dateValue);
							break;
						case CallLog.Calls.INCOMING_TYPE:
							//dir = "INCOMING";
							speak( contactName +" " + " at the time " + dateValue);
							break;

						case CallLog.Calls.MISSED_TYPE:
							//dir = "MISSED";
							if (!getDateDifference(dateValue).equals("")) {
								speak( contactName +" " + " at the time " + getDateDifference(dateValue));
								waitForShortSpeechFinished();	
							}
							break;
						}
						count ++;
					} while (callCursor.moveToNext());
				}

			}
		} catch (Exception e) {

		} finally {
			if (callCursor != null) {
				callCursor.close();
			}
		}
	}

	@SuppressLint("SimpleDateFormat")
	private String getDateDifference(final String dateValue) {
		String diffData = "";
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy HH:mm");
		String formattedDate = df.format(c.getTime());
		Date d1 = null;
		Date d2 = null;

		try {
			d1 = df.parse(formattedDate);
			d2 = df.parse(dateValue);
		} catch (ParseException e) {
		}
		int diffInDays = (int) ((d1.getTime() - d2.getTime())/ (1000 * 60 * 60 * 24));

		switch (diffInDays) {
		case 0:
			diffData = getHourMinute(df.format(d2.getTime())) + " today";
			break;
		case 1:
			diffData = getHourMinute(df.format(d2.getTime())) + " yesterday";
			break;
		case 2:
			diffData = getHourMinute(df.format(d2.getTime())) + " two days back";
			break;
		case 3:
			diffData = getHourMinute(df.format(d2.getTime())) + " three days back";
			break;
		case 4:
			diffData = getHourMinute(df.format(d2.getTime())) + " four days back";
			break;
		default:
			diffData = "";
			break;
		}
		return diffData;
	}


	private String getHourMinute(String time) {
		String[] s = time.split(" ");

		int hour = Integer.parseInt(s[1].split(":")[0]);
		int min = Integer.parseInt(s[1].split(":")[1]);

		return hour + "hour " + min + "minutes";

	}
	private void initialize() {
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
		num0Button.setOnTouchListener(buttonOnTouchListener);
		num0Button.setOnTouchListener(buttonOnTouchListener);
		num1Button.setOnTouchListener(buttonOnTouchListener);
		num2Button.setOnTouchListener(buttonOnTouchListener);
		num3Button.setOnTouchListener(buttonOnTouchListener);
		num4Button.setOnTouchListener(buttonOnTouchListener);
		num5Button.setOnTouchListener(buttonOnTouchListener);
		num6Button.setOnTouchListener(buttonOnTouchListener);
		num7Button.setOnTouchListener(buttonOnTouchListener);
		num8Button.setOnTouchListener(buttonOnTouchListener);
		num9Button.setOnTouchListener(buttonOnTouchListener);
		numFButton.setOnTouchListener(buttonOnTouchListener);
		numGButton.setOnTouchListener(buttonOnTouchListener);
		numHashButton.setOnTouchListener(buttonOnTouchListener);
		numHButton.setOnTouchListener(buttonOnTouchListener);
		numStarButton.setOnTouchListener(buttonOnTouchListener);


		//Register for Battery receiver
		mBatteryBroadcastReciever = new BatteryReceiver(this);
		IntentFilter mIntentFilter = new IntentFilter();  
		mIntentFilter.addAction(Intent.ACTION_BATTERY_LOW);  
		mIntentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);  
		mIntentFilter.addAction(Intent.ACTION_BATTERY_OKAY);  
		registerReceiver(mBatteryBroadcastReciever, mIntentFilter);

		//Disable for first version
		numFButton.setEnabled(true);
		numGButton.setEnabled(true);
		numHButton.setEnabled(true);
	}

	private OnTouchListener buttonOnTouchListener  = new OnTouchListener() {

		@SuppressLint("NewApi")
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (v.getId()) {
			case R.id.num0Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("0");
					num0Button.setBackgroundResource(R.drawable.btn_green);  
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					ttsForStandbyMode();
					num0Button.setBackgroundResource(R.drawable.btn_normal);
				} 
				break;
			case R.id.num1Button:
				Date date = new Date();
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("1");
					if (keyPressTimeout != null)
						keyPressTimeout.cancel();
					num1Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					keyPressTimeout.start();
					if(starPressed==true) {
						starPressed = false;
						String emergencyNumber = getSpeedNumber.getString("speedNumber_1", "");
						String emergencyName = getSpeedNumber.getString("speedName_1", "");
						if(emergencyNumber == "") {
							speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.active_notassign));
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
						String strHrs = date.getHours() + " hours";
						String strMin = date.getMinutes() + " minutes";
						speak("The time is "+strHrs);
						speak(strMin);
					}
					num1Button.setBackgroundResource(R.drawable.btn_normal);
				} 
				break;
			case R.id.num2Button:
				Date dt = new Date();
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("2");

					num2Button.setBackgroundResource(R.drawable.btn_green); 

				} else if (event.getAction() == MotionEvent.ACTION_UP) {

					if(starPressed==true){
						starPressed = false;
						String emergencyNumber = getSpeedNumber.getString("speedNumber_2", "");
						String emergencyName = getSpeedNumber.getString("speedName_2", "");
						if(emergencyNumber == "") {
							speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.active_notassign));
						}
						else {
							speak("Call to " + emergencyName);
							waitForShortSpeechFinished();
							CallOperations call = new CallOperations(getApplicationContext());
							call.makeCall(emergencyNumber);
						}
					} else {

						Calendar c = Calendar.getInstance();
						String dateValue = dt.getDate() + "";

						String month = "";
						switch (dt.getMonth()) {
						case 0:
							month = "January";
							break;

						case 1:
							month = "February";
							break;

						case 2:
							month = "March";
							break;

						case 3:
							month = "April";
							break;

						case 4:
							month = "May";
							break;

						case 5:
							month = "June";
							break;

						case 6:
							month = "July";
							break;

						case 7:
							month = "August";
							break;

						case 8:
							month = "September";
							break;

						case 9:
							month = "October";
							break;

						case 10:
							month = "November";
							break;

						case 11:
							month = "December";
							break;
						}
						int DayName = c.get(Calendar.DAY_OF_WEEK);

						String dayName = Integer.toString(DayName);
						if (DayName == 1) {
							dayName = "Sunday";
						}
						if (DayName == 2) {
							dayName = "Monday";
						}
						if (DayName == 3) {
							dayName = "Tuesday";
						}
						if (DayName == 4) {
							dayName = "Wednesday";
						}
						if (DayName == 5) {
							dayName = "Thursday";
						}
						if (DayName == 6) {
							dayName = "Friday";
						}
						if (DayName == 7) {
							dayName = "Saturday";
						}

						String gDate = "Today is" + dayName + dateValue + month ;
						speak(gDate);
					}
					num2Button.setBackgroundResource(R.drawable.btn_normal);
				} 
				break;
			case R.id.num3Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("3");
					keyPressTimeout.cancel();
					num3Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num3Button.setBackgroundResource(R.drawable.btn_normal);
					if(starPressed==true) {
						starPressed = false;
						String emergencyNumber = getSpeedNumber.getString("speedNumber_3", "");
						String emergencyName = getSpeedNumber.getString("speedName_3", "");
						if(emergencyNumber == "") {
							speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.active_notassign));
						} else {
							speak("Call to " + emergencyName);
							waitForShortSpeechFinished();
							CallOperations call = new CallOperations(getApplicationContext());
							call.makeCall(emergencyNumber);
						}
					} else {
						speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.standMenu_missCall));
						getCallDetails(CallLog.Calls.MISSED_TYPE);
					}
				} 

				break;
			case R.id.num4Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("4");
					if (keyPressTimeout != null)
						keyPressTimeout.cancel();
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num4Button.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();
					if(starPressed==true) {
						starPressed = false;
						String emergencyNumber = getSpeedNumber.getString("speedNumber_3", "");
						String emergencyName = getSpeedNumber.getString("speedName_3", "");
						if(emergencyNumber == "") {
							speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.active_notassign));
						} else {
							speak("Call to " + emergencyName);
							waitForShortSpeechFinished();
							CallOperations call = new CallOperations(getApplicationContext());
							call.makeCall(emergencyNumber);
						}
					} else if (isHashPress == true) {
						isfourPress = true;
					} else {
						sms(); 
					}
				} 
				break;
			case R.id.num5Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("5");
					num5Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					if(starPressed==true) {
						starPressed = false;
						String emergencyNumber = getSpeedNumber.getString("speedNumber_5", "");
						String emergencyName = getSpeedNumber.getString("speedName_5", "");
						if(emergencyNumber == "") {
							speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.active_notassign));
						} else {
							speak("Call to " + emergencyName);
							waitForShortSpeechFinished();
							CallOperations call = new CallOperations(getApplicationContext());
							call.makeCall(emergencyNumber);
						}
					} else {
						if (signalvalue.length() != 0) {
							speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.standMenu_signal)+ signalvalue);
						} else {
							speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.standMenu_simcheck));
						}
					}
					num5Button.setBackgroundResource(R.drawable.btn_normal);

				} 
				/*if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("5");
					num5Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num5Button.setBackgroundResource(R.drawable.btn_normal);
					if(starPressed==true){

					}else {
						speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.standMenu_dialCall)); 
						getCallDetails(CallLog.Calls.OUTGOING_TYPE);
					}
				} */
				break;
			case R.id.num6Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("6");
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num6Button.setBackgroundResource(R.drawable.btn_normal);
					if(starPressed==true) {
						starPressed = false;
						String emergencyNumber = getSpeedNumber.getString("speedNumber_6", "");
						String emergencyName = getSpeedNumber.getString("speedName_6", "");
						if(emergencyNumber == "") {
							speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.active_notassign));
						} else {
							speak("Call to " + emergencyName);
							waitForShortSpeechFinished();
							CallOperations call = new CallOperations(getApplicationContext());
							call.makeCall(emergencyNumber);
						}
					} else {
						speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.standMenu_battery)+ mBatteryBroadcastReciever.getBatteryLevel() + "%" + "and health is" + 
								getHealthString(mBatteryBroadcastReciever.getBatteryHealth()));
					}
				} 


				/*if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("6");
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num6Button.setBackgroundResource(R.drawable.btn_normal);
					if(starPressed==true) {

					}else {
						speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.standMenu_receiveCall));
						getCallDetails(CallLog.Calls.INCOMING_TYPE);
					}
				} */
				break;
			case R.id.num7Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("7");
					num7Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num7Button.setBackgroundResource(R.drawable.btn_normal);
					if(starPressed==true) {
						starPressed = false;
						String emergencyNumber = getSpeedNumber.getString("speedNumber_7", "");
						String emergencyName = getSpeedNumber.getString("speedName_7", "");
						if(emergencyNumber == "") {
							speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.active_notassign));
						} else {
							speak("Call to " + emergencyName);
							waitForShortSpeechFinished();
							CallOperations call = new CallOperations(getApplicationContext());
							call.makeCall(emergencyNumber);
						}
					} else if((isHashPress == true) && (isOnePress == true) && (isfourPress == true)) {
						isHashPress = false;
						isOnePress = false;
						isfourPress = false;
						sendMessage();
					} else {
						speak("Your current location is being obtained, please wait");
						String gpsLogation = getGPSLocatio();
						Log.i("the Gps location is",gpsLogation);
						if(gpsLogation.length()==0)	{
							speak("g p s location could not be fetched.");
						} else	{
							speak(" Your g p s currunt Location is." + gpsLogation);
						}

						//						launchApp.setClassName(PackageUtil.PACKAGE,
						//								"com.evision.android.CalanderDate");
						//						startActivity(launchApp);
						//						finish();
					} 
				}
				break;
			case R.id.num8Button:

				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("8");
					num8Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num8Button.setBackgroundResource(R.drawable.btn_normal);
					if(starPressed==true) {
						starPressed = false;
						String emergencyNumber = getSpeedNumber.getString("speedNumber_8", "");
						String emergencyName = getSpeedNumber.getString("speedName_8", "");
						if(emergencyNumber == "") {
							speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.active_notassign));
						} else {
							speak("Call to " + emergencyName);
							waitForShortSpeechFinished();
							CallOperations call = new CallOperations(getApplicationContext());
							call.makeCall(emergencyNumber);
						}
					} else 
						speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.wrong_choice));
				}
				break;
			case R.id.num9Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("9");
					//speak("Personal Information");
					num9Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num9Button.setBackgroundResource(R.drawable.btn_normal);
					if(starPressed==true) {
						starPressed = false;
						String emergencyNumber = getSpeedNumber.getString("speedNumber_9", "");
						String emergencyName = getSpeedNumber.getString("speedName_9", "");
						if(emergencyNumber == "") {
							speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.active_notassign));
						} else {
							speak("Call to " + emergencyName);
							waitForShortSpeechFinished();
							CallOperations call = new CallOperations(getApplicationContext());
							call.makeCall(emergencyNumber);
						}
					} else {
						SharedPreferences sPreferences = getSharedPreferences(PREFS_NAME,0);
						String PersonalInformationName = sPreferences.getString("PersonalInformationName","empty");	
						String PersonalInformationContact= sPreferences.getString("PersonalInformationContact","empty");
						String PersonalInformationAddress= sPreferences.getString("PersonalInformationAddress","empty");
						String PersonalInformationGPSLocation= sPreferences.getString("PersonalInformationGPSLocation","empty");
						String PersonalInformationBloodGroup= sPreferences.getString("PersonalInformationBloodGroup","empty");

						if(PersonalInformationName.equalsIgnoreCase("empty")) {
							speak("Personal profile is yet to be drawn up");
							waitForSpeechFinished();
							launchApp.setClassName(PackageUtil.PACKAGE,PackageUtil.PERSONALINFORMATIONNAME);
							startActivity(launchApp);
							finish();
						}else {
							stop();

							speak("Personal Information about you");
							speak("Name "+PersonalInformationName);
							speak("Contact number "+PersonalInformationContact);
							speak("Address "+PersonalInformationAddress);
							speak("G P S location "+PersonalInformationGPSLocation);
							speak("blood group "+PersonalInformationBloodGroup);
						}
					}
				} 
				break;

			case R.id.numFButton:
				vibrate.vibrate(10);
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					numFButton.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("F");
					b1 = setBoolean();   
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					numFButton.setBackgroundResource(R.drawable.btn_normal);
				} 
				break;
			case R.id.numGButton:
				vibrate.vibrate(10);
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					numGButton.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("g"); 
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					numGButton.setBackgroundResource(R.drawable.btn_normal);
					b2 = setBoolean();
				} 
				break;
			case R.id.numHButton:
				vibrate.vibrate(10);
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					numHButton.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("h");  
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					if (starPressed == true) {
						keyPressTimeout.cancel();
						stop();
						starPressed = false;
					}
					numHButton.setBackgroundResource(R.drawable.btn_normal);
					b3 = setBoolean();
					lookuptable();
				} 
				break;
			case R.id.numStarButton:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("star");
					if (keyPressTimeout != null)
						keyPressTimeout.cancel();
					numStarButton.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					/*launchApp.setClassName(PackageUtil.PACKAGE, "com.evision.android.SpeedDialing"); 
					 startActivity(launchApp); finish();*/
					starPressed = true;
					keyPressTimeout.start();
					numStarButton.setBackgroundResource(R.drawable.btn_normal);
				} 
				break;
			case R.id.numHashButton:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("hash");
					isHashPress = true;
					if (keyPressTimeout != null)
						keyPressTimeout.cancel();
					numHashButton.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					numHashButton.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();

				} 
				break;

			default:
				break;
			}

			return true;
		}
	};

	private class MyPhoneStateListener extends PhoneStateListener {
		public void onSignalStrengthsChanged(SignalStrength signalStrength) {
			super.onSignalStrengthsChanged(signalStrength);

			if (!checkWhetherSIMInserted()) {
				value = String.valueOf(signalStrength.getGsmSignalStrength());
				signalvalue = getSignal();
			} else {
				signalvalue = "";
			}

		}
	};

	private boolean checkWhetherSIMInserted() {
		boolean simCheck = false;
		TelephonyManager telMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		int simState = telMgr.getSimState();
		switch (simState) {
		case TelephonyManager.SIM_STATE_ABSENT:
			simCheck = true;
			break;
		case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
			simCheck = false;
			break;
		case TelephonyManager.SIM_STATE_PIN_REQUIRED:
			simCheck = false;
			break;
		case TelephonyManager.SIM_STATE_PUK_REQUIRED:
			simCheck = false;
			break;
		case TelephonyManager.SIM_STATE_READY:
			simCheck = false;
			break;
		case TelephonyManager.SIM_STATE_UNKNOWN:
			simCheck = false;
			break;
		}
		return simCheck;
	}

	private final void sendMessage() {    
		String SENT = "SMS_SENT";
		String DELIVERED = "SMS_DELIVERED";

		String emergencyNumber1;
		String emergencyNumber2;
		String emergencyNumber3;
		String emergencyNumber4;

		String gpsLogation = getGPSLocatio();

		SharedPreferences getEmergencyNumber = getSharedPreferences(ASSIGN_NAME,0);

		emergencyNumber1 = getEmergencyNumber.getString("emergencyNumber_1", "");
		emergencyNumber2 = getEmergencyNumber.getString("emergencyNumber_2", "");
		emergencyNumber3 = getEmergencyNumber.getString("emergencyNumber_3", "");
		emergencyNumber4 = getEmergencyNumber.getString("emergencyNumber_4", "");

		if ((emergencyNumber1 == "") && (emergencyNumber2 == "") && (emergencyNumber3 == "") && (emergencyNumber4 =="")) {
			speak("Please Assign the Emergency Number");
		} else {
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
						speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.sms_send) + "Emergency Contact");
						break;

					case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
						//   Toast.makeText(getBaseContext(), "Generic failure", 
						//           Toast.LENGTH_SHORT).show();
						speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.generic_failure));
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
						speak(ResourceUtil.getStringFromResource(StandbyMainMenu.this,R.string.active_deliversms)+ "Emergency Contact");
						break;
					case Activity.RESULT_CANCELED:
						break;                        
					}
				}
			},new IntentFilter(DELIVERED));        

			SmsManager sms = SmsManager.getDefault();
			if (emergencyNumber1 != "")
				sms.sendTextMessage(emergencyNumber1, null, "i am  in emergency situation at " + gpsLogation, sentPI, deliveredPI);
			if (emergencyNumber2 != "")
				sms.sendTextMessage(emergencyNumber2, null, "i am  in emergency situation at  " + gpsLogation, sentPI, deliveredPI);
			if (emergencyNumber3 != "")
				sms.sendTextMessage(emergencyNumber3, null, "i am in emergency situation at  " + gpsLogation, sentPI, deliveredPI);
			if (emergencyNumber4 != "")
				sms.sendTextMessage(emergencyNumber4, null, "i am  in emergency situation at  " + gpsLogation, sentPI, deliveredPI);
		}
	}


	private String getSignal() 
	{
		int i = Integer.parseInt(value);
		if (i == 0 || i == 1) {
			return WEAK;
		} else if (i == 2 || i <= 10) {
			return AVERAGE;
		} else if (i == 11 || i <= 20) {
			return GOOD;
		} else if (i == 21 || i <= 31) {
			return STRONG;
		}
		return null;
	}

	@Override
	public void onPause() {
		super.onPause();
		Tel.listen(mPhoneStateListener, PhoneStateListener.LISTEN_NONE);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Tel.listen(mPhoneStateListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
	}

	public void sms() {
		//TextView view = new TextView(this);
		String SMS_READ_COLUMN = "read";
		String UNREAD_CONDITION = SMS_READ_COLUMN + "=0";
		int count = 0;
		Cursor cursor = getContentResolver().query(	SMS_INBOX_CONTENT_URI,
				null,
				UNREAD_CONDITION,null,null);    

		try {
			String contactName = null;
			if (cursor.moveToFirst()) {
				count = cursor.getCount();

				String sms =  Integer.toString(count);
				speak(" you have "+ sms +" un  read  s m s");
				do
				{
					String phoneNumber = cursor.getString(cursor.getColumnIndex("address"));
					String phoneName = getContactName(phoneNumber);
					if(phoneName != null) {
						contactName = phoneName;
					} else {
						contactName = phoneNumber;
					}
					speak(" SMS from "+ contactName );

				}while(cursor.moveToNext());
			} else {
				speak("No; Un read S M S at the moment");
			}
		}catch (Exception e) {
			// TODO: handle exception
		} finally {
			cursor.close();
		}
	}

	public String getContactName(String phoneNumber) {

		Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
				Uri.encode(phoneNumber));
		Cursor cursor = getContentResolver().query(uri,
				new String[] { PhoneLookup.DISPLAY_NAME }, null, null, null);
		if (cursor == null) {
			return null;
		}
		String contactName = null;
		if (cursor.moveToFirst()) {
			contactName = cursor.getString(cursor
					.getColumnIndex(PhoneLookup.DISPLAY_NAME));
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return contactName;
	}

	/*
	 * General health of the Battery
	 * */
	private String getHealthString(int health) {
		String healthString = "Unknown";

		switch (health) {
		case BatteryManager.BATTERY_HEALTH_DEAD:
			healthString = "Dead";
			break;
		case BatteryManager.BATTERY_HEALTH_GOOD:
			healthString = "Good";
			break;
		case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
			healthString = "Over Voltage";
			break;
		case BatteryManager.BATTERY_HEALTH_OVERHEAT:
			healthString = "Over Heat";
			break;
		case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
			healthString = "Failure";
			break;
		}

		return healthString;
	}

	private String getGPSLocatio() {

		try {
			locationManager = (LocationManager) 
					getSystemService(LOCATION_SERVICE);

			// getting GPS status
			isGPSEnabled = locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);

			// getting network status
			isNetworkEnabled = locationManager
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (!isGPSEnabled && !isNetworkEnabled) {
				// no network provider is enabled
			} else {
				//this.canGetLocation = true;
				if (isNetworkEnabled) {
					locationManager.requestLocationUpdates(
							LocationManager.NETWORK_PROVIDER,
							MIN_TIME_BW_UPDATES,
							MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
					Log.d("Network", "Network Enabled");
					if (locationManager != null) {
						location = locationManager
								.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if (location != null) {
							latitude = location.getLatitude();
							Log.i("jin","jin latitude22"+latitude);
							longitude = location.getLongitude();
						}
					}
				}
				// if GPS Enabled get lat/long using GPS Services
				if (isGPSEnabled) {
					if (location == null) {
						locationManager.requestLocationUpdates(
								LocationManager.GPS_PROVIDER,
								MIN_TIME_BW_UPDATES,
								MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
						Log.d("GPS", "GPS Enabled");
						if (locationManager != null) {
							location = locationManager
									.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							if (location != null) {
								latitude = location.getLatitude();
								longitude = location.getLongitude();
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		String address = getAddress(latitude,longitude);


		return address;

	}
	private String getAddress(double latitude, double longitude) {
		StringBuilder result = new StringBuilder();
		try {
			Geocoder geocoder = new Geocoder(this, Locale.getDefault());
			List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
			if (addresses.size() > 0) {
				Address address = addresses.get(0);
				result.append(address.getSubLocality()).append("\n");
				result.append(address.getLocality()).append("\n");
				result.append(address.getCountryName());
			}
		} catch (IOException e) {
			Log.e("tag", e.getMessage());
		}

		return result.toString();
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDownloadComplete(File filename) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProgressUpdate(int progress) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDownloadFailure(String msg) {
		// TODO Auto-generated method stub

	}

}

