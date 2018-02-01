package com.evision.android.education;

import com.evision.android.BaseActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import com.evision.android.R;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;

public class EbookMainMenu extends BaseActivity {

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


	boolean isHgKeypress = false;
	boolean isGFKeypress = false;
	Intent launchApp = new Intent();
	CountDownTimer keyPressTimeout = null;
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	CountDownTimer timer = null;
	CountDownTimer doubleClickTimer = null;
	private boolean doubleClick = false;
	boolean b1 = false, b2 = false, b3 = false;

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



		timer = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onFinish() {

				ttsForEbookMenu();
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
					ttsForEbookMenu();
				}
			}
		};

		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onFinish() {
				if(isGFKeypress == true || isHgKeypress == true) {
					isGFKeypress = false;
					isHgKeypress = false;
					lookuptable();
				}
			}
		};

		num0Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("0");
					num0Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					ttsForEbookMenu();
					num0Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		// Replace EbookList.class with notepad.class by Meltronics team
		num1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("1");
					stop();

				} else if (ACTION_UP == event.getAction()) {
					num2Button.setBackgroundResource(R.drawable.btn_normal);
					launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.NOTEPADLIST);
					launchApp.putExtra(BrailleCommonUtil.APP_NAME_INTENT, BrailleCommonUtil.APP_NAME_EBOOK);
					launchApp.putExtra("className", "EbookMainMenu");
					startActivity(launchApp); 
					finish();

				}
				return true;

			}
		});

		//	final Intent IntEbookList = new Intent(EbookMainMenu.this, CreateFileList.class);
		num2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("2");
					num2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num2Button.setBackgroundResource(R.drawable.btn_normal);
					stop();
					launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.DELETEVOICERECORD);
					launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					launchApp.putExtra(BrailleCommonUtil.APP_NAME_INTENT,BrailleCommonUtil.APP_NAME_EBOOK);
					launchApp.putExtra("classname", "EbookMainMenu");
					startActivity(launchApp);
					finish();

				}
				return true;
			}
		});

		// Please create new activity for Editing files
		num3Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("3");
					num3Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(EbookMainMenu.this,R.string.wrong_choice));
					num3Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num4Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("4");
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(EbookMainMenu.this,R.string.wrong_choice));
					num4Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("5");
					num5Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(EbookMainMenu.this,R.string.wrong_choice));
					num5Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("6");
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(EbookMainMenu.this,R.string.wrong_choice));
					num6Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num7Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("7");
					num7Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(EbookMainMenu.this,R.string.wrong_choice));
					// Searching for what ??
					num7Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});


		num8Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("8");
					// ttsInvalidChoice();
					num8Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					// ttsForEbookMenu();
					speak(ResourceUtil.getStringFromResource(EbookMainMenu.this,R.string.wrong_choice));
					// Exit Ebook and start Active mode

					num8Button.setBackgroundResource(R.drawable.btn_normal);

				}
				return true;
			}
		});

		num9Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("9");
					// ttsInvalidChoice();
					num9Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(EbookMainMenu.this,R.string.wrong_choice));
					num9Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});


		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("H");
					b3 = setBoolean();
					keyPressTimeout.cancel();
					isHgKeypress=true;
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					lookuptable();
					keyPressTimeout.start();
					numHButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numGButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("G");
					b2 = setBoolean();
					keyPressTimeout.cancel();
					isGFKeypress = true;
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (isHgKeypress == true) {
						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						// launchApp.putExtra("TDLalarmhrs",reStr);
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.EDUCATIONMENU); 
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
					speak("F");
					b1 = setBoolean();
					keyPressTimeout.cancel();
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					if (isGFKeypress == true) {
						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						// launchApp.putExtra("TDLalarmhrs",reStr);
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
					stop();
					speak("Star");
					numStarButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(EbookMainMenu.this , R.string.wrong_choice));
					numStarButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numHashButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("Hash");
					numHashButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(EbookMainMenu.this , R.string.wrong_choice));
					numHashButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		/*
		 * resetButton.setOnTouchListener(new OnTouchListener() { public boolean
		 * onTouch(View v, MotionEvent event) { timer.cancel(); //
		 * timer.start();
		 * 
		 * if (ACTION_DOWN == event.getAction()) {
		 * resetButton.setBackgroundResource(R.drawable.lower2); } else if
		 * (ACTION_UP == event.getAction()) {
		 * resetButton.setBackgroundResource(R.drawable.lower); if (doubleClick
		 * == true) { doubleClick = false; timer.cancel();
		 * doubleClickTimer.cancel(); stop();
		 * speak("Launching  Stand by Mode", TextToSpeech.QUEUE_ADD, null);
		 * Intent intent = new Intent(); intent.setClassName("com.pharynx",
		 * "com.pharynx.StandbyScreen"); startActivity(intent); finish(); } else
		 * { doubleClick = true; doubleClickTimer.cancel();
		 * doubleClickTimer.start(); } } return true; } });
		 *//*
		 * final Intent resetIntent = new
		 * Intent(this,com.pharynx.Ebook.class);
		 * resetButton.setOnTouchListener( new OnTouchListener() { public
		 * boolean onTouch(View v, MotionEvent event) { if ( ACTION_DOWN ==
		 * event.getAction()) {
		 * resetButton.setBackgroundResource(R.drawable.lower2);
		 * //speak("Resetting ", TextToSpeech.QUEUE_ADD, null); } else
		 * if (ACTION_UP == event.getAction()) {
		 * resetButton.setBackgroundResource(R.drawable.lower); stop();
		 * startActivity(resetIntent); finish(); } return true; } });
		 */}


	public void onInit(int status) {

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


	@Override
	public void onPause() {

		if (timer != null)
			timer.cancel();
		/*
		 * if (startUpTimer!=null) startUpTimer.cancel();
		 */if (doubleClickTimer != null)
			 doubleClickTimer.cancel();

		 if (mTts != null)
			 stop();
		 super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onResume() {
		if (timer != null)
			timer.start();

		/*
		 * if (startUpTimer!=null) startUpTimer.start();
		 */if (doubleClickTimer != null)
			 doubleClickTimer.start();
		 super.onResume();
	}


	public void ttsForEbookMenu() {

		speak(ResourceUtil.getStringFromResource(EbookMainMenu.this,R.string.ebook_welcome));
		speak(ResourceUtil.getStringFromResource(EbookMainMenu.this,R.string.ebook_one));
		speak(ResourceUtil.getStringFromResource(EbookMainMenu.this,R.string.ebook_two));
		//		speak(ResourceUtil.getStringFromResource(EbookMainMenu.this,R.string.ebook_three));
		//		speak(ResourceUtil.getStringFromResource(EbookMainMenu.this,R.string.ebook_four));
		//		speak(ResourceUtil.getStringFromResource(EbookMainMenu.this,R.string.ebook_five));
		//		speak(ResourceUtil.getStringFromResource(EbookMainMenu.this,R.string.ebook_six));
		speak(ResourceUtil.getStringFromResource(EbookMainMenu.this,R.string.repeat_0));
		speak(ResourceUtil.getStringFromResource(EbookMainMenu.this,R.string.previous_hg));


	}
}
