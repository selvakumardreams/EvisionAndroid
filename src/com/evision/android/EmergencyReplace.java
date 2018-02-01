package com.evision.android;

import java.util.ArrayList;

import android.content.ContentProviderOperation;
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

public class EmergencyReplace extends BaseActivity {

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
	boolean b1 = false, b2 = false, b3 = false;
	int check = 0;
	boolean isReplace = false;
	String str ="",reStr="";
	SharedPreferences getPref;
	SharedPreferences.Editor editor;
	public static final String PREFS_NAME ="EvisionAssignGroup";

	String emergencynumber1;
	String emergencyName1;
	String emergencynumber2;
	String emergencyName2 ;
	String emergencynumber3 ;
	String emergencyName3;
	String emergencynumber4 ;
	String emergencyName4 ;

	ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
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


		//  ** Get the Emergency Contact Name and Number** // 
		Intent intent = getIntent();
		replaceName = intent.getStringExtra("phoneName");
		replaceNumber = intent.getStringExtra("phoneNumber");

		SharedPreferences getPref = getSharedPreferences(PREFS_NAME,0);
		editor = getPref.edit();
		emergencynumber1 = getPref.getString("emergencyNumber_1", "");
		emergencyName1 = getPref.getString("emergencyName_1", "");
		emergencynumber2 = getPref.getString("emergencyNumber_2", "");
		emergencyName2 = getPref.getString("emergencyName_2", "");
		emergencynumber3 = getPref.getString("emergencyNumber_3", "");
		emergencyName3 = getPref.getString("emergencyName_3", "");
		emergencynumber4 = getPref.getString("emergencyNumber_4", "");
		emergencyName4 = getPref.getString("emergencyName_4", "");

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
				if (isFgKeypress == true || isHgKeypress == true || isGFKeyPressed == true
						|| isReplace == true) {
					isFgKeypress = false;
					isHgKeypress = false;
					isGFKeyPressed = false;
					isReplace = false;
					lookuptable();
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
					stop();
					num1Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("1");
					speak("if you want to replace." + emergencyName1 + "press F G to Continue");
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
					stop();
					num2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("2");
					speak("if you want to replace." + emergencyName2 + "press F G to Continue");
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
					stop();
					num3Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("3");
					speak("if you want to replace." + emergencyName3 + "press F G to Continue");
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
					speak("if you want to replace." + emergencyName4 + " press F G to Continue");
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
					speak(ResourceUtil.getStringFromResource(EmergencyReplace.this, R.string.Emergency_invalid));
					num5Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("6");
					speak(ResourceUtil.getStringFromResource(EmergencyReplace.this, R.string.Emergency_invalid));
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
					speak(ResourceUtil.getStringFromResource(EmergencyReplace.this, R.string.Emergency_invalid));
					num7Button.setBackgroundResource(R.drawable.btn_normal);
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
					speak(ResourceUtil.getStringFromResource(EmergencyReplace.this, R.string.Emergency_invalid));
					num8Button.setBackgroundResource(R.drawable.btn_normal);
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
					speak(ResourceUtil.getStringFromResource(EmergencyReplace.this, R.string.Emergency_invalid));
					num9Button.setBackgroundResource(R.drawable.btn_normal);
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
					b1 = setBoolean();
					numFButton.setBackgroundResource(R.drawable.btn_green);
					keyPressTimeout.cancel();
				} else if (ACTION_UP == event.getAction()) {
					numFButton.setBackgroundResource(R.drawable.btn_normal);
					if(isGFKeyPressed == true) {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.STANDBYMAINMENU);
						startActivity(launchApp);
						finish();
					}
					keyPressTimeout.start();
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
					b2 = setBoolean();
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
					}
					keyPressTimeout.start();
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
					b3 = setBoolean();
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					lookuptable();
					keyPressTimeout.start();
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

	private boolean setBoolean() {
		return true;
	}

	public void lookuptable() {

		if (b1 == true && b2 == true && b3 == true) {
			goActiveMode();
		}
		b1 = false;
		b2 = false;
		b3 = false;
	}

	public void ttsMenu() {

		speak(ResourceUtil.getStringFromResource(EmergencyReplace.this, R.string.EmergencyReplace_welcome));
		speak("press 1 for replaceing." + emergencyName1 );
		speak("press 2 for replaceing." + emergencyName2 );
		speak("press 3 for replaceing." + emergencyName3 );
		speak("press 4 for replaceing." + emergencyName4 );
		speak(ResourceUtil.getStringFromResource(EmergencyReplace.this,R.string.repeat_0));
		speak(ResourceUtil.getStringFromResource(EmergencyReplace.this, R.string.previous_hg));

	}


	/** 
	 * 
	 * To Replace the Emergency Name number
	 * @param value
	 */
	public void replaceEmergency(int value)
	{
		editor.putString("emergencyName_"+ value, replaceName);
		editor.putString("emergencyNumber_"+ value, replaceNumber);
		editor.commit();

	}
}
