package com.evision.android.education;


import com.evision.android.BaseActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.evision.android.R;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;


public class DictionaryMenu extends BaseActivity {

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

	CountDownTimer timer = null;
	CountDownTimer readTimer = null;
	CountDownTimer doubleClickTimer = null;
	CountDownTimer keyPressTimeout = null;

	boolean isHGKeypress = false;
	boolean isGFKeypress = false;
	boolean doubleClick = false;
	boolean b1 = false, b2 = false, b3 = false;
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	Intent launchApp = new Intent(); 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.standbymode_screen);



		initialize(); //Initialize the buttons


		timer = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {

			}
			@Override
			public void onFinish() {
				ttsFornoteReadMenu();
			}
		}.start();


		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				if ( isHGKeypress == true || isGFKeypress == true) {
					isGFKeypress = false;
					isHGKeypress = false;
					lookuptable();
				} else {
					//	String str = BrailleCommonUtil.AlpabeticalLookUpTable();
					//	ResetBrailleKeys();

				}
			}
		};



		readTimer = new CountDownTimer(100, 100) {
			@Override
			public void onTick(long millisUntilFinished) {

			}

			@Override
			public void onFinish() {

			}
		};


		doubleClickTimer = new CountDownTimer(2000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {

			}

			@Override
			public void onFinish() {
				// This single key is pressed & timer expires and no docble key
				// pressed
				if ( doubleClick == true){
					doubleClick = false;					
					ttsFornoteReadMenu();
				}
			}
		};
	}

	private void ttsFornoteReadMenu() {
		speak(ResourceUtil.getStringFromResource(DictionaryMenu.this, R.string.dictionary_welcome));
		speak(ResourceUtil.getStringFromResource(DictionaryMenu.this, R.string.please));
		speak(ResourceUtil.getStringFromResource(DictionaryMenu.this, R.string.dictionary_one));
		speak(ResourceUtil.getStringFromResource(DictionaryMenu.this, R.string.repeat_0));
		speak(ResourceUtil.getStringFromResource(DictionaryMenu.this, R.string.previous_hg));
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
					ttsFornoteReadMenu();
					num0Button.setBackgroundResource(R.drawable.btn_normal);
				} 
				break;

			case R.id.num1Button:

				if (ACTION_DOWN == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("1");

				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(DictionaryMenu.this,R.string.not_assign));
					num1Button.setBackgroundResource(R.drawable.btn_normal);
					launchApp.setClassName(PackageUtil.PACKAGE,PackageUtil.DICTIONARYSEARCH);
					startActivity(launchApp);
					finish();
				}
				break;
			case R.id.num2Button:

				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("2");
					keyPressTimeout.cancel();
					num2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num2Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(DictionaryMenu.this,R.string.not_assign));
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
					speak(ResourceUtil.getStringFromResource(DictionaryMenu.this,R.string.not_assign));
				} 

				break;
			case R.id.num4Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("4");
					keyPressTimeout.cancel();
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num4Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(DictionaryMenu.this,R.string.not_assign));
				} 

				break;
			case R.id.num5Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("5");
					keyPressTimeout.cancel();
					num5Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num5Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(DictionaryMenu.this,R.string.not_assign));
				} 

				break;
			case R.id.num6Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("6");
					keyPressTimeout.cancel();
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num6Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(DictionaryMenu.this,R.string.not_assign));
				} 

				break;
			case R.id.num7Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("7");
					keyPressTimeout.cancel();
					num7Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num7Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(DictionaryMenu.this,R.string.not_assign));
				} 

				break;
			case R.id.num8Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("8");
					keyPressTimeout.cancel();
					num8Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num8Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(DictionaryMenu.this,R.string.not_assign));
				} 

				break;
			case R.id.num9Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("9");
					keyPressTimeout.cancel();
					num9Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num9Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(DictionaryMenu.this,R.string.not_assign));
				} 

				break;
			case R.id.numFButton:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("F");
					keyPressTimeout.cancel();
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					if(isGFKeypress == true) {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.STANDBYMAINMENU);
						startActivity(launchApp);
						finish();
					}
					keyPressTimeout.start();
					numFButton.setBackgroundResource(R.drawable.btn_normal);
				} 

				break;
			case R.id.numGButton:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("G");
					keyPressTimeout.cancel();
					isGFKeypress = true;
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {

					if(isHGKeypress = true) {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.EDUCATIONMENU);
						startActivity(launchApp);
						finish();
					}
					keyPressTimeout.start();
					numGButton.setBackgroundResource(R.drawable.btn_normal);
				} 

				break;
			case R.id.numHButton:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("H");
					keyPressTimeout.cancel();
					isHGKeypress = true;
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					keyPressTimeout.start();
					numHButton.setBackgroundResource(R.drawable.btn_normal);
				} 
				break;
			case R.id.numStarButton:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("Star");
					keyPressTimeout.cancel();
					numStarButton.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					speak(ResourceUtil.getStringFromResource(DictionaryMenu.this,R.string.not_assign));
					numStarButton.setBackgroundResource(R.drawable.btn_normal);
				} 

				break;
			case R.id.numHashButton:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("Hash");
					keyPressTimeout.cancel();
					numHashButton.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					speak(ResourceUtil.getStringFromResource(DictionaryMenu.this,R.string.not_assign));
					numHashButton.setBackgroundResource(R.drawable.btn_normal);
				} 
				break;
			default:
				break;
			}
			return true;
		}
	};
}