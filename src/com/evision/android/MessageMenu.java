package com.evision.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;

public class MessageMenu extends BaseActivity {

	String msgmenu ="";
	boolean isHgKeypress = false;
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
	CountDownTimer keyPressTimeout = null;
	private boolean isGFKeyPressed = false;
	private String TAG = "ActiveMode";
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	public static int counter = 0;
	CountDownTimer timer = null, startUpTimer = null;
	CountDownTimer doubleClickTimer = null;
	private boolean doubleClick = false;
	boolean b1 = false, b2 = false, b3 = false;

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


		Intent intent = getIntent();
		msgmenu = intent.getStringExtra("msgbody");
		Log.i("manju","msgbodyjinmenu=" +msgmenu);		

		startUpTimer = new CountDownTimer(1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				Log.i(TAG, "OnFinish : startUpTimer Global: " + mTts);
				ttsForsmsMode();
			}
		}.start();
		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				if (isGFKeyPressed == true || isHgKeypress == true) {
					isGFKeyPressed = false;
					isHgKeypress = false;
					lookuptable();
				}
			}
		};


		timer = new CountDownTimer(30000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				Log.i(TAG, "OnFinish : tts.isSpeaking & Restarting --Global: "+ mTts.isSpeaking());
			}
		}.start();

		doubleClickTimer = new CountDownTimer(2000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onFinish() {
				// This single key is pressed & timer expires and no docble key
				// pressed
				if (doubleClick == true) {
					doubleClick = false;
					ttsForsmsMode();
				}
			}
		};

		num0Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					timer.start();
					// String speechOut = "Calender";
					stop();
					speak("0");
					num0Button.setBackgroundResource(R.drawable.btn_green);
					// speak(speechOut);
				} else if (ACTION_UP == event.getAction()) {
					ttsForsmsMode();
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
					// speak("launching cell phone",
					// TextToSpeech.QUEUE_ADD,null);

				} else if (ACTION_UP == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_normal);
					Intent launchApp = new Intent();
					launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					launchApp.putExtra("msgbodymainone", msgmenu);
					launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.WRITESMSNUMBER); 
					startActivity(launchApp); finish();
					// launchApp.setClassName(PackageUtil.PACKAGE, "com.datapadsystem.WriteSms"); 
					// startActivity(launchApp); finish();

				}
				return true;
			}
		});

		num2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				// timer.start();

				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak("2");
					num2Button.setBackgroundResource(R.drawable.btn_green);
					//			speak("NotePad is under delopment",
					//				TextToSpeech.QUEUE_ADD, null);
				} else if (ACTION_UP == event.getAction()) {
					num2Button.setBackgroundResource(R.drawable.btn_normal);
					// TODO ADD YOUR PACKAGE NAME & CLASS NAME TO LAUNCH APP
					Intent launchApp = new Intent();
					launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					launchApp.putExtra("msgbodymaintwo", msgmenu);
					launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.SMSNUMBERCONTACT); 
					startActivity(launchApp); finish();
					//	 launchApp.setClassName(PackageUtil.PACKAGE, "com.datapadsystem.SMSNumberFrmContact"); 
					//	 startActivity(launchApp); finish();

				}
				return true;
			}
		});

		num3Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak("3");
					num3Button.setBackgroundResource(R.drawable.btn_green);
					speak(ResourceUtil.getStringFromResource(MessageMenu.this,R.string.msgMenu_num3));
				} else if (ACTION_UP == event.getAction()) {
					num3Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(MessageMenu.this, R.string.wrong_choice));
					//	launchApp.setClassName(PackageUtil.PACKAGE, "com.datapadsystem.Ebook");
					//	startActivity(launchApp);
					//	finish();
				}
				return true;
			}
		});

		num4Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak("4");
					num4Button.setBackgroundResource(R.drawable.btn_green);
					speak(ResourceUtil.getStringFromResource(MessageMenu.this,R.string.msgMenu_num4));
				} else if (ACTION_UP == event.getAction()) {
					num4Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(MessageMenu.this, R.string.wrong_choice));
					//	launchApp.setClassName(PackageUtil.PACKAGE,"com.datapadsystem.VoiceRecordMenu");
					//	startActivity(launchApp);
					//	finish();
				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak("5");
					num5Button.setBackgroundResource(R.drawable.btn_green);

				} else if (ACTION_UP == event.getAction()) {
					num5Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(MessageMenu.this, R.string.wrong_choice));
					//	launchApp.setClassName(PackageUtil.PACKAGE,
					//	"com.datapadsystem.locator");
					//	speak("locator app is under development",TextToSpeech.QUEUE_ADD, null);
					//startActivity(launchApp);
					//    finish();
				}
				return true;
			}
		});

		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak("6");
					num6Button.setBackgroundResource(R.drawable.btn_green);
					//	speak("Guide app is under development",
					//			TextToSpeech.QUEUE_ADD, null);
				} else if (ACTION_UP == event.getAction()) {
					num6Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(MessageMenu.this, R.string.wrong_choice));
					// TODO ADD YOUR PACKAGE NAME & CLASS NAME TO LAUNCH APP
					/*
					 * launchApp.setClassName(PackageUtil.PACKAGE,
					 * "ADD Guide APP Class"); startActivity(launchApp);
					 * finish();
					 */
				}
				return true;
			}
		});

		num7Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak("7");
					num7Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num7Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(MessageMenu.this, R.string.wrong_choice));
					// TODO ADD YOUR PACKAGE NAME & CLASS NAME TO LAUNCH APP

					//	 launchApp.setClassName(PackageUtil.PACKAGE,"com.datapadsystem.AccessoryMenu"); 
					//	 startActivity(launchApp);
					//	 finish();
				}
				return true;
			}
		});

		num8Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak("8");
					num8Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num8Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(MessageMenu.this, R.string.wrong_choice));
				}
				return true;
			}
		});

		num9Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak("9");
					num9Button.setBackgroundResource(R.drawable.btn_green);
					//	speak("Settings App is under development",
					//			TextToSpeech.QUEUE_ADD, null);
				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(MessageMenu.this, R.string.wrong_choice));
					num9Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});


		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("h");
					keyPressTimeout.cancel();
					isHgKeypress=true;
					b3 =setBoolean();
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					lookuptable();
					keyPressTimeout.start();
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
					speak("g");
					keyPressTimeout.cancel();
					isGFKeyPressed = true;
					b2 = setBoolean();
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (isHgKeypress == true) {
						Intent launchApp = new Intent();
						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						// launchApp.putExtra("TDLalarmhrs",reStr);
						launchApp.setClassName(PackageUtil.PACKAGE, "com.evision.android.WriteSmsBody"); 
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
					speak("f");
					keyPressTimeout.cancel();
					b1 = setBoolean();
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if(isGFKeyPressed == true) {
						isGFKeyPressed = false;
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
					speak("star");
					keyPressTimeout.cancel();
					numStarButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					numStarButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numHashButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak("hash");
					numHashButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					numHashButton.setBackgroundResource(R.drawable.btn_normal);
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
		if (doubleClickTimer != null)
			doubleClickTimer.cancel();

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
		if (doubleClickTimer != null)
			doubleClickTimer.start();
		super.onResume();
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


	public void ttsForsmsMode() {
		// singleClick = true;
		timer.cancel();
		speak(ResourceUtil.getStringFromResource(MessageMenu.this,R.string.please));
		speak(ResourceUtil.getStringFromResource(MessageMenu.this,R.string.msgMenu_one));
		speak(ResourceUtil.getStringFromResource(MessageMenu.this,R.string.msgMenu_two));
		speak(ResourceUtil.getStringFromResource(MessageMenu.this,R.string.repeat_0));
		speak(ResourceUtil.getStringFromResource(MessageMenu.this,R.string.previous_hg));
		/*speak(" 3 for e book reader");
		// speak(" 4 for optical reader");
		speak(" 4 for voice recorder");
		// timer.cancel();
		timer.start();
		speak(" 5 for LOCATOR ");
		// speak("Press 7 no operation available",TextToSpeech.QUEUE_ADD,
		// null);
		speak(" 6 for Guide");
		speak(" 7 for accessories ");*/
		timer.cancel();
		timer.start();
	}

}
