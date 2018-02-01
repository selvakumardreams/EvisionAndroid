package com.evision.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;

public class MessageMain extends BaseActivity  {
	//private static Uri SMS_INBOX_CONTENT_URI;

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
	CountDownTimer timer = null , startUpTimer = null;
	boolean isHgKeypress = false;
	CountDownTimer doubleClickTimer = null;
	CountDownTimer keyPressTimeout = null;
	private boolean isGFKeyPressed = false;
	boolean b1 = false, b2 = false, b3 = false;
	Intent launchApp = new Intent();


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.standbymode_screen);

		num0Button = (Button)findViewById(R.id.num0Button);
		num1Button = (Button)findViewById(R.id.num1Button);
		num2Button = (Button)findViewById(R.id.num2Button);
		num3Button = (Button)findViewById(R.id.num3Button);
		num4Button = (Button)findViewById(R.id.num4Button);
		num5Button = (Button)findViewById(R.id.num5Button);
		num6Button = (Button)findViewById(R.id.num6Button);
		num7Button = (Button)findViewById(R.id.num7Button);
		num8Button = (Button)findViewById(R.id.num8Button);
		num9Button = (Button)findViewById(R.id.num9Button);
		numFButton = (Button)findViewById(R.id.numFButton);
		numGButton = (Button)findViewById(R.id.numGButton);
		numHButton = (Button)findViewById(R.id.numHButton);
		numStarButton = (Button)findViewById(R.id.numStarButton);
		numHashButton = (Button)findViewById(R.id.numHashButton);

		timer = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onFinish() {

				ttsForsms();
			}
		}.start();
		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				if (isGFKeyPressed == true || isHgKeypress == true ) {
					isGFKeyPressed = false;
					isHgKeypress = false;
					lookuptable();
				}
			}
		};

		num1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {	
					num1Button.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("1");

				} else if (ACTION_UP == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_normal);
					launchApp.setClassName(PackageUtil.PACKAGE,PackageUtil.WRITESMSBODY); 
					startActivity(launchApp); 
					finish();
				}
				return true;
			}
		});

		num2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {	
					num2Button.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("2");

				} else if (ACTION_UP == event.getAction()) {
					num2Button.setBackgroundResource(R.drawable.btn_normal);
					launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.SMSINBOX); 
					startActivity(launchApp); 
					finish();
				}
				return true;
			}
		});
		num3Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {	
					num3Button.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("3");

				} else if (ACTION_UP == event.getAction()) {
					num3Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(MessageMain.this,R.string.wrong_choice));
				}

				return true;
			}
		});
		num4Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {	
					num4Button.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("4");

				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(MessageMain.this,R.string.wrong_choice));
					num4Button.setBackgroundResource(R.drawable.btn_normal);
				}

				return true;
			}
		});
		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {	
					num5Button.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("5");
				} else if (ACTION_UP == event.getAction()) {
					num5Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(MessageMain.this,R.string.wrong_choice));
				}

				return true;
			}
		});
		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {	
					num6Button.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("6");
				} else if (ACTION_UP == event.getAction()) {
					num6Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(MessageMain.this,R.string.wrong_choice));
				}

				return true;
			}
		});
		num7Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {	
					num7Button.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("7");

				} else if (ACTION_UP == event.getAction()) {
					num7Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(MessageMain.this,R.string.wrong_choice));
				}

				return true;
			}
		});
		num0Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {	
					num0Button.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("0");

				} else if (ACTION_UP == event.getAction()) {
					num0Button.setBackgroundResource(R.drawable.btn_normal);
					ttsForsms();

				}

				return true;
			}
		});

		numHashButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {	
					numHashButton.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("Hash");

				} else if (ACTION_UP == event.getAction()) {
					numHashButton.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(MessageMain.this,R.string.wrong_choice));
				}

				return true;
			}
		});
		numStarButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {	
					numStarButton.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("star");

				} else if (ACTION_UP == event.getAction()) {
					numStarButton.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(MessageMain.this,R.string.wrong_choice));
				}

				return true;
			}
		});

		num8Button.setOnTouchListener( new OnTouchListener()  {

			public boolean onTouch(View v, MotionEvent event) {
				if ( ACTION_DOWN == event.getAction()) {
					num8Button.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("8");
				}
				else if (ACTION_UP == event.getAction()) {
					num8Button.setBackgroundResource(R.drawable.btn_normal);   
					speak(ResourceUtil.getStringFromResource(MessageMain.this,R.string.wrong_choice));
				}
				return true;
			}
		});
		num9Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {

				if ( ACTION_DOWN == event.getAction()) {
					num9Button.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("9");
				}
				else if (ACTION_UP == event.getAction()) {
					num9Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(MessageMain.this,R.string.wrong_choice));
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
					b2 =setBoolean();
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (isHgKeypress == true) {
						Intent launchApp = new Intent();
						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.CELLPHONE); 
						startActivity(launchApp);
						finish();
					}
					//	keyPressTimeout.start();
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

	public void ttsForsms() {

		stop();
		speak(ResourceUtil.getStringFromResource(MessageMain.this,R.string.msgMain_welcome));

		speak(ResourceUtil.getStringFromResource(MessageMain.this,R.string.please));
		speak(ResourceUtil.getStringFromResource(MessageMain.this,R.string.msgMain_one));
		speak(ResourceUtil.getStringFromResource(MessageMain.this,R.string.msgMain_two));
		speak(ResourceUtil.getStringFromResource(MessageMain.this,R.string.repeat_0));
		speak(ResourceUtil.getStringFromResource(MessageMain.this,R.string.previous_hg));
		//speak(" three for delete an s m s from inbox");
		//speak(" four for sent items");
	}
	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(mTts);
	}
	public void ResetBrailleKeys() {
		BrailleCommonUtil.ResetBrailleKeyflag();
	}
}  