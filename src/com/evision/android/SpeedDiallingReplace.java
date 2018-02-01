package com.evision.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;

public class SpeedDiallingReplace extends BaseActivity {

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

	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	CountDownTimer timer = null;
	CountDownTimer keyPressTimeout = null;
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	boolean isGFKeyPressed = false;
	int check = 0;
	private boolean isReplace = false;
	String str ="",reStr="";
	SharedPreferences getPref;
	SharedPreferences.Editor editor;
	public static final String PREFS_NAME ="EvisionAssignGroup";

	String speedName1 = "",speedName2 = "",speedName3 = "",speedName4 = "",speedName5 = "",speedName6 = "",
			speedName7 = "",speedName8 = "",speedName9 = "";
	String speednumber1 = "",speednumber2 = "",speednumber3 = "",speednumber4 = "",speednumber5 = "",speednumber6 = "",
			speednumber7 = "",speednumber8 = "",speednumber9 = "";


	String replaceName ="";
	String replaceNumber = "";
	final Intent launchApp = new Intent();


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


		//  ** Get the speed Contact Name and Number** // 
		Intent intent = getIntent();
		replaceName = intent.getStringExtra("phoneName");
		replaceNumber = intent.getStringExtra("phoneNumber");

		SharedPreferences getPref = getSharedPreferences(PREFS_NAME,0);
		editor = getPref.edit();

		speedName1 = getPref.getString("speedName_1", "");
		speedName2 = getPref.getString("speedName_2", "");
		speedName3 = getPref.getString("speedName_3", "");
		speedName4 = getPref.getString("speedName_4", "");
		speedName5 = getPref.getString("speedName_5", "");
		speedName6 = getPref.getString("speedName_6", "");
		speedName7 = getPref.getString("speedName_7", "");
		speedName8 = getPref.getString("speedName_8", "");
		speedName9 = getPref.getString("speedName_9", "");
		speednumber1 = getPref.getString("speedNumber_1", "");
		speednumber2 = getPref.getString("speedNumber_2", "");
		speednumber3 = getPref.getString("speedNumber_3", "");
		speednumber4 = getPref.getString("speedNumber_4", "");
		speednumber5 = getPref.getString("speedNumber_5", "");
		speednumber6 = getPref.getString("speedNumber_6", "");
		speednumber7 = getPref.getString("speedNumber_7", "");
		speednumber8 = getPref.getString("speedNumber_8", "");
		speednumber9 = getPref.getString("speedNumber_9", "");

		timer = new CountDownTimer(1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				ttsMenu();
			}
		}.start();


		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				if (isFgKeypress == true || isHgKeypress == true) {
					isFgKeypress = false;
					isHgKeypress = false;
				} else {/*
					str = BrailleCommonUtil.NumericLookUpTable(tts);
					reStr =reStr+str;
				//	ResetBrailleKeys();

				 */}
			}
		};

		num0Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num0Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("0");
					ttsMenu();
					num0Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
		num1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					stop();
					speak("1");
					speak("if you want to replace." + speedName1 + "press F G to Continue");
					check = 1;
					isReplace = true;
					num1Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
		num2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					stop();
					speak("2");
					speak("if you want to replace." + speedName2 + "press F G to Continue");
					check = 2;
					isReplace = true;
					num2Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});



		num3Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num3Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					stop();
					speak("3");
					speak("if you want to replace." + speedName3 + "press F G to Continue");
					check = 3;
					isReplace = true;
					num3Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num4Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("4");
					speak("if you want to replace." + speedName4 + " press F G to Continue");
					check = 4;
					isReplace = true;
					num4Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					num5Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("5");
					speak("if you want to replace." + speedName5 + "press F G to Continue");
					check = 5;
					isReplace = true;
					num5Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					stop();
					speak("6");
					speak("if you want to replace." + speedName6 + "press F G to Continue");
					check = 6;
					isReplace = true;
					num6Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
		num7Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					num7Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("7");
					num7Button.setBackgroundResource(R.drawable.btn_normal);
					speak("if you want to replace." + speedName7 + "press F G to Continue");
					check = 7;
					isReplace = true;
				}
				return true;
			}
		});

		num8Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					num8Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("8");
					num8Button.setBackgroundResource(R.drawable.btn_normal);
					speak("if you want to replace." + speedName8 + "press F G to Continue");
					check = 8;
					isReplace = true;
				}
				return true;
			}
		});
		num9Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					num9Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("9");
					num9Button.setBackgroundResource(R.drawable.btn_normal);
					speak("if you want to replace." + speedName9 + "press F G to Continue");
					check = 9;
					isReplace = true;
				}
				return true;
			}
		});



		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("f");
					isFgKeypress = true;
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numFButton.setBackgroundResource(R.drawable.btn_normal);
					if(isGFKeyPressed == true) {
						isGFKeyPressed = false;
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.STANDBYMAINMENU);
						startActivity(launchApp);
						finish();
					}
				}
				return true;
			}
		});

		/** 
		 * 
		 * To save the Emergency Contact Name and Number
		 */

		numGButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					speak("g" );
					isGFKeyPressed = true;
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} 
				else if (ACTION_UP == event.getAction()) {
					if (isFgKeypress == true) {
						stop();
						if (isReplace == true) {
							replaceEmergency(check);
							launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
							launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.STANDBYMAINMENU); 
							startActivity(launchApp);
							finish();
							isFgKeypress = false;
							isReplace = false;
						}
						else {
							speak("please select Number");
						}
					} 
					if (isHgKeypress == true) {

						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.EMERGENCYDIALING); 
						startActivity(launchApp);
						finish();

						isHgKeypress = false;
					}
					numGButton.setBackgroundResource(R.drawable.btn_normal);
				}



				return true;

			}});

		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("h");
					keyPressTimeout.cancel();
					isHgKeypress=true;
					isGFKeyPressed = false;
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numHButton.setBackgroundResource(R.drawable.btn_normal);
				}

				return true;
			}
		});

		numStarButton.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("star");
					numStarButton.setBackgroundResource(R.drawable.btn_green);
					if (keyPressTimeout != null)
						keyPressTimeout.cancel();
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					/*launchApp.setClassName(PackageUtil.PACKAGE, "com.evision.android.SpeedDialing"); 
					 startActivity(launchApp); finish();*/
					numStarButton.setBackgroundResource(R.drawable.btn_normal);
				} 
				return true;
			}
		});

		numHashButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("hash");
					numHashButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {

				}
				return true;
			}
		});
	}

	@Override
	public void onPause() {
		super.onPause();
	}


	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(mTts);
	}

	public void ttsMenu() {

		speak("Welcome to Speed Dialing  Replace. please ");
		speak("press 1 for replaceing." + speedName1 );
		speak("press 2 for replaceing." + speedName2 );
		speak("press 3 for replaceing." + speedName3 );
		speak("press 4 for replaceing." + speedName4 );
		speak("press 5 for replaceing." + speedName5 );
		speak("press 6 for replaceing." + speedName6 );
		speak("press 7 for replaceing." + speedName7 );
		speak("press 8 for replaceing." + speedName8 );
		speak("press 9 for replaceing." + speedName9 );
		speak(ResourceUtil.getStringFromResource(SpeedDiallingReplace.this,R.string.repeat_0));
		speak(ResourceUtil.getStringFromResource(SpeedDiallingReplace.this, R.string.previous_hg));
		speak(ResourceUtil.getStringFromResource(SpeedDiallingReplace.this, R.string.EmergencyReplace_FG));

	}


	/** 
	 * 
	 * To Replace the Speed Dialing   Name number
	 * @param value
	 */
	public void replaceEmergency(int value)
	{
		editor.putString("speedName_"+ value, replaceName);
		editor.putString("speedNumber_"+ value, replaceNumber);
		editor.commit();

	}
}
